package com.itallume.projedata.domain.rawMaterial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RawMaterialResponse {
    private Long id;
    private String code;
    private String name;
    private BigDecimal stockQuantity;
    private UnitOfMeasurement unitOfMeasurement;
}
