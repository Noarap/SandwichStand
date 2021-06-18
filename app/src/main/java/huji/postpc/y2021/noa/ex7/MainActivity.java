package huji.postpc.y2021.noa.ex7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    String idFromSP;

    public void sendToActivity(String status)
    {
        switch (status) {
            case "Edit":
                startActivity(new Intent(this, EditOrderActivity.class));
                finish();
                break;
            case "Making":
                startActivity(new Intent(this, MakingOrderActivity.class));
                finish();
                break;
            case "Ready":
                startActivity(new Intent(this, ReadyOrderActivity.class));
                finish();
                break;
            default:
                createNew();
        }
    }
    public void createNew()
    {
        this.startActivity(new Intent(this, NewOrderActivity.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        SandwichApp sandwichApp = new SandwichApp(this);
        this.idFromSP = sandwichApp.getIdSp();

        if (this.idFromSP.equals("")) {
            createNew();
        }

        else{
            fireStore.collection("orders").document(this.idFromSP).get().addOnSuccessListener(result -> {// result has the information of the collection orders
                String status = result.getString("status");
                if(status == null)
                {
                    createNew();
                }
                else
                {
                    sendToActivity(status);
                }
            });
        }
    }
}
