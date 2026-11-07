package ar.edu.utn.dds.k3003.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import java.util.List;


import ar.edu.utn.dds.k3003.clases.*;

@FeignClient(name = "consultas", url = "${FACHADA_C}")
public interface ConsultasClient {

    @GetMapping("/consultas/fichaIntegralDonador/{id}")
    Optional<FichaIntegralDonador> getFichaIntegralDonador(@PathVariable String id);

    @GetMapping("/consultas/fichaIntegralDonador")
    Optional<List<FichaIntegralDonador>> getFichaIntegralDonadores();

    @GetMapping("/consultas/estadoDepositos/{id}")
    Optional<EstadoDepositos> getEstadoDeposito(@PathVariable String id);

    @GetMapping("/consultas/estadoDepositos")
    Optional<List<EstadoDepositos>> getEstadoDepositos();

    @GetMapping("/consultas/calidadDonacion/{id}")
    Optional<CalidadDonacion> getCalidadDonacion(@PathVariable String id);

    @GetMapping("/consultas/calidadDonacion")
    Optional<List<CalidadDonacion>> getCalidadDonaciones();

    @GetMapping("/consultas/necesidadesUrgentesSinCubrir/{id}")
    Optional<NecesidadesUrgentesSinCubrir> getNecesidadUrgenteSinCubrir(@PathVariable String id);

    @GetMapping("/consultas/necesidadesUrgentesSinCubrir")
    Optional<List<NecesidadesUrgentesSinCubrir>> getNecesidadesUrgentesSinCubrir();

}
