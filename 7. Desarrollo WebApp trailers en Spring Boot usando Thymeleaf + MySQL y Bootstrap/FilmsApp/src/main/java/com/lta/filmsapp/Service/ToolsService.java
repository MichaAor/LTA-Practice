package com.lta.filmsapp.Service;

import com.lta.filmsapp.PException.FileNotFoundException;
import com.lta.filmsapp.PException.StorageException;
import com.lta.filmsapp.Repository.FilmRepository;
import com.lta.filmsapp.Service.Interface.IToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ToolsService implements IToolsService {
    @Value("${storage.location}")
    private String stLocation;  //Definimos en variable donde guardar los files indicados en el appProperties

    @PostConstruct      //Indica que el metodo se ejecuta cada vez que se cree una nueva instancia.
    @Override
    public void InitTools() {
        try{
            Files.createDirectories(Paths.get(stLocation));
        }catch (IOException exception){
            throw new StorageException("Error starting file storage location");
        }
    }

    @Override
    public String SaveFile(MultipartFile file) {
        String nameFile = file.getOriginalFilename();
        if(nameFile.isEmpty()){
            throw new StorageException("Cannot store an empty file");
        }
        try{
            InputStream inS = file.getInputStream();
            Files.copy(inS,Paths.get(stLocation).resolve(nameFile), StandardCopyOption.REPLACE_EXISTING);
        //Files.copy(inputStream,Paths.get(localizacionParaGuardar).resolve(nombreArchivo)
            // , StandardCopyOption.REPLACE_EXISTING) lo ultimo hace que remplaze el aarchivo si existe uno con el mismo nombre;
        }catch (IOException exception){
            throw new StorageException("Error to storage files" + nameFile,exception);
        }
        return nameFile;
    }

    @Override
    public Path UploadFile(String filename) {
        return Paths.get(stLocation).resolve(filename);
    }

    @Override
    public Resource LoadAsResource(String filename) {
        try{
            Path file = UploadFile(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()||resource.isReadable()){
                return resource;
            }else{
                throw new FileNotFoundException("Could not find the file "+filename);
            }

        }catch (MalformedURLException exception){
            throw new FileNotFoundException("Could not find the file "+filename,exception);
        }
    }

    @Override
    public void DeleteFile(String filename) {
        Path file = UploadFile(filename);
        try{
            FileSystemUtils.deleteRecursively(file);
        }catch(Exception exception){
            System.out.println(exception);
        }
    }

    @Service
    public static class FilmService {
        @Autowired
        private FilmRepository fr;


    }
}
