package ru.naumen.sd40.log.parser.loglineparsers;

import ru.naumen.sd40.log.parser.*;
import ru.naumen.sd40.log.parser.datasets.DataSet;
import ru.naumen.sd40.log.parser.dataparsers.DataParser;
import ru.naumen.sd40.log.parser.timeparsers.TimeParser;

import java.text.ParseException;

public class BlockOfLinesParser implements LogLineParser {

    private TimeParser timeParser;
    private DataParser dataParser;
    private IDataSetService dataSetService;

    private DataSet currentSet;

    public BlockOfLinesParser(TimeParser timeParser, DataParser dataParser, IDataSetService dataSetService,
                              DataSet startDataSet)
    {
        this.timeParser = timeParser;
        this.dataParser = dataParser;
        this.dataSetService = dataSetService;
        currentSet = startDataSet;
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
