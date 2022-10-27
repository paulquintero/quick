package com.quick.rest.constants;

import com.quick.rest.enums.TemplatesEnum;

public class FileConstants {
    public static String DIR_TEMPLATE = "/templates/";
    public static String SLASH = "/";
    public static String DIR_CONTROLLER = "controller";
    public static String DIR_ENTITYS = "entity";
    public static String DIR_REPOSITORY = "repository";
    public static String DIR_SERVICE = "service";

    public static StringBuilder getDirTemplate(TemplatesEnum template) {
        StringBuilder dirTemplate = new StringBuilder();
        dirTemplate.append(FileConstants.DIR_TEMPLATE);
        switch (template){
            case CONTROLLER:
                dirTemplate.append(FileConstants.DIR_CONTROLLER).append(FileConstants.SLASH);
                break;
            case SERVICE:
                dirTemplate.append(FileConstants.DIR_SERVICE).append(FileConstants.SLASH);
                break;
            case REPOSITORY:
                dirTemplate.append(FileConstants.DIR_REPOSITORY).append(FileConstants.SLASH);
                break;
            case ENTITY:
                dirTemplate.append(FileConstants.DIR_ENTITYS).append(FileConstants.SLASH);
                break;
            default:
                dirTemplate = null;
                break;
        }
        return dirTemplate;
    }
}
