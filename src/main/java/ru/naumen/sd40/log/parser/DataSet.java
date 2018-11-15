package ru.naumen.sd40.log.parser;

/**
 * Created by doki on 22.10.16.
 */
public class DataSet
{
    private TopData topData = new TopData();
    private ActionDoneData actionDoneData = new ActionDoneData();
    private ErrorData errorData = new ErrorData();
    private GCData gcData = new GCData();


    public ActionDoneData getActionsDone()
    {
        return actionDoneData;
    }

    public ErrorData getError()
    {
        return errorData;
    }

    public GCData getGcData() {
        return gcData;
    }

    public TopData topData()
    {
        return topData;
    }
}
