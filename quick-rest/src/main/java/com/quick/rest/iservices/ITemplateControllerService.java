package com.quick.rest.iservices;


import com.quick.rest.models.request.ControllerTemplateDTO;

import java.io.File;
import java.io.IOException;

public interface ITemplateControllerService {
    Boolean generate(ControllerTemplateDTO controllerTemplateDTO);
    StringBuilder createTemplate(File rawTemplate,ControllerTemplateDTO controllerTemplateDTO) throws IOException;
}
