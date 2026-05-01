package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.*;
import pro1.reports.report4.reportDataModel.ThesisDuration;

import java.time.temporal.ChronoUnit;
import java.util.*;

public class ThesisDurationReporting {

    public static Object[] GetReport(DataSource dataSource, String katedra, String[] years) {

        Gson gson = new Gson();
        Map<String, List<Long>> durationsByYear = new HashMap<>();

        for (String year : years) {
            durationsByYear.put(year, new ArrayList<>());
        }

        for (String year : years) {
            String json = dataSource.getKvalifikacniPrace(year, katedra);
            ThesisList list = gson.fromJson(json, ThesisList.class);
            if (list == null || list.items == null) continue;

            for (Thesis t : list.items) {
                if (t.startDate == null || t.endDate == null) continue;
                if (!t.startDate.isValid() || !t.endDate.isValid()) continue;

                long days = ChronoUnit.DAYS.between(
                        t.startDate.toLocalDate(),
                        t.endDate.toLocalDate()
                );

                durationsByYear.get(year).add(days);
            }
        }

        List<ThesisDuration> result = new ArrayList<>();

        for (String year : years) {
            List<Long> values = durationsByYear.get(year);
            long avg = 0;
            if (!values.isEmpty()) {
                avg = Math.round(
                        values.stream()
                                .mapToLong(v -> v)
                                .average()
                                .orElse(0)
                );
            }
            result.add(new ThesisDuration(year, avg));
        }

        return result.toArray();
    }
}