package com.quick.rest.models.request;

import com.quick.rest.enums.DataTypesColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EntityChildTemplateDTO {
    private String columnName;
    private DataTypesColumn dataType;
    private Boolean primaryKey;
}
