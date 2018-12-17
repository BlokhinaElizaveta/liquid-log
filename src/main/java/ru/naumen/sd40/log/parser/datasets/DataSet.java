package ru.naumen.sd40.log.parser.datasets;

import java.util.Map;

public interface DataSet {
    Map<String, Object> getFields();
    boolean isNan();
    void printStatistic(long key);
}
