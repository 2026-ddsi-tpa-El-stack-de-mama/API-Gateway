package ar.edu.utn.dds.k3003.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import java.util.List;


import ar.edu.utn.dds.k3003.clases.EstadoDepositos;
import ar.edu.utn.dds.k3003.clases.FichaIntegralDonador;

@FeignClient(name = "consultas", url = "${FACHADA_C}")
public interface ConsultasClient {

    @GetMapping("/consultas/fichaIntegralDonador/{id}")
    Optional<FichaIntegralDonador> getFichaIntegralDonador(@PathVariable String id);

    @GetMapping("/consultas/estadoDepositos/{id}")
    Optional<EstadoDepositos> getEstadoDeposito(@PathVariable String id);

    @GetMapping("/consultas/estadoDepositos")
    Optional<List<EstadoDepositos>> getEstadoDepositos();

}
