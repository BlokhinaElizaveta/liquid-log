package ru.naumen.sd40.log.parser.parserservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataparsers.DataParser;
import ru.naumen.sd40.log.parser.datasetfactories.DataSetFactory;
import ru.naumen.sd40.log.parser.datasetfactories.TopDataSetFactory;
import ru.naumen.sd40.log.parser.timeparserfactories.TimeParserFactory;
import ru.naumen.sd40.log.parser.dataparsers.TopDataParser;
import ru.naumen.sd40.log.parser.timeparserfactories.TopTimeParserFactory;

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
