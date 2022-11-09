package com.quick.rest.models.request;

import com.quick.rest.enums.DataTypesColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ControllerTemplateDTO {
    private String controllerName;
    private String serviceName;
    private String entityName;
    private DataTypesColumn idDataType;
    private String pathMapping;
}
