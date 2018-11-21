package ru.naumen.sd40.log.parser;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by doki on 22.10.16.
 */
@Component
public class ActionDoneParser
{
    private static Set<String> EXCLUDED_ACTIONS = new HashSet<>();

    static
    {
        EXCLUDED_ACTIONS.add("EventAction".toLowerCase());
    }


    Pattern doneRegEx = Pattern.compile("Done\\((\\d+)\\): ?(.*?Action)");


    public void parseLine(String line, SdngDataSet data)
    {
        Matcher matcher = doneRegEx.matcher(line);

        if (matcher.find())
        {
            String actionInLowerCase = matcher.group(2).toLowerCase();
            if (EXCLUDED_ACTIONS.contains(actionInLowerCase))
            {
                return;
            }

            data.getActionsDone().addTimes(Integer.parseInt(matcher.group(1)));
            if (actionInLowerCase.equals("addobjectaction"))
            {
                data.getActionsDone().addObjectActions();
            }
            else if (actionInLowerCase.equals("editobjectaction"))
            {
                data.getActionsDone().addEditObjectsActions();
            }
            else if (actionInLowerCase.equals("getcatalogsaction"))
            {
                data.getActionsDone().addCatalogsActions();
            }
            else if (actionInLowerCase.matches("(?i)[a-zA-Z]+comment[a-zA-Z]+"))
            {
                data.getActionsDone().addCommentActions();
            }
            else if (!actionInLowerCase.contains("advlist")
                    && actionInLowerCase.matches("(?i)^([a-zA-Z]+|Get)[a-zA-Z]+List[a-zA-Z]+"))

            {
                data.getActionsDone().addListActions();
            }
            else if (actionInLowerCase.matches("(?i)^([a-zA-Z]+|Get)[a-zA-Z]+Form[a-zA-Z]+"))
            {
                data.getActionsDone().addFormActions();
            }
            else if (actionInLowerCase.matches("(?i)^([a-zA-Z]+|Get)[a-zA-Z]+DtObject[a-zA-Z]+"))
            {
                data.getActionsDone().addDtObjectActions();
            }
            else if (actionInLowerCase.matches("(?i)[a-zA-Z]+search[a-zA-Z]+"))
            {
                data.getActionsDone().addSearchActions();
            }

        }
    }
}
