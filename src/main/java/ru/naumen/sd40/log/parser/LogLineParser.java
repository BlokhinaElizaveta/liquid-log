package ru.naumen.sd40.log.parser;

import java.text.ParseException;

public interface LogLineParser {
    void parseTimeAndData(String line) throws ParseException;
}
