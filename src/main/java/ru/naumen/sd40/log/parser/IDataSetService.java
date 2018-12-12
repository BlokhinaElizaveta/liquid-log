package ru.naumen.sd40.log.parser;

import ru.naumen.sd40.log.parser.DataSets.DataSet;

public interface IDataSetService {
    DataSet get(long key);
    void close();
}
