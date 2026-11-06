package ar.edu.utn.dds.k3003.controllers;
import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.InsigniaDTO;

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


}
