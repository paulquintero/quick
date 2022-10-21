package com.quick.rest.services;

import com.quick.rest.configs.PackageProperties;
import com.quick.rest.enums.TemplatesEnum;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.io.BufferedReader;
import java.io.File;
import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
public class FileGeneratorServiceTests {
    @InjectMocks
    FileGeneratorService fileGeneratorService;

    @Mock
    PackageProperties packageProperties;

    @BeforeEach
    public void initMocks() throws IllegalAccessException {
        MockitoAnnotations.openMocks(this);
    }

    public PackageProperties getPackageProperties() throws IllegalAccessException {
        packageProperties = new PackageProperties();
        FieldUtils.writeField(packageProperties, "projectName", "Hello", true);
        return packageProperties;
    }

    @Test
    public void readFileSuccess(){
        try {
            File rawTemplate = this.fileGeneratorService.readFile(TemplatesEnum.CONTROLLER);
            System.out.println(rawTemplate);
            Assertions.assertNotNull(rawTemplate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
