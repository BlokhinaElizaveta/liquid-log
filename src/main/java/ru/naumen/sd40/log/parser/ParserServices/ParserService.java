package ru.naumen.sd40.log.parser.ParserServices;

import ru.naumen.sd40.log.parser.DataParsers.DataParser;
import ru.naumen.sd40.log.parser.DataSetFactories.DataSetFactory;
import ru.naumen.sd40.log.parser.TimeParserFactories.TimeParserFactory;

public interface ParserService {
    TimeParserFactory getTimeParserFactory();
    DataParser getDataParser();
    DataSetFactory getDataSetFactory();
}
