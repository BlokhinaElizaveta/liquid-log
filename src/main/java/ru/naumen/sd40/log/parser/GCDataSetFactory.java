package ru.naumen.sd40.log.parser;

import org.springframework.stereotype.Component;

@Component("gcDataSetFactory")
public class GCDataSetFactory implements DataSetFactory {
    @Override
    public DataSet create() {
        return new GCDataSet();
    }
}
