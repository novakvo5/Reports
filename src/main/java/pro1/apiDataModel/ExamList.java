package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExamList {

    @SerializedName("termin")
    public List<Exam> items;
}