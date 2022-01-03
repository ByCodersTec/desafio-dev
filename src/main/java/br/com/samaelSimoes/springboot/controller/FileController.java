package br.com.samaelSimoes.springboot.controller;

import br.com.samaelSimoes.springboot.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam MultipartFile file)  {
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            fileService.readFile(file.getInputStream());
            return ResponseEntity.ok("successfully imported");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error uploading");
        }
    }

    @GetMapping
    public ResponseEntity<List<Map>> get() {
        final List<Map> list = fileService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/store/{store}")
    public ResponseEntity<List<Map>> getByLoja(@PathVariable String store) {
        final List<Map> list = fileService.getAllStore(store);
        return ResponseEntity.ok(list);
    }
}
