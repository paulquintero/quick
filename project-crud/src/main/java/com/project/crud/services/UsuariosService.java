package com.project.crud.services;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.text.MessageFormat;
import com.project.crud.repositoryies.IUsuariosRepository;
import com.project.crud.entityies.UsuariosEntity;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuariosService {

  @Autowired
  private IUsuariosRepository iUsuariosRepository;

  public List<UsuariosEntity> findAll() {
    log.debug("Find all");
    return this.iUsuariosRepository.findAll();
  }

  public UsuariosEntity findById(Integer id){
    log.debug("Find by Id");
    Optional<UsuariosEntity> opusuariosEntity = this.iUsuariosRepository.findById(id);
    return opusuariosEntity.orElseThrow(() -> new RuntimeException("Not found"));
  }

  public UsuariosEntity save(UsuariosEntity usuariosEntity){
    log.debug("Save");
    try {
      return this.iUsuariosRepository.save(usuariosEntity);
    } catch (IllegalArgumentException e) {
      log.error(MessageFormat.format("Error produced while saving the information: {0}",e.getMessage()));
      throw e;
    }
  }

  public UsuariosEntity update(Integer id, UsuariosEntity usuariosEntity) {
        log.debug("update");
      try {
        usuariosEntity.setId(id);
        return this.save(usuariosEntity);
      } catch (IllegalArgumentException e) {
        log.error(MessageFormat.format("Error produced while saving the information: {0}",e.getMessage()));
        throw e;
      }
  }

  public void delete(Integer id){
    log.debug("delete");
    UsuariosEntity usuariosEntity = this.findById(id);
    this.iUsuariosRepository.delete(usuariosEntity);
  }

}
