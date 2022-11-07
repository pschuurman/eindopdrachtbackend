package com.eindopdracht.garagebedrijf.service;

import com.eindopdracht.garagebedrijf.model.Carpaper;
import com.eindopdracht.garagebedrijf.repository.CarPaperRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Service
public class CarPaperService {

    private final CarPaperRepository carPaperRepository;

    public CarPaperService(CarPaperRepository carPaperRepository) {
        this.carPaperRepository = carPaperRepository;
    }

    public Carpaper uploadFileDocument(MultipartFile file) throws IOException {
        String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Carpaper carpaper = new Carpaper();
        carpaper.setFileName(name);
        carpaper.setDocFile(file.getBytes());

        carPaperRepository.save(carpaper);

        return carpaper;

    }

    public ResponseEntity<byte[]> singleFileDownload(String fileName, HttpServletRequest request){

        Carpaper document = carPaperRepository.findByFileName(fileName);

//        this mediaType decides witch type you accept if you only accept 1 type
//        MediaType contentType = MediaType.IMAGE_JPEG;
//        this is going to accept multiple types

        String mimeType = request.getServletContext().getMimeType(document.getFileName());

//        for download attachment use next line
//        return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + resource.getFilename()).body(resource);
//        for showing image in browser
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + document.getFileName()).body(document.getDocFile());

    }

}
