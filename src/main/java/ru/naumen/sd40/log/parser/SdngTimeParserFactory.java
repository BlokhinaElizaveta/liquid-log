package ru.naumen.sd40.log.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("SdngTimeParserFactory")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SdngTimeParserFactory implements TimeParserFactory {
    
    @Autowired
    private TimeParser sdngTimeParser;

    @Override
    public TimeParser Get() {
        return sdngTimeParser;
    }
}
