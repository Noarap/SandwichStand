package huji.postpc.y2021.noa.ex7;

import java.io.Serializable;
import java.util.UUID;


public class DataSandwichFireStore implements Serializable {
    public String id; //randomly
    public String name; //of the customer
    public int pickles; //between 1 to 10
    public boolean hummus;
    public boolean tahini;
    public String comment;
    public String status; // "waiting", "in-progress", "ready", "done".

    public DataSandwichFireStore(){} //needed to implement when loading elements

    public DataSandwichFireStore(String name, int pickles, boolean hummus, boolean tahini, String comment)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.pickles = pickles;
        this.hummus = hummus;
        this.tahini = tahini;
        this.comment = comment;
        this.status = "Edit";
    }
}

