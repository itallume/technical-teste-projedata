package com.itallume.projedata.service;

import com.itallume.projedata.domain.product.*;
import com.itallume.projedata.domain.rawMaterial.RawMaterial;
import com.itallume.projedata.repository.ProductMaterialRepository;
import com.itallume.projedata.repository.ProductRepository;
import com.itallume.projedata.repository.RawMaterialRepository;
import com.itallume.projedata.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, RawMaterialRepository rawMaterialRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.rawMaterialRepository = rawMaterialRepository;
        this.productMapper = productMapper;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        attachMaterialsToProduct(product, productRequest.getProductMaterials());
        product = productRepository.save(product);

        return productMapper.toResponse(product);
    }

    public ProductResponse updateProduct(Long productId, ProductRequest productRequest){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        product.setName(productRequest.getName());
        product.setCode(productRequest.getCode());
        product.setValue(productRequest.getValue());
        product.clearMaterials();
        attachMaterialsToProduct(product, productRequest.getProductMaterials());
        productRepository.save(product);

        return productMapper.toResponse(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public void attachMaterialsToProduct(Product product, List<ProductMaterialRequest> productMaterialRequests) {
        for (ProductMaterialRequest materialRequest : productMaterialRequests) {
            RawMaterial rawMaterial = rawMaterialRepository.findById(materialRequest.getMaterialId())
                    .orElseThrow(() -> new IllegalArgumentException("Raw material id:"+ materialRequest.getMaterialId() +" not found"));

            product.addProductMaterial(rawMaterial, materialRequest.getQuantity(), materialRequest.getUnitOfMeasurement());
        }

    }
}
