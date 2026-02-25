package com.itallume.projedata.service;


import com.itallume.projedata.domain.rawMaterial.RawMaterial;
import com.itallume.projedata.domain.rawMaterial.RawMaterialRequest;
import com.itallume.projedata.domain.rawMaterial.RawMaterialResponse;
import com.itallume.projedata.repository.RawMaterialRepository;
import com.itallume.projedata.service.mapper.RawMaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RawMaterialService {

    private RawMaterialRepository _rawMaterialRepository;
    private RawMaterialMapper _rawMaterialMapper;

    @Autowired
    public RawMaterialService(RawMaterialRepository rawMaterialRepository, RawMaterialMapper rawMaterialMapper) {
        this._rawMaterialRepository = rawMaterialRepository;
        this._rawMaterialMapper = rawMaterialMapper;
    }

    public RawMaterialResponse createRawMaterial(RawMaterialRequest rawMaterialRequest) {

        if (_rawMaterialRepository.existsByName(rawMaterialRequest.getName())) {
            throw new IllegalArgumentException("Raw material with the same name already exists.");
        }

        if(_rawMaterialRepository.existsByCode(rawMaterialRequest.getCode())) {
            throw new IllegalArgumentException("Raw material with the same code already exists.");
        }

        RawMaterial rawMaterial = _rawMaterialMapper.toEntity(rawMaterialRequest);

        rawMaterial = _rawMaterialRepository.save(rawMaterial);

        return _rawMaterialMapper.toResponse(rawMaterial, rawMaterialRequest.getUnitOfMeasurement());
    }

    public RawMaterialResponse getRawMaterialById(Long id) {
        RawMaterial rawMaterial = _rawMaterialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Raw material not found with id: " + id));
        return _rawMaterialMapper.toResponse(rawMaterial, rawMaterial.getBestUnitForDisplay());
    }

    public RawMaterialResponse updateRawMaterial(Long id, RawMaterialRequest rawMaterialRequest) {
        RawMaterial rawMaterial = _rawMaterialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Raw material not found with id: " + id));

        rawMaterial.setName(rawMaterialRequest.getName());
        rawMaterial.setCode(rawMaterialRequest.getCode());
        rawMaterial.setStockWithUnitOfMeasurement(rawMaterialRequest.getStockQuantity(), rawMaterialRequest.getUnitOfMeasurement());

        rawMaterial = _rawMaterialRepository.save(rawMaterial);
        return _rawMaterialMapper.toResponse(rawMaterial, rawMaterial.getBestUnitForDisplay());
    }

    public void deleteRawMaterial(Long id) {
        _rawMaterialRepository.deleteById(id);
    }

}
