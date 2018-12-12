package ru.naumen.sd40.log.parser.ParserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataParsers.DataParser;
import ru.naumen.sd40.log.parser.DataSetFactories.DataSetFactory;
import ru.naumen.sd40.log.parser.DataSetFactories.SdngDataSetFactory;
import ru.naumen.sd40.log.parser.DataParsers.SdngDataParser;
import ru.naumen.sd40.log.parser.TimeParserFactories.SdngTimeParserFactory;
import ru.naumen.sd40.log.parser.TimeParserFactories.TimeParserFactory;

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
