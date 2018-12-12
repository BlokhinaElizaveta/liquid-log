package ru.naumen.sd40.log.parser;

import ru.naumen.sd40.log.parser.DataSets.DataSet;

public interface DBWriter {
    void write(long key, DataSet dataSet);
}
