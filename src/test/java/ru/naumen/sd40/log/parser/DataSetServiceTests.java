package ru.naumen.sd40.log.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DataSetServiceTests {

    private DBWriter writer;

    @Before
    public void Setup(){
        writer = mock(InfluxDBWriter.class);
    }

    @Test
    public void mustReturnOldDataSetWhenOldKey() {
        //given
        IDataSetService dataSetService = new DataSetService(writer);
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
        IDataSetService dataSetService = new DataSetService(writer);
        long key = 5;
        DataSet oldDataSet = dataSetService.get(key);

        //when
        key++;
        DataSet currentDataSet = dataSetService.get(key);

        //then
        Assert.assertNotEquals(oldDataSet, currentDataSet);
    }

    @Test
    public void mustWriteToDBWhenDifferentKeys() {
        //given
        IDataSetService dataSetService = new DataSetService(writer);

        //when
        DataSet oldDataSet = dataSetService.get(5);
        DataSet currentDataSet = dataSetService.get(7);
        dataSetService.close();

        //then
        verify(writer, times(1)).write(5, oldDataSet);
        verify(writer, times(1)).write(7, currentDataSet);
    }

    @Test
    public void mustWriteToDBWhenEqualsKeys() {
        //given
        IDataSetService dataSetService = new DataSetService(writer);

        //when
        DataSet oldDataSet = dataSetService.get(5);
        dataSetService.get(5);
        dataSetService.close();

        //then
        verify(writer, times(1)).write(5, oldDataSet);
    }
}
