package com.TokoBukuNovel.TokoBukuNovel.controller;

import com.TokoBukuNovel.TokoBukuNovel.DTO.DataProdukDTO;
import com.TokoBukuNovel.TokoBukuNovel.model.Produk;
import com.TokoBukuNovel.TokoBukuNovel.service.ProdukService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/data/tambah/{idAdmin}")
    public ResponseEntity<DataProdukDTO> tambahProduk(
            @PathVariable Long idAdmin,
            @RequestBody DataProdukDTO dataprodukDTO) {
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
}
