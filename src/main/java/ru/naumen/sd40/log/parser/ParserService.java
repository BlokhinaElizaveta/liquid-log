package ru.naumen.sd40.log.parser;

public interface ParserService {
    TimeParserFactory getTimeParserFactory();
    DataParser getDataParser();
    DataSetFactory getDataSetFactory();
}
