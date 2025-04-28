package org.aplicacao.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReaderService {
    private final Path path = Paths.get("formulario.txt");

    public String readFormTxt() throws IOException {
        return Files.readString(path);
    }
}
