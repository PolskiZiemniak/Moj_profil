package com.example.mjprofil;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button editProfile;
    private TextView message, emailText, passwordText;
    boolean buttonTemp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editProfile = findViewById(R.id.editProfile);
        message = findViewById(R.id.message);
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);

        message.setText("Witaj! Aplikacja wykonana przez: Jakub Maciaszczyk");

        editProfile.setOnClickListener(v -> {
            if(buttonTemp) {
                changeEmail();
            }else{
                changePassword(emailText.getText().toString());
                buttonTemp = true;
            }
        });
    }

    public void changeEmail(){
        final EditText dialogEditText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Zmień Email");
        builder.setMessage("Podaj nowy email:");
        builder.setView(dialogEditText);
        builder.setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String email = dialogEditText.getText().toString();
                if(email.contains("@")){
                    changePassword(email);
                }else{
                    message.setText("Błąd: Nieprawidłowy format emaila.");
                    message.setTextColor(Color.RED);
                }
            }
        });
        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                message.setText("Edycja profilu anulowana.");
                message.setTextColor(Color.GRAY);
                buttonTemp = false;
            }
        });
        builder.create().show();
    }
    public void changePassword(String email){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        TextView text1 = new TextView(this);
        text1.setText("Podaj nowe hasło:");
        EditText edit1 = new EditText(this);
        edit1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        TextView text2 = new TextView(this);
        text2.setText("Powtórz hasło:");
        EditText edit2 = new EditText(this);
        edit2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        layout.addView(text1);
        layout.addView(edit1);
        layout.addView(text2);
        layout.addView(edit2);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Zmień Hasło");
        builder.setView(layout);
        builder.setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String pass1 = edit1.getText().toString();
                String pass2 = edit2.getText().toString();

                if(pass1.equals(pass2)){
                    emailText.setText(email);
                    passwordText.setText(pass1);
                    message.setText("Profil zaktualizowany! Nowy email: " + email);
                    message.setTextColor(Color.GREEN);
                }else {
                    message.setText("Błąd: Hasła nie pasują do siebie.");
                    message.setTextColor(Color.RED);
                }
            }
        });
        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                message.setText("Edycja profilu anulowana.");
                message.setTextColor(Color.GRAY);
            }
        });
        builder.create().show();
    }
}