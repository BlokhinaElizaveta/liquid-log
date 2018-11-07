package ru.naumen.perfhouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.naumen.sd40.log.parser.LogParser;

import java.io.IOException;
import java.text.ParseException;

@Controller
public class LogParserController {

    private LogParser logParser;

    @Autowired
    public LogParserController(LogParser logParser)
    {
        this.logParser = logParser;
    }


    @RequestMapping(path = "/parse", method = RequestMethod.POST)
    public void Parse(@RequestParam(name = "db") String db,
                      @RequestParam(name = "mode") String mode,
                      @RequestParam(name = "path") String path,
                      @RequestParam(name = "timezone") String timeZone,
                      @RequestParam(name = "trace") String trace) throws IOException, ParseException {
        boolean withTrace = trace.equals("true");
        logParser.parse(path, mode, db, timeZone, withTrace);
    }
}
