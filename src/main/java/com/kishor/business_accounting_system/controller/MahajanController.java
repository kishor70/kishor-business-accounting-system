package com.kishor.business_accounting_system.controller;

import com.kishor.business_accounting_system.entity.Mahajan;
import com.kishor.business_accounting_system.service.MahajanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mahajans")
public class MahajanController {

    private final MahajanService mahajanService;

    public MahajanController(MahajanService mahajanService){
        this.mahajanService = mahajanService;
    }

    @PostMapping
    public Mahajan createMahajan(@RequestBody Mahajan mahajan){
        return mahajanService.saveMahajan(mahajan);
    }

    @GetMapping
    public List<Mahajan> getAllMahajans(){
        return mahajanService.getAllMahajans();
    }

    @GetMapping("/{id}")
    public Mahajan getMahajanById(@PathVariable Long id){
        return mahajanService.getMahajanById(id);
    }
}