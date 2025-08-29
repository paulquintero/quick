
## Uso y ejemplos

### configuracion inicial de la libreria
Para hacer uso de la libreria es necesario agregar las siguientes propiedades ya sea en tu archivo .properties o yml, ya que estas propiedades se encargan de realizar el escaneo de paquetes para la lectura y generacion de las clases necesarias, se listan las propiedades necesarias:
```properties
quick.api.parent.basePackage //corresponde al nombre del package base del proyecto
quick.api.package.controller //nombre del paquete donde se encuentran los controller
quick.api.package.entity //nombre del paquete donde se encuentran los entityies
quick.api.package.service //nombre del paquete donde se encuentran los services
quick.api.package.repository //nombre del paquete donde se encuentran los repository
```

### ejemplo
```properties
quick.api.parent.basePackage=com.project.crud
quick.api.package.controller=controllers
quick.api.package.entity=entityies
quick.api.package.service=services
quick.api.package.repository=repositoryies

```

Para poder utilizar la libreria es necesario importarla asi como generan un controller en el cual se especifique cuales los archivos que se desean generar, a continuacion se listan los metodos y controller como ejemplo de cuales pueden ser incluidos.

El controller se encuentra actualmente ubicado en el proyecto crud-repository, con el nombre TemplateController

## Implementacion

Importar scaneo de la libreria, primero se indica la ruta base del proyecto, seguido de la libreria "com.quick.rest.*"

### Aplicaction
```java
@SpringBootApplication(scanBasePackages = {"com.project.crud.*","com.quick.rest.*"})
public class ProjectCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectCrudApplication.class, args);
	}

}
```

### Controller

```java
package com.project.crud.controllers;

import com.quick.rest.models.request.*;
import com.quick.rest.models.response.QuickApiResponse;
import com.quick.rest.services.QuickApiGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class serves as the main controller for automatically generating files.
 * Be careful! Do not leave this endpoint exposed in deployed environments. Only use it in your local environment or as part of your coding process.
 * @author Paul Quintero
 */
@RestController
@RequestMapping("quick/template")
public class TemplateController {

	@Autowired
	private QuickApiGeneratorService quickApiGeneratorService;

    /**
     * This method allows you to generate the entity exclusively.
     * @param entityTemplateDTO It contains the name of the entity and its columns.
     * @return The response is 'success' if the file was created or 'error' if it wasn't generated correctly.
     */
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

    /**
     * It allows to generate its repository interface and its corresponding entity.
     * @param repositoryTemplateDTO It contains the name of the entity and its columns, and the name of repository.
     * @return The response is 'success' if the file was created or 'error' if it wasn't generated correctly.
     */
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

    /**
     * It allows to generate its service, repository interface and its corresponding entity.
     * @param businessTemplateDTO It contains the name of the entity and its columns, the name of repository and the name of service.
     * @return The response is 'success' if the file was created or 'error' if it wasn't generated correctly.
     */
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

    /**
     * It allows the full path including controller, service, repository, entity
     * @param fullBodyDTO It contains the name of the endpoint, the entity and its columns, the name of repository and the name of service.
     * @return The response is 'success' if the file was created or 'error' if it wasn't generated correctly.
     */
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

```
Nota: si quieres conocer como funciona te invito a que eches un vistazo a las entrañas del proyecto en la carpeta quick-rest ;)