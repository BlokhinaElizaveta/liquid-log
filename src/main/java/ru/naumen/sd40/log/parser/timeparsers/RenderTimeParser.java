package ru.naumen.sd40.log.parser.timeparsers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RenderTimeParser implements TimeParser {
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS",
            new Locale("ru", "RU"));

    private static final Pattern TIME_PATTERN = Pattern
            .compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3})");

    public RenderTimeParser() {
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    @Override
    public void configureTimeZone(String timeZone) {
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timeZone));
    }

    @Override
    public long parseLine(String line) throws ParseException
    {
        Matcher matcher = TIME_PATTERN.matcher(line);
        if (matcher.find())
        {
            Date parse = DATE_FORMAT.parse(matcher.group(1));
            return parse.getTime();
        }
        return 0L;
    }

    @Override
    public void prepareFileName(String fileName) throws IllegalArgumentException {}
}
