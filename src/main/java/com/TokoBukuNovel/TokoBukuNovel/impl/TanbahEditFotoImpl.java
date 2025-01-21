//package com.TokoBukuNovel.TokoBukuNovel.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Service
//public class TambahEditFotoImpl implements TambahEditFoto {
//
//    private final ProdukRepository produkRepository;
//    private final FotoService fotoService;
//
//    private static final Logger logger = LoggerFactory.getLogger(TambahEditFotoImpl.class);
//
//    @Autowired
//    public TambahEditFotoImpl(ProdukRepository produkRepository, FotoService fotoService) {
//        this.produkRepository = produkRepository;
//        this.fotoService = fotoService;
//    }
//
//    @Override
//    public String tambahFoto(Long produkId, MultipartFile file) {
//        try {
//            if (file.isEmpty()) {
//                throw new IllegalArgumentException("File foto tidak boleh kosong");
//            }
//
//            // Upload file ke server
//            String fotoUrl = fotoService.uploadFotoToServer(file);
//            logger.info("File berhasil diupload, URL foto: {}", fotoUrl);
//
//            // Ambil produk berdasarkan ID
//            Produk produk = produkRepository.findById(produkId)
//                    .orElseThrow(() -> new IllegalArgumentException("Produk dengan ID " + produkId + " tidak ditemukan"));
//
//            // Set foto URL pada produk
//            produk.setFotoUrl(fotoUrl);
//            // Simpan perubahan ke database
//            produkRepository.save(produk);
//
//            return fotoUrl;
//        } catch (IllegalArgumentException e) {
//            logger.error("Error: {}", e.getMessage());
//            throw e; // Lemparkan exception yang jelas
//        } catch (Exception e) {
//            logger.error("Gagal mengupload foto untuk produk ID {}: {}", produkId, e.getMessage());
//            throw new RuntimeException("Gagal mengupload foto: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public String editFoto(Long produkId, MultipartFile file) {
//        try {
//            if (file.isEmpty()) {
//                throw new IllegalArgumentException("File foto tidak boleh kosong");
//            }
//
//            // Upload file ke server
//            String fotoUrl = fotoService.uploadFotoToServer(file);
//            logger.info("File berhasil diupload, URL foto: {}", fotoUrl);
//
//            // Ambil produk berdasarkan ID
//            Produk produk = produkRepository.findById(produkId)
//                    .orElseThrow(() -> new IllegalArgumentException("Produk dengan ID " + produkId + " tidak ditemukan"));
//
//            // Set foto URL pada produk
//            produk.setFotoUrl(fotoUrl);
//            // Simpan perubahan ke database
//            produkRepository.save(produk);
//
//            return fotoUrl;
//        } catch (IllegalArgumentException e) {
//            logger.error("Error: {}", e.getMessage());
//            throw e; // Lemparkan exception yang jelas
//        } catch (Exception e) {
//            logger.error("Gagal mengedit foto untuk produk ID {}: {}", produkId, e.getMessage());
//            throw new RuntimeException("Gagal mengedit foto: " + e.getMessage(), e);
//        }
//    }
//}
