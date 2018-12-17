package ru.naumen.sd40.log.parser.datasets;

import ru.naumen.sd40.log.parser.data.RenderData;

import java.util.HashMap;
import java.util.Map;

import static ru.naumen.sd40.log.parser.datatypes.ResponseDataType.*;

public class RenderDataSet implements DataSet {

    private RenderData renderData = new RenderData();

    public RenderData getRenderData() {
        return renderData;
    }

    @Override
    public Map<String, Object> getFields() {
        Map<String, Object> fields = new HashMap<>();
        fields.put(COUNT, renderData.getCount());
        fields.put(MIN, renderData.getMin());
        fields.put(MAX, renderData.getMax());
        fields.put(MEAN, renderData.getMean());
        fields.put(PERCENTILE50, renderData.getPercent50());
        fields.put(PERCENTILE99, renderData.getPercent99());
        return fields;
    }

    @Override
    public boolean isNan() {
        return renderData.isNan();
    }

    @Override
    public void printStatistic(long key) {
        renderData.calculate();
        System.out.print(String.format("%d;%d;%f;%f;%f;%f;%f;\n", key, renderData.getCount(),
                renderData.getMin(), renderData.getMax(),renderData.getMean(), renderData.getPercent50(),
                renderData.getPercent99()));
    }
}
