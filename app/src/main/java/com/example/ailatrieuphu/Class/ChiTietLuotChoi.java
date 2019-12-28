package com.example.ailatrieuphu.Class;

public class ChiTietLuotChoi {
    public int id;

    public ChiTietLuotChoi(int luot_choi_id, int cau_hoi_id, String phuong_an, int diem) {
        this.luot_choi_id = luot_choi_id;
        this.cau_hoi_id = cau_hoi_id;
        this.phuong_an = phuong_an;
        this.diem = diem;
    }
    public ChiTietLuotChoi( int cau_hoi_id, String phuong_an, int diem) {
        this.cau_hoi_id = cau_hoi_id;
        this.phuong_an = phuong_an;
        this.diem = diem;
    }

    public int luot_choi_id;
    public int cau_hoi_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLuot_choi_id() {
        return luot_choi_id;
    }

    public void setLuot_choi_id(int luot_choi_id) {
        this.luot_choi_id = luot_choi_id;
    }

    public int getCau_hoi_id() {
        return cau_hoi_id;
    }

    public void setCau_hoi_id(int cau_hoi_id) {
        this.cau_hoi_id = cau_hoi_id;
    }

    public String getPhuong_an() {
        return phuong_an;
    }

    public void setPhuong_an(String phuong_an) {
        this.phuong_an = phuong_an;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String phuong_an;
    public int diem;
}
