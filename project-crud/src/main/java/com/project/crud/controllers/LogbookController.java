package com.project.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.crud.services.LogbookService;
import com.project.crud.entityies.Logbook;

import java.util.List;

@RestController
@RequestMapping(path ="logbook")
public class LogbookController {

  @Autowired
  private LogbookService logbookService;

  @GetMapping
  public ResponseEntity<List<Logbook>> findAll() {
    return  ResponseEntity.ok(this.logbookService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Logbook> findById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(this.logbookService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Logbook> save(@RequestBody Logbook logbook){
    return  ResponseEntity.ok(this.logbookService.save(logbook));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Logbook> update(@PathVariable("id") Integer id, @RequestBody Logbook logbook){
    return  ResponseEntity.ok(this.logbookService.update(id, logbook));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
    this.logbookService.delete(id);
    return ResponseEntity.ok("Success");
  }

}
