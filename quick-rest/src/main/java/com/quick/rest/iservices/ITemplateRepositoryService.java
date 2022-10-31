package com.quick.rest.iservices;

import com.quick.rest.models.request.EntityTemplateDTO;
import com.quick.rest.models.request.RepositoryTemplateDTO;

import java.io.File;
import java.io.IOException;

public interface ITemplateRepositoryService {
    Boolean generate(RepositoryTemplateDTO repositoryTemplateDTO);
    StringBuilder createTemplate(File rawTemplate, RepositoryTemplateDTO repositoryTemplateDTO) throws IOException;
}
