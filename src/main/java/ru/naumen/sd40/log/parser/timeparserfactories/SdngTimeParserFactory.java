package ru.naumen.sd40.log.parser.timeparserfactories;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.timeparsers.TimeParser;
import ru.naumen.sd40.log.parser.timeparsers.SdngTimeParser;

@Component("sdngTimeParserFactory")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SdngTimeParserFactory implements TimeParserFactory {
    private TimeParser sdngTimeParser;

    public SdngTimeParserFactory()
    {
        sdngTimeParser = new SdngTimeParser();
    }

    @Override
    public TimeParser get() {
        return sdngTimeParser;
    }
}
