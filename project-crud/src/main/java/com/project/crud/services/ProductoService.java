package com.project.crud.services;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.text.MessageFormat;
import com.project.crud.repositoryies.IProductoRepository;
import com.project.crud.entityies.ProductoEntity;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductoService {

  @Autowired
  private IProductoRepository iProductoRepository;

  public List<ProductoEntity> findAll() {
    log.debug("Find all");
    return this.iProductoRepository.findAll();
  }

  public ProductoEntity findById(Integer id){
    log.debug("Find by Id");
    Optional<ProductoEntity> opproductoEntity = this.iProductoRepository.findById(id);
    return opproductoEntity.orElseThrow(() -> new RuntimeException("Not found"));
  }

  public ProductoEntity save(ProductoEntity productoEntity){
    log.debug("Save");
    try {
      return this.iProductoRepository.save(productoEntity);
    } catch (IllegalArgumentException e) {
      log.error(MessageFormat.format("Error produced while saving the information: {0}",e.getMessage()));
      throw e;
    }
  }

  public ProductoEntity update(Integer id, ProductoEntity productoEntity) {
        log.debug("update");
      try {
        productoEntity.setId(id);
        return this.save(productoEntity);
      } catch (IllegalArgumentException e) {
        log.error(MessageFormat.format("Error produced while saving the information: {0}",e.getMessage()));
        throw e;
      }
  }

  public void delete(Integer id){
    log.debug("delete");
    ProductoEntity productoEntity = this.findById(id);
    this.iProductoRepository.delete(productoEntity);
  }

}
