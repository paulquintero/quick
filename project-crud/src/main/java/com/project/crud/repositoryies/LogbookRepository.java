package com.project.crud.repositoryies;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.crud.entityies.Logbook;

public interface LogbookRepository extends JpaRepository<Logbook , Integer> {

}
