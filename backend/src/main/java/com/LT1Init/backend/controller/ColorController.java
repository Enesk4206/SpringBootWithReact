package com.LT1Init.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LT1Init.backend.dto.ColorRequest;
import com.LT1Init.backend.service.ColorService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/color")
@RequiredArgsConstructor
public class ColorController {
    private final ColorService colorService;

     @GetMapping("/all-colors")
    public ResponseEntity<List<ColorRequest>> getAllProductsAPI(){
        return ResponseEntity.status(HttpStatus.OK).body(colorService.getAllColors());
    }

    @PostMapping("/create")
    public ResponseEntity<ColorRequest> createAPI(@RequestBody ColorRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(colorService.create(request));
    }

     @PutMapping("/update/{id}")
    public ResponseEntity<ColorRequest> updateAPI(@PathVariable Long id, @RequestBody ColorRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(colorService.update(id,request));
    }
     @DeleteMapping("/delete/{id}")
    public ResponseEntity<ColorRequest> deleteAPI(@PathVariable Long id){
        colorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
