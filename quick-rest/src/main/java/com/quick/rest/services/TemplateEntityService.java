package com.quick.rest.services;

import com.quick.rest.iservices.IFileGeneratorService;
import com.quick.rest.models.request.EntityTemplateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TemplateEntityService")
public class TemplateEntityService {

    @Autowired
    IFileGeneratorService fileGenerator;
    //Generar entity
    public static Boolean generate(EntityTemplateDTO entityTemplateDTO){
        return null;
    }


}
