package ar.edu.utn.dds.k3003.controllers;

import java.util.List;

import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.AsignacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.AsignacionDirecta;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.PaqueteDTO;
import ar.edu.utn.dds.k3003.exceptions.ManejoDeExcepciones;
import ar.edu.utn.dds.k3003.exceptions.AsignacionNoEncontradaException;
import ar.edu.utn.dds.k3003.model.*;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logistica")
public class LogisticaController {
    private final Fachada fachada;
    private final ManejoDeExcepciones mdExcepciones;
    
    public LogisticaController(Fachada fachada) {
        this.fachada = fachada;
        this.mdExcepciones= new ManejoDeExcepciones();
    }


    //Prueba 1. Como venimos haciendo
    @GetMapping("/asignaciones/{id}/prueba1")
    public ResponseEntity<?> getAsignacion1(@PathVariable String id){
        
        try{
           return ResponseEntity.ok(fachada.getAsignacion(id));
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    /*
    //Prueba 2.Con .orElseThrow(() -> new
    @GetMapping("/asignaciones/{id}/prueba2")
    public Asignacion getAsignacion2(@PathVariable String id){
        return fachada.getAsignacion(id).orElseThrow(() -> new AsignacionNoEncontradaException(id));
    }
    */
    //Con nuestro propio throw en el catch
    @GetMapping("/asignaciones/{id}/prueba3")
    public Asignacion getAsignacion3(@PathVariable String id){
        
        try{
           return fachada.getAsignacion(id);
        } catch(RuntimeException ex){
            throw mdExcepciones.manejarThrowExceptionConTCausa(ex);
        }
    }

    @GetMapping("/asignaciones/{id}/prueba4")
    public Asignacion getAsignacion4(@PathVariable String id){
        
        try{
           return fachada.getAsignacion(id);
        } catch(RuntimeException ex){
            throw mdExcepciones.manejarThrowException(ex);
        }
    }


    /*
    @GetMapping("/asignaciones/{id}")
    public Asignacion getAsignacion(@PathVariable String id){
        return fachada.getAsignacion(id);
        try{
           return fachada.eliminarCategoria(categoriaID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/asignaciones")
    public List<Asignacion> getAsignaciones() {
        return fachada.getAsignaciones();
        try{
           return fachada.eliminarCategoria(categoriaID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

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
