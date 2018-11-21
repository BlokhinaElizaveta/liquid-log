package ru.naumen.sd40.log.parser;

public class GCDataSetFactory extends DataSetFactory {
    @Override
    public DataSet create() {
        return new GCDataSet();
    }
}
