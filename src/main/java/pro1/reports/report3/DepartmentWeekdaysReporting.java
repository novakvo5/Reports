package pro1.reports.report3;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Action;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report3.reportDataModel.DepartmentWeekdays;

import java.util.*;

public class DepartmentWeekdaysReporting {

    public static Object[] GetReport(DataSource dataSource, String rok, String katedra, String[] days) {

        String json = dataSource.getRozvrhByKatedra(rok, katedra);

        Gson gson = new Gson();
        ActionsList actionList = gson.fromJson(json, ActionsList.class);

        List<Action> actions = actionList.items;

        Map<String, Long> counts = new HashMap<>();

        for (String day : days) {
            counts.put(day, 0L);
        }

        for (Action a : actions) {
            if (a.weekdayShort == null) continue;

            if (counts.containsKey(a.weekdayShort)) {
                counts.put(a.weekdayShort,
                        counts.get(a.weekdayShort) + 1);
            }
        }

        List<DepartmentWeekdays> result = new ArrayList<>();

        for (String day : days) {
            result.add(new DepartmentWeekdays(
                    day,
                    counts.getOrDefault(day, 0L)
            ));
        }

        return result.toArray();
    }
}