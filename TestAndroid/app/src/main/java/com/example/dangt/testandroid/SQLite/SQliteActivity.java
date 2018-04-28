package com.example.dangt.testandroid.SQLite;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.dangt.testandroid.R;

public class SQliteActivity extends AppCompatActivity {
    SQliteDB database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        database = new SQliteDB(this,"ghichu.sqlite",null,1);
        database.QueeryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV NVARCHAR(200))");
//        database.QueeryData("Insert Into CongViec Values(null, 'Làm bài tập android')");
//        database.QueeryData("Insert Into CongViec Values(null, 'VIết ứng dụng ghi chứ')");
        Cursor DataCongViec;
        DataCongViec = database.GetData("Select * from CongViec");
        while(DataCongViec.moveToNext()){
            String ten = DataCongViec.getString(1);
            Toast.makeText(this,ten,Toast.LENGTH_SHORT).show();
        }
    }
}
