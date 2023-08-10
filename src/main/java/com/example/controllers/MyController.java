package com.example.controllers;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@RestController
public class MyController {
	
	private final Tesseract tesseract;

    public MyController(Tesseract tesseract) {
        this.tesseract = tesseract;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestBody DocumentRequest documentRequest) {
        try {
            String documentBase64 = documentRequest.getDocumentBase64();

          
            byte[] imageData = java.util.Base64.getDecoder().decode(documentBase64);
            
           
            String uniqueFilename = StringUtils.cleanPath("image_" + System.currentTimeMillis() + ".png");
            
           
            String savePath = "C:\\Users\\adoui\\OneDrive\\Desktop\\traitementimage\\" + uniqueFilename;
            
            
            File imageFile = new File(savePath);
            FileUtils.writeByteArrayToFile(imageFile, imageData);

            
            String extractedText = tesseract.doOCR(imageFile);

            return ResponseEntity.ok(extractedText);
        } catch (TesseractException e) {
            return ResponseEntity.status(500).body("Une erreur est survenue lors de l'extraction du texte : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Une erreur est survenue lors du traitement du document : " + e.getMessage());
        }
    }
}
