package com.TokoBukuNovel.TokoBukuNovel.impl;

import com.TokoBukuNovel.TokoBukuNovel.DTO.DataProdukDTO;
import com.TokoBukuNovel.TokoBukuNovel.exception.NotFoundException;
import com.TokoBukuNovel.TokoBukuNovel.model.Admin;
import com.TokoBukuNovel.TokoBukuNovel.model.Produk;
import com.TokoBukuNovel.TokoBukuNovel.repository.DataProdukRepository;
import com.TokoBukuNovel.TokoBukuNovel.repository.AdminRepository;
import com.TokoBukuNovel.TokoBukuNovel.service.ProdukService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DataProdukImpl implements ProdukService {  // Implement ProdukService

    private static final String BASE_URL = "https://s3.lynk2.co/api/s3"; // URL API

    private final DataProdukRepository dataprodukRepository;
    private final AdminRepository adminRepository;

    public DataProdukImpl(DataProdukRepository dataprodukRepository, AdminRepository adminRepository) {
        this.dataprodukRepository = dataprodukRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Produk> getAllProduk() {
        return dataprodukRepository.findAll();
    }

    @Override
    public List<Produk> getAllByAdmin(Long idAdmin) {
        return dataprodukRepository.findByAdminId(idAdmin);
    }

    @Override
    public Optional<Produk> getDataById(Long id) {
        return dataprodukRepository.findById(id);
    }

    @Override
    public DataProdukDTO tambahDataProdukDTO(Long idAdmin, DataProdukDTO dataprodukDTO) {
        Admin admin = adminRepository.findById(idAdmin)
                .orElseThrow(() -> new NotFoundException("Admin dengan ID " + idAdmin + " tidak ditemukan"));

        Produk data = new Produk();
        data.setAdmin(admin);
        data.setJudulNovel(dataprodukDTO.getJudulNovel());
        data.setDeskripsiNovel(dataprodukDTO.getDeskripsiNovel().trim());
        data.setRatingNovel(Double.valueOf(dataprodukDTO.getRatingNovel())); // Convert String ke Double
        data.setPenulisNovel(dataprodukDTO.getPenulisNovel());

        // Konversi String ke BigDecimal untuk hargaNovel
        try {
            data.setHargaNovel(dataprodukDTO.getHargaNovel());  // Menggunakan BigDecimal langsung
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Harga novel tidak valid: " + dataprodukDTO.getHargaNovel());
        }

        Produk savedProduk = dataprodukRepository.save(data);

        DataProdukDTO result = new DataProdukDTO();
        result.setIdAdmin(admin.getId());
        result.setJudulNovel(savedProduk.getJudulNovel());
        result.setDeskripsiNovel(savedProduk.getDeskripsiNovel());
        result.setRatingNovel(Double.valueOf(String.valueOf(savedProduk.getRatingNovel()))); // Convert Double ke String
        result.setHargaNovel(savedProduk.getHargaNovel()); // Menggunakan BigDecimal langsung
        result.setPenulisNovel(savedProduk.getPenulisNovel());

//        // Tambahkan URL API
//        result.setApiUrl(BASE_URL + "/produk/" + savedProduk.getId());

        return result;
    }

    @Override
    public DataProdukDTO editDataProdukDTO(Long id, Long idAdmin, DataProdukDTO dataProdukDTO) throws IOException {
        Produk existingData = dataprodukRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produk tidak ditemukan"));

        Admin admin = adminRepository.findById(idAdmin)
                .orElseThrow(() -> new NotFoundException("Admin dengan ID " + idAdmin + " tidak ditemukan"));

        // Update produk
        existingData.setJudulNovel(dataProdukDTO.getJudulNovel());
        existingData.setDeskripsiNovel(dataProdukDTO.getDeskripsiNovel());
        existingData.setRatingNovel(dataProdukDTO.getRatingNovel());
        existingData.setHargaNovel(dataProdukDTO.getHargaNovel());
        existingData.setPenulisNovel(dataProdukDTO.getPenulisNovel());
        existingData.setGambarNovel(dataProdukDTO.getGambarNovel()); // Update URL gambar
        existingData.setAdmin(admin);

        Produk updatedProduk = dataprodukRepository.save(existingData);

        // Map ke DTO
        DataProdukDTO result = new DataProdukDTO();
        result.setId(updatedProduk.getId());
        result.setJudulNovel(updatedProduk.getJudulNovel());
        result.setDeskripsiNovel(updatedProduk.getDeskripsiNovel());
        result.setRatingNovel(updatedProduk.getRatingNovel());
        result.setHargaNovel(updatedProduk.getHargaNovel());
        result.setPenulisNovel(updatedProduk.getPenulisNovel());
        result.setGambarNovel(updatedProduk.getGambarNovel()); // Set URL gambar ke DTO

        return result;
    }


    @Override
    public void deleteProduk(Long id) {
        dataprodukRepository.deleteById(id);
    }

    @Override
    public String uploadFoto(MultipartFile file) {
        return "";
    }

    @Override
    public DataProdukDTO getDataProdukById(Long id) {
        return null;
    }
}
