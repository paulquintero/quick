package com.quick.rest;

import com.quick.rest.services.FileGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

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
	void contextLoads() throws IOException {
		fileGenerator.readFile("templateExample.txt");
		String userDirectory = Paths.get("")
				.toAbsolutePath()
				.toString();
		assertTrue(userDirectory.endsWith(CURRENT_DIR));
		assertTrue(true);
	}

}
