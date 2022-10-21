package com.quick.rest;

import com.quick.rest.enums.TemplatesEnum;
import com.quick.rest.services.FileGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class QuickRestApplicationTests {

	static final String CURRENT_DIR = "project-crud";
	@InjectMocks
	FileGeneratorService fileGenerator;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	void readFileSucces() throws IOException {
		String result = fileGenerator.readFile("controller.template");
		assertNotNull(result);
	}

	//@Test
	//void readFileError() throws IOException {
	//	String result = fileGenerator.readFile("entity.template");
	//	assertNotNull(result);
	//}

	@Test
	void saveFile() throws IOException {
		File myObj = new File("controller.template");
		TemplatesEnum enumFiles = null;
		String result = fileGenerator.saveFile(myObj, enumFiles.CONTROLLER);
		assertNotNull(result);
	}
}
