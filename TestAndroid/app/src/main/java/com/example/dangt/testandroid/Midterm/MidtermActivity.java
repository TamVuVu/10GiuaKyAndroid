package com.example.dangt.testandroid.Midterm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dangt.testandroid.MenuDrawer;
import com.example.dangt.testandroid.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


public class MidtermActivity extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference mData = database.getReference();

    Button btnAdd;
    Button btnUpdate;
    Button btnCancelUpdate;

    Button btnOK;
    Button btnCancel;
    EditText edtName;
    EditText edtClass;
    EditText edtBirthYear;
    EditText edtId;
    EditText edtUpdateName;
    EditText edtUpdateClass;
    EditText edtUpdateNS;
    EditText edtUpdateId;

    Button btnDel;
    Button btnCancelDel;
    ListView lvSv;
    ArrayList<Student> arraySv;
    MidtermAdapter adapter;

    //    Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
//    Button btnDelete = (Button) findViewById(R.id.btnDelete);
//    ListView lvMidTerm = (ListView) findViewById(R.id.listViewMidterm);
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.midterm_custom_listview);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        lvSv = (ListView) findViewById(R.id.listViewMidterm);
        arraySv = new ArrayList<Student>();
        adapter = new MidtermAdapter(this, R.layout.dong_sinh_vien, arraySv);
        mData.child("Học sinh").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Student sinhVien = dataSnapshot.getValue(Student.class);
                arraySv.add(new Student(sinhVien.getHoten(), sinhVien.getLop(), sinhVien.getNamsinh(), sinhVien.getMssv()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        lvSv.setAdapter(adapter);
//        lvSv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String selectedType=arraySv.get(i).getHoten();
//                AlertDialog dialog=showOptionMenu(selectedType);//<<pass selected index
//                DialodUpdate();
//                return true;
//            }
//        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd();
            }
        });
    }

    public void DialodUpdate(final String ten, final String malop, final Integer namsinh, final String id, final Integer pos) {
        final Dialog dialogupdate = new Dialog(this);
        dialogupdate.setContentView(R.layout.dialog_custom_update);

        btnUpdate = (Button) dialogupdate.findViewById(R.id.btnUpdate);
        btnCancelUpdate = (Button) dialogupdate.findViewById(R.id.btnCancelUpdate);
        edtUpdateClass = (EditText) dialogupdate.findViewById(R.id.edtClassUpdate);
        edtUpdateId = (EditText) dialogupdate.findViewById(R.id.edtIDUpdate);
        edtUpdateName = (EditText) dialogupdate.findViewById(R.id.edtNameUpdate);
        edtUpdateNS = (EditText) dialogupdate.findViewById(R.id.edtBirthYearUpdate);

        edtUpdateName.setText(ten);
        edtUpdateClass.setText(malop);
        edtUpdateNS.setText(Integer.toString(namsinh));
        edtUpdateId.setText(id);
        edtUpdateId.setFocusable(false);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updateName, updateClass, updateId;
                Integer updateYear;
                updateName = edtUpdateName.getText().toString().trim().toUpperCase();
                updateClass = edtUpdateClass.getText().toString().trim().toUpperCase();
                updateId = edtUpdateId.getText().toString().trim().toUpperCase();
                if (updateName.equals("") || updateClass.equals("") || updateId.equals("") || edtUpdateNS.getText().toString().trim().equals("")) {
                    Toast.makeText(MidtermActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Student student = new Student(updateName, updateClass,Integer.parseInt(edtUpdateNS.getText().toString().trim()), updateId);
                    mData.child("Học sinh").child(updateId).setValue(student);

                    arraySv.get(pos).setHoten(updateName);
                    arraySv.get(pos).setLop(updateClass);
                    arraySv.get(pos).setNamsinh(Integer.parseInt(edtUpdateNS.getText().toString().trim()));
                    arraySv.get(pos).setMssv(updateId);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MidtermActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    dialogupdate.dismiss();
                }
            }
        });
        btnCancelUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogupdate.cancel();
            }
        });
        dialogupdate.show();
    }

    public void DialogDelete(final String ms, final int pos) {
        final Dialog dialogDelete = new Dialog(this);
        dialogDelete.setContentView(R.layout.dialog_custom_delete);
        btnDel = (Button) dialogDelete.findViewById(R.id.btnDel);
        btnCancelDel = (Button) dialogDelete.findViewById(R.id.btnCancelDel);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.child("Học sinh").child(ms).removeValue();
                arraySv.remove(pos);
                adapter.notifyDataSetChanged();
                Toast.makeText(MidtermActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                dialogDelete.dismiss();
            }
        });
        btnCancelDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDelete.cancel();
            }
        });
        dialogDelete.show();
    }

    private void DialogAdd() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom_add);
        btnOK = (Button) dialog.findViewById(R.id.btnOK);
        btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

        edtName = (EditText) dialog.findViewById(R.id.edtName);
        edtBirthYear = (EditText) dialog.findViewById(R.id.edtBirthYear);
        edtClass = (EditText) dialog.findViewById(R.id.edtClass);
        edtId = (EditText) dialog.findViewById(R.id.edtID);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten, lop, mssv;
                Integer namsinh;
                ten = edtName.getText().toString().trim().toUpperCase();
                lop = edtClass.getText().toString().trim().toUpperCase();
                mssv = edtId.getText().toString().trim().toUpperCase();
                if (ten.equals("") || lop.equals("") || mssv.equals("") || edtBirthYear.getText().toString().trim().equals("")) {
                    Toast.makeText(MidtermActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Student student = new Student(ten, lop, Integer.parseInt(edtBirthYear.getText().toString().trim()), mssv);
                    mData.child("Học sinh").child(mssv).setValue(student);
                    dialog.dismiss();
                    Toast.makeText(MidtermActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
