package com.itallume.projedata.service.mapper;

import com.itallume.projedata.domain.product.*;
import com.itallume.projedata.domain.rawMaterial.UnitOfMeasurement;
import com.itallume.projedata.utils.UnitConverter;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public Product toEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setCode(productRequest.getCode());
        product.setValue(productRequest.getValue());
        return product;
    }

    public ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setCode(product.getCode());
        response.setValue(product.getValue());

        List<ProductMaterialResponse> materialResponses = new ArrayList<>();
        for (ProductMaterial material : product.getProductMaterials()) {
            ProductMaterialResponse materialResponse = new ProductMaterialResponse();
            materialResponse.setMaterialId(material.getRawMaterial().getId());
            UnitOfMeasurement unitOfMeasurement = material.getBestUnitForDisplay();
            materialResponse.setQuantity(material.getQuantityForDisplay(unitOfMeasurement));
            materialResponse.setUnitOfMeasurement(unitOfMeasurement);
            materialResponses.add(materialResponse);
        }
        response.setProductMaterials(materialResponses);

        return response;
    }
}
