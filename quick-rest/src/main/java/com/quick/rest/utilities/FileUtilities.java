package com.quick.rest.utilities;

import com.quick.rest.constants.FileConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Path;

@Slf4j
public abstract class FileUtilities {
    public static String DOT = ".";
    public static String COMMA = ",";
    public static String TEMPLATE = ".template";

    public static boolean findPath(){
        File directorio = new File(FileConstants.DIR_CONTROLLER);
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

    public static boolean validateifExist(File file, String dirFile){
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
}
