package com.example.ailatrieuphu;

import androidx.annotation.NonNull;

public class CauHoiItem {
    private int ID;
    private String Noidung;
    private String PhuonganA;
    private String PhuonganB;
    private String PhuonganC;
    private String PhuonganD;

    public CauHoiItem(int ID, String noidung, String phuonganA, String phuonganB, String phuonganC, String phuonganD) {
        this.ID = ID;
        Noidung = noidung;
        PhuonganA = phuonganA;
        PhuonganB = phuonganB;
        PhuonganC = phuonganC;
        PhuonganD = phuonganD;
    }

    public CauHoiItem(){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNoidung() {
        return Noidung;
    }

    public void setNoidung(String noidung) {
        Noidung = noidung;
    }

    public String getPhuonganA() {
        return PhuonganA;
    }

    public void setPhuonganA(String phuonganA) {
        PhuonganA = phuonganA;
    }

    public String getPhuonganB() {
        return PhuonganB;
    }

    public void setPhuonganB(String phuonganB) {
        PhuonganB = phuonganB;
    }

    public String getPhuonganC() {
        return PhuonganC;
    }

    public void setPhuonganC(String phuonganC) {
        PhuonganC = phuonganC;
    }

    public String getPhuonganD() {
        return PhuonganD;
    }

    public void setPhuonganD(String phuonganD) {
        PhuonganD = phuonganD;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n" + this.ID);
        sb.append("\n" + this.Noidung);
        sb.append("\n" + this.PhuonganA);
        sb.append("\n" + this.PhuonganB);
        sb.append("\n" + this.PhuonganC);
        sb.append("\n" + this.PhuonganD);

        return sb.toString();
    }
}

