package com.itallume.projedata.domain.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductRequest {
    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotNull
    @DecimalMin(value = "0.0", message = "Value must be non-negative")
    private BigDecimal value;

    @NotEmpty
    @NotNull
    @Valid
    private List<ProductMaterial> productMaterials = new ArrayList<>();
}
