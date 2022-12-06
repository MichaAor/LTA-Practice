package com.lta.filmsapp.Service.Interface;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Path;

public interface IToolsService {
    String SaveFile(MultipartFile file);
    Path UploadFile(String filename);
    Resource LoadAsResource(String filename);
    void DeleteFile(String filename);
}
