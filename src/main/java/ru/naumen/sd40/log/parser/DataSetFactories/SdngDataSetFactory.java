package ru.naumen.sd40.log.parser.DataSetFactories;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.DataSetFactories.DataSetFactory;
import ru.naumen.sd40.log.parser.DataSets.DataSet;
import ru.naumen.sd40.log.parser.DataSets.SdngDataSet;

@Component("sdngDataSetFactory")
public class SdngDataSetFactory implements DataSetFactory {
    @Override
    public DataSet create() {
        return new SdngDataSet();
    }
}
