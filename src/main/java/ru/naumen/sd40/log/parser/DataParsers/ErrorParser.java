package ru.naumen.sd40.log.parser.DataParsers;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataSets.SdngDataSet;

import java.util.regex.Pattern;

/**
 * Created by doki on 22.10.16.
 */
@Component
public class ErrorParser
{
    Pattern warnRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) WARN");
    Pattern errorRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) ERROR");
    Pattern fatalRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) FATAL");

    public void parseLine(String line, SdngDataSet data)
    {
        if (warnRegEx.matcher(line).find())
        {
            data.getError().addWarnCount();
        }
        if (errorRegEx.matcher(line).find())
        {
            data.getError().addErrorCount();
        }
        if (fatalRegEx.matcher(line).find())
        {
            data.getError().addFatalCount();
        }
    }
}
