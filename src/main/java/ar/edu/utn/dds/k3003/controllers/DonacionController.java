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

    public DonacionController(Fachada fachada) {
        this.fachada = fachada;
        this.mdExcepciones= new ManejoDeExcepciones();
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
        try{
            return this.fachada.obtenerDonaciones();
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @DeleteMapping("/donaciones/{id}")
    public ResponseEntity<?> deleteDonacion(@PathVariable("id") String donacionID) {
        //DonacionDTO donacionEliminada = fachada.eliminarDonacion(donacionID);
        //return ResponseEntity.ok(donacionEliminada);
        

        try{
            return this.fachada.eliminarDonacion(donacionID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }
    
    @GetMapping("/donaciones/search/{id}/{date}")
    public ResponseEntity<?> findByDonadorAndFechaInicio(@PathVariable("id") String donadorID, @PathVariable("date") LocalDate fecha){
        
        try{
            return this.fachada.findByDonadorAndFechaInicio(donadorID, fecha);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PatchMapping("/donaciones/{id}/estado")
    public ResponseEntity<?> cambiarEstadoDeDonacion(
            @PathVariable("id") String donacionID, 
            @RequestParam EstadoDonacionEnum estado){
        
        try{
            return fachada.cambiarEstadoDeDonacion(donacionID, estado);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PostMapping("/donaciones/{id}/queja")
    public ResponseEntity<?> registrarQuejaEnDonacion(
            @PathVariable("id") String donacionID, 
            @RequestBody String descripcion){
            
        try{
            return fachada.registrarQuejaEnDonacion(donacionID,descripcion);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }
    
    // ----- ------------- -----
    // ----- De productos -----
    // ----- ------------- -----

    @PostMapping("/productos")
    public ResponseEntity<?> registrarProducto(@RequestBody ProductoDTO productoDTO) {
        
        try{
            return fachada.registrarProducto(productoDTO);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @DeleteMapping ("/productos/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable("id") String productoID){
        
        try{
            return this.fachada.eliminarProducto(productoID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<?> modificarProducto(
            @PathVariable("id") String productoID,
            @RequestBody ProductoDTO nuevoProductoDTO) {
        
        try{
            return fachada.modificarProducto(nuevoProductoDTO, productoID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }
    
    @GetMapping("/productos/{id}")
    public ResponseEntity<?> getProductoByID(String id){
        
        try{
            return fachada.getProductoByID(id);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    @GetMapping("/productos")
    public ResponseEntity<?> obtenerProductos() {
        
        try{
            return fachada.obtenerProductos();
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }
    
    // ----- ---------------- -----
    // ----- De identificador -----
    // ----- ---------------- -----

    @PostMapping("/identificadores")
    public ResponseEntity<?> registrarIdentificador(@RequestBody IdentificadorDTO identificadorDTO) {
        
        try{
            return fachada.registrarIdentificador(identificadorDTO);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }
    
    @GetMapping("/identificadores")
    public ResponseEntity<?> obtenerIdentificadores() {
        
        try{
           return fachada.obtenerIdentificadores();
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }
    
    @DeleteMapping("/identificadores/{id}")
    public ResponseEntity<?> eliminarIdentificador(@PathVariable("id") String identificadorID){
        
        try{
           return fachada.eliminarIdentificador(identificadorID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    // ----- ------------ -----
    // ----- De categoria -----
    // ----- ------------ -----

    @PostMapping("/categorias")
    public ResponseEntity<?> registrarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        
        try{
           return fachada.registrarCategoria(categoriaDTO);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }
    
    @GetMapping("/categorias")
    public ResponseEntity<?> obtenerCategorias() {
        
        try{
           return fachada.obtenerCategorias();
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }
    
    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable("id") String categoriaID){
        
        try{
           return fachada.eliminarCategoria(categoriaID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

    // ----- ----------------- -----
    // ----- De DonacionesHist -----
    // ----- ----------------- -----

    @GetMapping("/donacioneshist")
    public ResponseEntity<?> obtenerDonacionesHist() {
        try{
           return fachada.obtenerDonacionesHist();
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
        
    }

    @GetMapping("/donacioneshist/{id}")
    public ResponseEntity<?> buscarDonacionesHist(@PathVariable("id") String donacionID) {
        
        try{
           return fachada.buscarDonacionesHist(donacionID);
        } catch(RuntimeException ex){
            return mdExcepciones.manejarRuntimeException(ex);
        }
    }

}