package ru.naumen.sd40.log.parser.TimeParserFactories;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.TimeParsers.TimeParser;
import ru.naumen.sd40.log.parser.TimeParsers.GCTimeParser;

@Component("gcTimeParserFactory")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GCTimeParserFactory implements TimeParserFactory {

    private TimeParser gcTimeParser;

    public GCTimeParserFactory()
    {
        gcTimeParser = new GCTimeParser();
    }

    @Override
    public TimeParser get() {
        return gcTimeParser;
    }
}
