package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Exam {

    @SerializedName("obsazeni")
    public String occupied;

    @SerializedName("mistnost")
    public String room;
}