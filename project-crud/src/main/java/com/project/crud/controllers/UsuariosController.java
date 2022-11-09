package com.project.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.crud.services.UsuariosService;
import com.project.crud.entityies.UsuariosEntity;

import java.util.List;

@RestController
@RequestMapping(path ="users")
public class UsuariosController {

  @Autowired
  private UsuariosService usuariosService;

  @GetMapping
  public ResponseEntity<List<UsuariosEntity>> findAll() {
    return  ResponseEntity.ok(this.usuariosService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuariosEntity> findById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(this.usuariosService.findById(id));
  }

  @PostMapping
  public ResponseEntity<UsuariosEntity> save(@RequestBody UsuariosEntity usuariosEntity){
    return  ResponseEntity.ok(this.usuariosService.save(usuariosEntity));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UsuariosEntity> update(@PathVariable("id") Integer id, @RequestBody UsuariosEntity usuariosEntity){
    return  ResponseEntity.ok(this.usuariosService.update(id, usuariosEntity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
    this.usuariosService.delete(id);
    return ResponseEntity.ok("Success");
  }

}
