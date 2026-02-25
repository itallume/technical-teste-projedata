package com.itallume.projedata.repository;

import com.itallume.projedata.domain.rawMaterial.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {

    boolean existsByName(String name);

    boolean existsByCode(String code);
}
