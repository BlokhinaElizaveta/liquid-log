package ru.naumen.sd40.log.parser.timeparserfactories;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.timeparsers.RenderTimeParser;
import ru.naumen.sd40.log.parser.timeparsers.TimeParser;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RenderTimeParserFactory implements TimeParserFactory {

    private TimeParser renderTimeParser;

    public RenderTimeParserFactory()
    {
        renderTimeParser = new RenderTimeParser();
    }

    @Override
    public TimeParser get() {
        return renderTimeParser;
    }
}
