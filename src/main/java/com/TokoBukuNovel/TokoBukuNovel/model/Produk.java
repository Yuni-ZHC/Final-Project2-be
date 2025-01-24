package com.TokoBukuNovel.TokoBukuNovel.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produk")
public class Produk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "judul_novel", nullable = false)
    private String judulNovel;

    @Column(name = "deskripsi_novel", columnDefinition = "TEXT")
    private String deskripsiNovel;

    @Column(name = "rating_novel")
    private Double ratingNovel;

    @Column(name = "harga_novel")
    private BigDecimal hargaNovel;

    @Column(name = "penulis_novel")
    private String penulisNovel;

    @Column(name = "foto_url") // Menambahkan fotoUrl
    private String fotoUrl;

    @Column(name = "gambar_novel", nullable = true) // Gambar novel
    private String gambarNovel;

    @ManyToOne
    @JoinColumn(name = "id_admin", nullable = false)
    private Admin admin;

    // Default constructor
    public Produk() {
    }

    // Parameterized constructor without 'id' (since it's auto-generated)
    public Produk(Admin admin, String judulNovel, String deskripsiNovel, Double ratingNovel, BigDecimal hargaNovel, String penulisNovel, String fotoUrl, String gambarNovel) {
        this.admin = admin;
        this.judulNovel = judulNovel;
        this.deskripsiNovel = deskripsiNovel;
        this.ratingNovel = ratingNovel;
        this.hargaNovel = hargaNovel;
        this.penulisNovel = penulisNovel;
        this.fotoUrl = fotoUrl;
        this.gambarNovel = gambarNovel;
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for judulNovel
    public String getJudulNovel() {
        return judulNovel;
    }

    public void setJudulNovel(String judulNovel) {
        if (judulNovel == null || judulNovel.isEmpty()) {
            throw new IllegalArgumentException("Judul novel tidak boleh kosong");
        }
        this.judulNovel = judulNovel;
    }

    // Getter and Setter for deskripsiNovel
    public String getDeskripsiNovel() {
        return deskripsiNovel;
    }

    public void setDeskripsiNovel(String deskripsiNovel) {
        this.deskripsiNovel = deskripsiNovel;
    }

    // Getter and Setter for ratingNovel
    public Double getRatingNovel() {
        return ratingNovel;
    }

    public void setRatingNovel(Double ratingNovel) {
        if (ratingNovel < 0 || ratingNovel > 5) {
            throw new IllegalArgumentException("Rating novel harus antara 0 dan 5");
        }
        this.ratingNovel = ratingNovel;
    }

    // Getter and Setter for hargaNovel
    public BigDecimal getHargaNovel() {
        return hargaNovel;
    }

    public void setHargaNovel(BigDecimal hargaNovel) {
        if (hargaNovel.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Harga novel tidak boleh negatif");
        }
        this.hargaNovel = hargaNovel;
    }

    // Getter and Setter for penulisNovel
    public String getPenulisNovel() {
        return penulisNovel;
    }

    public void setPenulisNovel(String penulisNovel) {
        this.penulisNovel = penulisNovel;
    }

    // Getter and Setter for fotoUrl
    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    // Getter and Setter for gambarNovel
    public String getGambarNovel() {
        return gambarNovel;
    }

    public void setGambarNovel(String gambarNovel) {
        this.gambarNovel = gambarNovel;
    }

    // Getter and Setter for admin
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Admin tidak boleh null");
        }
        this.admin = admin;
    }
}
