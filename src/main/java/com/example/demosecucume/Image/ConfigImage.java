package com.example.demosecucume.Image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ConfigImage {

    public static void saveimgRegion(String uploaDirRegion, String nomfileRegion, MultipartFile multipartFile) throws IOException {

        Path UploadPathRegion = Paths.get(uploaDirRegion);

        if(!Files.exists(UploadPathRegion)) {
            Files.createDirectories(UploadPathRegion);
        }
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path fichierPathRegion = UploadPathRegion.resolve(nomfileRegion);

            Files.copy(inputStream, fichierPathRegion, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe){
            throw new IOException("Impossible d'enregistrer le fichier image:" + nomfileRegion, ioe);
        }
    }

}
