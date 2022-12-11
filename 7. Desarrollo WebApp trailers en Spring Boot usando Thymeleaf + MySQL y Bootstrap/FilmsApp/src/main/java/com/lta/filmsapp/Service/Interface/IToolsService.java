package com.lta.filmsapp.Service.Interface;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface IToolsService {
    public void InitTools();
    String SaveFile(MultipartFile file);
    Path UploadFile(String filename);
    Resource LoadAsResource(String filename);
    void DeleteFile(String filename);
}
