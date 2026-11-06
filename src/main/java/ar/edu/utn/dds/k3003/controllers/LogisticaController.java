package ar.edu.utn.dds.k3003.controllers;

import java.util.List;

import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.AsignacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.AsignacionDirecta;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.PaqueteDTO;
import ar.edu.utn.dds.k3003.model.*;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logistica")
public class LogisticaController {
    private final Fachada fachada;
    
    public LogisticaController(Fachada fachada) {
        this.fachada = fachada;
    }


    @GetMapping("/asignaciones/{id}")
    public Asignacion getAsignacion(@PathVariable String id){
        return fachada.getAsignacion(id);
    }

    @GetMapping("/asignaciones")
    public List<Asignacion> getAsignaciones() {
        return fachada.getAsignaciones();
    }

    /*
    @GetMapping("/asignacionesHistorial")
    public List<AsignacionesHistorial> getAsignacionesHistorial(){
        return fachada.getAsignacionesHistorial();
    }

    @GetMapping("/paquetes/{id}")
    public Optional<PaqueteDTO> buscarPaquete(@PathVariable("id") String id){
        return fachada.getPaquete(id);
    }

    @GetMapping("/paquetes")
    public List<PaqueteDTO> getPaquetes(){
        return fachada.getPaquetes();
    }

    @PostMapping("/asignaciones")
    public AsignacionDTO crearAsignacion(@RequestBody AsignacionDirecta asignacionDirecta){
        return fachada.postAsignacion(asignacionDirecta);
    }

    @PostMapping("/asignacionesDirecta")
    public Asignacion postAsignacionesDirecta(@RequestBody AsignacionDirecta asignacionDirecta) {
        return fachada.postAsignacionesDirecta(asignacionDirecta);
    }

    
         */
}
