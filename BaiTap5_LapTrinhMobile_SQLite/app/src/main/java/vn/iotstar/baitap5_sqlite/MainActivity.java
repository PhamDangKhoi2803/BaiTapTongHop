package vn.iotstar.baitap5_sqlite;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import vn.iotstar.baitap5_sqlite.Adapter.NotesAdapter;
import vn.iotstar.baitap5_sqlite.DatabaseConfigs.DatabaseHandler;
import vn.iotstar.baitap5_sqlite.Models.NotesModel;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NotesAdapter adapter;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //anh xa listview gọi adapter
        listView = (ListView) findViewById(R.id.listview1);
        arrayList = new ArrayList<>();
        adapter = new NotesAdapter(this, R.layout.note_item,arrayList);
        listView.setAdapter(adapter);

        //goi ham databaseSQLite
        InitDatabseSQLite();
        //createDatabaseSQLite
        databaseSQLite();
    }

    //setting toolbar
    // Tạo menu trong Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // Xử lý sự kiện click vào menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuAddNotes){
            DialogThem();
        }
        return true;
        //return super.onOptionsItemSelected(item);
    }


    //CRUD
    //Them notes
    private void DialogThem()
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_note);

        //Anh xa trong dialog
        EditText editText = (EditText) dialog.findViewById(R.id.editTextNameNote);
        Button addBtn = (Button) dialog.findViewById(R.id.buttonAdd);
        Button cancelBtn = (Button) dialog.findViewById(R.id.buttonCancel);

        //Bat su kien cho nut add
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString().trim();
                if(name.equals("")){
                    Toast.makeText(MainActivity.this, "Vui long nhap ten note!", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseHandler.QueryData("Insert into Notes Values(null, '"+name+"')");
                    Toast.makeText(MainActivity.this,"Da them note", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    databaseSQLite();
                }
            }
        });

        //Bat sư kien cho nut huy
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //Dialog update
    public void DialogUpdateTheme(String name, int id){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_note);

        //Anh xạ
        EditText editText = dialog.findViewById(R.id.editTextNameNote);
        Button cancleBtn = dialog.findViewById(R.id.buttonCancel);
        Button updateBtn = dialog.findViewById(R.id.buttonUpdate);
        editText.setText(name);

        //Bat su kien edit
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(name.trim().equals("")){
                        Toast.makeText(MainActivity.this, "Vui long nhap ten", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String query = "Update Notes Set NameNotes = '"+editText.getText().toString().trim() +"' Where Id = " +id;
                        databaseHandler.QueryData(query);
                        Toast.makeText(MainActivity.this, "Da thay doi thanh cong", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        databaseSQLite();
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
        });

        //Bat sư kien huy
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //Dialog delete
    public void DialogDeleteTheme(String name, final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa Notes "+ name + " này không ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String query = "Delete From Notes Where Id = "+id;
                databaseHandler.QueryData(query);
                Toast.makeText(MainActivity.this, "Da xoa thanh cong", Toast.LENGTH_SHORT).show();
                databaseSQLite();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    //Tạo database, tạo bảng và nhập dữ liệu
    private void createDatabaseSQLite()
    {
        //them du lieu
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, 'Vi du SQLite1')");
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, 'Vi du SQLite2')");
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, 'Vi du SQLite3')");
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, 'Vi du SQLite4')");
    }

    private void InitDatabseSQLite()
    {
        //khoi tao databse
        databaseHandler = new DatabaseHandler(this,"notes.sqlite",null,1);
        //tao bang Notes
        databaseHandler.QueryData("CREATE TABLE IF NOT EXISTS Notes (Id INTEGER PRIMARY KEY AUTOINCREMENT, NameNotes VARCHAR(200))");
    }
    private void databaseSQLite()
    {
        arrayList.clear();
        //lay du lieu
        Cursor cursor = databaseHandler.GetData("SELECT * FROM Notes");
        while (cursor.moveToNext()){
            //them du lieu vao arraylist
            String name = cursor.getString(1);
            int id = cursor.getInt(0);
            arrayList.add(new NotesModel(id,name));
            //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }
}