package ru.naumen.sd40.log.parser.DataParsers;

import ru.naumen.sd40.log.parser.DataSets.DataSet;

public interface DataParser<T extends DataSet> {
    void parseLine(String line, T data);
}
