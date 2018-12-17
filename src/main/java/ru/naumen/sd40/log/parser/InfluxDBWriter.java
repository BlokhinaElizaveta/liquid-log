package ru.naumen.sd40.log.parser;

import org.influxdb.dto.BatchPoints;
import ru.naumen.perfhouse.influx.InfluxDAO;
import ru.naumen.sd40.log.parser.datasets.DataSet;

public class InfluxDBWriter implements DBWriter {

    private BatchPoints points;
    private InfluxDAO storage;
    private String InfluxDb;
    private boolean trace;

    public InfluxDBWriter(String influxDb, InfluxDAO storage, boolean trace){
        this.storage = storage;
        InfluxDb = influxDb;
        this.trace = trace;

        if (influxDb != null) {
            storage.init();
            storage.connectToDB(influxDb);
        }

        if (storage != null) {
            points = storage.startBatchPoints(influxDb);
        }

    }

    public void write(long key, DataSet dataSet) {
        if (trace) {
            dataSet.printStatistic(key);
        }

        if (!dataSet.isNan()) {
            storage.store(points, InfluxDb, key, dataSet);
        }
    }
}
