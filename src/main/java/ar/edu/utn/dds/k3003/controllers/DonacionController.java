package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.EstadoDonacionEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//
//import ar.edu.utn.dds.k3003.services.DonacionService;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.MDC;




@RestController
@RequestMapping("/donaciones")
public class DonacionController {

    private final Fachada fachada;

    public DonacionController(Fachada fachada) {
        this.fachada = fachada;
    }
    // ----- ------------- -----
    // ----- De donaciones -----
    // ----- ------------- -----

    @PostMapping("/donaciones")
    public ResponseEntity<?> postDonacion(@RequestBody DonacionDTO donacionDTO) {
        return fachada.registrarDonacion(donacionDTO);
        
    }

    // Versión nueva de getDonacionByID
    @GetMapping("/donaciones/{id}")
	public ResponseEntity<?> getDonacionById(@PathVariable("id") String donacionID) {
        return this.fachada.buscarDonacion(donacionID);
	}
    //Agregado
    @GetMapping("/donaciones")
    public ResponseEntity<?> getDonaciones(){
        return this.fachada.obtenerDonaciones();
    }

    @DeleteMapping("/donaciones/{id}")
    public ResponseEntity<DonacionDTO> deleteDonacion(@PathVariable("id") String donacionID) {
        //DonacionDTO donacionEliminada = fachada.eliminarDonacion(donacionID);
        //return ResponseEntity.ok(donacionEliminada);
        return this.fachada.eliminarDonacion(donacionID);
    
    }
    
    @GetMapping("/donaciones/search/{id}/{date}")
    public ResponseEntity<?> findByDonadorAndFechaInicio(@PathVariable("id") String donadorID, @PathVariable("date") LocalDate fecha){
        return this.fachada.findByDonadorAndFechaInicio(donadorID, fecha);
    }

    @PatchMapping("/donaciones/{id}/estado")
    public ResponseEntity<?> cambiarEstadoDeDonacion(
            @PathVariable("id") String donacionID, 
            @RequestParam EstadoDonacionEnum estado){
        return fachada.cambiarEstadoDeDonacion(donacionID, estado);  
    }

    //Versión nueva de registrarQuejaEnDonacion
    @PostMapping("/donaciones/{id}/queja")
    public ResponseEntity<?> registrarQuejaEnDonacion(
            @PathVariable("id") String donacionID, 
            @RequestBody String descripcion){
            return fachada.registrarQuejaEnDonacion(donacionID,descripcion);
    }
    
}