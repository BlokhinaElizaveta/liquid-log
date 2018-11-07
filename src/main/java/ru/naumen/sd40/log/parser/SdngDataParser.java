package ru.naumen.sd40.log.parser;

public class SdngDataParser implements DataParser {

    private ErrorParser errorsParser;
    private ActionDoneParser actionsDoneParser;

    public SdngDataParser(ErrorParser errorsParser, ActionDoneParser actionsDoneParser)
    {
        this.errorsParser = errorsParser;
        this.actionsDoneParser = actionsDoneParser;
    }

    @Override
    public void parseLine(String line, DataSet data) {
        errorsParser.parseLine(line, data);
        actionsDoneParser.parseLine(line, data);
    }
}
