package com.quick.rest.iservices;

import com.quick.rest.models.request.EntityTemplateDTO;

import java.io.File;
import java.io.IOException;

public interface ITemplateEntityService {
    Boolean generate(EntityTemplateDTO entityTemplateDTO);
    StringBuilder createTemplate(File rawTemplate, EntityTemplateDTO entityTemplateDTO) throws IOException;
}
