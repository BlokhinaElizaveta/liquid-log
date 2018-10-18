package ru.naumen.sd40.log.parser;

public class SdgnDataParser implements DataParser {
    @Override
    public void parseLine(String line, DataSet data) {
        data.parseSdgnLine(line);
    }
}
