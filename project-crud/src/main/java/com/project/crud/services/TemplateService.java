package com.project.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quick.rest.iservices.IFileGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TemplateService {
	@Autowired
	IFileGeneratorService fileGeneratorService;

	public void readFile() {
		log.debug("entrando al metodo TemplateService");
		fileGeneratorService.readFile();
	}
	
}
