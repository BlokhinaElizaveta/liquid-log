package ru.naumen.sd40.log.parser.ParserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataParsers.GCDataParser;
import ru.naumen.sd40.log.parser.DataSetFactories.DataSetFactory;
import ru.naumen.sd40.log.parser.DataSetFactories.GCDataSetFactory;
import ru.naumen.sd40.log.parser.DataParsers.DataParser;
import ru.naumen.sd40.log.parser.TimeParserFactories.GCTimeParserFactory;
import ru.naumen.sd40.log.parser.TimeParserFactories.TimeParserFactory;

@Component("gc")
public class GCParserService implements ParserService {

    @Autowired
    private GCTimeParserFactory timeParserFactory;

    @Autowired
    private GCDataParser dataParser;

    @Autowired
    private GCDataSetFactory dataSetFactory;

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
