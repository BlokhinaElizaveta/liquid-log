package ru.naumen.sd40.log.parser;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("GCDataParser")
public class GCDataParser implements DataParser
{
    private Pattern gcExecutionTime = Pattern.compile(".*real=(.*)secs.*");

    @Override
    public void parseLine(String line, DataSet data)
    {
        Matcher matcher = gcExecutionTime.matcher(line);
        if (matcher.find())
            data.getGcData().addTime(Double.parseDouble(matcher.group(1).trim().replace(',', '.')));
    }
}
