package com.TokoBukuNovel.TokoBukuNovel.DTO;

import java.math.BigDecimal;

public class DataProdukDTO {
    private String judulNovel;
    private String deskripsiNovel;
    private Double ratingNovel;
    private BigDecimal hargaNovel; // Gunakan BigDecimal untuk harga
    private String penulisNovel;

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

    public void setIdAdmin(Long id) {
    }

    public void setId(Long id) {
    }
}
