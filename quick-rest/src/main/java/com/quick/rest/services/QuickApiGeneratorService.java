package com.quick.rest.services;

import com.quick.rest.models.request.RepositoryTemplateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quick.rest.iservices.IQuickApiGeneratorService;
import com.quick.rest.models.request.EntityTemplateDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuickApiGeneratorService implements IQuickApiGeneratorService {
  
  @Autowired
  TemplateEntityService templateEntityService;

  @Autowired
  TemplateRepositoryService templateRepositoryService;

  @Override
  public Boolean generateEntity(EntityTemplateDTO entityTemplateDTO) {
    log.debug("Process to generate entity");
    return this.templateEntityService.generate(entityTemplateDTO);
  }

  @Override
  public Boolean generateRepository(RepositoryTemplateDTO repositoryTemplateDTO) {
    log.debug("Process to generate repository");
    return this.templateRepositoryService.generate(repositoryTemplateDTO);
  }

}
