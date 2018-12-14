package ru.naumen.sd40.log.parser.dataparsers;

import ru.naumen.sd40.log.parser.datasets.DataSet;

public interface DataParser<T extends DataSet> {
    void parseLine(String line, T data);
}
