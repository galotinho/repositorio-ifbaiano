package com.sistema.academicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.academicos.model.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

}