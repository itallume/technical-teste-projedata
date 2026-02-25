package com.itallume.projedata.domain.rawMaterial;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum UnitOfMeasurement {

    GRAM(1),
    KILOGRAM(1000),
    TONNE(1_000_000),

    MILLILITER(1),
    LITER(1000),

    UNIT(1);

    private final BigDecimal multiplier;

    UnitOfMeasurement(long multiplier) {
        this.multiplier = BigDecimal.valueOf(multiplier);
    }

    public BigDecimal fromBase(BigDecimal baseValue) {
        return baseValue.divide(multiplier, 6, RoundingMode.HALF_UP);
    }

    public BigDecimal toBase(BigDecimal value) {
        return value.multiply(multiplier);
    }
}
