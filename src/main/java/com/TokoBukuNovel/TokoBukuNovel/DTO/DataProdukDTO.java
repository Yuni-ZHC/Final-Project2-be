package com.TokoBukuNovel.TokoBukuNovel.DTO;

import java.math.BigDecimal;

public class DataProdukDTO {
    private String judulNovel;
    private String deskripsiNovel;
    private Double ratingNovel;
    private BigDecimal hargaNovel; // Gunakan BigDecimal untuk harga
    private String penulisNovel;
    private String gambarNovel;
    private String fotoNovel;

    // Getters and setters
    public String getJudulNovel() {
        return judulNovel;
    }

    public void setJudulNovel(String judulNovel) {
        this.judulNovel = judulNovel;
    }

    public String getDeskripsiNovel() {
        return deskripsiNovel;
    }

    public void setDeskripsiNovel(String deskripsiNovel) {
        this.deskripsiNovel = deskripsiNovel;
    }

    public Double getRatingNovel() {
        return ratingNovel;
    }

    public void setRatingNovel(Double ratingNovel) {
        this.ratingNovel = ratingNovel;
    }

    public BigDecimal getHargaNovel() {
        return hargaNovel;
    }

    public void setHargaNovel(BigDecimal hargaNovel) {
        this.hargaNovel = hargaNovel;
    }

    public String getPenulisNovel() {
        return penulisNovel;
    }

    public void setPenulisNovel(String penulisNovel) {
        this.penulisNovel = penulisNovel;
    }

    public String getGambarNovel() {
        return gambarNovel;
    }

    public void setGambarNovel(String gambarNovel) {
        this.gambarNovel = gambarNovel;
    }

    public String getFotoNovel() {
        return fotoNovel;
    }

    public void setFotoNovel(String fotoNovel) {
        this.fotoNovel = fotoNovel;
    }

    public void setIdAdmin(Long id) {
    }

    public void setId(Long id) {
        
    }

    public void setApiUrl(String s) {
    }

    public void setFotoUrl(String fileUrl) {
    }
}
