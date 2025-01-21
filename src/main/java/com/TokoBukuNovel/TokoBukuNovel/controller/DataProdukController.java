package com.TokoBukuNovel.TokoBukuNovel.controller;

import com.TokoBukuNovel.TokoBukuNovel.DTO.DataProdukDTO;
import com.TokoBukuNovel.TokoBukuNovel.model.Produk;
import com.TokoBukuNovel.TokoBukuNovel.service.ProdukService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.json.JSONObject;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DataProdukController {

    private final ProdukService produkService;

    public DataProdukController(ProdukService produkService) {
        this.produkService = produkService;
    }

    @GetMapping("/data/produk")
    public ResponseEntity<List<Produk>> getAllProduk() {
        List<Produk> produkList = produkService.getAllProduk();
        if (produkList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Handle case when no data found
        }
        return ResponseEntity.ok(produkList);
    }

    @GetMapping("/data/getAllByAdmin/{idAdmin}")
    public ResponseEntity<List<Produk>> getAllByAdmin(@PathVariable Long idAdmin) {
        List<Produk> produkList = produkService.getAllByAdmin(idAdmin);
        if (produkList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Handle case when no data found for user
        }
        return ResponseEntity.ok(produkList);
    }

    @GetMapping("/data/produk/{id}")
    public ResponseEntity<Produk> getDataById(@PathVariable Long id) {
        Optional<Produk> data = produkService.getDataById(id);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/data/tambah/{idAdmin}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DataProdukDTO> tambahProduk(
            @PathVariable Long idAdmin,
            @RequestPart("data") DataProdukDTO dataprodukDTO,
            @RequestPart("file") MultipartFile file) throws IOException {

        // Upload foto dan dapatkan URL file
        String fileUrl = uploadFotoToServer(file);

        // Set URL foto ke DTO
        dataprodukDTO.setFotoUrl(fileUrl);

        // Simpan data produk menggunakan service
        DataProdukDTO savedProduk = produkService.tambahDataProdukDTO(idAdmin, dataprodukDTO);

        return ResponseEntity.ok(savedProduk);
    }



    @PutMapping(value = "/data/editById/{id}")
    public ResponseEntity<DataProdukDTO> editDataProduk(
            @PathVariable Long id,
            @RequestParam Long idAdmin,  // Expecting idAdmin as a request parameter
            @RequestBody DataProdukDTO dataProdukDTO) throws IOException {  // Using @RequestBody for dataProdukDTO
        // Edit dataProduk without photo
        DataProdukDTO updatedDataProduk = produkService.editDataProdukDTO(id, idAdmin, dataProdukDTO);
        return ResponseEntity.ok(updatedDataProduk);
    }

    @DeleteMapping("/data/delete/{id}")
    public ResponseEntity<Void> deleteProduk(@PathVariable Long id) throws IOException {
        produkService.deleteProduk(id);
        return ResponseEntity.noContent().build(); // No content response for successful deletion
    }


    @PostMapping("/data/uploadFoto")
    public ResponseEntity<String> uploadFoto(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileUrl = uploadFotoToServer(multipartFile);
        return ResponseEntity.ok(fileUrl);
    }

    private String uploadFotoToServer(MultipartFile multipartFile) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String base_url = "https://s3.lynk2.co/api/s3/absenMasuk";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", multipartFile.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(base_url, HttpMethod.POST, requestEntity, String.class);

        // Parsing URL file dari respons API
        return extractFileUrlFromResponse(response.getBody());
    }

    private String extractFileUrlFromResponse(String responseBody) {
        // Contoh parsing jika respons berupa JSON
        JSONObject json = new JSONObject(responseBody);
        return json.getJSONObject("data").getString("url_file");
    }

}

