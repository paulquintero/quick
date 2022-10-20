package com.quick.rest.services;

import com.quick.rest.iservices.IFileGeneratorService;
import com.quick.rest.iservices.ITemplateEntity;
import com.quick.rest.models.request.EntityTemplateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("TemplateEntityService")
@Slf4j
public class TemplateEntityService implements ITemplateEntity {

    @Autowired
    IFileGeneratorService fileGenerator;
    //Generar entity
    @Override
    public Boolean generate(EntityTemplateDTO entityTemplateDTO) {
        try {
            String file = this.fileGenerator.readFile(null);
        } catch (IOException e){
            log.error("Template not found {}",e);
        }
        return null;
    }


}
