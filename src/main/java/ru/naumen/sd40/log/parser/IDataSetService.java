package ru.naumen.sd40.log.parser;

public interface IDataSetService {
    void write();
    DataSet get(long key);
}
