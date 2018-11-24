package ru.naumen.sd40.log.parser;

public interface DataParser<T extends DataSet> {
    void parseLine(String line, T data);
}
