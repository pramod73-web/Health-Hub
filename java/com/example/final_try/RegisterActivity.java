package com.example.final_try;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    EditText rgUsername,rgEmail,rgPassword,rgConfirm;
    Button rgbtn;
    TextView rgtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rgUsername = findViewById(R.id.editTextRegisterUsername);
        rgEmail = findViewById(R.id.editTextRegisterEmailid);
        rgPassword = findViewById(R.id.editTextRegisterPassword);
        rgConfirm = findViewById(R.id.editTextRegisterConfirmPassword);
        rgbtn = findViewById(R.id.buttonRegister);
        rgtv = findViewById(R.id.textViewAlreadyRegister);

        rgtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        rgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = rgUsername.getText().toString();
                String email = rgEmail.getText().toString();
                String password = rgPassword.getText().toString();
                String confirm = rgConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if(username.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0){
                    Toast.makeText(getApplicationContext(), "Fill all details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!isValidEmail(email)) {
                        Toast.makeText(getApplicationContext(), "Invalid email format", Toast.LENGTH_SHORT).show();
                    } else {
                        if (password.compareTo(confirm) == 0) {
                            if (isValid(password)) {
                                if(db.register(username, email, password)){
                                    Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                } else{
                                    Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Password must contains atleast letter, digit and special symbol", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "passwords doesn't match", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if(passwordhere.length()<8){
            return false;
        }else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordhere.length(); s++) {
                char c = passwordhere.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1) {
                return true;
            }
            return false;
        }
    }

        // Method to validate email
        public static boolean isValidEmail(String email) {
            // Regular expression for validating email
            String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

            // Return whether the email matches the pattern
            return email.matches(emailPattern);
        }

        // Example usage
        public static void main(String[] args) {
            String emailInput = "example@domain.com"; // Replace with actual input

            if (isValidEmail(emailInput)) {
                // Proceed with valid email
                System.out.println("Email is valid.");
            } else {
                // Show error message
                System.out.println("Invalid email format.");
            }
        }
}