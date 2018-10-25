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
     * @param args [0] - sdng.log, [1] - gc.log, [2] - top, [3] - dbName, [4] timezone
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws IOException, ParseException {
        String influxDb = null;

        if (args.length > 1) {
            influxDb = args[1];
            influxDb = influxDb.replaceAll("-", "_");
        }

        InfluxDAO storage = null;
        if (influxDb != null) {
            storage = new InfluxDAO(System.getProperty("influx.host"), System.getProperty("influx.user"),
                    System.getProperty("influx.password"));
        }

        String log = args[0];

        TimeParser timeParser;
        LogLineParser logLineParser;
        DBWriter writer = new InfluxDBWriter(influxDb, storage);
        IDataSetService dataSetService = new DataSetService(writer);

        String mode = System.getProperty("parse.mode", "");

        switch (mode) {
            case "sdng":
                timeParser = new SdngTimeParser();
                logLineParser = new OneLineParser(timeParser, new SdgnDataParser(), dataSetService);
                break;
            case "gc":
                timeParser = new GCTimeParser();
                logLineParser = new OneLineParser(timeParser, new GCDataParser(), dataSetService);
                break;
            case "top":
                timeParser = new TopTimeParser(log);
                logLineParser = new BlockOfLinesParser(timeParser, new TopDataParser(), dataSetService);
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown parse mode! Availiable modes: sdng, gc, top. Requested mode: " + mode);
        }

        if (args.length > 2) {
            timeParser.configureTimeZone(args[2]);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(log))) {
            String line;
            while ((line = br.readLine()) != null) {
                logLineParser.parse(line);
            }
        }

        dataSetService.close();
    }
}
