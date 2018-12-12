package ru.naumen.sd40.log.parser.DataSetFactories;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataSetFactories.DataSetFactory;
import ru.naumen.sd40.log.parser.DataSets.DataSet;
import ru.naumen.sd40.log.parser.DataSets.GCDataSet;

@Component("gcDataSetFactory")
public class GCDataSetFactory implements DataSetFactory {
    @Override
    public DataSet create() {
        return new GCDataSet();
    }
}
