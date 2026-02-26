package com.itallume.projedata.domain.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String code;
    private String name;
    private BigDecimal value;
    private List<ProductMaterialResponse> productMaterials = new ArrayList<>();
}

