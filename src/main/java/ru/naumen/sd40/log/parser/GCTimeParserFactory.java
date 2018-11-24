package ru.naumen.sd40.log.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("GCTimeParserFactory")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GCTimeParserFactory implements TimeParserFactory {

    @Autowired
    private TimeParser gcTimeParser;

    @Override
    public TimeParser Get() {
        return gcTimeParser;
    }
}
