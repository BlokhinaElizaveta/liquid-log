package ru.naumen.sd40.log.parser;

import ru.naumen.sd40.log.parser.datasets.DataSet;

public interface DBWriter {
    void write(long key, DataSet dataSet);
}
