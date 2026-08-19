package com.camiones.controller;

import com.camiones.model.Camion;
import com.camiones.model.Conductor;
import com.camiones.repository.CamionRepository;
import com.camiones.repository.ConductorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camiones")
public class CamionController {

    private final CamionRepository camionRepository;
    private final ConductorRepository conductorRepository;

    public CamionController(
            CamionRepository camionRepository,
            ConductorRepository conductorRepository) {

        this.camionRepository = camionRepository;
        this.conductorRepository = conductorRepository;
    }

    // ADMIN: crear camión
    @PostMapping
    public Camion crearCamion(@RequestBody Camion camion) {
        return camionRepository.save(camion);
    }

    // ADMIN y SUPERVISOR: consultar camiones
    @GetMapping
    public List<Camion> obtenerCamiones() {
        return camionRepository.findAll();
    }

    // SUPERVISOR y ADMIN: asociar conductor a camión
    @PutMapping("/{camionId}/conductor/{conductorId}")
    public ResponseEntity<Camion> asociarConductor(
            @PathVariable Long camionId,
            @PathVariable Long conductorId) {

        Camion camion = camionRepository
                .findById(camionId)
                .orElseThrow();

        Conductor conductor = conductorRepository
                .findById(conductorId)
                .orElseThrow();

        camion.setConductor(conductor);

        return ResponseEntity.ok(
                camionRepository.save(camion)
        );
    }
}