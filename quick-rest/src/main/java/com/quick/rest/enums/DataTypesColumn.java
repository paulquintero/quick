package com.quick.rest.enums;

import java.util.Arrays;
import java.util.Optional;

public enum DataTypesColumn {
    INT("Integer"), INTEGER("Integer"), LONG("Long"), STRING("String"), BOOLEAN("Boolean"), FLOAT("Float"), DOUBLE("Double");

    private String type;

    DataTypesColumn(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static Optional<DataTypesColumn> get(String type) {
        return Arrays.stream(DataTypesColumn.values())
                .filter(dataType -> dataType.type.equals(type))
                .findFirst();
    }
}
