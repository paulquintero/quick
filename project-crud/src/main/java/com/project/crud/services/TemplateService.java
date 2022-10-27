package com.project.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.quick.rest.iservices.IFileGeneratorService;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class TemplateService {
	@Autowired
	IFileGeneratorService fileGeneratorService;
	
	public void readFile(String fileName) throws IOException {
		
		log.debug("entrando al metodo TemplateService");
	}
	
}
