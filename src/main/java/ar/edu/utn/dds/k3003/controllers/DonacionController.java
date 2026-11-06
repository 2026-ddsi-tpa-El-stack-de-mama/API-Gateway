package ar.edu.utn.dds.k3003.controllers;

import ar.edu.utn.dds.k3003.Fachada;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionHistDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.CategoriaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.IdentificadorDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.ProductoDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.EstadoDonacionEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.edu.utn.dds.k3003.exceptions.ManejoDeExcepciones;

//
//import ar.edu.utn.dds.k3003.services.DonacionService;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.MDC;

@RestController
@RequestMapping("/donaciones")
public class DonacionController {

    private final Fachada fachada;
    private final ManejoDeExcepciones mdExcepciones;

    public DonacionController(Fachada fachada, ManejoDeExcepciones mdExcepciones) {
        this.fachada = fachada;
        this.mdExcepciones= mdExcepciones;
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
        try{
            return this.fachada.buscarDonacion(donacionID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
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
    
    // ----- ---------------- -----
    // ----- De identificador -----
    // ----- ---------------- -----

    @PostMapping("/identificadores")
    public ResponseEntity<IdentificadorDTO> registrarIdentificador(@RequestBody IdentificadorDTO identificadorDTO) {
        return fachada.registrarIdentificador(identificadorDTO);
    }
    
    @GetMapping("/identificadores")
    public ResponseEntity<List<IdentificadorDTO>> obtenerIdentificadores() {
        return fachada.obtenerIdentificadores();
    }
    
    @DeleteMapping("/identificadores/{id}")
    public ResponseEntity<String> eliminarIdentificador(@PathVariable("id") String identificadorID){
        return ResponseEntity.ok(fachada.eliminarIdentificador(identificadorID));
    }

    // ----- ------------ -----
    // ----- De categoria -----
    // ----- ------------ -----

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaDTO> registrarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return fachada.registrarCategoria(categoriaDTO);
    }
    
    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaDTO>> obtenerCategorias() {
        return fachada.obtenerCategorias();
    }
    
    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable("id") String categoriaID){
        return ResponseEntity.ok(fachada.eliminarCategoria(categoriaID));
    }

    // ----- ----------------- -----
    // ----- De DonacionesHist -----
    // ----- ----------------- -----

    @GetMapping("/donacioneshist")
    public ResponseEntity<List<DonacionHistDTO>> obtenerDonacionesHist() {
        return fachada.obtenerDonacionesHist();
    }

    @GetMapping("/donacioneshist/{id}")
    public ResponseEntity<List<DonacionHistDTO>> buscarDonacionesHist(@PathVariable("id") String donacionID) {
        return fachada.buscarDonacionesHist(donacionID);
    }

}