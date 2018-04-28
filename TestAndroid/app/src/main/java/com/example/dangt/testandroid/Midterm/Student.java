package com.example.dangt.testandroid.Midterm;

public class Student {
    private String Hoten;
    private String Lop;
    private int Namsinh;
    private String Mssv;
    public Student() {
    }
    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String Lop) {
        this.Lop = Lop;
    }

    public int getNamsinh() {
        return Namsinh;
    }

    public void setNamsinh(int Namsinh) {
        this.Namsinh = Namsinh;
    }

    public String getMssv() {
        return Mssv;
    }

    public void setMssv(String Mssv) {
        this.Mssv = Mssv;
    }

    public Student(String hoten, String lop, int namsinh, String mssv) {
        Hoten = hoten;
        Lop = lop;
        Namsinh = namsinh;
        Mssv = mssv;
    }
}
