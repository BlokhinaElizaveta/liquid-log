package ru.naumen.sd40.log.parser;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("TopDataParser")
public class TopDataParser implements DataParser {

    private Pattern cpuAndMemPattren = Pattern
            .compile("^ *\\d+ \\S+ +\\S+ +\\S+ +\\S+ +\\S+ +\\S+ +\\S+ \\S+ +(\\S+) +(\\S+) +\\S+ java");

    @Override
    public void parseLine(String line, DataSet data) {
        //get la
        Matcher la = Pattern.compile(".*load average:(.*)").matcher(line);
        if (la.find())
        {
            data.topData().addLa(Double.parseDouble(la.group(1).split(",")[0].trim()));
            return;
        }

        //get cpu and mem
        Matcher cpuAndMemMatcher = cpuAndMemPattren.matcher(line);
        if (cpuAndMemMatcher.find())
        {
            data.topData().addCpu(Double.valueOf(cpuAndMemMatcher.group(1)));
            data.topData().addMem(Double.valueOf(cpuAndMemMatcher.group(2)));
            return;
        }
    }
}
