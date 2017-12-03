package com.motionlaboratory.adochi;

/**
 * Created by naofal on 3/11/2017.
 */

public class Anak {
    String nama, gender,daerah, id_anak, umur;
    int gambar;

    public Anak() {
    }

    public Anak(String nama, String gender, String daerah, String umur, int gambar) {
        this.nama = nama;
        this.gender = gender;
        this.daerah = daerah;
        this.umur = umur;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public String getAsal_panti() {
        return gender;
    }

    public String getDaerah() {
        return daerah;
    }

    public int getGambar() {
        return gambar;
    }

    public String getId_anak() {
        return id_anak;
    }

    public String getUmur() {
        return umur;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAsal_panti(String asal_panti) {
        this.gender = asal_panti;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public void setId_anak(String id_anak) {
        this.id_anak = id_anak;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
