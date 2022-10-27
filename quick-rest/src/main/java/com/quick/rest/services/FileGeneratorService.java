package com.quick.rest.services;


import com.quick.rest.constants.ExceptionsConstants;
import com.quick.rest.constants.FileConstants;
import com.quick.rest.enums.TemplatesEnum;
import com.quick.rest.exceptions.FileGeneratorException;
import com.quick.rest.utilities.FileUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.quick.rest.configs.PackageProperties;
import com.quick.rest.iservices.IFileGeneratorService;

import javax.naming.Context;
import javax.servlet.ServletContext;
import java.io.File;

import java.io.IOException;


@Service("fileGenerator")
@Slf4j
public class FileGeneratorService implements IFileGeneratorService {

    @Autowired
    private PackageProperties packageProperties;
    @Autowired
    private ServletContext context;

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

    public File saveFile(File file, TemplatesEnum enumFiles) throws IOException {
        boolean ifExist = true;
        boolean path = true;
        //reemplazar los puntos por comas

        StringBuilder pathFile = new StringBuilder(FileUtilities.replaceCharacters(packageProperties.getBasePackage(), FileUtilities.DOT, FileUtilities.SLASH));
        pathFile.append(FileUtilities.SLASH);
        pathFile.append(FileUtilities.replaceCharacters(enumFiles.getUrl(), ".template", ""));
        pathFile.append(FileUtilities.SLASH);
        File myObj = new File("filename.txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
            myObj.getAbsolutePath();
        } else {
            System.out.println("File already exists.");
            myObj.getPath();
            myObj.getAbsolutePath();
        }
        //pathFile.append(file);
        //String context1 =context.getRealPath(String.valueOf(pathFile));
        log.info(String.valueOf(context));
        //path = FileUtilities.validatePath(pathFile.toString());

        ifExist = FileUtilities.validateifExistFile( file, pathFile.toString());

        //Validar si existe el archivo
        //existFile= FileUtilities.validateifExist(file, pathFile);
        log.info(String.valueOf(pathFile));
        return null;
    }


}
