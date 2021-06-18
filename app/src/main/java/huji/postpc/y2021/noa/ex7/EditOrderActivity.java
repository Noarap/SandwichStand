package huji.postpc.y2021.noa.ex7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

public class EditOrderActivity extends AppCompatActivity
{
    EditText name;
    SeekBar pickles;
    CheckBox hummus;
    CheckBox tahini;
    EditText comment;
    Button saveButton;
    Button delButton;
    SandwichApp sandwichApp;
    DataSandwichFireStore data;
    ListenerRegistration dbData;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_order);
        FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

        //find all views
        this.name = findViewById(R.id.name);
        this.pickles = findViewById(R.id.seekPickles);
        this.hummus = findViewById(R.id.hummusCheckbox);
        this.tahini = findViewById(R.id.tahiniCheckbox);
        this.comment = findViewById(R.id.comment);
        this.saveButton = findViewById(R.id.buttonSave);
        this.delButton = findViewById(R.id.buttonDel);

        //init
        this.sandwichApp = new SandwichApp(this);
        this.sandwichApp.loadData();
        this.id = this.sandwichApp.getIdSp();

        DocumentReference doc = fireStore.collection("orders").document(this.id);
        doc.get().addOnSuccessListener(result->{
            this.data = result.toObject(DataSandwichFireStore.class);
            assert this.data != null;
            this.name.setText(this.data.name);
            this.pickles.setProgress(this.data.pickles);
            this.hummus.setChecked(this.data.hummus);
            this.tahini.setChecked(this.data.tahini);
            this.comment.setText(this.data.comment);
        });

        this.dbData = doc.addSnapshotListener((v, e)->{
           if(v != null && e == null)
           {
               String status = v.getString("status");
               if (status != null)
               {
                    switch (status)
                    {
                        case "Making":
                            Intent intentMaking = new Intent(this, MakingOrderActivity.class);
                            this.startActivity(intentMaking);
                            finish();
                            break;

                        case "Ready":
                            Intent intentReady = new Intent(this, ReadyOrderActivity.class);
                            this.startActivity(intentReady);
                            finish();
                            break;
                    }
               }
           }
        });

        saveButton.setOnClickListener(v->{
            doc.update("name", this.name.getText().toString());
            doc.update("pickles", this.pickles.getProgress());
            doc.update("hummus", this.hummus.isChecked());
            doc.update("tahini", this.tahini.isChecked());
            doc.update("comment", this.comment.getText().toString());

            Toast toast = Toast.makeText(this,"Changes Saved Successfully!", Toast.LENGTH_LONG);
            toast.show();
        });

        delButton.setOnClickListener(v->{
            doc.delete();
            this.sandwichApp.saveData("");
            Toast toast = Toast.makeText(this,"Order Deleted", Toast.LENGTH_LONG);
            toast.show();
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbData.remove();
    }
}
