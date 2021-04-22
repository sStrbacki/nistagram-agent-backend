package rs.ac.uns.ftn.nistagram.image.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.nistagram.config.Config;
import rs.ac.uns.ftn.nistagram.image.exceptions.ImageException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageService {

    private final Config config;

    public ImageService(Config config) {
        this.config = config;
    }

    public String store(MultipartFile file) {
        String extension = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1)
                .toLowerCase();

        String imageName = System.currentTimeMillis() + "." + extension;
        try (OutputStream os = Files.newOutputStream(Paths.get(config.getImagePath(), imageName))) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImageException("Error uploading image.");
        }
        return imageName;
    }
}
