package ru.naumen.sd40.log.parser;

public class SdngDataParser implements DataParser {
    @Override
    public void parseLine(String line, DataSet data) {
        data.parseSdgnLine(line);
    }
}
