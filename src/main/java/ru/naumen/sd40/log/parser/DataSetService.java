package ru.naumen.sd40.log.parser;

import ru.naumen.sd40.log.parser.datasetfactories.DataSetFactory;
import ru.naumen.sd40.log.parser.datasets.DataSet;

public class DataSetService implements IDataSetService {

    private DBWriter writer;
    private DataSetFactory factory;
    private DataSet currentDataSet;
    private long key;

    public DataSetService(DBWriter writer, DataSetFactory factory) {
        this.writer = writer;
        this.factory = factory;
        if (System.getProperty("NoCsv") == null) {
            System.out.print("Timestamp;Actions;Min;Mean;Stddev;50%%;95%%;99%%;99.9%%;Max;Errors\n");
        }
    }

    @Override
    public DataSet get(long key) {
        if (this.key != key)
        {
            if (currentDataSet != null)
                writer.write(this.key, currentDataSet);

            this.key = key;
            currentDataSet = factory.create();
        }
        return currentDataSet;
    }

    @Override
    public void close() {
        if (currentDataSet != null)
            writer.write(this.key, currentDataSet);
    }
}
