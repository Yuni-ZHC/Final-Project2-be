package com.TokoBukuNovel.TokoBukuNovel.DTO;

import java.math.BigDecimal;

public class DataProdukDTO {
    private Long id;
    private Long idAdmin;
    private String judulNovel;
    private String deskripsiNovel;
    private String ratingNovel;
    private BigDecimal hargaNovel;  // Ubah ke BigDecimal
    private String penulisNovel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

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

    public String getRatingNovel() {
        return ratingNovel;
    }

    public void setRatingNovel(String ratingNovel) {
        this.ratingNovel = ratingNovel;
    }

    public BigDecimal getHargaNovel() {
        return hargaNovel;
    }

    public void setHargaNovel(BigDecimal hargaNovel) {  // Gantilah ke BigDecimal
        this.hargaNovel = hargaNovel;
    }

    public String getPenulisNovel() {
        return penulisNovel;
    }

    public void setPenulisNovel(String penulisNovel) {
        this.penulisNovel = penulisNovel;
    }

}
