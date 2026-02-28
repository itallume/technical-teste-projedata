package com.itallume.projedata.domain.product;

import com.itallume.projedata.domain.rawMaterial.UnitOfMeasurement;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductMaterialResponse {

    private Long id;
    private Long materialId;
    @DecimalMin(value = "0.0", message = "Quantity must be non-negative")
    private BigDecimal quantity;
    private UnitOfMeasurement unitOfMeasurement;
}
