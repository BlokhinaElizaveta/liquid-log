package ru.naumen.sd40.log.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.influx.InfluxDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 * Created by doki on 22.10.16.
 */

@Component("logParser")
public class LogParser {
    private InfluxDAO storage;
    private Map<String, ParserService> parserServices;


    @Autowired
    public LogParser(InfluxDAO storage, Map<String, ParserService> parserServices) {
        this.storage = storage;
        this.parserServices = parserServices;
    }

    /**
     * @throws IOException
     * @throws ParseException
     */
    public void parse(String path, String mode, String db, String timeZone, boolean trace) throws IOException, ParseException {
        String influxDb = db.replaceAll("-", "_");
        ParserService parserService = parserServices.get(mode);
        TimeParserFactory timeParserFactory = parserService.getTimeParserFactory();
        TimeParser timeParser = timeParserFactory.get();
        LogLineParser logLineParser;
        DBWriter writer = new InfluxDBWriter(influxDb, storage, trace);
        DataParser dataParser = parserService.getDataParser();
        DataSetFactory dataSetFactory = parserService.getDataSetFactory();
        IDataSetService dataSetService = new DataSetService(writer, dataSetFactory);
        logLineParser = new OneLineParser(timeParser, dataParser, dataSetService);

        if (mode.equals("top")) {
            ((TopTimeParser)timeParser).setDate(path);
            logLineParser = new BlockOfLinesParser(timeParser, dataParser, dataSetService, dataSetFactory.create());
        }

        timeParser.configureTimeZone(timeZone);
        timeParser.prepareFileName(path);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                logLineParser.parse(line);
            }
        }

        dataSetService.close();
    }
}
