package com.project.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.crud.services.BookService;
import com.project.crud.entityies.Book;

import java.util.List;

@RestController
@RequestMapping(path ="book")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping
  public ResponseEntity<List<Book>> findAll() {
    return  ResponseEntity.ok(this.bookService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> findById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(this.bookService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Book> save(@RequestBody Book book){
    return  ResponseEntity.ok(this.bookService.save(book));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> update(@PathVariable("id") Integer id, @RequestBody Book book){
    return  ResponseEntity.ok(this.bookService.update(id, book));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
    this.bookService.delete(id);
    return ResponseEntity.ok("Success");
  }

}
