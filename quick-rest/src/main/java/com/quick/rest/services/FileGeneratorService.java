package com.quick.rest.services;


import com.quick.rest.constants.ExceptionsConstants;
import com.quick.rest.constants.FileConstants;
import com.quick.rest.enums.TemplatesEnum;
import com.quick.rest.exceptions.FileGeneratorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quick.rest.configs.PackageProperties;
import com.quick.rest.iservices.IFileGeneratorService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service("fileGenerator")
@Slf4j
public class FileGeneratorService implements IFileGeneratorService {

    @Autowired
    PackageProperties packageProperties;

    @Override
    public File readFile(TemplatesEnum template) throws FileGeneratorException {
        StringBuilder dirTemplate = FileConstants.getDirTemplate(template);
        if (dirTemplate == null) {
            throw new FileGeneratorException(ExceptionsConstants.TEMPLATE_NOT_VALID);
        }
        dirTemplate.append(template.getUrl());
        File file = null;
        try {
            Resource resource = new ClassPathResource(dirTemplate.toString());
            file = resource.getFile();
        } catch (IOException e) {
            log.error("Exception trying to read template {} ", e);
            throw new FileGeneratorException(ExceptionsConstants.TEMPLATE_NOT_FOUND);
        }
        return file;
    }


}
