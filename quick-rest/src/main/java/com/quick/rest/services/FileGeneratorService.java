package com.quick.rest.services;

import com.quick.rest.iservices.IFileGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("fileGenerator")
@Slf4j
public class FileGeneratorService  implements IFileGeneratorService {
    @Override
    public void readFile() {
        log.debug("Prueba de lectura");
    }
}
