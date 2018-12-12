package ru.naumen.sd40.log.parser;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

import static ru.naumen.perfhouse.statdata.Constants.TIME;

@Component("top_type")
public class TopDataType implements DataType {
    public static final String AVG_LA = "avgLa";
    public static final String AVG_CPU = "avgCpu";
    public static final String AVG_MEM = "avgMem";
    public static final String MAX_LA = "maxLa";
    public static final String MAX_CPU = "maxCpu";
    public static final String MAX_MEM = "maxMem";

    @Override
    public List<String> getTypeProperties() {
        return Lists.newArrayList(TIME, AVG_LA, AVG_CPU, AVG_MEM, MAX_LA, MAX_CPU, MAX_MEM);
    }

    @Override
    public String getViewName() {
        return "history_top";
    }
}
