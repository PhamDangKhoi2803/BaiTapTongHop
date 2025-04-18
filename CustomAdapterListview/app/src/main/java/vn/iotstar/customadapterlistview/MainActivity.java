package vn.iotstar.customadapterlistview;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewMH;
    List<MonHoc> monHocList;
    MonhocAdapter adapter;

    Button buttonAdd,buttonUpdate;
    EditText editTextName;

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

        Anhxa();

        //Tạo Adapter
        adapter = new MonhocAdapter(MainActivity.this,
                R.layout.row_monhoc, monHocList
        );
        //truyền dữ liệu từ adapter ra listview
        listViewMH.setAdapter(adapter);

        //Thêm sự kiên vào listview
    }

    public void Anhxa()
    {
        listViewMH = (ListView) findViewById(R.id.listViewMH);
        monHocList = new ArrayList<MonHoc>();

        //Thêm dữ liệu vào List
        monHocList.add(new MonHoc("Java","Java 1",R.drawable.java));
        monHocList.add(new MonHoc("C#","C# 1",R.drawable.c));
        monHocList.add(new MonHoc("PHP","PHP 1",R.drawable.php));
        monHocList.add(new MonHoc("Kotlin","Kotlin 1",R.drawable.kotlin));
        monHocList.add(new MonHoc("Dart","Dart 1",R.drawable.dart));
    }
}