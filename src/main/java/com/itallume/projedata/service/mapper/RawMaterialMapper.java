package com.itallume.projedata.service.mapper;

import com.itallume.projedata.domain.rawMaterial.RawMaterial;
import com.itallume.projedata.domain.rawMaterial.RawMaterialRequest;
import com.itallume.projedata.domain.rawMaterial.RawMaterialResponse;
import com.itallume.projedata.domain.rawMaterial.UnitOfMeasurement;
import org.springframework.stereotype.Component;

@Component
public class RawMaterialMapper {

    public RawMaterial toEntity(RawMaterialRequest request) {
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setName(request.getName());
        rawMaterial.setCode(request.getCode());
        rawMaterial.setStockWithUnitOfMeasurement(request.getStockQuantity(), request.getUnitOfMeasurement());
        return rawMaterial;
    }

    public RawMaterialResponse toResponse(RawMaterial rawMaterial) {
        RawMaterialResponse response = new RawMaterialResponse();
        response.setId(rawMaterial.getId());
        response.setName(rawMaterial.getName());
        response.setCode(rawMaterial.getCode());
        UnitOfMeasurement unitOfMeasurement = rawMaterial.getBestUnitForDisplay();
        response.setStockQuantity(rawMaterial.getStockQuantityForDisplay(unitOfMeasurement));
        response.setUnitOfMeasurement(unitOfMeasurement);
        return response;
    }
}
