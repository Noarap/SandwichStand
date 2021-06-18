package huji.postpc.y2021.noa.ex7;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

public class MakingOrderActivity extends AppCompatActivity {

    SandwichApp sandwichApp;
    ListenerRegistration dbData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.making_order);

        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        this.sandwichApp = new SandwichApp(this);
        DocumentReference doc = fireStore.collection("orders").document(this.sandwichApp.getIdSp());
        dbData = doc.addSnapshotListener((v, e)->{
           if (v != null && e == null)
           {
               String status = v.getString("status");
               if(status != null)
               {
                   if (status.equals("Ready"))
                   {
                       Intent intent = new Intent(this, ReadyOrderActivity.class);
                       this.startActivity(intent);
                       finish();
                   }
               }
           }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbData.remove();
    }
}
