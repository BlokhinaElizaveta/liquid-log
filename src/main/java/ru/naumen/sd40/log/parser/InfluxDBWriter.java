package ru.naumen.sd40.log.parser;

import org.influxdb.dto.BatchPoints;
import ru.naumen.perfhouse.influx.InfluxDAO;

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
        if (dataSet instanceof SdngDataSet && trace) {
            ActionDoneData dones = ((SdngDataSet)dataSet).getActionsDone();
            dones.calculate();
            ErrorData erros = ((SdngDataSet)dataSet).getError();
            System.out.print(String.format("%d;%d;%f;%f;%f;%f;%f;%f;%f;%f;%d\n", key, dones.getCount(),
                    dones.getMin(), dones.getMean(), dones.getStddev(), dones.getPercent50(), dones.getPercent95(),
                    dones.getPercent99(), dones.getPercent999(), dones.getMax(), erros.getErrorCount()));
        }
        if (!dataSet.isNan()) {
            storage.store(points, InfluxDb, key, dataSet);
        }
    }
}
