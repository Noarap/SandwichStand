package huji.postpc.y2021.noa.ex7;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("huji.postpc.y2021.noa.ex7", appContext.getPackageName());
    }

    @Test
    public void whenEnteringNameItSavesToDB() {
        DataSandwichFireStore data = new DataSandwichFireStore("Noa", 2, false, true, "");
        String testId = data.id;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(context);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("orders").document(testId).get().addOnSuccessListener(result -> {
            assertEquals("Noa", result.getString("name"));
        }).addOnFailureListener(documentSnapshot -> {
            fail();
        });
    }

    @Test
    public void whenEnteringPicklesItSavesToDB() {
        DataSandwichFireStore data = new DataSandwichFireStore("Noa", 2, false, true, "");
        String testId = data.id;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(context);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("orders").document(testId).get().addOnSuccessListener(result -> {
            assertEquals(2, result.get("pickles"));
        }).addOnFailureListener(documentSnapshot -> {
            fail();
        });
    }

    @Test
    public void whenEnteringCommentItSavesToDB() {
        DataSandwichFireStore data = new DataSandwichFireStore("Noa", 2, false, true, "HI");
        String testId = data.id;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(context);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("orders").document(testId).get().addOnSuccessListener(result -> {
            assertEquals("HI", result.get("comment"));
        }).addOnFailureListener(documentSnapshot -> {
            fail();
        });
    }

    @Test
    public void whenNotEnteringHummusItSavesToDB() {
        DataSandwichFireStore data = new DataSandwichFireStore("Noa", 2, false, true, "HI");
        String testId = data.id;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(context);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("orders").document(testId).get().addOnSuccessListener(result -> {
            assertEquals(false, result.get("hummus"));
        }).addOnFailureListener(documentSnapshot -> {
            fail();
        });
    }

    @Test
    public void whenEnteringTahiniItSavesToDB() {
        DataSandwichFireStore data = new DataSandwichFireStore("Noa", 2, false, true, "HI");
        String testId = data.id;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(context);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("orders").document(testId).get().addOnSuccessListener(result -> {
            assertEquals(true, result.get("tahini"));
        }).addOnFailureListener(documentSnapshot -> {
            fail();
        });
    }

}