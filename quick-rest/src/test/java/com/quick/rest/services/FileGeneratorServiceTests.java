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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void saveFile_Success() throws IOException {
        File myObj = new File("TemplateController.java");
        try{
            if (myObj.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File is already existed!");
            }
        } catch (Exception e){

        }
        Mockito.when(this.packageProperties.getBasePackage()).thenReturn("com.quick.rest");
        Mockito.when(this.packageProperties.getProjectName()).thenReturn("quick-rest");
        Mockito.when(this.packageProperties.getController()).thenReturn("controllers");
        String myobj2 = fileGeneratorService.saveFile(myObj, TemplatesEnum.CONTROLLER);
        assertNotNull(myobj2);
        assertTrue(this.fileGeneratorService.deleteFile(myobj2));

    }
}
