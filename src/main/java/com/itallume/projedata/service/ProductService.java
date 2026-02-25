package com.itallume.projedata.service;

import com.itallume.projedata.domain.product.Product;
import com.itallume.projedata.domain.product.ProductMaterial;
import com.itallume.projedata.domain.product.ProductMaterialRequest;
import com.itallume.projedata.repository.ProductMaterialRepository;
import com.itallume.projedata.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository _productRepository;
    private ProductMaterialRepository _productMaterialRepository;

    public void addProduct(Product product, List<ProductMaterialRequest> productMaterialRequests) {
        for (ProductMaterialRequest materialRequest : productMaterialRequests) {

            ProductMaterial productMaterial = _productMaterialRepository.findById(materialRequest.getMaterialId())
                    .orElseThrow(() -> new IllegalArgumentException("Raw material id:"+ materialRequest.getMaterialId() +" not found"));


        }

    }
}
