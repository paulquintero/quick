package com.quick.rest.iservices;

import com.quick.rest.enums.TemplatesEnum;

import java.io.File;
import java.io.IOException;

public interface IFileGeneratorService {
    File readFile(TemplatesEnum template) throws IOException;
}
