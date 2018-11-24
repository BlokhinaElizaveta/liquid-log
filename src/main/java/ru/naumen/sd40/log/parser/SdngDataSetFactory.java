package ru.naumen.sd40.log.parser;

public class SdngDataSetFactory implements DataSetFactory {
    @Override
    public DataSet create() {
        return new SdngDataSet();
    }
}
