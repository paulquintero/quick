package com.project.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quick.rest.iservices.IFileGeneratorService;

import java.io.IOException;

@Controller
@RequestMapping("quick/template")
public class TemplateController {

	@Autowired
	private IFileGeneratorService fileGenerator;
	
	@GetMapping("/{fileName}")
	public ResponseEntity<String> readTemplate(@PathVariable("fileName") String fileName) throws IOException {
		return ResponseEntity.ok(fileGenerator.readFile(fileName));
	}
}
