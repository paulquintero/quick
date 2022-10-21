package com.quick.rest.services;

import com.quick.rest.enums.TemplatesEnum;
import com.quick.rest.iservices.IFileGeneratorService;
import com.quick.rest.iservices.ITemplateEntity;
import com.quick.rest.iservices.ITemplatesServices;
import com.quick.rest.models.request.EntityChildTemplateDTO;
import com.quick.rest.models.request.EntityTemplateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Optional;

@Service("TemplateEntityService")
@Slf4j
public class TemplateEntityService implements ITemplateEntity, ITemplatesServices {

    @Autowired
    IFileGeneratorService fileGenerator;

    protected static String TABLE_NAME = ":tableName";
    protected static String COLUMNS = ":propiedad";
    protected static String PRIVATE = "private";

    @Override
    public Boolean generate(EntityTemplateDTO entityTemplateDTO) {
        Boolean generated = false;
        try {
            File file = this.fileGenerator.readFile(TemplatesEnum.ENTITY);

            log.debug("archivo {}", file);
        } catch (IOException e) {
            log.error("Template not found {}", e);
        }
        return generated;
    }

    @Override
    public void createTemplate(File rawTemplate, EntityTemplateDTO entityTemplateDTO) throws IOException {
        StringBuilder template = new StringBuilder();
        FileReader fileReader = new FileReader(rawTemplate);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linea;
        Boolean alreadyPrimaryKey = false;
        while ((linea = bufferedReader.readLine()) != null) {

            if (linea.contains(TemplateEntityService.TABLE_NAME)) {
                linea.replace(TABLE_NAME, entityTemplateDTO.getEntityName());
            }
            if (linea.contains(TemplateEntityService.COLUMNS)) {
                for (EntityChildTemplateDTO column : entityTemplateDTO.getColumns()) {
                    if (this.validateId(entityTemplateDTO) && !alreadyPrimaryKey) {
                        template.append("@Id");
                        alreadyPrimaryKey = true;
                    }
                    StringBuilder columnFormat = new StringBuilder();
                    columnFormat.append(PRIVATE).append(" ").append(column.getDataType().getType()).append(" ").append(column.getColumnName());
                    template.append(columnFormat + "\n");
                }

                linea.replace(COLUMNS, "");
            }

            template.append(linea + "\n");
        }
        bufferedReader.close();
    }

    private Boolean validateId(EntityTemplateDTO entityTemplateDTO) {
        Optional<EntityChildTemplateDTO> entityChildTemplateDTO = entityTemplateDTO.getColumns().stream().filter(column -> (column.getPrimaryKey() != null && column.getPrimaryKey().equals(Boolean.TRUE))).findFirst();
        return entityChildTemplateDTO.isPresent();
    }
}
