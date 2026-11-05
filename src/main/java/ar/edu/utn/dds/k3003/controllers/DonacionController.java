package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.CategoriaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.IdentificadorDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.ProductoDTO;
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

    @GetMapping("/donaciones/{id}")
	public ResponseEntity<?> getDonacionById(@PathVariable("id") String donacionID) {
        return this.fachada.buscarDonacion(donacionID);
	}
    
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

    @PostMapping("/donaciones/{id}/queja")
    public ResponseEntity<?> registrarQuejaEnDonacion(
            @PathVariable("id") String donacionID, 
            @RequestBody String descripcion){
            return fachada.registrarQuejaEnDonacion(donacionID,descripcion);
    }
    
    // ----- ------------- -----
    // ----- De productos -----
    // ----- ------------- -----

    @PostMapping("/productos")
    public ResponseEntity<?> registrarProducto(@RequestBody ProductoDTO productoDTO) {
        return fachada.registrarProducto(productoDTO);
    }

    @DeleteMapping ("/productos/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable("id") String productoID){
        return ResponseEntity.ok(this.fachada.eliminarProducto(productoID));
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> modificarProducto(
            @PathVariable("id") String productoID,
            @RequestBody ProductoDTO nuevoProductoDTO) {
        return fachada.modificarProducto(nuevoProductoDTO, productoID);
    }
    
    @GetMapping("/productos/{id}")
    public ResponseEntity<?> buscarProducto(String id){
        return fachada.buscarProducto(id);
    }

    @GetMapping("/productos")
    public ResponseEntity<List <ProductoDTO>> obtenerProductos() {
        return fachada.obtenerProductos();
    }
    
}