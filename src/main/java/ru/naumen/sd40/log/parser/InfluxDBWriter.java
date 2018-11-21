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

    public void write(long key, SdngDataSet dataSet) {
        ActionDoneData dones = dataSet.getActionsDone();
        dones.calculate();
        ErrorData erros = dataSet.getError();
        if (trace) {
            System.out.print(String.format("%d;%d;%f;%f;%f;%f;%f;%f;%f;%f;%d\n", key, dones.getCount(),
                    dones.getMin(), dones.getMean(), dones.getStddev(), dones.getPercent50(), dones.getPercent95(),
                    dones.getPercent99(), dones.getPercent999(), dones.getMax(), erros.getErrorCount()));
        }
        if (!dones.isNan()) {
            storage.storeActionsFromLog(points, InfluxDb, key, dones, erros);
        }
    }

    public void write(long key, GCDataSet dataSet) {
        GCData gc = dataSet.getGcData();
        if (!gc.isNan()) {
            storage.storeGc(points, InfluxDb, key, gc);
        }
    }

    public void write(long key, TopDataSet dataSet) {
        TopData cpuData = dataSet.topData();
        if (!cpuData.isNan()) {
            storage.storeTop(points, InfluxDb, key, cpuData);
        }
    }

    @Override
    public void write(long key, DataSet dataSet) {
        if(dataSet instanceof TopDataSet)
            write(key, (TopDataSet) dataSet);
        if(dataSet instanceof SdngDataSet)
            write(key, (SdngDataSet) dataSet);
        if(dataSet instanceof GCDataSet)
            write(key, (GCDataSet) dataSet);
    }
}
