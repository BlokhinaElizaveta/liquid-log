package ru.naumen.sd40.log.parser.dataparsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.datasets.SdngDataSet;

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
