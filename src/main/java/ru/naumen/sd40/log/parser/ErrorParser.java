package ru.naumen.sd40.log.parser;

import java.util.regex.Pattern;

/**
 * Created by doki on 22.10.16.
 */
public class ErrorParser
{
    Pattern warnRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) WARN");
    Pattern errorRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) ERROR");
    Pattern fatalRegEx = Pattern.compile("^\\d+ \\[.+?\\] \\(.+?\\) FATAL");

    void parseLine(String line, DataSet data)
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
