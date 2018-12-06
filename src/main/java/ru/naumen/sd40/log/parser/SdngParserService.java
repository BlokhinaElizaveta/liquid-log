package ru.naumen.sd40.log.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sdng")
public class SdngParserService implements ParserService {
    @Autowired
    private SdngTimeParserFactory timeParserFactory;

    @Autowired
    private SdngDataParser dataParser;

    @Autowired
    private SdngDataSetFactory dataSetFactory;

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
