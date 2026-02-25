package com.itallume.projedata.domain.product;


import com.itallume.projedata.domain.rawMaterial.RawMaterial;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal value;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductMaterial> productMaterials = new ArrayList<>();

    public void addProductMaterial(RawMaterial rawMaterial, BigDecimal quantityInBaseUnit) {

        boolean alreadyExists = productMaterials.stream()
                .anyMatch(pm -> pm.getRawMaterial().equals(rawMaterial));

        if (alreadyExists) {
            throw new IllegalArgumentException("Material already added to this product");
        }

        ProductMaterial productMaterial = new ProductMaterial();
        productMaterial.setProduct(this);
        productMaterial.setRawMaterial(rawMaterial);
        productMaterial.setQuantityInBaseUnit(quantityInBaseUnit);

        this.productMaterials.add(productMaterial);
    }

    public void removeProductMaterial(ProductMaterial productMaterial){
        this.productMaterials.remove(productMaterial);
        productMaterial.setProduct(null);
    }
}
