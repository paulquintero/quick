package com.quick.rest.services;

import com.quick.rest.configs.PackageProperties;
import com.quick.rest.enums.TemplatesEnum;
import com.quick.rest.iservices.IFileGeneratorService;
import com.quick.rest.iservices.ITemplateRepositoryService;
import com.quick.rest.models.request.RepositoryTemplateDTO;
import com.quick.rest.utilities.FileUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Service("TemplateRepositoryService")
@Slf4j
public class TemplateRepositoryService implements ITemplateRepositoryService {

    protected static String REPOSITORY_NAME_TEMPLATE = ":RepositoryName";
    protected static String IMPORT_ENTITY_TEMPLATE = ":importEntityName";
    protected static String ENTITY_TEMPLATE = ":EntityName";
    protected static String PACKAGE_NAME_TEMPLATE = ":package";
    protected static String ID_TYPE_TEMPLATE = ":idType";



    private static String REPOSITORY_NAME = "Entity";
    private static String JAVA_FILE = ".java";
    private static String DOT = ".";
    private static String REPOSITORY = "Repository";
    private static String I_INTERFACE = "I";

    @Autowired
    private PackageProperties packageProperties;

    @Autowired
    IFileGeneratorService fileGenerator;

    private String getPackageEntity() {
        return packageProperties.getBasePackage() + TemplateRepositoryService.DOT + packageProperties.getEntity();
    }

    private String getPackageRepository() {
        return packageProperties.getBasePackage() + TemplateRepositoryService.DOT + packageProperties.getRepository();
    }

    @Override
    public Boolean generate(RepositoryTemplateDTO repositoryTemplateDTO) {
        Boolean generated = false;
        try {
            File file = this.fileGenerator.readFile(TemplatesEnum.REPOSITORY);
            StringBuilder template = this.createTemplate(file, repositoryTemplateDTO);
            String repositoryName = I_INTERFACE + FileUtilities.capitalize(this.addRepositorySuffix(repositoryTemplateDTO.getRepositoryName()));
            File tempFile = FileUtilities.createTempFile(repositoryName, JAVA_FILE, template.toString().getBytes(StandardCharsets.UTF_8));
            String isCreated = this.fileGenerator.saveFile(tempFile, repositoryName + JAVA_FILE, TemplatesEnum.REPOSITORY);
            generated = Boolean.TRUE;
            log.debug("archivo {}", file);
        } catch (IOException e) {
            log.error("Template not found {}", e);
        }
        return generated;
    }

    @Override
    public StringBuilder createTemplate(File rawTemplate, RepositoryTemplateDTO repositoryTemplateDTO) throws IOException {
        StringBuilder template = new StringBuilder();
        FileReader fileReader = new FileReader(rawTemplate);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linea;
        Boolean alreadyPrimaryKey = false;
        while ((linea = bufferedReader.readLine()) != null) {
            if (linea.contains(PACKAGE_NAME_TEMPLATE)) {
                linea = linea.replace(PACKAGE_NAME_TEMPLATE, this.getPackageRepository());
            }
            if (linea.contains(IMPORT_ENTITY_TEMPLATE)) {
                linea = linea.replace(IMPORT_ENTITY_TEMPLATE, this.getPackageEntity()+"."+FileUtilities.capitalize(repositoryTemplateDTO.getEntityName()));
            }

            if (linea.contains(REPOSITORY_NAME_TEMPLATE)) {
                if (!repositoryTemplateDTO.getRepositoryName().contains(REPOSITORY)) {
                    linea = linea.replace(REPOSITORY_NAME_TEMPLATE, I_INTERFACE + FileUtilities.capitalize(repositoryTemplateDTO.getRepositoryName()) + REPOSITORY);
                } else {
                    linea = linea.replace(REPOSITORY_NAME_TEMPLATE, I_INTERFACE + FileUtilities.capitalize(repositoryTemplateDTO.getRepositoryName()));
                }
            }

            if (linea.contains(ENTITY_TEMPLATE)) {
                linea = linea.replace(ENTITY_TEMPLATE, FileUtilities.capitalize(repositoryTemplateDTO.getEntityName()));
            }

            if (linea.contains(ID_TYPE_TEMPLATE)) {
                linea = linea.replace(ID_TYPE_TEMPLATE, FileUtilities.capitalize(repositoryTemplateDTO.getDataTypeId().getType()));
            }

            template.append(linea + "\n");
        }
        bufferedReader.close();
        return template;
    }

    private String addRepositorySuffix(String name){
        if (!name.contains(REPOSITORY)) {
            name = name.replace(REPOSITORY_NAME_TEMPLATE, FileUtilities.capitalize(name) + REPOSITORY);
        } else {
            name = name.replace(REPOSITORY_NAME_TEMPLATE, FileUtilities.capitalize(name));
        }
        return name;
    }
}