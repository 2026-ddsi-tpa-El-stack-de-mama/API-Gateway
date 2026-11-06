package ar.edu.utn.dds.k3003.controllers;
import ar.edu.utn.dds.k3003.Fachada;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.DonadorDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.DonadorStatsDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.EntidadBeneficaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.NecesidadMaterialDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.QuejaDTO;
import ar.edu.utn.dds.k3003.exceptions.ManejoDeExcepciones;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.HistorialEstadoDonadorDTO;

// Los que en el modulo original no estaban como ResponseEntity son:
// agregarQueja
// obtenerHistorialEstado
@RestController
@RequestMapping("/donadoresyentidades")
public class DonadoresYEntidadesController {
    private final Fachada fachada;
    private final ManejoDeExcepciones mdExcepciones;
    public DonadoresYEntidadesController(Fachada fachada) {
        this.fachada = fachada;
        this.mdExcepciones= new ManejoDeExcepciones();
    }

    // ----- ------------ -----
    // ----- De Donadores -----
    // ----- ------------ -----

    @PostMapping("/donadores")
    public ResponseEntity<?> registrarDonador(@RequestBody DonadorDTO donadorDTO){
        
        try{
           return fachada.registrarDonador(donadorDTO);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/donadores/{id}/estadisticas")
    public ResponseEntity<?> obtenerEstadisticasDonador(@PathVariable String id){
        try{
           return fachada.obtenerEstadisticasDonador(id);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/donadores/{id}")
    public ResponseEntity<?> buscarDonador(@PathVariable String id){
        
        try{
           return fachada.buscarDonador(id);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/donadores")
    public ResponseEntity<?> obtenerDonadores(){
        

        try{
           return fachada.obtenerDonadores();
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }
    /*
    Faltan:
    modificarEstado
    modificarCategoria
    */
    @GetMapping("/donadores/{id}/puede-donar")
    public ResponseEntity<?> puedeDonar(@PathVariable String id){
        
        try{
           return fachada.puedeDonar(id);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    // Despues tal vez pedirle a Abel que revise como tiene su manejo de exceptions
    @PostMapping("/donadores/{id}/quejas")
    public ResponseEntity<?> registrarQueja(@PathVariable String id, @RequestBody QuejaDTO quejaDTO){
        try{
            QuejaDTO quejaAgregada = fachada.registrarQueja(id, quejaDTO);
            return ResponseEntity.ok(quejaAgregada);
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/donadores/{id}/quejas")
    public ResponseEntity<?> obtenerQuejas(@PathVariable String id){
        
        try{
           return fachada.obtenerQuejas(id);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    // Cuiado que no es un ResponseEntity
    @GetMapping("/donadores/{id}/historial-estados")
    public ResponseEntity<?> obtenerHistorialEstado(@PathVariable String id){
        try{
            return ResponseEntity.ok(fachada.obtenerHistorialEstado(id));
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    // ----- ------------ -----
    // ----- De Entidades -----
    // ----- ------------ -----
    @PostMapping("/entidades")
    public ResponseEntity<?> agregarEntidad(@RequestBody EntidadBeneficaDTO entidadDTO){
        
        try{
           return fachada.registrarEntidad(entidadDTO);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PutMapping("/entidades/{id}")
    public ResponseEntity<?> modificarEntidad(@PathVariable String id, @RequestBody EntidadBeneficaDTO entidadDTO){
        
        try{
           return fachada.modificarEntidad(id, entidadDTO);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/entidades")
    public ResponseEntity<?> obtenerEntidades(){
        
        try{
           return fachada.obtenerEntidades();
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/entidades/{id}")
    public ResponseEntity<?> buscarEntidad(@PathVariable String id){
        
        try{
           return fachada.buscarEntidad(id);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }
    
    // ----- -------------- -----
    // ----- De Necesidades -----
    // ----- -------------- -----
    @PostMapping("/necesidades")
    public ResponseEntity<?> registrarNecesidad(@RequestBody NecesidadMaterialDTO necesidadDTO){
        
        try{
           return fachada.registrarNecesidad(necesidadDTO);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/necesidades")
    public ResponseEntity<?> obtenerNecesidades(){
        
        try{
           return fachada.obtenerNecesidades();
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/necesidades/{necesidadID}")
    public ResponseEntity<?> obtenerNecesidad(@PathVariable String necesidadID){
        
        try{
           return fachada.buscarNecesidad(necesidadID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/necesidades/insatisfechas")
    public ResponseEntity<?> obtenerNecesidadesInsatisfechasDe(@RequestParam String productoId){
        
        try{
           return fachada.obtenerNecesidadesInsatisfechasDe(productoId);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
        //try{
        //    return ResponseEntity.ok(fachada.obtenerNecesidadesInsatisfechasDe(productoId));
        //}catch(RuntimeException ex){
        //    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        //}
    }

    @DeleteMapping("/necesidades/{necesidadID}")
    public ResponseEntity<?> eliminarNecesidad(@PathVariable String necesidadID){
        
        try{
           return ResponseEntity.ok(fachada.eliminarNecesidad(necesidadID));
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PutMapping("/necesidades/{necesidadID}")
    public ResponseEntity<?> modificarNecesidad(@PathVariable String necesidadID, @RequestBody NecesidadMaterialDTO necesidadDTO){
        
        try{
           return fachada.modificarNecesidad(necesidadID, necesidadDTO);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PostMapping("/necesidades/{necesidadID}/satisfaccion")
    public ResponseEntity<?> satisfacerNecesidad(@PathVariable String necesidadID, @RequestParam Integer cantidad){
        
        try{
           return fachada.satisfacerNecesidad(necesidadID, cantidad);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

}
