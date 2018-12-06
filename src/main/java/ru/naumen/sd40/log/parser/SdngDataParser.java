package ru.naumen.sd40.log.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sdngDataParser")
public class SdngDataParser implements DataParser<SdngDataSet> {

    private ErrorParser errorsParser;
    private ActionDoneParser actionsDoneParser;

    @Autowired
    public SdngDataParser(ErrorParser errorsParser, ActionDoneParser actionsDoneParser)
    {
        this.errorsParser = errorsParser;
        this.actionsDoneParser = actionsDoneParser;
    }

    @Override
    public void parseLine(String line, SdngDataSet data) {
        errorsParser.parseLine(line, data);
        actionsDoneParser.parseLine(line, data);
    }
}
