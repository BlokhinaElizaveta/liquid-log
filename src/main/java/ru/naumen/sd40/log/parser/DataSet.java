package ru.naumen.sd40.log.parser;

import java.util.Map;

public interface DataSet {
    Map<String, Object> getFields();
    boolean isNan();
}
