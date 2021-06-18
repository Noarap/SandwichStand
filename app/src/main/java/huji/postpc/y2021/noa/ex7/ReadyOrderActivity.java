package huji.postpc.y2021.noa.ex7;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ReadyOrderActivity extends AppCompatActivity {
    SandwichApp sandwichApp;
    Button finishButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready_order);

        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

        this.finishButton = findViewById(R.id.buttonFin);
        this.sandwichApp = new SandwichApp(this);

        this.finishButton.setOnClickListener(v ->{
            DocumentReference doc = fireStore.collection("orders").document(sandwichApp.getIdSp());
            doc.update("status", "done");
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            finish();
        });
    }
}
