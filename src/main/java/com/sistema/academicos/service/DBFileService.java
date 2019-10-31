package com.sistema.academicos.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sistema.academicos.exception.FileStorageException;
import com.sistema.academicos.exception.MyFileNotFoundException;
import com.sistema.academicos.model.DBFile;
import com.sistema.academicos.repository.DBFileRepository;

@Service
public class DBFileService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
          
            if(fileName.contains("..")) {
                throw new FileStorageException("Desculpe! Nome do arquivo contém caracter inválido " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Não foi possível armazenar o arquivo " + fileName + ". Tente novamente!", ex);
        }
    }

    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("Arquivo não encontrado com o id: " + fileId));
    }
}
