package ru.naumen.sd40.log.parser;

import java.text.ParseException;

public class BlockOfLinesParser implements LogLineParser {

    private TimeParser timeParser;
    private DataParser dataParser;
    private IDataSetService dataSetService;

    private DataSet currentSet = new DataSet();

    public BlockOfLinesParser(TimeParser timeParser, DataParser dataParser, IDataSetService dataSetService) {
        this.timeParser = timeParser;
        this.dataParser = dataParser;
        this.dataSetService = dataSetService;
    }

    @Override
    public void parse(String line) throws ParseException {
        long time = timeParser.parseLine(line);

        if (time == 0)
            dataParser.parseLine(line, currentSet);
        else {
            long key = NumberUtils.getTimeInterval(time);

            currentSet = dataSetService.get(key);
        }

    }
}
