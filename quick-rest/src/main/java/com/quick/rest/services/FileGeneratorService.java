package com.quick.rest.services;


import com.quick.rest.configs.PackageProperties;
import com.quick.rest.constants.ExceptionsConstants;
import com.quick.rest.constants.FileConstants;
import com.quick.rest.enums.TemplatesEnum;
import com.quick.rest.exceptions.FileGeneratorException;
import com.quick.rest.iservices.IFileGeneratorService;
import com.quick.rest.utilities.FileUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


@Service("fileGenerator")
@Slf4j
public class FileGeneratorService implements IFileGeneratorService {

    @Autowired
    private PackageProperties packageProperties;

    private String getMainDir() {
        return "src" + File.separator + "main" + File.separator + "java";
    }

    @Override
    public File readFile(TemplatesEnum template) throws FileGeneratorException {
        StringBuilder dirTemplate = FileConstants.getDirTemplate(template);
        if (dirTemplate == null) {
            throw new FileGeneratorException(ExceptionsConstants.TEMPLATE_NOT_VALID);
        }
        dirTemplate.append(template.getUrl());
        File file = null;
        try {
            Resource resource = new ClassPathResource(dirTemplate.toString());
            file = resource.getFile();
        } catch (IOException e) {
            log.error("Exception trying to read template {} ", e);
            throw new FileGeneratorException(ExceptionsConstants.TEMPLATE_NOT_FOUND);
        }
        return file;
    }

    @Override
    public String saveFile(File file, String name, TemplatesEnum enumFiles) {
        String workingDirectory = System.getProperty("user.dir");
        String projectDir = null;
        if (workingDirectory.contains(packageProperties.getProjectName())) {
            projectDir = workingDirectory.substring(0, workingDirectory.lastIndexOf(File.separator));
        } else {
            projectDir = workingDirectory;
        }
        StringBuilder pathFile = new StringBuilder(projectDir).append(File.separator).append(packageProperties.getProjectName()).append(File.separator);
        try {
            pathFile.append(this.getMainDir()).append(File.separator);
            pathFile.append(FileUtilities.replaceCharacters(packageProperties.getBasePackage(), FileUtilities.DOT, File.separator));
            pathFile.append(File.separator);
            switch (enumFiles) {
                case CONTROLLER:
                    pathFile.append(packageProperties.getController());
                    break;
                case SERVICE:
                    pathFile.append(packageProperties.getService());
                    break;
                case REPOSITORY:
                    pathFile.append(packageProperties.getRepository());
                    break;
                case ENTITY:
                    pathFile.append(packageProperties.getEntity());
                    break;
                default:
                    break;
            }

            String sourceDir = pathFile.toString();
            File dir = new File(sourceDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            pathFile.append(File.separator);
            pathFile.append(name);
            Files.move(file.toPath(), new File(pathFile.toString()).toPath(), StandardCopyOption.REPLACE_EXISTING);
            this.deleteFile(file.getAbsolutePath());
        } catch (IOException e) {
            log.error("Error moving file to {}, error: {}", projectDir.toString(), e);
        } catch (Exception e) {
            log.error("Error {}", e);
        }
        return pathFile.toString();
    }

    @Override
    public Boolean deleteFile(String file) {
        Boolean fileDeletes = false;
        try {
            System.out.println(file);
           Files.deleteIfExists(new File(file).toPath());
            fileDeletes = true;
        } catch (IOException e) {
             log.error("File not found {}", e);
        } catch (Exception e) {
            log.error("Error {}", e);
        }
        return fileDeletes;
    }


}
