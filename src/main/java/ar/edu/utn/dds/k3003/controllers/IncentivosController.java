package ar.edu.utn.dds.k3003.controllers;
import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.InsigniaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.MisionDTO;
import ar.edu.utn.dds.k3003.exceptions.ManejoDeExcepciones;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.CambioCategoriaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.CambioCategoriaRequest;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/incentivos")
public class IncentivosController {
    private final Fachada fachada;
    private final ManejoDeExcepciones mdExcepciones;
    
    public IncentivosController(Fachada fachada) {
        this.fachada = fachada;
        this.mdExcepciones= new ManejoDeExcepciones();
    }

    // ----- ------------ -----
    // ----- De Insignias -----
    // ----- ------------ -----
    @PostMapping("/insignias")
    public ResponseEntity<?> postInsignia(@RequestBody InsigniaDTO insigniaDTO){
        
        try{
           return fachada.registrarInsignia(insigniaDTO);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PutMapping("/insignias/{id}")
    public ResponseEntity<?> putInsignia(@PathVariable("id") String insigniaID, @RequestBody InsigniaDTO insigniaDTO){
        
        try{
           return fachada.modificarInsignia(insigniaID, insigniaDTO);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @DeleteMapping("/insignias/{id}")
    public ResponseEntity<?> deleteInsignia(@PathVariable("id") String insigniaID){
        
        try{
           return fachada.eliminarInsignia(insigniaID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/insignias")
    public ResponseEntity<?> getInsignias(){
        
        try{
           return fachada.obtenerInsignias();
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/insignias/{id}")
    public ResponseEntity<?> getInsigniaById(@PathVariable("id") String insigniaID){
        
        try{
           return fachada.buscarInsignia(insigniaID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PostMapping("/insignias/{insigniaID}/donadores/{donadorID}")
    public ResponseEntity<?> asignarInsigniaADonador(
      @PathVariable("insigniaID") String insigniaID,
      @PathVariable("donadorID") String donadorID){
        
        try{
           return fachada.asignarInsigniaADonador(insigniaID,donadorID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/insignias/donadores/{donadorID}")
    public ResponseEntity<?> getInsigniasDeDonador(@PathVariable("donadorID") String donadorID){
        
        try{
           return fachada.getInsigniasDeDonador(donadorID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    // ------------------------
    // ----- De Donadores -----
    // ------------------------
    @PatchMapping("/donadores/{donadorID}/categoria")
    public ResponseEntity<?> registrarCambioCategoria(
      @PathVariable("donadorID") String donadorID,
      @RequestBody CambioCategoriaRequest request){
        
        try{
           return fachada.registrarCambioCategoria(donadorID,request);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
      }

    @GetMapping("/donadores/{donadorID}/categorias/historial")
    public ResponseEntity<?> historialCategorias(@PathVariable("donadorID") String donadorID){
        
        try{
           return fachada.historialCategorias(donadorID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    // -----------------------
    // ----- De Misiones -----
    // -----------------------
    @PostMapping("/misiones")
    public ResponseEntity<?> registrarMision(@RequestBody MisionDTO misionDTO){
        
        try{
           return fachada.registrarMision(misionDTO);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PutMapping("/misiones/{id}")
    public ResponseEntity<?> putMision(
      @PathVariable("id") String misionID,
      @RequestBody MisionDTO misionDTO){
        
        try{
           return fachada.modificarMision(misionID,misionDTO);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
      }

    @DeleteMapping("/misiones/{id}")
    public ResponseEntity<?> deleteMision(@PathVariable("id") String misionID){
        
        try{
           return fachada.eliminarMision(misionID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/misiones")
    public ResponseEntity<?> getMisiones(){
        
        try{
           return fachada.obtenerMisiones();
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/misiones/{id}")
    public ResponseEntity<?> getMisionById(@PathVariable("id") String misionID){
        
        try{
           return fachada.buscarMision(misionID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PostMapping("/misiones/{misionID}/donadores/{donadorID}")
    public ResponseEntity<?> asignarMisionADonador(
      @PathVariable("misionID") String misionID,
      @PathVariable("donadorID") String donadorID){
        
        try{
           return fachada.asignarMisionADonador(misionID, donadorID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
      }

    @GetMapping("/misiones/donadores/{donadorID}/mision")
    public ResponseEntity<?> getMisionEnCursoDeDonador(@PathVariable("donadorID") String donadorID){
        
        try{
           return fachada.getMisionEnCursoDeDonador(donadorID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PostMapping("/misiones/donadores/{donadorID}/procesar")
    public ResponseEntity<?> procesarDonador(@PathVariable("donadorID") String donadorID){
        
        try{
           return fachada.procesarDonador(donadorID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/misiones/donadores/{donadorID}/historial")
    public ResponseEntity<?> historialMisiones(@PathVariable("donadorID") String donadorID){
        
        try{
           return fachada.historialMisiones(donadorID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }
}
