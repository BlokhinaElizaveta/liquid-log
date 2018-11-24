package ru.naumen.sd40.log.parser;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TopTimeParser implements TimeParser {
    private Pattern timeRegex = Pattern.compile("^_+ (\\S+)");
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH:mm");
    private String dataDate;

    public TopTimeParser()
    {
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    @Override
    public void configureTimeZone(String timeZone)
    {
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
    }

    @Override
    public long parseLine(String line) throws ParseException {
        Matcher matcher = timeRegex.matcher(line);
        if (matcher.find()) {
           return sdf.parse(dataDate + matcher.group(1)).getTime();
        }
        return 0L;
    }

    @Override
    public void prepareFileName(String fileName) throws IllegalArgumentException {
        //Supports these masks in file name: YYYYmmdd, YYY-mm-dd i.e. 20161101, 2016-11-01
        Matcher matcher = Pattern.compile("\\d{8}|\\d{4}-\\d{2}-\\d{2}").matcher(fileName);
        if (!matcher.find())
        {
            throw new IllegalArgumentException();
        }
        this.dataDate = matcher.group(0).replaceAll("-", "");
    }
}
