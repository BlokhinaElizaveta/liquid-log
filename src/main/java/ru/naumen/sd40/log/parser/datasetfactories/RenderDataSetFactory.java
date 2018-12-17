package ru.naumen.sd40.log.parser.datasetfactories;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.datasets.DataSet;
import ru.naumen.sd40.log.parser.datasets.RenderDataSet;

@Component
public class RenderDataSetFactory implements DataSetFactory {
    @Override
    public DataSet create() {
        return new RenderDataSet();
    }
}
