package ru.naumen.sd40.log.parser;

public interface DBWriter {
    void write(long key, DataSet dataSet);
}
