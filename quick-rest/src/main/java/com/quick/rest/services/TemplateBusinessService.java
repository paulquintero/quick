package com.quick.rest.services;

import com.quick.rest.configs.PackageProperties;
import com.quick.rest.enums.TemplatesEnum;
import com.quick.rest.iservices.IFileGeneratorService;
import com.quick.rest.iservices.ITemplateBusinessService;
import com.quick.rest.models.request.BusinessTemplateDTO;
import com.quick.rest.utilities.FileUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Service("templateBusinessService")
@Slf4j
public class TemplateBusinessService implements ITemplateBusinessService  {

    protected static String SERVICE_NAME_TEMPLATE = ":serviceName";
    protected static String IMPORT_REPOSITORY_NAME_TEMPLATE = ":importRepository";
    protected static String IMPORT_ENTITY_TEMPLATE = ":importEntity";
    protected static String ENTITY_VAR_TEMPLATE = ":entityVar";
    protected static String PACKAGE_NAME_TEMPLATE = ":package";
    protected static String ID_DATA_TYPE_TEMPLATE = ":idDataType";
    protected static String REPOSITORY_NAME_TEMPLATE = ":repositoryName";
    protected static String ENTITY_TYPE_TEMPLATE = ":entityType";
    protected static String REPOSITORY_TYPE_PARAM_TEMPLATE = ":repositoryType";


    private static String ENTITY_NAME = "Entity";
    private static String JAVA_FILE = ".java";
    private static String DOT = ".";
    private static String REPOSITORY = "Repository";

    @Autowired
    private PackageProperties packageProperties;

    @Autowired
    IFileGeneratorService fileGenerator;

    private String getPackageService() {
        return packageProperties.getBasePackage() + TemplateBusinessService.DOT + packageProperties.getService();
    }

    private String getPackageEntity() {
        return packageProperties.getBasePackage() + TemplateBusinessService.DOT + packageProperties.getEntity();
    }

    private String getPackageRepository() {
        return packageProperties.getBasePackage() + TemplateBusinessService.DOT + packageProperties.getRepository();
    }

    @Override
    public Boolean generate(BusinessTemplateDTO businessTemplateDTO) {
        Boolean generated = false;

        try {
            File file = this.fileGenerator.readFile(TemplatesEnum.SERVICE);
            StringBuilder template = this.createTemplate(file, businessTemplateDTO);
            String serviceName =  FileUtilities.capitalize(FileUtilities.addServiceSuffix(businessTemplateDTO.getServiceName()));
            File tempFile = FileUtilities.createTempFile(serviceName, JAVA_FILE, template.toString().getBytes(StandardCharsets.UTF_8));
            this.fileGenerator.saveFile(tempFile, serviceName + JAVA_FILE, TemplatesEnum.SERVICE);
            generated = Boolean.TRUE;
            log.debug("archivo {}", file);
        } catch (IOException e) {
            log.error("Template not found {}", e);
        }
        return generated;
    }

    @Override
    public StringBuilder createTemplate(File rawTemplate, BusinessTemplateDTO businessTemplateDTO) throws IOException {
        StringBuilder template = new StringBuilder();
        FileReader fileReader = new FileReader(rawTemplate);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linea;
        Boolean alreadyPrimaryKey = false;

        String repositoryType =  FileUtilities.capitalize(businessTemplateDTO.getRepositoryName());
        String repositoryName =  FileUtilities.lowerCase(businessTemplateDTO.getRepositoryName());
        String serviceName =  FileUtilities.capitalize(FileUtilities.addServiceSuffix(businessTemplateDTO.getServiceName()));

        String entityName =  FileUtilities.capitalize(businessTemplateDTO.getEntityName());
        String entityVar =  FileUtilities.lowerCase(businessTemplateDTO.getEntityName());
        while ((linea = bufferedReader.readLine()) != null) {
            if (linea.contains(PACKAGE_NAME_TEMPLATE)) {
                linea = linea.replace(PACKAGE_NAME_TEMPLATE, this.getPackageService());
            }
            if (linea.contains(IMPORT_REPOSITORY_NAME_TEMPLATE)) {
                linea = linea.replace(IMPORT_REPOSITORY_NAME_TEMPLATE, this.getPackageRepository()+"."+repositoryType);
            }
            if (linea.contains(IMPORT_ENTITY_TEMPLATE)) {
                linea = linea.replace(IMPORT_ENTITY_TEMPLATE, this.getPackageEntity()+"."+FileUtilities.capitalize(businessTemplateDTO.getEntityName()));
            }
            if (linea.contains(SERVICE_NAME_TEMPLATE)) {
                linea = linea.replace(SERVICE_NAME_TEMPLATE, serviceName);
            }
            if (linea.contains(ENTITY_TYPE_TEMPLATE)) {
                linea = linea.replace(ENTITY_TYPE_TEMPLATE, entityName);
            }
            if (linea.contains(ENTITY_VAR_TEMPLATE)) {
                linea = linea.replace(ENTITY_VAR_TEMPLATE, entityVar);
            }
            if (linea.contains(REPOSITORY_TYPE_PARAM_TEMPLATE)) {
                linea = linea.replace(REPOSITORY_TYPE_PARAM_TEMPLATE, repositoryType);
            }
            if (linea.contains(REPOSITORY_NAME_TEMPLATE)) {
                linea = linea.replace(REPOSITORY_NAME_TEMPLATE, repositoryName);
            }
            if (linea.contains(ID_DATA_TYPE_TEMPLATE)) {
                linea = linea.replace(ID_DATA_TYPE_TEMPLATE, businessTemplateDTO.getIdDataType().getType());
            }
            template.append(linea + "\n");
        }
        bufferedReader.close();
        return template;
    }
}
