package ru.naumen.sd40.log.parser.TimeParserFactories;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.TimeParserFactories.TimeParserFactory;
import ru.naumen.sd40.log.parser.TimeParsers.TimeParser;
import ru.naumen.sd40.log.parser.TimeParsers.TopTimeParser;

@Component("topTimeParserFactory")
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
