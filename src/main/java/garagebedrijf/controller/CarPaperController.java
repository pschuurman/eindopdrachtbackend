package garagebedrijf.controller;


import garagebedrijf.dto.CarPaperDto;
import garagebedrijf.model.Carpaper;
import garagebedrijf.service.CarPaperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;


@RestController
public class CarPaperController {

    private final CarPaperService carPaperService;

    public CarPaperController(CarPaperService carPaperService) {
        this.carPaperService = carPaperService;
    }

    @PostMapping("single/uploadDb")
    public CarPaperDto singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        Carpaper carpaper = carPaperService.uploadFileDocument(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        return new CarPaperDto(carpaper.getFileName(), contentType, url );
    }

    //    get for single download
    @GetMapping("/downloadFromDB/{fileName}")
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        return carPaperService.singleFileDownload(fileName, request);
    }




}