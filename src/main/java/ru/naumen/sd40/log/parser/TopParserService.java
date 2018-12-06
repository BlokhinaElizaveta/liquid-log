package ru.naumen.sd40.log.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("top")
public class TopParserService implements ParserService {

    @Autowired
    private TopTimeParserFactory timeParserFactory;

    @Autowired
    private TopDataParser dataParser;

    @Autowired
    private TopDataSetFactory dataSetFactory;

    @Override
    public TimeParserFactory getTimeParserFactory() {
        return timeParserFactory;
    }

    @Override
    public DataParser getDataParser() {
        return dataParser;
    }

    @Override
    public DataSetFactory getDataSetFactory() {
        return dataSetFactory;
    }
}
