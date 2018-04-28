//package com.example.dangt.testandroid;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import org.w3c.dom.Text;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class MainActivity extends AppCompatActivity {
//    TextView txtLogin;
//    String itemdrawer[] = {"Hình ảnh","Phim"};
//    int itemHinhAnh[] = {R.drawable.crycat,R.drawable.fatcat};
//    List<MenuDrawer> listDrawer;
//    ListView lvMenuDrawer;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        final TextView txtKhoahoc = (TextView) findViewById(R.id.textViewKhoahoc);
//        Button btnAndroid = (Button) findViewById(R.id.btnAndroid);
//        Button btnIos = (Button) findViewById(R.id.btnIos);
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference mData;
//        DatabaseReference myRef;
//        myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");
//
//        mData = database.getReference();
//
//        mData.child("Ho ten").setValue("Gao đỏ");
//        SinhVien sv = new SinhVien("Gao gao","SG",1996);
//        mData.child("Sinh Vien").setValue(sv);
//
//        Map<String, Integer> myMap = new HashMap<String, Integer>();
//        myMap.put("Xe may", 2);
//        mData.child("Phương Tiện").setValue(myMap);
//
//        SinhVien sinhvien = new SinhVien("Haha","Hà Lội ih", 2000);
//        mData.child("Học Viên").push().setValue(sinhvien);
//
////        mData.child("KhoaPhamTraining").setValue("Android Programing", new DatabaseReference.CompletionListener() {
////            @Override
////            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
////                if(databaseError == null){
////                    Toast.makeText(MainActivity.this,"Lưu thành công", Toast.LENGTH_SHORT).show();
////                }
////                else{
////                    Toast.makeText(MainActivity.this, databaseError.toString(),Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
////        mData.child("Khóa học").push().setValue("Lập trình PHP");
//        mData.child("PhuongTien").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                PhuongTien ptien = dataSnapshot.getValue(PhuongTien.class);
//                Toast.makeText(MainActivity.this,ptien.Ten.toString(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        PhuongTien phuongTien = new PhuongTien("Xe tải",8);
////        mData.child("PhuongTien").push().setValue(phuongTien);
////        mData.child("Khóa học").addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                txtKhoahoc.setText(dataSnapshot.getValue().toString());
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
//        btnAndroid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mData.child("Khóa học").setValue("Android");
//            }
//        });
//        btnIos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mData.child("Khóa học").setValue("Ios");
//            }
//        });
//        txtLogin = (TextView) findViewById(R.id.textviewLogin);
//        txtLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogLogin();
//            }
//        });
//
//        listDrawer = new ArrayList<MenuDrawer>();
//        for(int i=0; i<itemdrawer.length;i++){
//            MenuDrawer item = new MenuDrawer();
//            item.setHinhAnh(itemHinhAnh[i]);
//            item.setTenMenu(itemdrawer[i]);
//            listDrawer.add(item);
//        }
//
//        lvMenuDrawer = (ListView) findViewById(R.id.lvMenuDrawer);
//        AdapterListviewDrawer adapter = new AdapterListviewDrawer(this, R.layout.custom_layout_listview_drawer, listDrawer);
//        lvMenuDrawer.setAdapter(adapter);
//
//        lvMenuDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                switch (position){
//                    case 0:
//                        Toast.makeText(getApplicationContext(),"Bạn vừa chọn hình ảnh",Toast.LENGTH_LONG).show();break;
//                    case 1:
//                        Toast.makeText(getApplicationContext(),"Bạn vừa chọn phim ảnh",Toast.LENGTH_LONG).show();break;
//                }
//            }
//        });
//    }
//    private  void DialogLogin(){
//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.dialog_custom);
//        dialog.show();
//    }
//}
