package com.example.ribhav.smartsociety.LoginActivities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ribhav.smartsociety.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG ="" ;
    private EditText EmailEdit;
    private EditText PasswordEdit;
    private EditText ConfirmPassword;
    private Button SignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EmailEdit= (EditText) findViewById(R.id.EmailFieldEditText);
        PasswordEdit=(EditText) findViewById(R.id.passwordEditText);
        ConfirmPassword=(EditText) findViewById(R.id.ConfirmPasswordEditText);
        SignUp=(Button) findViewById(R.id.SignupButton);
        mAuth=FirebaseAuth.getInstance();
        SignUp.setOnClickListener(new View.OnClickListener() {
            public static final String TAG = "";

            @Override
            public void onClick(View view) {

                String email=EmailEdit.getText().toString().trim();
                String password=PasswordEdit.getText().toString().trim();
                String ConfirmPass=ConfirmPassword.getText().toString().trim();
                if(password.equals(ConfirmPass))
                {
                    createAccount(email,password);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Try Again",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void createAccount(String email,String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"User Created "+user.getEmail(),Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
