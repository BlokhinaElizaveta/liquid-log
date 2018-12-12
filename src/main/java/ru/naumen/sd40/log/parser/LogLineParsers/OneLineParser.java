package ru.naumen.sd40.log.parser.LogLineParsers;

import ru.naumen.sd40.log.parser.DataParsers.DataParser;
import ru.naumen.sd40.log.parser.IDataSetService;
import ru.naumen.sd40.log.parser.NumberUtils;
import ru.naumen.sd40.log.parser.TimeParsers.TimeParser;

import java.text.ParseException;

public class OneLineParser implements LogLineParser {
    private TimeParser timeParser;
    private DataParser dataParser;
    private IDataSetService dataSetService;


    public OneLineParser(TimeParser timeParser, DataParser dataParser, IDataSetService dataSetService) {
        this.timeParser = timeParser;
        this.dataParser = dataParser;
        this.dataSetService = dataSetService;
    }

    @Override
    public void parse(String line) throws ParseException {
        long time = timeParser.parseLine(line);
        if (time == 0)
            return;

        long key = NumberUtils.getTimeInterval(time);

        dataParser.parseLine(line, dataSetService.get(key));
    }
}
