package ru.naumen.sd40.log.parser;

public class GCDataSetFactory implements DataSetFactory {
    @Override
    public DataSet create() {
        return new GCDataSet();
    }
}
