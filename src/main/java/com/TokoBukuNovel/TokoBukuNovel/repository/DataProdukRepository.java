package com.TokoBukuNovel.TokoBukuNovel.repository;

import com.TokoBukuNovel.TokoBukuNovel.model.Produk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataProdukRepository extends JpaRepository<Produk, Long> {
    List<Produk> findByAdminId(Long idAdmin);

    List<Produk> findAll();

    Optional<Produk> findById(Long id);

    void deleteById(Long id);


}