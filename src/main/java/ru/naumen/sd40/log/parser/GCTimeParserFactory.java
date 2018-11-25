package ru.naumen.sd40.log.parser;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("GCTimeParserFactory")
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
