package com.quick.rest.services;

import com.quick.rest.enums.DataTypesColumn;
import com.quick.rest.enums.TemplatesEnum;
import com.quick.rest.iservices.IFileGeneratorService;
import com.quick.rest.models.request.EntityChildTemplateDTO;
import com.quick.rest.models.request.EntityTemplateDTO;
import com.quick.rest.utilities.FileUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

@ExtendWith(MockitoExtension.class)
public class TemplateEntityServiceTest {

    @InjectMocks
    TemplateEntityService templateEntityService;

    @Mock
    IFileGeneratorService fileGenerator;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void generateSuccess() throws IOException {
        Mockito.when(this.fileGenerator.readFile(TemplatesEnum.ENTITY)).thenReturn(new File("controllers"));
        Assertions.assertFalse(this.templateEntityService.generate(this.getEntityTemplateDTO()));
    }

    public EntityTemplateDTO getEntityTemplateDTO() {
        List<EntityChildTemplateDTO> entityChildTemplateDTOS = new ArrayList<>();
        entityChildTemplateDTOS.add(this.getEntityChildTemplateDTO());
        EntityTemplateDTO entityTemplateDTO = new EntityTemplateDTO();
        entityTemplateDTO.setEntityName("users");
        entityTemplateDTO.setColumns(entityChildTemplateDTOS);
        return entityTemplateDTO;
    }

    public EntityChildTemplateDTO getEntityChildTemplateDTO() {
        EntityChildTemplateDTO entityChildTemplateDTO = new EntityChildTemplateDTO();
        entityChildTemplateDTO.setDataType(DataTypesColumn.INT);
        entityChildTemplateDTO.setColumnName("id");
        entityChildTemplateDTO.setPrimaryKey(true);
        return entityChildTemplateDTO;
    }

    @Test
    public void findFiles() throws IOException{
        List<String> prueba = FileUtilities.findPaths("./src/main/java/com/quick/rest/services");
        Assertions.assertNotNull(prueba);
    }
}
