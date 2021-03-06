package ru.naumen.sd40.log.parser.datasets;

import ru.naumen.sd40.log.parser.data.ActionDoneData;
import ru.naumen.sd40.log.parser.data.ErrorData;

import java.util.HashMap;
import java.util.Map;

import static ru.naumen.sd40.log.parser.datatypes.ActionsDataType.*;
import static ru.naumen.sd40.log.parser.datatypes.ResponseDataType.*;

public class SdngDataSet implements DataSet {
    private ActionDoneData actionDoneData = new ActionDoneData();
    private ErrorData errorData = new ErrorData();

    public ActionDoneData getActionsDone() {
        return actionDoneData;
    }

    public ErrorData getError() {
        return errorData;
    }

    @Override
    public Map<String, Object> getFields() {
        Map<String, Object> fields = new HashMap<>();
        fields.put(COUNT, actionDoneData.getCount());
        fields.put(MIN, actionDoneData.getMin());
        fields.put(MEAN, actionDoneData.getMean());
        fields.put(STDDEV, actionDoneData.getStddev());
        fields.put(PERCENTILE50, actionDoneData.getPercent50());
        fields.put(PERCENTILE95, actionDoneData.getPercent95());
        fields.put(PERCENTILE99, actionDoneData.getPercent99());
        fields.put(PERCENTILE999, actionDoneData.getPercent999());
        fields.put(MAX, actionDoneData.getMax());
        fields.put(ERRORS, errorData.getErrorCount());
        fields.put(ADD_ACTIONS, actionDoneData.getAddObjectActions());
        fields.put(EDIT_ACTIONS, actionDoneData.getEditObjectsActions());
        fields.put(LIST_ACTIONS, actionDoneData.getListActions());
        fields.put(COMMENT_ACTIONS, actionDoneData.getCommentActions());
        fields.put(GET_FORM_ACTIONS, actionDoneData.getFormActions());
        fields.put(GET_DT_OBJECT_ACTIONS, actionDoneData.getDtObjectActions());
        fields.put(SEARCH_ACTIONS, actionDoneData.getSearchActions());
        fields.put(GET_CATALOGS_ACTIONS, actionDoneData.getGetCatalogsActions());

        return fields;
    }

    @Override
    public boolean isNan() {
        return actionDoneData.isNan();
    }

    @Override
    public void printStatistic(long key) {
        actionDoneData.calculate();
        System.out.print(String.format("%d;%d;%f;%f;%f;%f;%f;%f;%f;%f;%d\n", key, actionDoneData.getCount(),
                actionDoneData.getMin(), actionDoneData.getMean(), actionDoneData.getStddev(), actionDoneData.getPercent50(), actionDoneData.getPercent95(),
                actionDoneData.getPercent99(), actionDoneData.getPercent999(), actionDoneData.getMax(), errorData.getErrorCount()));
    }
}
