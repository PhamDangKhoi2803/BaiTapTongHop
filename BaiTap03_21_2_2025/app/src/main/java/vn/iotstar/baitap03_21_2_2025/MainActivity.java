package vn.iotstar.baitap03_21_2_2025;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btn_LinearLayout;
    private Button btn_Login;

    private Button btn_Register;
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

        //1. Random khi làm mới
        // Danh sách hình nền
        int[] backgrounds = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3};

        // Chọn ngẫu nhiên một hình nền
        int randomBackground = backgrounds[new Random().nextInt(backgrounds.length)];

        // Đặt hình nền cho layout chính
        findViewById(R.id.main).setBackgroundResource(randomBackground);

        //2. Random khi nhấn switch
        View rootLayout = findViewById(R.id.main);
        Switch switchTheme = findViewById(R.id.switchTheme);

        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int newBackground = isChecked ? backgrounds[1] : backgrounds[0];
            rootLayout.setBackgroundResource(newBackground);
        });
        //3. Linear Layout
        btn_LinearLayout = findViewById(R.id.button_linearlayout);
        btn_LinearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LinearLayoutActivity.class);
            startActivity(intent);
        });
        //4. Giao diện Login
        btn_Login = findViewById(R.id.button_login);
        btn_Login.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btn_Register = findViewById(R.id.button_register);
        btn_Register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}