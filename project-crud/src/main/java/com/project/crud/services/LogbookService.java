package com.project.crud.services;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.text.MessageFormat;
import com.project.crud.repositoryies.LogbookRepository;
import com.project.crud.entityies.Logbook;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LogbookService {

  @Autowired
  private LogbookRepository logbookRepository;

  public List<Logbook> findAll() {
    log.debug("Find all");
    return this.logbookRepository.findAll();
  }

  public Logbook findById(Integer id){
    log.debug("Find by Id");
    Optional<Logbook> oplogbook = this.logbookRepository.findById(id);
    return oplogbook.orElseThrow(() -> new RuntimeException("Not found"));
  }

  public Logbook save(Logbook logbook){
    log.debug("Save");
    try {
      return this.logbookRepository.save(logbook);
    } catch (IllegalArgumentException e) {
      log.error(MessageFormat.format("Error produced while saving the information: {0}",e.getMessage()));
      throw e;
    }
  }

  public Logbook update(Integer id, Logbook logbook) {
        log.debug("update");
      try {
        logbook.setId(id);
        return this.save(logbook);
      } catch (IllegalArgumentException e) {
        log.error(MessageFormat.format("Error produced while saving the information: {0}",e.getMessage()));
        throw e;
      }
  }

  public void delete(Integer id){
    log.debug("delete");
    Logbook logbook = this.findById(id);
    this.logbookRepository.delete(logbook);
  }

}
