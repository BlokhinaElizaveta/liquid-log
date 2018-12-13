package ru.naumen.sd40.log.parser.parserservices;

import ru.naumen.sd40.log.parser.dataparsers.DataParser;
import ru.naumen.sd40.log.parser.datasetfactories.DataSetFactory;
import ru.naumen.sd40.log.parser.timeparserfactories.TimeParserFactory;

public interface ParserService {
    TimeParserFactory getTimeParserFactory();
    DataParser getDataParser();
    DataSetFactory getDataSetFactory();
}
