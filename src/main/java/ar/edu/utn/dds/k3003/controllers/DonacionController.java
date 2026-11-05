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
        //Para prueba
        String requestId = MDC.get("request_id");
        try{
        System.out.println(donacionDTO);
        DonacionDTO donacionAgregada = fachada.registrarDonacion(donacionDTO);
        return ResponseEntity.ok(donacionAgregada);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("X-Request-Id", requestId).body(ex.getMessage());
        }
    }

    // Versión nueva de getDonacionByID
    @GetMapping("/donaciones/{id}")
	public ResponseEntity<?> getDonacionById(@PathVariable("id") String donacionID) {
	    String requestId = MDC.get("request_id");
        try{
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.buscarDonacion(donacionID));
        }catch(RuntimeException ex){
            if (ex.getMessage() != null && ex.getMessage().toLowerCase().contains("no existe")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).header("X-Request-Id", requestId).body(ex.getMessage());
            }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("X-Request-Id", requestId).body(ex.getMessage());
        }
	}
    //Agregado
    @GetMapping("/donaciones")
    public ResponseEntity<?> getDonaciones(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.obtenerDonaciones());
    
    }

    // ------------------- PARA PRUEBA -----------------
    @GetMapping("/donaciones/prueba")
    public ResponseEntity<?> getDonacionesPrueba(){
        return this.fachada.obtenerDonaciones();
    
    }
    /*
    @DeleteMapping("/donaciones")
    public ResponseEntity<Integer> deleteDonaciones() {
        fachada.borrarDonaciones();
        return ResponseEntity.ok(200);
    }
    */
    @DeleteMapping("/donaciones/{id}")
    public ResponseEntity<DonacionDTO> deleteDonacion(@PathVariable("id") String donacionID) {
        //DonacionDTO donacionEliminada = fachada.eliminarDonacion(donacionID);
        //return ResponseEntity.ok(donacionEliminada);
        return this.fachada.eliminarDonacion(donacionID);
    
    }

    /*
    @GetMapping("/donaciones/search/{id}/{date}")
    public ResponseEntity<?> findByDonadorAndFechaInicio(@PathVariable("id") String donadorID, @PathVariable("date") LocalDate fecha){
        String requestId = MDC.get("request_id");
        try{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.fachada.findByDonadorAndFechaInicio(donadorID, fecha)) ;
        }catch(RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("X-Request-Id", requestId).body(ex.getMessage());
        }
    }

    @PatchMapping("/donaciones/{id}/estado")
    public ResponseEntity<?> cambiarEstadoDeDonacion(
            @PathVariable("id") String donacionID, 
            @RequestParam EstadoDonacionEnum estado){
        String requestId = MDC.get("request_id");
        try{
        DonacionDTO nuevaVersionDonacion = fachada.cambiarEstadoDeDonacion(donacionID, estado);
        return ResponseEntity.ok(nuevaVersionDonacion);
        }catch(RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("X-Request-Id", requestId).body(ex.getMessage());
        }
    }

    //Versión nueva de registrarQuejaEnDonacion
    @PostMapping("/donaciones/{id}/queja")
    public ResponseEntity<?> registrarQuejaEnDonacion(
            @PathVariable("id") String donacionID, 
            @RequestBody String descripcion){
        String requestId = MDC.get("request_id");
        try{
            DonacionDTO donacionConQueja = fachada.registrarQuejaEnDonacion(donacionID,descripcion);
            return ResponseEntity.ok(donacionConQueja);
        }catch(RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("X-Request-Id", requestId).body(ex.getMessage());
        }
    }
    */
}