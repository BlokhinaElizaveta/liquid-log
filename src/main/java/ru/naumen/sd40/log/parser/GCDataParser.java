package ru.naumen.sd40.log.parser;

public class GCDataParser implements DataParser {
    @Override
    public void parseLine(String line, DataSet data) {
        data.parseGcLine(line);
    }
}
