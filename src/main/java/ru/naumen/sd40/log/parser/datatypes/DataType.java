package ru.naumen.sd40.log.parser.datatypes;

import java.util.List;

public interface DataType {
    List<String> getTypeProperties();
    String getViewName();
    String getModeName();
}
