package ru.naumen.sd40.log.parser;

public class SdngDataSet implements DataSet {
    private ActionDoneData actionDoneData = new ActionDoneData();
    private ErrorData errorData = new ErrorData();

    public ActionDoneData getActionsDone()
    {
        return actionDoneData;
    }
    public ErrorData getError()
    {
        return errorData;
    }
}
