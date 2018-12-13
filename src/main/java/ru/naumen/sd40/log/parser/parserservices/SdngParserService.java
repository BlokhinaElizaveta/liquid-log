package ru.naumen.sd40.log.parser.parserservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataparsers.DataParser;
import ru.naumen.sd40.log.parser.datasetfactories.DataSetFactory;
import ru.naumen.sd40.log.parser.datasetfactories.SdngDataSetFactory;
import ru.naumen.sd40.log.parser.dataparsers.SdngDataParser;
import ru.naumen.sd40.log.parser.timeparserfactories.SdngTimeParserFactory;
import ru.naumen.sd40.log.parser.timeparserfactories.TimeParserFactory;

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
