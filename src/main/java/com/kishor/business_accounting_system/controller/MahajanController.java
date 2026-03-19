package com.kishor.business_accounting_system.controller;

import com.kishor.business_accounting_system.entity.Mahajan;
import com.kishor.business_accounting_system.service.MahajanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mahajans")
public class MahajanController {

    private final MahajanService mahajanService;

    public MahajanController(MahajanService mahajanService){
        this.mahajanService = mahajanService;
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<Mahajan> createMahajan(@RequestBody Mahajan mahajan){
        Mahajan saved = mahajanService.saveMahajan(mahajan);

        return ResponseEntity
                .status(201) // CREATED
                .header("info", "Mahajan created successfully")
                .body(saved);
    }

    // ✅ READ ALL
    @GetMapping
    public ResponseEntity<List<Mahajan>> getAllMahajans(){
        List<Mahajan> list = mahajanService.getAllMahajans();

        if(list.isEmpty()){
            return ResponseEntity
                    .status(204) // NO CONTENT
                    .build();
        }

        return ResponseEntity.ok(list);
    }

    // ✅ READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Mahajan> getMahajanById(@PathVariable Long id){
        try {
            Mahajan mahajan = mahajanService.getMahajanById(id);

            return ResponseEntity
                    .ok()
                    .header("info", "Mahajan fetched successfully")
                    .body(mahajan);

        } catch (RuntimeException e){
            return ResponseEntity
                    .status(404)
                    .header("error", "Mahajan not found")
                    .build();
        }
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Mahajan> updateMahajan(@PathVariable Long id,
                                                 @RequestBody Mahajan mahajan){
        try {
            Mahajan updated = mahajanService.updateMahajan(id, mahajan);

            return ResponseEntity
                    .ok()
                    .header("info", "Mahajan updated successfully")
                    .body(updated);

        } catch (RuntimeException e){
            return ResponseEntity
                    .status(404)
                    .header("error", "Mahajan not found for update")
                    .build();
        }
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMahajan(@PathVariable Long id){
        try {
            mahajanService.deleteMahajan(id);

            return ResponseEntity
                    .noContent() // 204
                    .header("info", "Mahajan deleted successfully")
                    .build();

        } catch (RuntimeException e){
            return ResponseEntity
                    .status(404)
                    .header("error", "Mahajan not found for delete")
                    .build();
        }
    }
}