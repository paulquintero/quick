package com.quick.rest.utilities;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java.nio.file.Path;
import java.util.regex.Pattern;

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
            } else {
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

    public static List<String> findPaths(String evaluatePath) throws IOException {
        List<String> result = null;
        File directory = new File(evaluatePath);
        try {
            List<File> list = Arrays.asList(directory.listFiles());
            result = list.stream().map(File::getName).collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }

        return result;
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
