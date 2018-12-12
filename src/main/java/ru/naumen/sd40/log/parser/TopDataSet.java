package ru.naumen.sd40.log.parser;

import java.util.HashMap;
import java.util.Map;

import static ru.naumen.sd40.log.parser.TopDataType.*;

public class TopDataSet implements DataSet {
    private TopData topData = new TopData();

    public TopData topData() {
        return topData;
    }

    @Override
    public Map<String, Object> getFields() {
        Map<String, Object> fields = new HashMap<>();
        fields.put(AVG_LA, topData.getAvgLa());
        fields.put(AVG_CPU, topData.getAvgCpuUsage());
        fields.put(AVG_MEM, topData.getAvgMemUsage());
        fields.put(MAX_LA, topData.getMaxLa());
        fields.put(MAX_CPU, topData.getMaxCpu());
        fields.put(MAX_MEM, topData.getMaxMem());

        return fields;
    }

    @Override
    public boolean isNan() {
        return topData.isNan();
    }
}
