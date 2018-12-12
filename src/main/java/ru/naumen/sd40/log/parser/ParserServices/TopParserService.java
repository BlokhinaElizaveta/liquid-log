package ru.naumen.sd40.log.parser.ParserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataParsers.DataParser;
import ru.naumen.sd40.log.parser.DataSetFactories.DataSetFactory;
import ru.naumen.sd40.log.parser.DataSetFactories.TopDataSetFactory;
import ru.naumen.sd40.log.parser.TimeParserFactories.TimeParserFactory;
import ru.naumen.sd40.log.parser.DataParsers.TopDataParser;
import ru.naumen.sd40.log.parser.TimeParserFactories.TopTimeParserFactory;

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
