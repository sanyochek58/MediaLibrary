package com.example.homemedialibrary;

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

import com.example.homemedialibrary.API.ApiClient;
import com.example.homemedialibrary.API.ApiService;
import com.example.homemedialibrary.DTO.LoginDTO;
import com.google.android.material.badge.BadgeUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView reg;
    private EditText email;
    private EditText password;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        reg = findViewById(R.id.AUTHORIZATION_TITLE);
        email = findViewById(R.id.INPUT_EMAIL);
        password = findViewById(R.id.INPUT_PASSWORD);
        button = findViewById(R.id.AUTHORIZATION_BUTTON);
        button.setOnClickListener(this::SignIn);
    }

    private void SignIn(View view){
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        Intent intent = new Intent(this,HomeActivity.class);

        if(emailText.isEmpty() || passwordText.isEmpty()){
            Toast.makeText(this,"Введите email и password !",Toast.LENGTH_SHORT).show();
            return;
        }

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(emailText);
        loginDTO.setPassword(passwordText);

        ApiService service = ApiClient.getApiService();
        Call<ResponseBody> call = service.login(loginDTO);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    String message = response.body().toString();
                    Toast.makeText(MainActivity.this, "Успех", Toast.LENGTH_SHORT);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Некорректные данные", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Ошибка сервера",Toast.LENGTH_SHORT).show();
            }
        });
    }
}