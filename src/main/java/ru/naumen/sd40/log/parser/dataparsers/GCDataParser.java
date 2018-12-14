package ru.naumen.sd40.log.parser.dataparsers;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.datasets.GCDataSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("gcDataParser")
public class GCDataParser implements DataParser<GCDataSet>
{
    private Pattern gcExecutionTime = Pattern.compile(".*real=(.*)secs.*");

    @Override
    public void parseLine(String line, GCDataSet data)
    {
        Matcher matcher = gcExecutionTime.matcher(line);
        if (matcher.find())
            data.getGcData().addTime(Double.parseDouble(matcher.group(1).trim().replace(',', '.')));
    }
}
