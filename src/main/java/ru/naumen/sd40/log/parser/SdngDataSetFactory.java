package ru.naumen.sd40.log.parser;

import org.springframework.stereotype.Component;

@Component("sdngDataSetFactory")
public class SdngDataSetFactory implements DataSetFactory {
    @Override
    public DataSet create() {
        return new SdngDataSet();
    }
}
