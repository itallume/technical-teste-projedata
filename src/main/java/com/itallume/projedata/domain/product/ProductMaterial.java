package com.itallume.projedata.domain.product;

import com.itallume.projedata.domain.rawMaterial.RawMaterial;
import com.itallume.projedata.domain.rawMaterial.UnitOfMeasurement;
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

    private UnitOfMeasurement unitOfMeasurement;

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

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public BigDecimal getQuantity() {
        return unitOfMeasurement.fromBase(quantityInBaseUnit);
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantityInBaseUnit = unitOfMeasurement.toBase(quantity);
    }
}
