package com.quick.rest.iservices;

import java.io.IOException;

public interface IFileGeneratorService {
    String readFile(String templateName) throws IOException;
}
