package ru.naumen.sd40.log.parser;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("TopTimeParserFactory")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TopTimeParserFactory implements TimeParserFactory {
    private TimeParser topTimeParser;

    public TopTimeParserFactory()
    {
        topTimeParser = new TopTimeParser();
    }

    @Override
    public TimeParser get() {
        return topTimeParser;
    }
}
