package com.itallume.projedata.utils;

import com.itallume.projedata.domain.rawMaterial.MeasurementType;
import com.itallume.projedata.domain.rawMaterial.UnitOfMeasurement;

import java.math.BigDecimal;

public class UnitConverter {
    private static final BigDecimal GRAMS_IN_KILOGRAM = BigDecimal.valueOf(1000.0);
    private static final BigDecimal GRAMS_IN_TONNE = BigDecimal.valueOf(1_000_000.0);
    private static final BigDecimal MILLILITERS_IN_LITER = BigDecimal.valueOf(1000.0);
    private static final BigDecimal MILLILITERS_IN_CUBIC_METER = BigDecimal.valueOf(1_000_000.0);

    public static UnitOfMeasurement bestUnitForDisplay(MeasurementType type, BigDecimal quantityInBaseUnit) {
        return switch (type) {
            case WEIGHT -> _bestUnitOfMeasurementForWeight(quantityInBaseUnit);
            case VOLUME -> _bestUnitOfMeasurementForVolume(quantityInBaseUnit);
            case UNIT ->UnitOfMeasurement.UNIT;
            default -> throw new IllegalStateException("Unexpected measurement type: " + quantityInBaseUnit);
        };
    }

    public static UnitOfMeasurement _bestUnitOfMeasurementForWeight(BigDecimal quantityInGrams) {
        if (quantityInGrams.compareTo(GRAMS_IN_TONNE) >= 0 ) return UnitOfMeasurement.TONNE;
        if (quantityInGrams.compareTo(GRAMS_IN_KILOGRAM) >= 0) return UnitOfMeasurement.KILOGRAM;
        return UnitOfMeasurement.GRAM;
    }

    public static UnitOfMeasurement _bestUnitOfMeasurementForVolume(BigDecimal quantityInMilliliters) {
        if (quantityInMilliliters.compareTo(MILLILITERS_IN_CUBIC_METER) >= 0) return UnitOfMeasurement.CUBIC_METER;
        if (quantityInMilliliters.compareTo(MILLILITERS_IN_LITER) >= 0) return UnitOfMeasurement.LITER;
        return UnitOfMeasurement.MILLILITER;
    }
}
