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

    @GetMapping("/asignaciones/{id}")
    public ResponseEntity<?> getAsignacion(@PathVariable String id){
        
        try{
           return ResponseEntity.ok(fachada.getAsignacion(id));
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/asignaciones")
    public ResponseEntity<?> getAsignaciones() {
        
        try{
           return ResponseEntity.ok(fachada.getAsignaciones());
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/asignacionesHistorial")
    public ResponseEntity<?> getAsignacionesHistorial(){
        try{
           return ResponseEntity.ok(fachada.getAsignacionesHistorial());
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/paquetes/{id}")
    public ResponseEntity<?> buscarPaquete(@PathVariable("id") String id){
    
        try{
           return ResponseEntity.ok(fachada.buscarPaquete(id));
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/paquetes")
    public ResponseEntity<?> getPaquetes(){
        try{
           return ResponseEntity.ok(fachada.obtenerPaquetes());
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PostMapping("/asignaciones")
    public ResponseEntity<?> crearAsignacion(@RequestBody AsignacionDirecta asignacionDirecta){
        try{
           return ResponseEntity.ok(fachada.crearAsignacion(asignacionDirecta));
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PostMapping("/asignacionesDirecta")
    public ResponseEntity<?> postAsignacionesDirecta(@RequestBody AsignacionDirecta asignacionDirecta) {
    
        try{
           return ResponseEntity.ok(fachada.postAsignacionesDirecta(asignacionDirecta));
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    // -----------------------
    // ----- De Deposito -----
    // -----------------------

    @GetMapping("/depositos/{id}")
    public ResponseEntity<?> getDeposito(@PathVariable String id){
        try{
           return ResponseEntity.ok(fachada.getDeposito(id));
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @DeleteMapping("/depositos/{id}")
    public ResponseEntity<String> deleteDeposito(@PathVariable String id) {
        try{
           return fachada.deleteDeposito(id);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/depositos")
    public ResponseEntity<?> getDepositos(){
        try{
           return ResponseEntity.ok(fachada.getDepositos());
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PostMapping("/depositos")
    public ResponseEntity<?> postDeposito(@RequestBody Deposito deposito) {
        try{
           return ResponseEntity.ok(fachada.postDeposito(deposito));
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PostMapping("/depositos/{id}/donacion")
    public ResponseEntity<String> postDonacion(@PathVariable String id, @RequestBody PaqueteDTO paquete){
        
        try{
           return ResponseEntity.ok(fachada.postDonacion(id, paquete));
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PostMapping("/entregas")
    public ResponseEntity<String> postEntrega(@RequestBody PaqueteDTO paquete){
        try{
           return ResponseEntity.ok(fachada.postEntrega(paquete));
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/stock/{productoID}")
    public ResponseEntity<String> getStock(@PathVariable String productoID){
        try{
           return ResponseEntity.ok(fachada.getStock(productoID));
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PostMapping("/stock/{productoID}")
    public ResponseEntity<?> postStock(@PathVariable String productoID, @RequestBody Integer cantidad){
        try{
           return ResponseEntity.ok(fachada.postStock(productoID, cantidad));
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }
    
    
   /*
   // Pruebas de mejor legibilidad de Exceptions


    //Prueba 1. Como venimos haciendo
    @GetMapping("/asignaciones/{id}/prueba1")
    public ResponseEntity<?> getAsignacion1(@PathVariable String id){
        
        try{
           return ResponseEntity.ok(fachada.getAsignacion(id));
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    
    //Prueba 2.Con .orElseThrow(() -> new       
    //No funciona porque asignacion tendria que ser Optional
    @GetMapping("/asignaciones/{id}/prueba2")
    public Asignacion getAsignacion2(@PathVariable String id){
        return fachada.getAsignacion(id).orElseThrow(() -> new AsignacionNoEncontradaException(id));
    }
    
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
    */
}
