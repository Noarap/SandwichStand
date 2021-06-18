package huji.postpc.y2021.noa.ex7;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SandwichApp extends Application {

    private String idSp;
//    public String idSp;
    SharedPreferences sp;

    public SandwichApp(Context context)
    {
        this.idSp = "";
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        this.idSp = sp.getString("text", "");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public String getIdSp() {return this.idSp;}

    public void saveData(String newId)
    {
        this.idSp = newId;
        sp.edit().putString("text", this.idSp).apply();
    }

    public void loadData()
    {
        this.idSp = sp.getString("text", "");
    }
}


