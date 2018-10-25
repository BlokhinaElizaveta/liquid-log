package ru.naumen.sd40.log.parser;

public interface IDataSetService {
    DataSet get(long key);
    void close();
}
