package ru.naumen.sd40.log.parser;

import org.springframework.stereotype.Component;

@Component("topDataSetFactory")
public class TopDataSetFactory implements DataSetFactory {
    @Override
    public DataSet create() {
        return new TopDataSet();
    }
}
