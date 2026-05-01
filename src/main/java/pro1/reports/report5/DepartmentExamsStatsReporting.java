package pro1.reports.report5;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Exam;
import pro1.apiDataModel.ExamList;
import pro1.reports.report5.reportDataModel.DepartmentExamsStats;

import java.util.*;

public class DepartmentExamsStatsReporting {

    public static DepartmentExamsStats GetReport(DataSource dataSource, String katedra) {

        String json = dataSource.getTerminyZkousek2(katedra);

        Gson gson = new Gson();
        ExamList list = gson.fromJson(json, ExamList.class);

        long realizedCount = 0;

        Set<String> rooms = new TreeSet<>();

        for (Exam e : list.items) {

            if (e.occupied != null) {
                try {
                    if (Integer.parseInt(e.occupied) > 0) {
                        realizedCount++;
                    }
                } catch (NumberFormatException ignored) {}
            }

            if (e.room != null && !e.room.isEmpty()) {
                rooms.add(e.room);
            }
        }

        return new DepartmentExamsStats(realizedCount, new ArrayList<>(rooms));
    }
}