package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Thesis {

    @SerializedName("katedra")
    public String department;

    @SerializedName("datumZadani")
    public Date startDate;

    @SerializedName("datumOdevzdani")
    public Date endDate;
}