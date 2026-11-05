package ar.edu.utn.dds.k3003.clientes;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.CategoriaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.EstadoDonacionEnum;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.IdentificadorDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.ProductoDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionHistDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "donaciones", url = "${FACHADA_D}")
public interface DonacionesClient {

    @PostMapping("/donaciones")
    public ResponseEntity<?> postDonacion(@RequestBody DonacionDTO donacionDTO);

    @GetMapping("/donaciones/{id}")
    public ResponseEntity<?> getDonacionById(@PathVariable("id") String donacionID);

    @GetMapping("/donaciones")
    public ResponseEntity<List <DonacionDTO>> getDonaciones();

    @DeleteMapping("/donaciones/{id}")
    public ResponseEntity<DonacionDTO> deleteDonacion(@PathVariable("id") String donacionID);

    @GetMapping("/donaciones/search/{id}/{date}")
    public ResponseEntity<?> findByDonadorAndFechaInicio(@PathVariable("id") String donadorID, @PathVariable("date") LocalDate fecha);
    
    @PatchMapping("/donaciones/{id}/estado")
    public ResponseEntity<?> cambiarEstadoDeDonacion(@PathVariable("id") String donacionID, @RequestParam EstadoDonacionEnum estado);
    
    @PostMapping("/donaciones/{id}/queja")
    public ResponseEntity<?> registrarQuejaEnDonacion(
            @PathVariable("id") String donacionID, 
            @RequestBody String descripcion);

    @PostMapping("/productos")
    public ResponseEntity<?> postProducto(@RequestBody ProductoDTO productoDTO);

    @GetMapping("/productos/{id}")
    public ResponseEntity<?> getProductoByID(@PathVariable("id") String productoID);

    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDTO>> getProductos();

    @PutMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> putProducto(@PathVariable("id") String productoID, @RequestParam ProductoDTO nuevoProductoDTO);

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> deleteProducto(@PathVariable("id") String productoID);

    
    @PostMapping("/identificadores")
    public ResponseEntity<IdentificadorDTO> postIdentificador(@RequestBody IdentificadorDTO identificadorDTO);

    @GetMapping("/identificadores")
    public ResponseEntity<List <IdentificadorDTO>> getIdentificadores();

    @DeleteMapping("/identificadores/{id}")
    public ResponseEntity<IdentificadorDTO> deleteIdentificador(@PathVariable("id") String identificadorID);

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaDTO> postCategoria(@RequestBody CategoriaDTO categoriaDTO);

    @GetMapping("/categorias")
    public ResponseEntity<List <CategoriaDTO>> getCategorias();

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<CategoriaDTO> deleteCategoria(@PathVariable("id") String categoriaID);

    @GetMapping("/donacioneshist")
    public ResponseEntity<List <DonacionHistDTO>> getDonacionesHist();

    @GetMapping("/donacioneshist/{id}")
    public ResponseEntity<List<DonacionHistDTO>> getDonacionesHistPorID(@PathVariable("id") String donacionID);
}
