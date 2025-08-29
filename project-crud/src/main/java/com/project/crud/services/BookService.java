package com.project.crud.services;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.text.MessageFormat;
import com.project.crud.repositoryies.BookRepository;
import com.project.crud.entityies.Book;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  public List<Book> findAll() {
    log.debug("Find all");
    return this.bookRepository.findAll();
  }

  public Book findById(Integer id){
    log.debug("Find by Id");
    Optional<Book> opbook = this.bookRepository.findById(id);
    return opbook.orElseThrow(() -> new RuntimeException("Not found"));
  }

  public Book save(Book book){
    log.debug("Save");
    try {
      return this.bookRepository.save(book);
    } catch (IllegalArgumentException e) {
      log.error(MessageFormat.format("Error produced while saving the information: {0}",e.getMessage()));
      throw e;
    }
  }

  public Book update(Integer id, Book book) {
        log.debug("update");
      try {
        book.setId(id);
        return this.save(book);
      } catch (IllegalArgumentException e) {
        log.error(MessageFormat.format("Error produced while saving the information: {0}",e.getMessage()));
        throw e;
      }
  }

  public void delete(Integer id){
    log.debug("delete");
    Book book = this.findById(id);
    this.bookRepository.delete(book);
  }

}
