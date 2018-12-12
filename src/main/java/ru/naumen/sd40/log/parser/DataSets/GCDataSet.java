package ru.naumen.sd40.log.parser.DataSets;

import ru.naumen.sd40.log.parser.Data.GCData;

import java.util.HashMap;
import java.util.Map;

import static ru.naumen.sd40.log.parser.DataTypes.GCDataType.*;

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

}
