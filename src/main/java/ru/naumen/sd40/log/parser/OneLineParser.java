package ru.naumen.sd40.log.parser;

import java.text.ParseException;
import java.util.HashMap;

public class OneLineParser implements LogLineParser {
    private TimeParser timeParser;
    private DataParser dataParser;
    private HashMap<Long, DataSet> existingDataSet;

    public OneLineParser(TimeParser timeParser, DataParser dataParser, HashMap<Long, DataSet> existingDataSet) {
        this.timeParser = timeParser;
        this.dataParser = dataParser;
        this.existingDataSet = existingDataSet;
    }

    @Override
    public void parseTimeAndData(String line) throws ParseException {
        long time = timeParser.parseLine(line);
        if (time == 0)
            return;

        dataParser.parseLine(line, existingDataSet.computeIfAbsent(NumberUtils.getTimeInterval(time), k -> new DataSet()));
    }
}
