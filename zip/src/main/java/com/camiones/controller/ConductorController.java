package com.camiones.controller;

import com.camiones.model.Conductor;
import com.camiones.repository.ConductorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conductores")
public class ConductorController {

    private final ConductorRepository repository;

    public ConductorController(ConductorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Conductor crear(@RequestBody Conductor conductor) {
        return repository.save(conductor);
    }

    @GetMapping
    public List<Conductor> listar() {
        return repository.findAll();
    }
}