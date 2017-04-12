package com.epam.taxi.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaxiTextFileReader implements TaxiReader {

    private static final Logger LOGGER = LogManager.getLogger(TaxiTextFileReader.class);

    public TaxiTextFileReader() {
    }

    public List<String> read(String fileName) {
        List<String> strings;
        Path path = Paths.get(fileName);
        try (Stream<String> lines = (Files.lines(path))) {

            strings = lines.collect(Collectors.toList());
            if (!strings.isEmpty()) {
                return strings;
            }
            LOGGER.log(Level.FATAL, "File " + fileName + " is empty");
            throw new RuntimeException("File " + fileName + " is empty");
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "File " + fileName + " was not found; " + e);
            throw new RuntimeException("File " + fileName + " was not found; " + e);
        }
    }
}
