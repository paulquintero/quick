package com.quick.rest.services;

import com.quick.rest.iservices.*;
import com.quick.rest.models.request.*;
import com.quick.rest.utilities.FileUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Service
@Slf4j
public class QuickApiGeneratorService implements IQuickApiGeneratorService {

    private static String REPOSITORY = "Repository";
    @Autowired
    ITemplateEntityService templateEntityService;

    @Autowired
    ITemplateRepositoryService templateRepositoryService;

    @Autowired
    ITemplateBusinessService templateBusinessService;

    @Autowired
    ITemplateControllerService controllerTemplateDTO;

    @Override
    public Boolean generateFull(FullBodyDTO fullBodyDTO) {
        log.debug("Process to generate entity");
        String name = FileUtilities.capitalize(fullBodyDTO.getEntityName().replace("Service", ""));
        String repositoryName = FileUtilities.addRepositorySuffix(name);
        String serviceName = FileUtilities.addServiceSuffix(name);
        String entityName = FileUtilities.addEntitySuffix(name);
        String controllerName = FileUtilities.addControllerSuffix(name);
        fullBodyDTO.setEntityName(entityName);
        RepositoryTemplateDTO repositoryTemplateDTO = new RepositoryTemplateDTO();
        repositoryTemplateDTO.setRepositoryName(name);
        Optional<EntityChildTemplateDTO> entityChildTemplateDTO =
                fullBodyDTO.getColumns().stream().filter(column -> (column.getPrimaryKey() != null && column.getPrimaryKey().equals(Boolean.TRUE)))
                        .findFirst();
        EntityChildTemplateDTO primaryKey = null;
        if(entityChildTemplateDTO.isPresent()){
            primaryKey = entityChildTemplateDTO.get();
        }
        repositoryTemplateDTO.setDataTypeId(primaryKey.getDataType());
        repositoryTemplateDTO.setEntityName(entityName);
        BusinessTemplateDTO businessTemplateDTO = new BusinessTemplateDTO();
        businessTemplateDTO.setRepositoryName(repositoryName);
        businessTemplateDTO.setEntityName(entityName);
        businessTemplateDTO.setIdDataType(primaryKey.getDataType());
        businessTemplateDTO.setServiceName(serviceName);
        ControllerTemplateDTO controllerTemplateDTO = new ControllerTemplateDTO();
        controllerTemplateDTO.setControllerName(controllerName);
        controllerTemplateDTO.setServiceName(serviceName);
        controllerTemplateDTO.setEntityName(entityName);
        controllerTemplateDTO.setIdDataType(primaryKey.getDataType());
        controllerTemplateDTO.setPathMapping(fullBodyDTO.getPathMapping());
        this.generateEntity(fullBodyDTO);
        this.generateRepository(repositoryTemplateDTO);
        this.generateService(businessTemplateDTO);
        return this.generateController(controllerTemplateDTO);
    }

    @Override
    public Boolean generateEntity(EntityTemplateDTO entityTemplateDTO) {
        log.debug("Process to generate entity");
        return this.templateEntityService.generate(entityTemplateDTO);
    }

    @Override
    public Boolean generateRepository(RepositoryTemplateDTO repositoryTemplateDTO) {
        log.debug("Process to generate repository");
        return this.templateRepositoryService.generate(repositoryTemplateDTO);
    }

    @Override
    public Boolean generateService(BusinessTemplateDTO businessTemplateDTO) {
        log.debug("Process to generate repository");
        return this.templateBusinessService.generate(businessTemplateDTO);
    }

    @Override
    public Boolean generateController(ControllerTemplateDTO controllerTemplateDTO) {
        log.debug("Process to generate repository");
        return this.controllerTemplateDTO.generate(controllerTemplateDTO);
    }


}
