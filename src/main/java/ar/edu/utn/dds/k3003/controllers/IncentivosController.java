package ar.edu.utn.dds.k3003.controllers;
import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.InsigniaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.MisionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.CambioCategoriaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.CambioCategoriaRequest;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/incentivos")
public class IncentivosController {
    private final Fachada fachada;
    
    public IncentivosController(Fachada fachada) {
        this.fachada = fachada;
    }

    // ----- ------------ -----
    // ----- De Insignias -----
    // ----- ------------ -----
    @PostMapping("/insignias")
    public ResponseEntity<?> postInsignia(@RequestBody InsigniaDTO insigniaDTO){
        return fachada.registrarInsignia(insigniaDTO);
    }

    @PutMapping("/insignias/{id}")
    public ResponseEntity<?> putInsignia(@PathVariable("id") String insigniaID, @RequestBody InsigniaDTO insigniaDTO){
        return fachada.modificarInsignia(insigniaID, insigniaDTO);
    }

    @DeleteMapping("/insignias/{id}")
    public ResponseEntity<String> deleteInsignia(@PathVariable("id") String insigniaID){
        return ResponseEntity.ok(fachada.eliminarInsignia(insigniaID));
    }

    @GetMapping("/insignias")
    public ResponseEntity<List<InsigniaDTO>> getInsignias(){
        return fachada.obtenerInsignias();
    }

    @GetMapping("/insignias/{id}")
    public ResponseEntity<?> getInsigniaById(@PathVariable("id") String insigniaID){
        return fachada.buscarInsignia(insigniaID);
    }

    @PostMapping("/insignias/{insigniaID}/donadores/{donadorID}")
    public ResponseEntity<?> asignarInsigniaADonador(
      @PathVariable("insigniaID") String insigniaID,
      @PathVariable("donadorID") String donadorID){
        return fachada.asignarInsigniaADonador(insigniaID,donadorID);
    }

    @GetMapping("/insignias/donadores/{donadorID}")
    public ResponseEntity<?> getInsigniasDeDonador(@PathVariable("donadorID") String donadorID){
        return fachada.getInsigniasDeDonador(donadorID);
    }

    // ------------------------
    // ----- De Donadores -----
    // ------------------------
    @PatchMapping("/donadores/{donadorID}/categoria")
    public ResponseEntity<?> registrarCambioCategoria(
      @PathVariable("donadorID") String donadorID,
      @RequestBody CambioCategoriaRequest request){
        return fachada.registrarCambioCategoria(donadorID,request);
      }

    @GetMapping("/donadores/{donadorID}/categorias/historial")
    public ResponseEntity<List<CambioCategoriaDTO>> historialCategorias(@PathVariable("donadorID") String donadorID){
        return fachada.historialCategorias(donadorID);
    }

    // -----------------------
    // ----- De Misiones -----
    // -----------------------
    @PostMapping("/misiones")
    public ResponseEntity<?> registrarMision(@RequestBody MisionDTO misionDTO){
        return fachada.registrarMision(misionDTO);
    }

    @PutMapping("/misiones/{id}")
    public ResponseEntity<?> putMision(
      @PathVariable("id") String misionID,
      @RequestBody MisionDTO misionDTO){
        return fachada.modificarMision(misionID,misionDTO);
      }

    @DeleteMapping("/misiones/{id}")
    public ResponseEntity<?> deleteMision(@PathVariable("id") String misionID){
        return fachada.eliminarMision(misionID);
    }

    @GetMapping("/misiones")
    public ResponseEntity<List<MisionDTO>> getMisiones(){
        return fachada.obtenerMisiones();
    }

    @GetMapping("/misiones/{id}")
    public ResponseEntity<?> getMisionById(@PathVariable("id") String misionID){
        return fachada.buscarMision(misionID);
    }

    @PostMapping("/misiones/{misionID}/donadores/{donadorID}")
    public ResponseEntity<?> asignarMisionADonador(
      @PathVariable("misionID") String misionID,
      @PathVariable("donadorID") String donadorID){
        return fachada.asignarMisionADonador(misionID, donadorID);
      }

    @GetMapping("/misiones/donadores/{donadorID}/mision")
    public ResponseEntity<?> getMisionEnCursoDeDonador(@PathVariable("donadorID") String donadorID){
        return fachada.getMisionEnCursoDeDonador(donadorID);
    }

    @PostMapping("/misiones/donadores/{donadorID}/procesar")
    public ResponseEntity<?> procesarDonador(@PathVariable("donadorID") String donadorID){
        return fachada.procesarDonador(donadorID);
    }

    @GetMapping("/misiones/donadores/{donadorID}/historial")
    public ResponseEntity<?> historialMisiones(@PathVariable("donadorID") String donadorID){
        return fachada.historialMisiones(donadorID);
    }
}
