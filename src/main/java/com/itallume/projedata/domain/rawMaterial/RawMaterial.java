package com.itallume.projedata.domain.rawMaterial;

import com.itallume.projedata.utils.UnitConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private MeasurementType measurementType;

    @Column(nullable = false)
    private BigDecimal stockQuantityInBaseUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    public UnitOfMeasurement getBestUnitForDisplay() {
        return UnitConverter.bestUnitForDisplay(
                this.measurementType,
                this.stockQuantityInBaseUnit
        );
    }

    public BigDecimal getStockQuantityForDisplay(UnitOfMeasurement unit) {
        if (unit.getType() != measurementType){
            throw new IllegalArgumentException("Unit of measurement type does not match raw material measurement type");
        }
        return unit.fromBase(this.stockQuantityInBaseUnit);
    }

    public void setStockWithUnitOfMeasurement(BigDecimal stockQuantity, UnitOfMeasurement unit) {
        if (unit.getType() != measurementType){
            throw new IllegalArgumentException("Unit of measurement type does not match raw material measurement type");
        }
        this.stockQuantityInBaseUnit = unit.toBase(stockQuantity);
    }
}