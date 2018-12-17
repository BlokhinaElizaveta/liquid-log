package ru.naumen.sd40.log.parser.datatypes;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

import static ru.naumen.perfhouse.statdata.Constants.TIME;

@Component
public class RenderDataType implements DataType {
    public static final String PERCENTILE50 = "percent50";
    public static final String PERCENTILE99 = "percent99";
    public static final String MAX = "max";
    public static final String MIN = "min";
    public static final String COUNT = "count";
    public static final String MEAN = "mean";

    @Override
    public List<String> getTypeProperties() {
        return Lists.newArrayList(TIME, COUNT, MEAN, PERCENTILE50, PERCENTILE99, MIN, MAX);
    }

    @Override
    public String getViewName() {
        return "render_history";
    }

    @Override
    public String getModeName() {
        return "render";
    }
}
