package ru.naumen.sd40.log.parser.datasets;

import ru.naumen.sd40.log.parser.data.GCData;

import java.util.HashMap;
import java.util.Map;

import static ru.naumen.sd40.log.parser.datatypes.GCDataType.*;

public class GCDataSet implements DataSet {
    private GCData gcData = new GCData();

    public GCData getGcData() {
        return gcData;
    }

    @Override
    public Map<String, Object> getFields() {
        Map<String, Object> fields = new HashMap<>();
        fields.put(GCTIMES, gcData.getGcTimes());
        fields.put(AVARAGE_GC_TIME, gcData.getCalculatedAvg());
        fields.put(MAX_GC_TIME, gcData.getMaxGcTime());

        return fields;
    }

    @Override
    public boolean isNan() {
        return gcData.isNan();
    }

    @Override
    public void printStatistic(long key) {

    }

}
