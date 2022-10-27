package com.quick.rest.iservices;

import com.quick.rest.models.request.EntityTemplateDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ITemplatesServices {
    void createTemplate(File rawTemplate, EntityTemplateDTO entityTemplateDTO) throws IOException;
}
