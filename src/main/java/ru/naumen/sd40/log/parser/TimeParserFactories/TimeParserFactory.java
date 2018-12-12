package ru.naumen.sd40.log.parser.TimeParserFactories;

import ru.naumen.sd40.log.parser.TimeParsers.TimeParser;

public interface TimeParserFactory {
    TimeParser get();
}
