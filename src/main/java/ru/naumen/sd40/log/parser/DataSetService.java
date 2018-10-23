package ru.naumen.sd40.log.parser;

import org.influxdb.dto.BatchPoints;
import ru.naumen.perfhouse.influx.InfluxDAO;

public class DataSetService implements IDataSetService {

    private BatchPoints points;
    private InfluxDAO finalStorage;
    private String finalInfluxDb;

    private DataSet currentDataSet = new DataSet();
    private long key = 0;

    public DataSetService(String influxDb, InfluxDAO storage) {
        finalStorage = storage;
        finalInfluxDb = influxDb;

        if (influxDb != null) {
            storage.init();
            storage.connectToDB(influxDb);
        }

        if (storage != null) {
            points = storage.startBatchPoints(influxDb);
        }

        if (System.getProperty("NoCsv") == null) {
            System.out.print("Timestamp;Actions;Min;Mean;Stddev;50%%;95%%;99%%;99.9%%;Max;Errors\n");
        }
    }

    @Override
    public void write() {
        ActionDoneParser dones = currentDataSet.getActionsDone();
        dones.calculate();
        ErrorParser erros = currentDataSet.getErrors();
        if (System.getProperty("NoCsv") == null) {
            System.out.print(String.format("%d;%d;%f;%f;%f;%f;%f;%f;%f;%f;%d\n", key, dones.getCount(),
                    dones.getMin(), dones.getMean(), dones.getStddev(), dones.getPercent50(), dones.getPercent95(),
                    dones.getPercent99(), dones.getPercent999(), dones.getMax(), erros.getErrorCount()));
        }
        if (!dones.isNan()) {
            finalStorage.storeActionsFromLog(points, finalInfluxDb, key, dones, erros);
        }

        GCParser gc = currentDataSet.getGc();
        if (!gc.isNan()) {
            finalStorage.storeGc(points, finalInfluxDb, key, gc);
        }

        TopData cpuData = currentDataSet.cpuData();
        if (!cpuData.isNan()) {
            finalStorage.storeTop(points, finalInfluxDb, key, cpuData);
        }
    }

    @Override
    public DataSet get(long key) {
        if (this.key != key)
        {
            write();
            this.key = key;
            currentDataSet = new DataSet();
        }

        return currentDataSet;
    }
}
