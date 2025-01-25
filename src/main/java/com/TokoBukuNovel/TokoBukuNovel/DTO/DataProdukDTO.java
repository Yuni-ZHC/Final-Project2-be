package com.TokoBukuNovel.TokoBukuNovel.DTO;

import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;

public class DataProdukDTO {

    private Long id; // Untuk menyimpan ID produk
    private Long idAdmin; // Untuk menyimpan ID admin
    private String judulNovel; // Judul produk/novel
    private String deskripsiNovel; // Deskripsi produk/novel
    private Double ratingNovel; // Rating produk/novel
    private BigDecimal hargaNovel; // Harga produk/novel
    private String penulisNovel; // Nama penulis novel
    private String gambarNovel; // URL gambar produk (ganti dari fotoUrl ke gambarNovel)

    // Getter dan Setter
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

    // Override toString() untuk membantu debugging
    @Override
    public String toString() {
        return "DataProdukDTO{" +
                "id=" + id +
                ", idAdmin=" + idAdmin +
                ", judul='" + judulNovel + '\'' +
                ", deskripsi='" + deskripsiNovel + '\'' +
                ", rating=" + ratingNovel +
                ", harga=" + hargaNovel +
                ", penulis='" + penulisNovel + '\'' +
                ", gambar='" + gambarNovel + '\'' +
                '}';
    }
}
