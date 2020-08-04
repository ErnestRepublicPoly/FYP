package sg.edu.rp.c346.round3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private ProgressBar pbLogin;
    private TextView tvCreateAcc, tvForgetPW;

    FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.editTextLoginEmail);
        etPassword = findViewById(R.id.editTextLoginPW);
        btnLogin = findViewById(R.id.buttonLogin);
        pbLogin = findViewById(R.id.progressBarLogin);
        tvCreateAcc = findViewById(R.id.textViewCreateAccount);
        tvForgetPW = findViewById(R.id.textViewForgetPW);

        pbLogin.setVisibility(View.INVISIBLE);
        fbAuth = FirebaseAuth.getInstance();

        if(fbAuth.getCurrentUser() != null){
            Intent i =  new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(i);
        }

        tvCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        tvForgetPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetEmail = new EditText(v.getContext());
                final AlertDialog.Builder resetDialog = new AlertDialog.Builder(v.getContext());
                resetDialog.setTitle("Reset Password");
                resetDialog.setMessage("Enter Email to reset password");
                resetDialog.setView(resetEmail);

                resetDialog.setPositiveButton("Send Email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String email = resetEmail.getText().toString();

                        if(email.isEmpty()){
                            Toast.makeText(getApplicationContext(), "Email cannot be Empty", Toast.LENGTH_LONG).show();
                            return;
                        }
                        fbAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Link to reset password has been sent to " + email, Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error! Email not sent" + e, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                resetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                resetDialog.create().show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

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

                pbLogin.setVisibility(View.VISIBLE);

                fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                            Intent i =  new Intent(getApplicationContext(), Main2Activity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(LoginActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            pbLogin.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }
}