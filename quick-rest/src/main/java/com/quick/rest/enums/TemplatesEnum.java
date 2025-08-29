package com.quick.rest.enums;

import java.util.Arrays;
import java.util.Optional;

public enum TemplatesEnum {
    CONTROLLER("controller.template"),
    ENTITY("entity.template"),
    REPOSITORY("repository.template"),
    SERVICE("service.template");

    private String template;

    TemplatesEnum(String template) {
        this.template = template;
    }

    public String getUrl() {
        return this.template;
    }

    public static Optional<TemplatesEnum> get(String template) {
        return Arrays.stream(TemplatesEnum.values())
                .filter(temp -> temp.template.equals(template))
                .findFirst();
    }
}
