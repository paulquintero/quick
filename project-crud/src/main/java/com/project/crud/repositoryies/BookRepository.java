package com.project.crud.repositoryies;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.crud.entityies.Book;

public interface BookRepository extends JpaRepository<Book , Integer> {

}
