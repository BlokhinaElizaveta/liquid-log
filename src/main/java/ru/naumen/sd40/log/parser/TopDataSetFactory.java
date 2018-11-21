package ru.naumen.sd40.log.parser;

public class TopDataSetFactory extends DataSetFactory {
    @Override
    public DataSet create() {
        return new TopDataSet();
    }
}
