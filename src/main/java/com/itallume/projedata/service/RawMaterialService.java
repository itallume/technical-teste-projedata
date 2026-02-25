package com.itallume.projedata.service;


import com.itallume.projedata.domain.rawMaterial.RawMaterial;
import com.itallume.projedata.domain.rawMaterial.RawMaterialRequest;
import com.itallume.projedata.domain.rawMaterial.RawMaterialResponse;
import com.itallume.projedata.domain.rawMaterial.UnitOfMeasurement;
import com.itallume.projedata.repository.RawMaterialRepository;
import com.itallume.projedata.service.mapper.RawMaterialMapper;
import com.itallume.projedata.utils.UnitConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RawMaterialService {

    private RawMaterialRepository rawMaterialRepository;
    private RawMaterialMapper rawMaterialMapper;

    @Autowired
    public RawMaterialService(RawMaterialRepository rawMaterialRepository, RawMaterialMapper rawMaterialMapper) {
        this.rawMaterialRepository = rawMaterialRepository;
        this.rawMaterialMapper = rawMaterialMapper;
    }

    public RawMaterialResponse createRawMaterial(RawMaterialRequest rawMaterialRequest) {

        if (rawMaterialRepository.existsByName(rawMaterialRequest.getName())) {
            throw new IllegalArgumentException("Raw material with the same name already exists.");
        }

        if(rawMaterialRepository.existsByCode(rawMaterialRequest.getCode())) {
            throw new IllegalArgumentException("Raw material with the same code already exists.");
        }

        RawMaterial rawMaterial = rawMaterialMapper.toEntity(rawMaterialRequest);

        rawMaterial = rawMaterialRepository.save(rawMaterial);

        return rawMaterialMapper.toResponse(rawMaterial, rawMaterialRequest.getUnitOfMeasurement());
    }

    public RawMaterialResponse getRawMaterialById(Long id) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Raw material not found with id: " + id));
        return rawMaterialMapper.toResponse(rawMaterial, rawMaterial.getBestUnitForDisplay());
    }

    public RawMaterialResponse updateRawMaterial(Long id, RawMaterialRequest rawMaterialRequest) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Raw material not found with id: " + id));

        rawMaterial.setName(rawMaterialRequest.getName());
        rawMaterial.setCode(rawMaterialRequest.getCode());
        rawMaterial.setStockWithUnitOfMeasurement(rawMaterialRequest.getStockQuantity(), rawMaterialRequest.getUnitOfMeasurement());

        rawMaterial = rawMaterialRepository.save(rawMaterial);
        return rawMaterialMapper.toResponse(rawMaterial, rawMaterial.getBestUnitForDisplay());
    }

    public void deleteRawMaterial(Long id) {
        rawMaterialRepository.deleteById(id);
    }

}
