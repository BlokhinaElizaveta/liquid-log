package ru.naumen.sd40.log.parser.parserservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataparsers.GCDataParser;
import ru.naumen.sd40.log.parser.datasetfactories.DataSetFactory;
import ru.naumen.sd40.log.parser.datasetfactories.GCDataSetFactory;
import ru.naumen.sd40.log.parser.dataparsers.DataParser;
import ru.naumen.sd40.log.parser.timeparserfactories.GCTimeParserFactory;
import ru.naumen.sd40.log.parser.timeparserfactories.TimeParserFactory;

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
