package ru.naumen.sd40.log.parser;

import java.text.ParseException;

public interface LogLineParser {
    void parse(String line) throws ParseException;
}
