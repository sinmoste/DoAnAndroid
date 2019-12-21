package com.example.ailatrieuphu.Class;

public class LinhVuc {
    public int Id;
    public String Ten;
    public int TrangThai;

    public LinhVuc() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public LinhVuc(int id, String ten, int trangThai) {
        Id = id;
        Ten = ten;
        TrangThai = trangThai;
    }
}
