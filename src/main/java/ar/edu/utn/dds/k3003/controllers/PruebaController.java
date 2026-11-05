package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.EstadoDonacionEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//
//import ar.edu.utn.dds.k3003.services.DonacionService;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.MDC;




@RestController
@RequestMapping("/prueba")
public class PruebaController {

    private final Fachada fachada;

    public PruebaController(Fachada fachada) {
        this.fachada = fachada;
    }
    // ----- ------------- -----
    // ----- De donaciones -----
    // ----- ------------- -----
    @GetMapping("/donaciones")
    public ResponseEntity<?> getDonaciones(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.obtenerDonaciones());
    
    }
    // ______ Para probar ______
    @GetMapping
    public ResponseEntity<String> getD(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Llegan bien los request");
    
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getNum(@PathVariable("id") String pruebaID){
        return ResponseEntity.status(HttpStatus.OK).body("Llega bien el request"+ pruebaID);
    }

    // ------------------- PARA PRUEBA -----------------
    @GetMapping("/donaciones/prueba")
    public ResponseEntity<?> getDonacionesPrueba(){
        return this.fachada.obtenerDonaciones();
    
    }
}