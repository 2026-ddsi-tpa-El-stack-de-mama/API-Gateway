package ar.edu.utn.dds.k3003.clientes;

import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.DonadorDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.DonadorStatsDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.EntidadBeneficaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.HistorialEstadoDonadorDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.NecesidadMaterialDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.QuejaDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "donadoresYEntidades", url = "${FACHADA_DYE}")
public interface DonadoresYEntidadesClient {

    @PostMapping("/necesidades")
    public ResponseEntity<NecesidadMaterialDTO> registrarNecesidad(@RequestBody NecesidadMaterialDTO necesidadDTO);

    @DeleteMapping("/necesidades/{necesidadID}")
    public ResponseEntity<String> eliminarNecesidad(@PathVariable String necesidadID);

    @PutMapping("/necesidades/{necesidadID}")
    public ResponseEntity<NecesidadMaterialDTO> modificarNecesidad(@PathVariable String necesidadID, @RequestBody NecesidadMaterialDTO necesidadDTO);

    // Antes no era public  // -- Es "De" un producto, no trae todas las necesidades instisfechas
    // Cuidado que no es ResponseEntity
    @GetMapping("/necesidades/insatisfechas")
    public List<NecesidadMaterialDTO> obtenerNecesidadesInsatisfechasDe(@RequestParam String productoId);

    // Antes no era public ni tenía la primera "/"
    // Cuidado que no es un ResponseEntity
    @PostMapping("/necesidades/{necesidadID}/satisfaccion")
    public NecesidadMaterialDTO satisfacerNecesidad(@PathVariable String necesidadID, @RequestParam Integer cantidad);

    @GetMapping("/necesidades/{necesidadID}")
    public ResponseEntity<NecesidadMaterialDTO> obtenerNecesidad(@PathVariable String necesidadID);

    @GetMapping("/necesidades")
    public ResponseEntity<List<NecesidadMaterialDTO>> obtenerNecesidades();

    @PostMapping("/donadores")
    public ResponseEntity<DonadorDTO> agregarDonador(@RequestBody DonadorDTO donadorDTO);

    @GetMapping("/donadores/{id}/estadisticas")
    public ResponseEntity<DonadorStatsDTO> estadisticas(@PathVariable String id);

    // Antes no tenía la primera "/"
    @GetMapping("/donadores/{id}")
    public ResponseEntity<DonadorDTO> buscarDonador(@PathVariable String id);

    @GetMapping("/donadores")
    public ResponseEntity<List<DonadorDTO>> obtenerDonadores();

    @GetMapping("/donadores/{id}/puede-donar")
    public ResponseEntity<Boolean> puedeDonar(@PathVariable String id);

    // CUIDADO QUE NO ES UN RESPONSE ENTITY
    @PostMapping("/donadores/{id}/quejas")
    public QuejaDTO agregarQueja(@PathVariable String id, @RequestBody QuejaDTO quejaDTO);

    @GetMapping("/donadores/{id}/quejas")
    public ResponseEntity<List<QuejaDTO>> obtenerQuejas(@PathVariable String id);

    @GetMapping("/donadores/{id}/historial-estados")
    public List<HistorialEstadoDonadorDTO> obtenerHistorialEstado(@PathVariable String id);

    @PostMapping("/entidades")
    public ResponseEntity<EntidadBeneficaDTO> agregarEntidad(@RequestBody EntidadBeneficaDTO entidadDTO);

    @PutMapping("/entidades/{id}")
    public ResponseEntity<EntidadBeneficaDTO> modificarEntidad(@PathVariable String id, @RequestBody EntidadBeneficaDTO entidadDTO);

    @GetMapping("/entidades")
    public ResponseEntity<List<EntidadBeneficaDTO>> obtenerEntidades();

    @GetMapping("/entidades/{id}")
    public ResponseEntity<EntidadBeneficaDTO> buscarEntidad(@PathVariable String id);

}
