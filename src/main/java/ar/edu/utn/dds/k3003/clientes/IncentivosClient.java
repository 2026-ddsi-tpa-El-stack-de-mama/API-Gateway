package ar.edu.utn.dds.k3003.clientes;

import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.CambioCategoriaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.CambioCategoriaRequest;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.InsigniaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.MisionDTO;
import ar.edu.utn.dds.k3003.dtosBot.InsigniasResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "incentivos", url = "${FACHADA_I}")
public interface IncentivosClient {

    @PostMapping("/insignias")
    public ResponseEntity<?> postInsignia(@RequestBody InsigniaDTO insigniaDTO);

    @PutMapping("/insignias/{id}")
    public ResponseEntity<?> putInsignia(@PathVariable("id") String insigniaID, @RequestBody InsigniaDTO insigniaDTO);

    @DeleteMapping("/insignias/{id}")
    public ResponseEntity<?> deleteInsignia(@PathVariable("id") String insigniaID);

    @GetMapping("/insignias")
    public ResponseEntity<List<InsigniaDTO>> getInsignias();

    @GetMapping("/insignias/{id}")
    public ResponseEntity<?> getInsigniaById(@PathVariable("id") String insigniaID);

    // Faltaban:
    // asignarInsigniaADonador
    // getInsigniasDeDonador

    @PostMapping("/insignias/{insigniaID}/donadores/{donadorID}")
    public ResponseEntity<?> asignarInsigniaADonador(
      @PathVariable("insigniaID") String insigniaID,
      @PathVariable("donadorID") String donadorID);

    @GetMapping("/insignias/donadores/{donadorID}")
    public ResponseEntity<?> getInsigniasDeDonador(@PathVariable("donadorID") String donadorID);


    @PatchMapping("/donadores/{donadorID}/categoria")
     public ResponseEntity<?> registrarCambioCategoria(@PathVariable("donadorID") String donadorID, @RequestBody CambioCategoriaRequest request);

    @GetMapping("/donadores/{donadorID}/categorias/historial")
    public ResponseEntity<List<CambioCategoriaDTO>> historialCategorias(@PathVariable("donadorID") String donadorID);


    @PostMapping("/misiones")
    public ResponseEntity<?> postMision(@RequestBody MisionDTO misionDTO);

    @PutMapping("/misiones/{id}")
    public ResponseEntity<?> putMision(
      @PathVariable("id") String misionID,
      @RequestBody MisionDTO misionDTO);

    @DeleteMapping("/misiones/{id}")
    public ResponseEntity<?> deleteMision(@PathVariable("id") String misionID);

    @GetMapping("/misiones")
    public ResponseEntity<List<MisionDTO>> getMisiones();

    @GetMapping("/misiones/{id}")
    public ResponseEntity<?> getMisionById(@PathVariable("id") String misionID);

    @PostMapping("/misiones/{misionID}/donadores/{donadorID}")
    public ResponseEntity<?> asignarMisionADonador(
      @PathVariable("misionID") String misionID,
      @PathVariable("donadorID") String donadorID);

    @GetMapping("/misiones/donadores/{donadorID}/mision")
    public ResponseEntity<?> getMisionEnCursoDeDonador(@PathVariable("donadorID") String donadorID);

    @PostMapping("/misiones/donadores/{donadorID}/procesar")
    public ResponseEntity<?> procesarDonador(@PathVariable("donadorID") String donadorID);

    @GetMapping("/misiones/donadores/{donadorID}/historial")
    public ResponseEntity<?> historialMisiones(@PathVariable("donadorID") String donadorID);
}
