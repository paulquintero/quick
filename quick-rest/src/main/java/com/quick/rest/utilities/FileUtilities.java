package com.quick.rest.utilities;

import com.quick.rest.constants.FileConstants;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Pattern;

@Slf4j
public abstract class FileUtilities {
    public static String DOT = ".";
    public static String COMMA = ",";
    public static String TEMPLATE = ".template";
    public static String SLASH = "/";
    private static String ENTITY = "Entity";
    private static String SERVICE = "Service";
    private static String REPOSITORY = "Repository";
    private static String I_INTERFACE = "I";
    private static String CONTROLLER = "Controller";

    public static boolean validatePath(String dirFile){
        File directorio = new File(dirFile);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                log.info("el directorio fue creado");
            } else {
                log.info("Error al crear directorio");
                return false;
            }
        }
        return true;
    }

    public static boolean validateifExistFile(File file, String dirFile){
        boolean ifExist = true;
        File tmpDir = new File(dirFile + file);
        if (!tmpDir.exists()) {
            ifExist = false;
        }
        return ifExist;
    }

    public static String replaceCharacters(String characters, String regex, String replacement){
        return characters.replace(regex,replacement);
    }

    public static File createTempFile(String prefix, String suffix, byte[] content) throws IOException {
        File tempFile = File.createTempFile(prefix, suffix, null);
        FileOutputStream fos = new FileOutputStream(tempFile);
        fos.write(content);
        fos.close();
        return tempFile;
    }

    public static String capitalize(String setence){
        return setence.substring(0, 1).toUpperCase() + setence.substring(1);
    }

    public static String lowerCase(String setence){
        return setence.substring(0, 1).toLowerCase() + setence.substring(1);
    }

    public static String addEntitySuffix(String name){
        if (!name.contains(ENTITY)) {
            name = FileUtilities.capitalize(name) + ENTITY;
        } else {
            name = FileUtilities.capitalize(name);
        }
        return name;
    }


    public static String addServiceSuffix(String name){
        if (!name.contains(SERVICE)) {
            name = FileUtilities.capitalize(name) + SERVICE;
        } else {
            name = FileUtilities.capitalize(name);
        }
        return name;
    }

    public static String addRepositorySuffix(String name){
        if (!Pattern.compile("^II").matcher(name).find() && !Pattern.compile("^.{1}[I]").matcher(name).find()){
            name = I_INTERFACE + name;
        }
        if (!name.contains(REPOSITORY)) {
            name = FileUtilities.capitalize(name) + REPOSITORY;
        } else {
            name = FileUtilities.capitalize(name);
        }
        return name;
    }


    public static String addControllerSuffix(String name){
        if (!name.contains(CONTROLLER)) {
            name = FileUtilities.capitalize(name) + CONTROLLER;
        } else {
            name = FileUtilities.capitalize(name);
        }
        return name;
    }
}
