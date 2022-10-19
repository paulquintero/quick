package com.quick.rest.models.request;

import lombok.Data;

import java.util.List;

@Data
public class EntityTemplateDTO {
    private String entityName;
    private List<EntityChildTemplateDTO> columns;
}
