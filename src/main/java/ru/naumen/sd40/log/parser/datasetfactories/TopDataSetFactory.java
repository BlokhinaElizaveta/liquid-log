package ru.naumen.sd40.log.parser.datasetfactories;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.datasets.DataSet;
import ru.naumen.sd40.log.parser.datasets.TopDataSet;

@Component("topDataSetFactory")
public class TopDataSetFactory implements DataSetFactory {
    @Override
    public DataSet create() {
        return new TopDataSet();
    }
}
