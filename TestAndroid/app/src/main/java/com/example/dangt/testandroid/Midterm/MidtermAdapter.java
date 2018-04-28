package com.example.dangt.testandroid.Midterm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.icu.text.UnicodeSetSpanner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dangt.testandroid.Midterm.MidtermActivity;
import com.example.dangt.testandroid.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MidtermAdapter extends BaseAdapter {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference mData = database.getReference();
    private MidtermActivity context;
    private int layout;
    private List<Student> studentList;
    Button btnDel;
    Button btnupdate;
    public MidtermAdapter(MidtermActivity context, int layout, List<Student> studentList) {
        this.context = context;
        this.layout = layout;
        this.studentList = studentList;
    }
    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView txtTen = (TextView) view.findViewById(R.id.labelTen);
        TextView txtLop = (TextView) view.findViewById(R.id.labelLop);
        TextView txtId = (TextView) view.findViewById(R.id.labelId);
        TextView txtNs = (TextView) view.findViewById(R.id.labelNS);
        btnDel = (Button) view.findViewById(R.id.btn_delete);


        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ms = studentList.get(i).getMssv();
                context.DialogDelete(ms,i);
                notifyDataSetChanged();
            }
        });
        btnupdate = (Button) view.findViewById(R.id.btn_update);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten,malop,id;
                Integer namsinh;
                ten = studentList.get(i).getHoten().toString();
                malop = studentList.get(i).getLop().toString();
                namsinh = studentList.get(i).getNamsinh();
                id = studentList.get(i).getMssv();
                context.DialodUpdate(ten,malop,namsinh,id, i);
                notifyDataSetChanged();
            }
        });

        Student student = studentList.get(i);
        txtTen.setText(student.getHoten());
        txtLop.setText(student.getLop());
        txtId.setText(student.getMssv());
        txtNs.setText(Integer.toString(student.getNamsinh()));

        return view;
    }
}
