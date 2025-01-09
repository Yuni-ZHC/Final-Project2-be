package com.TokoBukuNovel.TokoBukuNovel.impl;

import com.TokoBukuNovel.TokoBukuNovel.DTO.DataProdukDTO;
import com.TokoBukuNovel.TokoBukuNovel.exception.NotFoundException;
import com.TokoBukuNovel.TokoBukuNovel.model.Admin;
import com.TokoBukuNovel.TokoBukuNovel.model.Produk;
import com.TokoBukuNovel.TokoBukuNovel.repository.DataProdukRepository;
import com.TokoBukuNovel.TokoBukuNovel.repository.AdminRepository;
import com.TokoBukuNovel.TokoBukuNovel.service.ProdukService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DataProdukImpl implements ProdukService {  // Implement ProdukService

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


        return result;
    }

    @Override
    public DataProdukDTO editDataProdukDTO(Long id, Long idAdmin, DataProdukDTO dataprodukDTO) throws IOException {
        Produk existingData = dataprodukRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("DataProduk tidak ditemukan"));

        Admin admin = adminRepository.findById(idAdmin)
                .orElseThrow(() -> new NotFoundException("Admin dengan ID " + idAdmin + " tidak ditemukan"));

        // Update product details
        existingData.setJudulNovel(dataprodukDTO.getJudulNovel());
        existingData.setDeskripsiNovel(dataprodukDTO.getDeskripsiNovel().trim());
        existingData.setRatingNovel(Double.valueOf(dataprodukDTO.getRatingNovel())); // Convert String to Double
        existingData.setPenulisNovel(dataprodukDTO.getPenulisNovel());
        existingData.setAdmin(admin);

        Produk updatedDataProduk = dataprodukRepository.save(existingData);

        DataProdukDTO result = new DataProdukDTO();
        result.setId(updatedDataProduk.getId());
        result.setIdAdmin(admin.getId());
        result.setJudulNovel(updatedDataProduk.getJudulNovel());
        result.setDeskripsiNovel(updatedDataProduk.getDeskripsiNovel());
        result.setRatingNovel(Double.valueOf(String.valueOf(updatedDataProduk.getRatingNovel()))); // Convert Double ke String
        result.setHargaNovel(updatedDataProduk.getHargaNovel()); // Menggunakan BigDecimal langsung
        result.setPenulisNovel(updatedDataProduk.getPenulisNovel());

        return result;
    }


    @Override
    public void deleteProduk(Long id) {
        dataprodukRepository.deleteById(id);
    }
}
