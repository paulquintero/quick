package com.quick.rest.services;

import com.quick.rest.configs.PackageProperties;
import com.quick.rest.enums.TemplatesEnum;
import com.quick.rest.iservices.IFileGeneratorService;
import com.quick.rest.iservices.ITemplateControllerService;
import com.quick.rest.models.request.ControllerTemplateDTO;
import com.quick.rest.utilities.FileUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service("templateControllerService")
@Slf4j
public class TemplateControllerService implements ITemplateControllerService {


    protected static String CONTROLLER_NAME_TEMPLATE = ":controllerName";
    protected static String ENDPOINT_TEMPLATE = ":endpoint";
    protected static String SERVICE_NAME_TEMPLATE = ":serviceName";
    protected static String SERVICE_TYPE_TEMPLATE = ":serviceType";
    protected static String IMPORT_SERVICE_NAME_TEMPLATE = ":importService";
    protected static String IMPORT_ENTITY_TEMPLATE = ":importEntity";
    protected static String ENTITY_VAR_TEMPLATE = ":entityVar";
    protected static String PACKAGE_NAME_TEMPLATE = ":package";
    protected static String ID_DATA_TYPE_TEMPLATE = ":idDataType";
    protected static String ENTITY_TYPE_TEMPLATE = ":entityType";

    private static String JAVA_FILE = ".java";
    private static String DOT = ".";

    @Autowired
    private PackageProperties packageProperties;

    @Autowired
    IFileGeneratorService fileGenerator;

    private String getPackageController() {
        return packageProperties.getBasePackage() + TemplateControllerService.DOT + packageProperties.getController();
    }

    private String getPackageService() {
        return packageProperties.getBasePackage() + TemplateControllerService.DOT + packageProperties.getService();
    }

    private String getPackageEntity() {
        return packageProperties.getBasePackage() + TemplateControllerService.DOT + packageProperties.getEntity();
    }

    @Override
    public Boolean generate(ControllerTemplateDTO controllerTemplateDTO) {
        Boolean generated = false;

        try {
            File file = this.fileGenerator.readFile(TemplatesEnum.CONTROLLER);
            StringBuilder template = this.createTemplate(file, controllerTemplateDTO);
            String controllerName =  FileUtilities.capitalize(FileUtilities.addControllerSuffix(controllerTemplateDTO.getControllerName()));
            File tempFile = FileUtilities.createTempFile(controllerName, JAVA_FILE, template.toString().getBytes(StandardCharsets.UTF_8));
            this.fileGenerator.saveFile(tempFile, controllerName + JAVA_FILE, TemplatesEnum.CONTROLLER);
            generated = Boolean.TRUE;
            log.debug("archivo {}", file);
        } catch (IOException e) {
            log.error("Template not found {}", e);
        }
        return generated;
    }

    @Override
    public StringBuilder createTemplate(File rawTemplate, ControllerTemplateDTO controllerTemplateDTO) throws IOException {
        StringBuilder template = new StringBuilder();
        FileReader fileReader = new FileReader(rawTemplate);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linea;
        String controllerName =  FileUtilities.capitalize(FileUtilities.addControllerSuffix(controllerTemplateDTO.getControllerName()));
        String serviceType =  FileUtilities.capitalize(controllerTemplateDTO.getServiceName());
        String serviceName =  FileUtilities.lowerCase(controllerTemplateDTO.getServiceName());

        String entityName =  FileUtilities.capitalize(controllerTemplateDTO.getEntityName());
        String entityVar =  FileUtilities.lowerCase(controllerTemplateDTO.getEntityName());
        while ((linea = bufferedReader.readLine()) != null) {
            if (linea.contains(PACKAGE_NAME_TEMPLATE)) {
                linea = linea.replace(PACKAGE_NAME_TEMPLATE, this.getPackageController());
            }
            if (linea.contains(IMPORT_SERVICE_NAME_TEMPLATE)) {
                linea = linea.replace(IMPORT_SERVICE_NAME_TEMPLATE, this.getPackageService()+"."+serviceType);
            }
            if (linea.contains(IMPORT_ENTITY_TEMPLATE)) {
                linea = linea.replace(IMPORT_ENTITY_TEMPLATE, this.getPackageEntity()+"."+FileUtilities.capitalize(controllerTemplateDTO.getEntityName()));
            }
            if (linea.contains(CONTROLLER_NAME_TEMPLATE)) {
                linea = linea.replace(CONTROLLER_NAME_TEMPLATE, controllerName);
            }
            if (linea.contains(ENDPOINT_TEMPLATE)) {
                linea = linea.replace(ENDPOINT_TEMPLATE, controllerTemplateDTO.getPathMapping());
            }
            if (linea.contains(SERVICE_TYPE_TEMPLATE)) {
                linea = linea.replace(SERVICE_TYPE_TEMPLATE, serviceType);
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
            if (linea.contains(ID_DATA_TYPE_TEMPLATE)) {
                linea = linea.replace(ID_DATA_TYPE_TEMPLATE, controllerTemplateDTO.getIdDataType().getType());
            }
            template.append(linea + "\n");
        }
        bufferedReader.close();
        return template;
    }

}
