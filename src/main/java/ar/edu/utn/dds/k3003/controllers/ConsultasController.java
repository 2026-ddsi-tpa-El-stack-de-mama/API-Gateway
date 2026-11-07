package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.clases.CalidadDonacion;
import ar.edu.utn.dds.k3003.clases.EstadoDepositos;
import ar.edu.utn.dds.k3003.clases.FichaIntegralDonador;
import ar.edu.utn.dds.k3003.clases.NecesidadesUrgentesSinCubrir;
import ar.edu.utn.dds.k3003.Fachada;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsultasController {

    private final Fachada fachada;

    public ConsultasController(Fachada fachada) {
        this.fachada = fachada;
    }

    @GetMapping("/consultas/fichaIntegralDonador/{id}")
    public Optional<FichaIntegralDonador> getFichaIntegralDonador(@PathVariable String id){
        return fachada.getFichaIntegralDonador(id);
    }

    @GetMapping("/consultas/fichaIntegralDonador")
    Optional<List<FichaIntegralDonador>> getFichaIntegralDonadores(){
        return fachada.getFichaIntegralDonadores();
    }

    @GetMapping("/consultas/estadoDepositos/{id}")
    public Optional<EstadoDepositos> getEstadoDeposito(@PathVariable String id){
        return fachada.getEstadoDeposito(id);
    }

    @GetMapping("/consultas/estadoDepositos")
    public Optional<List<EstadoDepositos>> getEstadoDepositos(){
        return fachada.getEstadoDepositos();
    }

    @GetMapping("/consultas/calidadDonacion/{id}")
    Optional<CalidadDonacion> getCalidadDonacion(@PathVariable String id){
        return fachada.getCalidadDonacion(id);
    }

    @GetMapping("/consultas/calidadDonacion")
    Optional<List<CalidadDonacion>> getCalidadDonaciones(){
        return fachada.getCalidadDonaciones();
    }

    @GetMapping("/consultas/necesidadesUrgentesSinCubrir/{id}")
    Optional<NecesidadesUrgentesSinCubrir> getNecesidadUrgenteSinCubrir(@PathVariable String id){
        return fachada.getNecesidadUrgenteSinCubrir(id);
    }

    @GetMapping("/consultas/necesidadesUrgentesSinCubrir")
    Optional<List<NecesidadesUrgentesSinCubrir>> getNecesidadesUrgentesSinCubrir(){
        return fachada.getNecesidadesUrgentesSinCubrir();
    }

}