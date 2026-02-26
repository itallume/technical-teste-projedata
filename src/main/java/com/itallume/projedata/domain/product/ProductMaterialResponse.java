package com.itallume.projedata.domain.product;

import com.itallume.projedata.domain.rawMaterial.UnitOfMeasurement;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductMaterialResponse {

    private Long id;
    private Long materialId;
    @DecimalMin(value = "0.0", message = "Quantity must be non-negative")
    private BigDecimal quantity;
    private UnitOfMeasurement unitOfMeasurement;
}
