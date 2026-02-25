package com.itallume.projedata.domain.product;

import com.itallume.projedata.domain.rawMaterial.UnitOfMeasurement;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class productMaterialResponse {

    private Long id;
    private Long materialId;
    private BigDecimal quantity;
    private UnitOfMeasurement unitOfMeasurement;
}
