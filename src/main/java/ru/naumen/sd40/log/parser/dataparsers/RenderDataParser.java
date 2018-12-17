package ru.naumen.sd40.log.parser.dataparsers;

import org.springframework.stereotype.Component;
import ru.naumen.sd40.log.parser.datasets.RenderDataSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RenderDataParser implements DataParser<RenderDataSet>{

    Pattern renderRegEx = Pattern.compile("render time: (\\d+)");

    @Override
    public void parseLine(String line, RenderDataSet data)
    {
        Matcher matcher = renderRegEx.matcher(line);

        if (matcher.find())
        {
            int renderTime = Integer.parseInt(matcher.group(1));
            data.getRenderData().addRenderTime(renderTime);
        }
    }
}
