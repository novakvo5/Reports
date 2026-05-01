package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {

    @SerializedName("value")
    public String value;

    public boolean isValid() {
        return value != null && !value.isEmpty();
    }

    public LocalDate toLocalDate() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d.M.yyyy");
        return LocalDate.parse(value, fmt);
    }
}