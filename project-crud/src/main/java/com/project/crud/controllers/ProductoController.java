package com.project.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.crud.services.ProductoService;
import com.project.crud.entityies.ProductoEntity;

import java.util.List;

@RestController
@RequestMapping(path ="productos")
public class ProductoController {

  @Autowired
  private ProductoService productoService;

  @GetMapping
  public ResponseEntity<List<ProductoEntity>> findAll() {
    return  ResponseEntity.ok(this.productoService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductoEntity> findById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(this.productoService.findById(id));
  }

  @PostMapping
  public ResponseEntity<ProductoEntity> save(@RequestBody ProductoEntity productoEntity){
    return  ResponseEntity.ok(this.productoService.save(productoEntity));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductoEntity> update(@PathVariable("id") Integer id, @RequestBody ProductoEntity productoEntity){
    return  ResponseEntity.ok(this.productoService.update(id, productoEntity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
    this.productoService.delete(id);
    return ResponseEntity.ok("Success");
  }

}
