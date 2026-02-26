package com.itallume.projedata.domain.product;

import com.itallume.projedata.domain.rawMaterial.MeasurementType;
import com.itallume.projedata.domain.rawMaterial.RawMaterial;
import com.itallume.projedata.domain.rawMaterial.UnitOfMeasurement;
import com.itallume.projedata.utils.UnitConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "raw_material_id"}))
public class ProductMaterial {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raw_material_id", nullable = false)
    @NotNull
    private RawMaterial rawMaterial;

    @Column(nullable = false, precision = 19, scale = 4)
    @DecimalMin(value = "0.0", message = "Quantity must be non-negative")
    private BigDecimal quantityInBaseUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public BigDecimal getQuantityInBaseUnit() {
        return quantityInBaseUnit;
    }

    public void setQuantityInBaseUnit(BigDecimal quantityInBaseUnit) {
        this.quantityInBaseUnit = quantityInBaseUnit;
    }

    public UnitOfMeasurement getBestUnitForDisplay() {
        return UnitConverter.bestUnitForDisplay(
                this.rawMaterial.getMeasurementType(),
                this.quantityInBaseUnit
        );
    }

    public BigDecimal getQuantityForDisplay(UnitOfMeasurement unit) {
        if (unit.getType() != this.rawMaterial.getMeasurementType()){
            throw new IllegalArgumentException("Unit of measurement type does not match raw material measurement type");
        }
        return unit.fromBase(this.quantityInBaseUnit);
    }

    public void setQuantityWithUnitOfMeasurement(BigDecimal stockQuantity, UnitOfMeasurement unit) {
        if (unit.getType() != rawMaterial.getMeasurementType()){
            throw new IllegalArgumentException("Unit of measurement type does not match raw material measurement type");
        }
        this.quantityInBaseUnit = unit.toBase(stockQuantity);
    }

    public BigDecimal getStockQuantityForDisplay(UnitOfMeasurement unit) {
        if (unit.getType() != rawMaterial.getMeasurementType()){
            throw new IllegalArgumentException("Unit of measurement type does not match raw material measurement type");
        }
        return unit.fromBase(this.quantityInBaseUnit);
    }


}
