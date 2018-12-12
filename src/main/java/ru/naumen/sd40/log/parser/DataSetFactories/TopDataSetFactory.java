package ru.naumen.sd40.log.parser.DataSetFactories;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataSetFactories.DataSetFactory;
import ru.naumen.sd40.log.parser.DataSets.DataSet;
import ru.naumen.sd40.log.parser.DataSets.TopDataSet;

@Component("topDataSetFactory")
public class TopDataSetFactory implements DataSetFactory {
    @Override
    public DataSet create() {
        return new TopDataSet();
    }
}
