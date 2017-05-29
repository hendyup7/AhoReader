package com.example.ahoreader;

/**
 * Created by A455LF-I3 on 12/30/2016.
 */

public class Novel {
    String judul;
    int[] engNovels, indNovels;

    public Novel(String judul, int[] engNovels) {
        this.judul = judul;
        this.engNovels = engNovels;
    }

    public Novel(String judul, int[] engNovels, int[] indNovels) {
        this.judul = judul;
        this.engNovels = engNovels;
        this.indNovels = indNovels;
    }

    public String getJudul() {
        return judul;
    }

    public int[] getEngNovels() {
        return engNovels;
    }

    public int[] getIndNovels() {
        return indNovels;
    }
}
