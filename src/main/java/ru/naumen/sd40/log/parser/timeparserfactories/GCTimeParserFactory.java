package ru.naumen.sd40.log.parser.timeparserfactories;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.timeparsers.TimeParser;
import ru.naumen.sd40.log.parser.timeparsers.GCTimeParser;

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
