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
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.HistorialEstadoDonadorDTO;

@RestController
@RequestMapping("/donadoresyentidades")
public class DonadoresYEntidadesController {
    private final Fachada fachada;

    public DonadoresYEntidadesController(Fachada fachada) {
        this.fachada = fachada;
    }

    // ----- ------------ -----
    // ----- De Donadores -----
    // ----- ------------ -----

    @PostMapping("/donadores")
    public ResponseEntity<DonadorDTO> registrarDonador(@RequestBody DonadorDTO donadorDTO){
        return fachada.registrarDonador(donadorDTO);
    }

    @GetMapping("/donadores/{id}/estadisticas")
    public ResponseEntity<DonadorStatsDTO> obtenerEstadisticasDonador(@PathVariable String id){
        return fachada.obtenerEstadisticasDonador(id);
    }

    @GetMapping("/donadores/{id}")
    public ResponseEntity<DonadorDTO> buscarDonador(@PathVariable String id){
        return fachada.buscarDonador(id);
    }

    @GetMapping("/donadores")
    public ResponseEntity<List<DonadorDTO>> obtenerDonadores(){
        return fachada.obtenerDonadores();
    }
    /*
    Faltan:
    modificarEstado
    modificarCategoria
    */
    @GetMapping("/donadores/{id}/puede-donar")
    public ResponseEntity<Boolean> puedeDonar(@PathVariable String id){
        return fachada.puedeDonar(id);
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
    public ResponseEntity<List<QuejaDTO>> obtenerQuejas(@PathVariable String id){
        return fachada.obtenerQuejas(id);
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
    public ResponseEntity<EntidadBeneficaDTO> agregarEntidad(@RequestBody EntidadBeneficaDTO entidadDTO){
        return fachada.registrarEntidad(entidadDTO);
    }

    @PutMapping("/entidades/{id}")
    public ResponseEntity<EntidadBeneficaDTO> modificarEntidad(@PathVariable String id, @RequestBody EntidadBeneficaDTO entidadDTO){
        return fachada.modificarEntidad(id, entidadDTO);
    }

    @GetMapping("/entidades")
    public ResponseEntity<List<EntidadBeneficaDTO>> obtenerEntidades(){
        return fachada.obtenerEntidades();
    }

    @GetMapping("/entidades/{id}")
    public ResponseEntity<EntidadBeneficaDTO> buscarEntidad(@PathVariable String id){
        return fachada.buscarEntidad(id);
    }
    
    // ----- -------------- -----
    // ----- De Necesidades -----
    // ----- -------------- -----
    @PostMapping("/necesidades")
    public ResponseEntity<NecesidadMaterialDTO> registrarNecesidad(@RequestBody NecesidadMaterialDTO necesidadDTO){
        return fachada.registrarNecesidad(necesidadDTO);
    }

    @GetMapping("/necesidades")
    public ResponseEntity<List<NecesidadMaterialDTO>> obtenerNecesidades(){
        return fachada.obtenerNecesidades();
    }

    @GetMapping("/necesidades/{necesidadID}")
    public ResponseEntity<NecesidadMaterialDTO> obtenerNecesidad(@PathVariable String necesidadID){
        return fachada.buscarNecesidad(necesidadID);
    }

    @GetMapping("/necesidades/insatisfechas")
    public ResponseEntity<?> obtenerNecesidadesInsatisfechasDe(@RequestParam String productoId){
        try{
            return ResponseEntity.ok(fachada.obtenerNecesidadesInsatisfechasDe(productoId));
        }catch(RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/necesidades/{necesidadID}")
    public ResponseEntity<String> eliminarNecesidad(@PathVariable String necesidadID){
        return ResponseEntity.ok(fachada.eliminarNecesidad(necesidadID));
    }

    @PutMapping("/necesidades/{necesidadID}")
    public ResponseEntity<NecesidadMaterialDTO> modificarNecesidad(@PathVariable String necesidadID, @RequestBody NecesidadMaterialDTO necesidadDTO){
        return fachada.modificarNecesidad(necesidadID, necesidadDTO);
    }

    @PostMapping("/necesidades/{necesidadID}/satisfaccion")
    public ResponseEntity<?> satisfacerNecesidad(@PathVariable String necesidadID, @RequestParam Integer cantidad){
        return fachada.satisfacerNecesidad(necesidadID, cantidad);
    }

}
