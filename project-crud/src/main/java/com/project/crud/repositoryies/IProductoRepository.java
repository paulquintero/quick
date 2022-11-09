package com.project.crud.repositoryies;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.crud.entityies.ProductoEntity;

public interface IProductoRepository extends JpaRepository<ProductoEntity , Integer> {

}
