package ru.naumen.sd40.log.parser.timeparserfactories;

import ru.naumen.sd40.log.parser.timeparsers.TimeParser;

public interface TimeParserFactory {
    TimeParser get();
}
