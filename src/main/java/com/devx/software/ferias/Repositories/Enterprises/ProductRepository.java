package com.devx.software.ferias.Repositories.Enterprises;

import com.devx.software.ferias.Entities.Enterprises.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
