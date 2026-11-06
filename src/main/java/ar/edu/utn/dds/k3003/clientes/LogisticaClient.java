package ar.edu.utn.dds.k3003.clientes;


import ar.edu.utn.dds.k3003.catedra.dtos.logistica.AsignacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.AsignacionDirecta;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.PaqueteDTO;
import ar.edu.utn.dds.k3003.model.Asignacion;
import ar.edu.utn.dds.k3003.model.AsignacionesHistorial;
import ar.edu.utn.dds.k3003.model.Deposito;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "logistica", url = "${FACHADA_L}")
public interface LogisticaClient {
    @GetMapping("/asignaciones/{id}")
    public Asignacion getAsignacion(@PathVariable String id);

    @GetMapping("/asignaciones")
    public List<Asignacion> getAsignaciones() ;

    @GetMapping("/asignacionesHistorial")
    public List<AsignacionesHistorial> getAsignacionesHistorial();

    @PostMapping("/asignaciones")
    public AsignacionDTO crearAsignacion(@RequestBody AsignacionDirecta asignacionDirecta);

    @PostMapping("/asignacionesDirecta")
    public Asignacion postAsignacionesDirecta(@RequestBody AsignacionDirecta asignacionDirecta);


    @GetMapping("/paquetes/{id}")
    public Optional<PaqueteDTO> buscarPaquete(@PathVariable("id") String id);
    @GetMapping("/paquetes")
    public List<PaqueteDTO> getPaquetes();




    @GetMapping("/depositos/{id}")
    public Deposito getDeposito(@PathVariable String id);

    @DeleteMapping("/depositos/{id}")
    public ResponseEntity<String> deleteDeposito(@PathVariable String id);

    @GetMapping("/depositos")
    public List<Deposito> getDepositos();

    @PostMapping("/depositos")
    public Deposito postDeposito(@RequestBody Deposito deposito);

    @PostMapping("/depositos/{id}/donacion")
    public String postDonacion(@PathVariable String id, @RequestBody PaqueteDTO paquete);

    @PostMapping("/entregas")
    public String postEntrega(@RequestBody PaqueteDTO paquete);

    @GetMapping("/stock/{productoID}")
    public String getStock(@PathVariable String productoID);

    @PostMapping("/stock/{productoID}")
    public Integer postStock(@PathVariable String productoID, @RequestBody Integer cantidad);

}
