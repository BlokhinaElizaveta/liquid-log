package ru.naumen.sd40.log.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogModesService {

    @Autowired
    List<DataParser> parsers;

    public List<String> getModes() {
        List<String> modes = new ArrayList<>();
        for (DataParser parser : parsers) {
            String mode = parser.getClass().getSimpleName().split("Data")[0].toLowerCase();
            modes.add(mode);
        }
        return modes;
    }
}
