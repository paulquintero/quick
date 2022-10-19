package com.quick.rest.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quick.rest.configs.PackageProperties;
import com.quick.rest.iservices.IFileGeneratorService;

@Service("fileGenerator")
@Slf4j
public class FileGeneratorService  implements IFileGeneratorService {
  
    @Autowired
    PackageProperties packageProperties;
  
    @Override
    public void readFile() {
      log.debug(packageProperties.getBasePackage());
      log.debug("Prueba de lectura");
    }
}
