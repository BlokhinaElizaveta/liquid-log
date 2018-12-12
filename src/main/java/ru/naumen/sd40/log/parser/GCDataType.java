package ru.naumen.sd40.log.parser;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

import static ru.naumen.perfhouse.statdata.Constants.TIME;

@Component("gc_type")
public class GCDataType implements DataType {
    public static final String GCTIMES = "gcTimes";
    public static final String AVARAGE_GC_TIME = "avgGcTime";
    public static final String MAX_GC_TIME = "maxGcTime";

    @Override
    public List<String> getTypeProperties() {
        return Lists.newArrayList(TIME, GCTIMES, AVARAGE_GC_TIME, MAX_GC_TIME);
    }

    @Override
    public String getViewName() {
        return "gc_history";
    }
}
