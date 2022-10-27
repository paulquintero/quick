package com.quick.rest.services;

import com.quick.rest.enums.DataTypesColumn;
import com.quick.rest.iservices.IFileGeneratorService;
import com.quick.rest.models.request.EntityChildTemplateDTO;
import com.quick.rest.models.request.EntityTemplateDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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
    public void generateSuccess() {
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
}
