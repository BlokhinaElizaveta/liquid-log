package ru.naumen.sd40.log.parser;

public class ErrorData {
    long warnCount;
    long errorCount;
    long fatalCount;

    public long getWarnCount()
    {
        return warnCount;
    }

    public long getErrorCount()
    {
        return errorCount;
    }

    public long getFatalCount()
    {
        return fatalCount;
    }

    public void addWarnCount()
    {
        warnCount++;
    }

    public void addErrorCount() { errorCount++; }

    public void addFatalCount()
    {
        fatalCount++;
    }


}
