package com.quick.rest.iservices;

import java.io.IOException;

public interface IFileGeneratorService {
    String readFile( String fileName) throws IOException;
}
