package ru.naumen.sd40.log.parser;

import ru.naumen.perfhouse.influx.InfluxDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by doki on 22.10.16.
 */
public class LogParser {
    /**
     * @throws IOException
     * @throws ParseException
     */
    public static void parse(String path, String mode, String db, String timeZone, boolean trace) throws IOException, ParseException {
        String influxDb = db.replaceAll("-", "_");

        InfluxDAO storage = null;
        storage = new InfluxDAO(System.getProperty("influx.host"), System.getProperty("influx.user"),
                System.getProperty("influx.password"));

        TimeParser timeParser;
        LogLineParser logLineParser;
        DBWriter writer = new InfluxDBWriter(influxDb, storage, trace);
        IDataSetService dataSetService = new DataSetService(writer);


        switch (mode) {
            case "sdng":
                timeParser = new SdngTimeParser();
                logLineParser = new OneLineParser(timeParser, new SdngDataParser(), dataSetService);
                break;
            case "gc":
                timeParser = new GCTimeParser();
                logLineParser = new OneLineParser(timeParser, new GCDataParser(), dataSetService);
                break;
            case "top":
                timeParser = new TopTimeParser(path);
                logLineParser = new BlockOfLinesParser(timeParser, new TopDataParser(), dataSetService);
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown parse mode! Availiable modes: sdng, gc, top. Requested mode: " + mode);
        }

        timeParser.configureTimeZone(timeZone);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                logLineParser.parse(line);
            }
        }

        dataSetService.close();
    }
}
