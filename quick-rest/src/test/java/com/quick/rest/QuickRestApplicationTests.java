package com.quick.rest;

import com.quick.rest.configs.PackageProperties;
import com.quick.rest.enums.TemplatesEnum;
import com.quick.rest.services.FileGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class QuickRestApplicationTests {
    static final String CURRENT_DIR = "project-crud";
    @InjectMocks
    private FileGeneratorService fileGenerator;
    @Mock
    private PackageProperties packageProperties;
    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void saveFile() throws IOException {
            File myObj = new File("TemplateController.java");
        Mockito.when(this.packageProperties.getBasePackage()).thenReturn("com.project.crud");
            File myobj2 = fileGenerator.saveFile(myObj, TemplatesEnum.CONTROLLER);
            assertNotNull(myobj2);
        }

}
