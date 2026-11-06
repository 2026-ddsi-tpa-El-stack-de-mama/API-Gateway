package ar.edu.utn.dds.k3003.clientes;


import ar.edu.utn.dds.k3003.catedra.dtos.logistica.PaqueteDTO;
import ar.edu.utn.dds.k3003.model.Asignacion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "logistica", url = "${FACHADA_L}")
public interface LogisticaClient {
    @GetMapping("/asignaciones/{id}")
    public Asignacion getAsignacion(@PathVariable String id);

    @GetMapping("/asignaciones")
    public List<Asignacion> getAsignaciones() ;




    @GetMapping("/paquetes/{id}")
    public Optional<PaqueteDTO> buscarPaquete(@PathVariable("id") String id);
    @GetMapping("/paquetes")
    public List<PaqueteDTO> getPaquetes();
}
