package com.example.ailatrieuphu.Class;

public class CauHoi {
    public String NoiDung;
    public int ID;
    public String dA;
    public String dB;

    public CauHoi(String noiDung, int ID, String dA, String dB, String dC, String dD, String dapAn) {
        NoiDung = noiDung;
        this.ID = ID;
        this.dA = dA;
        this.dB = dB;
        this.dC = dC;
        this.dD = dD;
        this.dapAn = dapAn;
    }

    public String dC;

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getdA() {
        return dA;
    }

    public void setdA(String dA) {
        this.dA = dA;
    }

    public String getdB() {
        return dB;
    }

    public void setdB(String dB) {
        this.dB = dB;
    }

    public String getdC() {
        return dC;
    }

    public void setdC(String dC) {
        this.dC = dC;
    }

    public String getdD() {
        return dD;
    }

    public void setdD(String dD) {
        this.dD = dD;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    public String dD;
    public String dapAn;
}
