package com.TokoBukuNovel.TokoBukuNovel.impl;

import com.TokoBukuNovel.TokoBukuNovel.DTO.DataProdukDTO;
import com.TokoBukuNovel.TokoBukuNovel.exception.NotFoundException;
import com.TokoBukuNovel.TokoBukuNovel.model.Admin;
import com.TokoBukuNovel.TokoBukuNovel.model.Produk;
import com.TokoBukuNovel.TokoBukuNovel.repository.DataProdukRepository;
import com.TokoBukuNovel.TokoBukuNovel.repository.AdminRepository;
import com.TokoBukuNovel.TokoBukuNovel.service.ProdukService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DataProdukImpl implements ProdukService {

    private static final String BASE_URL = "https://s3.lynk2.co/api/s3";

    private final DataProdukRepository dataprodukRepository;
    private final AdminRepository adminRepository;
    private final RestTemplate restTemplate = new RestTemplate();

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
    public Optional<Produk> getProdukById(Long id) {
        return Optional.empty();
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
        data.setRatingNovel(dataprodukDTO.getRatingNovel());
        data.setPenulisNovel(dataprodukDTO.getPenulisNovel());
        data.setHargaNovel(dataprodukDTO.getHargaNovel());
        data.setGambarNovel(dataprodukDTO.getGambarNovel());

        Produk savedProduk = dataprodukRepository.save(data);

        DataProdukDTO result = new DataProdukDTO();
        result.setIdAdmin(admin.getId());
        result.setJudulNovel(savedProduk.getJudulNovel());
        result.setDeskripsiNovel(savedProduk.getDeskripsiNovel());
        result.setRatingNovel(savedProduk.getRatingNovel());
        result.setHargaNovel(savedProduk.getHargaNovel());
        result.setPenulisNovel(savedProduk.getPenulisNovel());
        result.setGambarNovel(savedProduk.getGambarNovel());

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


    public String uploadFoto(MultipartFile file) throws IOException {
        String uploadUrl = BASE_URL + "/uploadFoto";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(uploadUrl, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return extractFileUrlFromResponse(response.getBody());
        } else {
            throw new IOException("Failed to upload file: " + response.getStatusCode());
        }
    }

    private String extractFileUrlFromResponse(String responseBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(responseBody);
        JsonNode dataNode = jsonResponse.path("data");
        return dataNode.path("url_file").asText();
    }

    @Override
    public DataProdukDTO getDataProdukById(Long id) {
        return null;
    }
}
