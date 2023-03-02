package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileReader {

    public String readFile(String fileName) {
        List<String> list;
        StringBuilder result = new StringBuilder();
        try (InputStream inputStream = getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            list = reader.lines().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        list.forEach(result::append);
        return result.toString();
    }
}