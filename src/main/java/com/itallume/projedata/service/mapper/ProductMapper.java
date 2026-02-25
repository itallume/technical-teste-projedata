package com.itallume.projedata.service.mapper;

import com.itallume.projedata.domain.product.Product;
import com.itallume.projedata.domain.product.ProductMaterial;
import com.itallume.projedata.domain.product.ProductMaterialRequest;
import com.itallume.projedata.domain.product.ProductRequest;

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

    public List<ProductMaterial> toProductMaterials(Product product, List<ProductMaterialRequest> productMaterialRequest) {
        return productMaterialRequest.stream()
                .map(materialRequest -> {
                    ProductMaterial productMaterial = new ProductMaterial();
                    productMaterial.setProduct(product);
                    productMaterial.setRawMaterialId(materialRequest.getMaterialId());
                    productMaterial.setQuantity(materialRequest.getQuantity());
                    return productMaterial;
                })
                .toList();
    }
}
