package com.example.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sourceforge.tess4j.Tesseract;

@Configuration
public class OCRConfig {
	
	 @Bean
	    public Tesseract tesseract() {
	        Tesseract tesseract = new Tesseract();
	        
	        tesseract.setDatapath("C:\\Users\\adoui\\OneDrive\\Desktop\\Tess4J\\tessdata");
	        return tesseract;
	    }

}
