package com.itallume.projedata.domain.rawMaterial;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum UnitOfMeasurement {
    GRAM(MeasurementType.WEIGHT, 1),
    KILOGRAM(MeasurementType.WEIGHT, 1000),
    TONNE(MeasurementType.WEIGHT, 1_000_000),

    MILLILITER(MeasurementType.VOLUME, 1),
    LITER(MeasurementType.VOLUME, 1000),
    CUBIC_METER(MeasurementType.VOLUME, 1_000_000),

    UNIT(MeasurementType.UNIT, 1);

    private MeasurementType type;
    private BigDecimal multiplier;

    UnitOfMeasurement(MeasurementType measurementType,long multiplier) {
        this.type = measurementType;
        this.multiplier = BigDecimal.valueOf(multiplier);
    }

    public MeasurementType getType() {
        return type;
    }

    public BigDecimal fromBase(BigDecimal baseValue) {
        return baseValue.divide(multiplier, 6, RoundingMode.HALF_UP);
    }

    public BigDecimal toBase(BigDecimal value) {
        return value.multiply(multiplier);
    }
}
