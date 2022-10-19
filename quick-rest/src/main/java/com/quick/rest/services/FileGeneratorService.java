package com.quick.rest.services;

import com.quick.rest.iservices.IFileGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service("fileGenerator")
@Slf4j
public class FileGeneratorService  implements IFileGeneratorService {
    @Override
    public String readFile(String fileName) throws IOException {
        Resource resource = new ClassPathResource("/templates/"+fileName);
        File file = resource.getFile();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String contenido = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder("");
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                // Lee línea por línea, omitiendo los saltos de línea
                stringBuilder.append(linea + "\n");
            }
            contenido = stringBuilder.toString();
        } catch (IOException e) {
            log.error("Excepción leyendo archivo: " + e.getMessage());
        }finally{
            bufferedReader.close();
        }
        return contenido;
    }

    public String writeFile(){
        return null;
    }
}
