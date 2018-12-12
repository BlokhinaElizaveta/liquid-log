package ru.naumen.perfhouse.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ru.naumen.perfhouse.statdata.StatData;
import ru.naumen.perfhouse.statdata.StatDataService;
import ru.naumen.sd40.log.parser.DataType;

/**
 * Created by doki on 23.10.16.
 */
@Controller
public class HistoryController {

    @Autowired
    StatDataService service;

    Map<String, DataType> dataTypes;

    private static final String NO_HISTORY_VIEW = "no_history";

    @Autowired
    public HistoryController(Map<String, DataType> dataTypes)
    {
        this.dataTypes = new HashMap<>();
        for (String typeName:dataTypes.keySet()) {
            this.dataTypes.put(typeName.split("_")[0], dataTypes.get(typeName));
        }
    }

    @RequestMapping(path = "/history/{client}/{year}/{month}/{day}")
    public ModelAndView defaultIndexByDay(@PathVariable("client") String client,
                                          @PathVariable(name = "year", required = false) int year,
                                          @PathVariable(name = "month", required = false) int month,
                                          @PathVariable(name = "day", required = false) int day) throws ParseException {
        DataType dataType = dataTypes.get("response");
        return getDataAndViewByDate(client, dataType, year, month, day, dataType.getViewName());
    }

    @RequestMapping(path = "/history/{client}/{typeName}/{year}/{month}/{day}")
    public ModelAndView indexByDay(
            @PathVariable("client") String client,
            @PathVariable(name = "year", required = false) int year,
            @PathVariable(name = "month", required = false) int month,
            @PathVariable(name = "day", required = false) int day,
            @PathVariable("typeName") String typeName) throws ParseException {
        DataType dataType = dataTypes.get(typeName);
        return getDataAndViewByDate(client, dataType, year, month, day, dataType.getViewName());
    }

    @RequestMapping(path = "/history/{client}/{year}/{month}")
    public ModelAndView defaultIndexByMonth(
            @PathVariable("client") String client,
            @PathVariable(name = "year", required = false) int year,
            @PathVariable(name = "month", required = false) int month) throws ParseException {
        DataType dataType = dataTypes.get("response");
        return getDataAndViewByDate(client, dataType, year, month, 0, dataType.getViewName(), true);
    }

    @RequestMapping(path = "/history/{client}/{typeName}/{year}/{month}")
    public ModelAndView indexByMonth(
            @PathVariable("client") String client,
            @PathVariable(name = "year", required = false) int year,
            @PathVariable(name = "month", required = false) int month,
            @PathVariable("typeName") String typeName) throws ParseException {
        DataType dataType = dataTypes.get(typeName);
        return getDataAndViewByDate(client, dataType, year, month, 0, dataType.getViewName(), true);
    }

    @RequestMapping(path = "/history/{client}/custom")
    public ModelAndView defaultCustomIndex(@PathVariable("client") String client, @RequestParam("from") String from,
                                           @RequestParam("to") String to, @RequestParam("maxResults") int maxResults) throws ParseException {
        DataType dataType = dataTypes.get("response");
        return getDataAndViewCustom(client, dataType, from, to, maxResults, dataType.getViewName());
    }

    @RequestMapping(path = "/history/{client}/custom/{typeName}")
    public ModelAndView customIndex(@PathVariable("client") String client, @RequestParam("from") String from,
                                    @RequestParam("to") String to, @RequestParam("maxResults") int count,
                                    @PathVariable("typeName") String typeName) throws ParseException {
        DataType dataType = dataTypes.get(typeName);
        return getDataAndViewCustom(client, dataType, from, to, count, dataType.getViewName());
    }


    @RequestMapping(path = "/history/{client}")
    public ModelAndView defaultIndexLast864(@PathVariable("client") String client,
                                            @RequestParam(name = "count", defaultValue = "864") int count) throws ParseException {
        DataType dataType = dataTypes.get("response");
        ru.naumen.perfhouse.statdata.StatData d = service.getData(client, dataType, count);

        if (d == null) {
            return new ModelAndView(NO_HISTORY_VIEW);
        }

        Map<String, Object> model = new HashMap<>(d.asModel());
        model.put("client", client);
        model.put("dataTypes", new ArrayList<>(dataTypes.keySet()));

        return new ModelAndView("history", model, HttpStatus.OK);
    }

    @RequestMapping(path = "/history/{client}/{typeName}")
    public ModelAndView indexLast864(@PathVariable("client") String client,
                                     @RequestParam(name = "count", defaultValue = "864") int count,
                                     @PathVariable("typeName") String typeName) throws ParseException {
        DataType dataType = dataTypes.get(typeName);
        return getDataAndView(client, dataType, count, dataType.getViewName());
    }

    private ModelAndView getDataAndView(String client, DataType dataType, int count, String viewName)
            throws ParseException {
        ru.naumen.perfhouse.statdata.StatData data = service.getData(client, dataType, count);
        if (data == null) {
            return new ModelAndView(NO_HISTORY_VIEW);
        }
        Map<String, Object> model = new HashMap<>(data.asModel());
        model.put("client", client);
        model.put("dataTypes", new ArrayList<>(dataTypes.keySet()));

        return new ModelAndView(viewName, model, HttpStatus.OK);
    }

    private ModelAndView getDataAndViewByDate(String client, DataType type, int year, int month, int day,
                                              String viewName) throws ParseException {
        return getDataAndViewByDate(client, type, year, month, day, viewName, false);
    }

    private ModelAndView getDataAndViewByDate(String client, DataType type, int year, int month, int day,
                                              String viewName, boolean compress) throws ParseException {
        ru.naumen.perfhouse.statdata.StatData dataDate = service.getDataDate(client, type, year, month, day);
        if (dataDate == null) {
            return new ModelAndView(NO_HISTORY_VIEW);
        }

        dataDate = compress ? service.compress(dataDate, 3 * 60 * 24 / 5) : dataDate;
        Map<String, Object> model = new HashMap<>(dataDate.asModel());
        model.put("client", client);
        model.put("year", year);
        model.put("month", month);
        model.put("day", day);
        model.put("dataTypes", new ArrayList<>(dataTypes.keySet()));
        return new ModelAndView(viewName, model, HttpStatus.OK);
    }

    private ModelAndView getDataAndViewCustom(String client, DataType dataType, String from, String to, int maxResults,
                                              String viewName) throws ParseException {
        StatData data = service.getDataCustom(client, dataType, from, to);
        if (data == null) {
            return new ModelAndView(NO_HISTORY_VIEW);
        }
        data = service.compress(data, maxResults);
        Map<String, Object> model = new HashMap<>(data.asModel());
        model.put("client", client);
        model.put("custom", true);
        model.put("from", from);
        model.put("to", to);
        model.put("maxResults", maxResults);
        model.put("dataTypes", new ArrayList<>(dataTypes.keySet()));
        return new ModelAndView(viewName, model, HttpStatus.OK);
    }
}
