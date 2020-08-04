package sg.edu.rp.c346.round3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import sg.edu.rp.c346.round3.DataClasses.ProfileEntry;

public class RegisterActivity extends AppCompatActivity {
    private EditText etEmail, etPassword, etAge;
    private Button btnRegister;
    private ProgressBar pbRegister;
    private Spinner spnGender, spnPosition;

    FirebaseAuth fbAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.editTextRegisterEmail);
        etPassword = findViewById(R.id.editTextRegisterPW);
        btnRegister = findViewById(R.id.buttonRegister);
        pbRegister = findViewById(R.id.progressBarRegister);
        etAge = findViewById(R.id.editTextRegisterAge);
        spnGender = findViewById(R.id.spinnerGender);
        spnPosition = findViewById(R.id.spinnerPosition);

        pbRegister.setVisibility(View.INVISIBLE);
        fbAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        if(fbAuth.getCurrentUser() != null){
            Intent i =  new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(i);
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                final String strAge = etAge.getText().toString().trim();

                if(email.isEmpty()){
                    etEmail.setError("Email Required");
                    return;
                }
                if(password.isEmpty()){
                    etPassword.setError("Password Required");
                    return;
                }
                if(password.length() < 8){
                    etPassword.setError("Password needs to be 8 characters or more");
                    return;
                }
                if(strAge.isEmpty()){
                    etAge.setError("Age Required");
                    return;
                }

                pbRegister.setVisibility(View.VISIBLE);

                fbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            FirebaseUser user = fbAuth.getCurrentUser();
                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("verification", "Successful");

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("verification", "Unsuccessful: " + e.getMessage());
                                }
                            });

                            String userId = fbAuth.getCurrentUser().getUid();
                            DocumentReference docRef = db.collection("User").document(userId);
                            String gender = spnGender.getSelectedItem().toString();
                            String position = spnPosition.getSelectedItem().toString();
                            ProfileEntry entry = new ProfileEntry(gender, position, Integer.valueOf(strAge), userId);

                            docRef.set(entry).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("Database", "Successful");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("Database", "Unsuccessful: " + e.getMessage());
                                }
                            });

                            Toast.makeText(RegisterActivity.this, "User Created Successfully", Toast.LENGTH_LONG).show();
                            Intent i =  new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(RegisterActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            pbRegister.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }
}