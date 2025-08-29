package com.quick.rest.iservices;

import com.quick.rest.models.request.*;

public interface IQuickApiGeneratorService {

  Boolean generateFull(FullBodyDTO fullBodyDTO);
  Boolean generateEntity(EntityTemplateDTO entityTemplateDTO);
  Boolean generateRepository(RepositoryTemplateDTO repositoryTemplateDTO);
  Boolean generateService(BusinessTemplateDTO businessTemplateDTO);;
  Boolean generateController(ControllerTemplateDTO controllerTemplateDTO);

}
