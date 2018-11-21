package ru.naumen.sd40.log.parser;

public class SdngDataSetFactory extends DataSetFactory {
    @Override
    public DataSet create() {
        return new SdngDataSet();
    }
}
