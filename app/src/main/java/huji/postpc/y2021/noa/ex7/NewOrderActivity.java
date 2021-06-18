package huji.postpc.y2021.noa.ex7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewOrderActivity extends AppCompatActivity {
    EditText name; //of the customer
    SeekBar pickles; //between 1 to 10
    CheckBox hummus;
    CheckBox tahini;
    EditText comment;
    Button createOrderButton;
    SandwichApp sandwichApp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
        setContentView(R.layout.new_order);
        //find all views
        this.name = findViewById(R.id.name);
        this.pickles = findViewById(R.id.seekPickles);
        this.hummus = findViewById(R.id.hummusCheckbox);
        this.tahini = findViewById(R.id.tahiniCheckbox);
        this.comment = findViewById(R.id.comment);
        this.createOrderButton = findViewById(R.id.button);

        //init
        this.sandwichApp = new SandwichApp(this);

        createOrderButton.setOnClickListener(v->{
            String textName = name.getText().toString();
            int pickleNum = pickles.getProgress();
            boolean checkHummus = hummus.isChecked();
            boolean checkTahini = tahini.isChecked();
            String commentText = comment.getText().toString();
            DataSandwichFireStore newSandwich = new DataSandwichFireStore(textName, pickleNum, checkHummus, checkTahini, commentText);
            CollectionReference ord = fireStore.collection("orders");
            DocumentReference doc = ord.document(newSandwich.id);
            doc.set(newSandwich);
            this.sandwichApp.saveData(newSandwich.id);
            Intent intent = new Intent(this, EditOrderActivity.class);
            intent.putExtra("text", newSandwich);
            this.startActivity(intent);
            finish();
        });
    }
}
