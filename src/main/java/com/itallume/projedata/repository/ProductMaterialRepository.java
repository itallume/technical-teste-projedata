package com.itallume.projedata.repository;

import com.itallume.projedata.domain.product.ProductMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Long> {
}
