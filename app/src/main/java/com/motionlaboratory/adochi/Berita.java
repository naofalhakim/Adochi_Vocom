package com.motionlaboratory.adochi;

/**
 * Created by naofal on 3/11/2017.
 */
public class Berita {
    private String judul;
    private String narasi;
    private int gambar;

    public Berita() {

    }

    public Berita(String judul, String narasi, int gambar) {
        this.judul = judul;
        this.narasi = narasi;
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public String getNarasi() {
        return narasi;
    }

    public int getGambar() {
        return gambar;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setNarasi(String narasi) {
        this.narasi = narasi;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
