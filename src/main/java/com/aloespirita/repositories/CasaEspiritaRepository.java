package com.aloespirita.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aloespirita.models.CasaEspirita;


@Repository
public interface CasaEspiritaRepository extends JpaRepository<CasaEspirita, Long> {

	public List<CasaEspirita> findByCidade(String cidade);
}
