package com.project.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quick.rest.iservices.IFileGeneratorService;

@Controller
@RequestMapping("quick/template")
public class TemplateController {

	@Autowired
	private IFileGeneratorService fileGenerator;
	
	@GetMapping
	public ResponseEntity<String> readTemplate(){

		return ResponseEntity.ok("Lectura correcta");
	}
}
