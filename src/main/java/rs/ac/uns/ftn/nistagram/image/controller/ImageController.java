package rs.ac.uns.ftn.nistagram.image.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.nistagram.image.service.ImageService;

@RestController
@RequestMapping("api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PreAuthorize("hasRole('ADMIN') && hasAuthority('POST_IMAGE')")
    @PostMapping
    public String handleImageUpload(@RequestParam("image")MultipartFile file) {
        return imageService.store(file);
    }
}
