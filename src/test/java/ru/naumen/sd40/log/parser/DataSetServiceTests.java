package ru.naumen.sd40.log.parser;

import org.junit.Assert;
import org.junit.Test;

public class DataSetServiceTests {

    @Test
    public void mustReturnOldDataSetWhenOldKey() {
        //given
        IDataSetService dataSetService = new DataSetService(null, null);
        long key = 5;
        DataSet expectedDataSet = dataSetService.get(key);

        //when
        DataSet currentDataSet = dataSetService.get(key);

        //then
        Assert.assertEquals(expectedDataSet, currentDataSet);
    }

    @Test
    public void mustReturnNewDataSetWhenNewKey() {
        //given
        IDataSetService dataSetService = new DataSetService(null, null);
        long key = 5;
        DataSet oldDataSet = dataSetService.get(key);

        //when
        key++;
        DataSet currentDataSet = dataSetService.get(key);

        //then
        Assert.assertNotEquals(oldDataSet, currentDataSet);
    }
}
