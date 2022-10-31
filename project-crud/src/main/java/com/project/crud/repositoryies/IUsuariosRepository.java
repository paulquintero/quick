package com.project.crud.repositoryies;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.crud.entityies.UsuariosEntity;

public interface IUsuariosRepository extends JpaRepository<UsuariosEntity , Integer> {

}
