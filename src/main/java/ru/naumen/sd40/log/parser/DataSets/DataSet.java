package ru.naumen.sd40.log.parser.DataSets;

import java.util.Map;

public interface DataSet {
    Map<String, Object> getFields();
    boolean isNan();
}
