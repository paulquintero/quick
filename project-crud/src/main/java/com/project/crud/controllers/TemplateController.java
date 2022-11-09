package com.project.crud.controllers;

import com.quick.rest.models.request.*;
import com.quick.rest.models.response.QuickApiResponse;
import com.quick.rest.services.QuickApiGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quick/template")
public class TemplateController {

	@Autowired
	private QuickApiGeneratorService quickApiGeneratorService;
	
	@PostMapping(value = "entity", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuickApiResponse> generateEntity(@RequestBody EntityTemplateDTO entityTemplateDTO){
	  QuickApiResponse quickResponse = new QuickApiResponse();
	  if(this.quickApiGeneratorService.generateEntity(entityTemplateDTO)) {
	    quickResponse.setMessageResponse("Success");
	  } else {
	    quickResponse.setMessageResponse("Fail");
	  }
	  return ResponseEntity.ok(quickResponse);
	}

	@PostMapping(value = "repository", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuickApiResponse> generateRepository(@RequestBody RepositoryTemplateDTO repositoryTemplateDTO){
		QuickApiResponse quickResponse = new QuickApiResponse();
		if(this.quickApiGeneratorService.generateRepository(repositoryTemplateDTO)) {
			quickResponse.setMessageResponse("Success");
		} else {
			quickResponse.setMessageResponse("Fail");
		}
		return ResponseEntity.ok(quickResponse);
	}

	@PostMapping(value = "service", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuickApiResponse> generateService(@RequestBody BusinessTemplateDTO businessTemplateDTO){
		QuickApiResponse quickResponse = new QuickApiResponse();
		if(this.quickApiGeneratorService.generateService(businessTemplateDTO)) {
			quickResponse.setMessageResponse("Success");
		} else {
			quickResponse.setMessageResponse("Fail");
		}
		return ResponseEntity.ok(quickResponse);
	}

	@PostMapping(value = "controller", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuickApiResponse> generateController(@RequestBody ControllerTemplateDTO controllerTemplateDTO){
		QuickApiResponse quickResponse = new QuickApiResponse();
		if(this.quickApiGeneratorService.generateController(controllerTemplateDTO)) {
			quickResponse.setMessageResponse("Success");
		} else {
			quickResponse.setMessageResponse("Fail");
		}
		return ResponseEntity.ok(quickResponse);
	}

	@PostMapping(value = "full-path", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuickApiResponse> generateFull(@RequestBody FullBodyDTO fullBodyDTO){
		QuickApiResponse quickResponse = new QuickApiResponse();
		if(this.quickApiGeneratorService.generateFull(fullBodyDTO)) {
			quickResponse.setMessageResponse("Success");
		} else {
			quickResponse.setMessageResponse("Fail");
		}
		return ResponseEntity.ok(quickResponse);
	}
}
