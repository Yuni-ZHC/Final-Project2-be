package com.TokoBukuNovel.TokoBukuNovel.service;

import com.TokoBukuNovel.TokoBukuNovel.DTO.DataProdukDTO;
import com.TokoBukuNovel.TokoBukuNovel.model.Produk;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProdukService {

    List<Produk> getAllProduk();

    List<Produk> getAllByAdmin(Long idAdmin);

    Optional<Produk> getDataById(Long id);

    DataProdukDTO tambahDataProdukDTO(Long idAdmin, DataProdukDTO dataprodukDTO);

    DataProdukDTO editDataProdukDTO(Long id, Long idAdmin, DataProdukDTO buketDTO) throws IOException;


    void deleteProduk(Long id) throws IOException;
}
