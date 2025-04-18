package vn.iotstar.customadapterrecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvSongs;
    private SongAdapter mSongAdapter;
    private List<SongModel> mSongs;

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

        rvSongs = (RecyclerView)findViewById(R.id.rv_songs);

        // Create song data
        mSongs = new ArrayList<>();
        mSongs.add(new SongModel("60696", "NEU EM CÒN TỔN TẠI", "Khi anh bat đầu 1 tinh yêu Là lúc anh tự thay", "Trinh Đinh Quang"));
        mSongs.add(new SongModel("60701", "NGOC", "Co rat nhieu nhung cau chuyen Em dau rieng minh em biet", "Khac Viet"));
        mSongs.add(new SongModel("60650", "HAY TIN ANH LAN NUA", "Dau cho ta da sai khi o ben nhau Co yeu thương", "Thien Dung"));
        mSongs.add(new SongModel("60610", "CHUOI NGAY VANG EM", "To khi em buoc ra di coi long anh ngap trang bao","Duy Cuong"));
        mSongs.add(new SongModel("60656", "KHI NGUỜI MINH YEU KHOC", "Nước mat em đang roi tren những ngon tay Nước mat em", "Pham Manh Quynh"));
        mSongs.add(new SongModel("60685", "MO", "Anh mơ gặp em anh mơ được om anh mơ được gần", "Trinh Thang Binh"));
        mSongs.add(new SongModel("60752", "TINH YEU CHAP VA", "Muon di xa noi yeu thuong minh tng co De khong nghe", "Mr. Siro"));
        mSongs.add(new SongModel("60608", "CHO NGAY MUA TAN", "1 ngay mua va em khuat xa noi anh bong dang cứ", "Trung Đức"));
        mSongs.add(new SongModel("60603", "CAU HOI EM CHUA TRA LOI", "Cần nơi em 1 lời giai thich that lòng Dừng lặng im", "Yuki Huy Nam"));
        mSongs.add(new SongModel("60720", "QUA DI LẶNG LỄ", "Đôi khi đến với nhau yêu thương chẳng được lâu nhung khi", "Phan Manh Quỳnh"));
        mSongs.add(new SongModel("60856", "QUEN ANH LA DIEU EM KHONG THE - REMIX", "Can them bao lau de em quen di niem dau Can them","Thien Ngon"));

        mSongAdapter = new SongAdapter(this, mSongs);
        rvSongs.setAdapter(mSongAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this, LinearLayoutManager. VERTICAL, false);
        rvSongs.setLayoutManager(linearLayoutManager);
    }
}