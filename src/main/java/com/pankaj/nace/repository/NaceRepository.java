package com.pankaj.nace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pankaj.nace.dto.Nace;

@Repository
public interface NaceRepository extends JpaRepository<Nace, String> {
}
