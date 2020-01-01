package com.example.ailatrieuphu;

public class CreDit {
    String id;
    String ten_goi_credit;
    int gia_credit;
    int gia_tien_credit;

    public int getXoa() {
        return xoa;
    }

    public void setXoa(int xoa) {
        this.xoa = xoa;
    }

    public CreDit() {
        this.id = id;
        this.ten_goi_credit = ten_goi_credit;
        this.gia_credit = gia_credit;
        this.gia_tien_credit = gia_tien_credit;
        this.xoa = xoa;
    }

    int xoa;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen_goi_credit() {
        return ten_goi_credit;
    }

    public void setTen_goi_credit(String ten_goi_credit) {
        this.ten_goi_credit = ten_goi_credit;
    }

    public int getGia_credit() {
        return gia_credit;
    }

    public void setGia_credit(int gia_credit) {
        this.gia_credit = gia_credit;
    }

    public int getGia_tien_credit() {
        return gia_tien_credit;
    }

    public void setGia_tien_credit(int gia_tien_credit) {
        this.gia_tien_credit = gia_tien_credit;
    }
}
