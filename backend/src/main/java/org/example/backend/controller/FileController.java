package org.example.backend.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.example.backend.entity.RestBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api")
public class FileController {
    private static final String uploadDirectory = "D:\\vs-code\\毕业设计\\backend";

    @PostMapping("/upload")
    public RestBean<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return RestBean.failure(HttpStatus.BAD_REQUEST.value(),"File is empty");
        }

        try {
            // Get the original file name
            String originalFilename = file.getOriginalFilename();
            // Save the file to the target directory, replacing any existing file with the same name
            File targetFile = new File(uploadDirectory + File.separator + originalFilename);
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(targetFile));
            return RestBean.success("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return RestBean.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Failed to upload file");
        }
    }


    // Process file download requests
    @PostMapping("/download")
    public ResponseEntity<byte[]> downloadFile() {
        try {
            // Load the file to be downloaded
            File file = new File(uploadDirectory + File.separator + "resnet18_parameters_global.txt");
            // Check if the file exists
            if (!file.exists()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            byte[] fileContent = Files.readAllBytes(file.toPath());

            // Set the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setContentDispositionFormData("filename", "resnet18_parameters.txt");
            headers.setContentLength(fileContent.length);

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

