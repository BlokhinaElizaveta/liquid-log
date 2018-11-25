package ru.naumen.sd40.log.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.naumen.perfhouse.influx.InfluxDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by doki on 22.10.16.
 */

@Component("logParser")
public class LogParser {
    private InfluxDAO storage;

    @Qualifier("SdngDataParser")
    @Autowired
    private DataParser sdngDataParser;

    @Qualifier("GCDataParser")
    @Autowired
    private DataParser gcDataParser;

    @Qualifier("TopDataParser")
    @Autowired
    private DataParser topDataParser;

    @Autowired
    @Qualifier("SdngTimeParserFactory")
    private TimeParserFactory sdngTimeParserFactory;

    @Autowired
    @Qualifier("GCTimeParserFactory")
    private TimeParserFactory gcTimeParserFactory;

    @Autowired
    @Qualifier("TopTimeParserFactory")
    private TimeParserFactory topTimeParserFactory;

    @Autowired
    public LogParser(InfluxDAO storage) {
        this.storage = storage;
    }

    /**
     * @throws IOException
     * @throws ParseException
     */
    public void parse(String path, String mode, String db, String timeZone, boolean trace) throws IOException, ParseException {
        String influxDb = db.replaceAll("-", "_");

        TimeParser timeParser;
        LogLineParser logLineParser;
        DBWriter writer = new InfluxDBWriter(influxDb, storage, trace);
        IDataSetService dataSetService;

        switch (mode) {
            case "sdng":
                timeParser = sdngTimeParserFactory.get();
                dataSetService = new DataSetService(writer, new SdngDataSetFactory());
                logLineParser = new OneLineParser(timeParser, sdngDataParser, dataSetService) ;
                break;
            case "gc":
                timeParser = gcTimeParserFactory.get();
                dataSetService = new DataSetService(writer, new GCDataSetFactory());
                logLineParser = new OneLineParser(timeParser, gcDataParser, dataSetService);
                break;
            case "top":
                timeParser = topTimeParserFactory.get();
                DataSetFactory factory = new TopDataSetFactory();
                dataSetService = new DataSetService(writer, factory);
                logLineParser = new BlockOfLinesParser(timeParser, topDataParser, dataSetService, factory.create());
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown parse mode! Availiable modes: sdng, gc, top. Requested mode: " + mode);
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
