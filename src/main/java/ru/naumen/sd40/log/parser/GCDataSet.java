package ru.naumen.sd40.log.parser;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.naumen.perfhouse.statdata.Constants.TIME;
import static ru.naumen.sd40.log.parser.GCDataType.*;

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
