package com.quick.rest.iservices;

import com.quick.rest.models.request.EntityTemplateDTO;
import com.quick.rest.models.request.RepositoryTemplateDTO;

public interface IQuickApiGeneratorService {
  
  Boolean generateEntity(EntityTemplateDTO entityTemplateDTO);
  Boolean generateRepository(RepositoryTemplateDTO repositoryTemplateDTO);

}
