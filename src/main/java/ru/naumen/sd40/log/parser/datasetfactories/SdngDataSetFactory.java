package ru.naumen.sd40.log.parser.datasetfactories;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.datasets.DataSet;
import ru.naumen.sd40.log.parser.datasets.SdngDataSet;

@Component("sdngDataSetFactory")
public class SdngDataSetFactory implements DataSetFactory {
    @Override
    public DataSet create() {
        return new SdngDataSet();
    }
}
