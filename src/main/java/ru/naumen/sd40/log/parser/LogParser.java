package ru.naumen.sd40.log.parser;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BeanFactory beanFactory;

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
        IDataSetService dataSetService = new DataSetService(writer);


        switch (mode) {
            case "sdng":
                timeParser = new SdngTimeParser();
                logLineParser = new OneLineParser(timeParser, beanFactory.getBean("SdngDataParser", DataParser.class), dataSetService);
                break;
            case "gc":
                timeParser = new GCTimeParser();
                logLineParser = new OneLineParser(timeParser, beanFactory.getBean("GCDataParser", DataParser.class), dataSetService);
                break;
            case "top":
                timeParser = new TopTimeParser(path);
                logLineParser = new BlockOfLinesParser(timeParser, beanFactory.getBean("TopDataParser", DataParser.class), dataSetService);
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
