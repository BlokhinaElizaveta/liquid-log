package ru.naumen.sd40.log.parser.parserservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.dataparsers.DataParser;
import ru.naumen.sd40.log.parser.dataparsers.RenderDataParser;
import ru.naumen.sd40.log.parser.datasetfactories.DataSetFactory;
import ru.naumen.sd40.log.parser.datasetfactories.RenderDataSetFactory;
import ru.naumen.sd40.log.parser.timeparserfactories.RenderTimeParserFactory;
import ru.naumen.sd40.log.parser.timeparserfactories.TimeParserFactory;

@Component("render")
public class RenderParserService implements ParserService {

    @Autowired
    private RenderTimeParserFactory timeParserFactory;

    @Autowired
    private RenderDataParser dataParser;

    @Autowired
    private RenderDataSetFactory dataSetFactory;

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
