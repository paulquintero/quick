package com.quick.rest.services;

import com.quick.rest.configs.PackageProperties;
import com.quick.rest.enums.TemplatesEnum;
import com.quick.rest.iservices.IFileGeneratorService;
import com.quick.rest.iservices.ITemplateEntityService;
import com.quick.rest.models.request.EntityChildTemplateDTO;
import com.quick.rest.models.request.EntityTemplateDTO;
import com.quick.rest.utilities.FileUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.regex.Pattern;

@Service("TemplateEntityService")
@Slf4j
public class TemplateEntityService implements ITemplateEntityService {

    @Autowired
    IFileGeneratorService fileGenerator;

    @Autowired
    private PackageProperties packageProperties;

    protected static String TABLE_NAME_TEMPLATE = ":tableName";
    protected static String COLUMNS_TEMPLATE = ":propiedad";
    protected static String ENTITY_NAME_TEMPLATE = ":entityName";
    protected static String PACKAGE_NAME_TEMPLATE = ":package";
    private static String ID_TEMPLATE = ":hasID";

    protected static String PRIVATE = "private";
    private static String JAVA_FILE = ".java";
    private static String ENTITY_NAME = "Entity";
    private static String IMPORT_ID = "import jakarta.persistence.Id;";
    private static String DOT = ".";

    private String getPackage() {
        return packageProperties.getBasePackage() + TemplateEntityService.DOT + packageProperties.getEntity();
    }

    @Override
    public Boolean generate(EntityTemplateDTO entityTemplateDTO) {
        Boolean generated = false;
        try {
            File file = this.fileGenerator.readFile(TemplatesEnum.ENTITY);
            StringBuilder template = this.createTemplate(file, entityTemplateDTO);
            String entityName = FileUtilities.addEntitySuffix(entityTemplateDTO.getEntityName());
            File tempFile = FileUtilities.createTempFile(entityName, TemplateEntityService.JAVA_FILE, template.toString().getBytes(StandardCharsets.UTF_8));
            String isCreated = this.fileGenerator.saveFile(tempFile, entityName + TemplateEntityService.JAVA_FILE, TemplatesEnum.ENTITY);
            generated = Boolean.TRUE;
            log.debug("archivo {}", file);
        } catch (IOException e) {
            log.error("Template not found {}", e);
        }
        return generated;
    }

    @Override
    public StringBuilder createTemplate(File rawTemplate, EntityTemplateDTO entityTemplateDTO) throws IOException {
        StringBuilder template = new StringBuilder();
        FileReader fileReader = new FileReader(rawTemplate);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linea;
        Boolean alreadyPrimaryKey = false;
        while ((linea = bufferedReader.readLine()) != null) {

            if (linea.contains(TemplateEntityService.PACKAGE_NAME_TEMPLATE)) {
                linea = linea.replace(PACKAGE_NAME_TEMPLATE, this.getPackage());
            }

            if (this.validateId(entityTemplateDTO) && linea.contains(TemplateEntityService.ID_TEMPLATE)) {
                linea = linea.replace(ID_TEMPLATE, IMPORT_ID);
            }

            if (linea.contains(TemplateEntityService.TABLE_NAME_TEMPLATE)) {
                linea = linea.replace(TABLE_NAME_TEMPLATE, entityTemplateDTO.getEntityName());
            }

            if (linea.contains(TemplateEntityService.ENTITY_NAME_TEMPLATE)) {
                linea = linea.replace(ENTITY_NAME_TEMPLATE, FileUtilities.addEntitySuffix(entityTemplateDTO.getEntityName()));
            }
            if (linea.contains(TemplateEntityService.COLUMNS_TEMPLATE)) {
                for (EntityChildTemplateDTO column : entityTemplateDTO.getColumns()) {
                    if (this.validateId(entityTemplateDTO) && !alreadyPrimaryKey) {
                        template.append("\t" + "@Id" + "\n");
                        alreadyPrimaryKey = true;
                    }
                    StringBuilder columnFormat = new StringBuilder();
                    columnFormat.append(PRIVATE).append(" ").append(column.getDataType().getType()).append(" ").append(column.getColumnName()).append(";");
                    template.append("\t" + columnFormat + "\n");
                }

                if(linea.contains(COLUMNS_TEMPLATE)) {
                    linea = linea.replace(COLUMNS_TEMPLATE, "");
                    template.append(linea);
                }
            }
            template.append(linea + "\n");
        }
        bufferedReader.close();
        return template;
    }



    private Boolean validateId(EntityTemplateDTO entityTemplateDTO) {
        Optional<EntityChildTemplateDTO> entityChildTemplateDTO = entityTemplateDTO.getColumns().stream().filter(column -> (column.getPrimaryKey() != null && column.getPrimaryKey().equals(Boolean.TRUE))).findFirst();
        return entityChildTemplateDTO.isPresent();
    }
}
