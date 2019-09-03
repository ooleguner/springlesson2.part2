package com.lesson5;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class ItemMapper {


    @Autowired
    ObjectMapper objectMapper;

    public Item mappingItem(HttpServletRequest req) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader reader = req.getReader();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return objectMapper.readValue(stringBuilder.toString(), Item.class);
    }
}
