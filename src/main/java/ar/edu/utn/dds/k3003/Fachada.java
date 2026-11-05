package ar.edu.utn.dds.k3003;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.CategoriaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionHistDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.EstadoDonacionEnum;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.IdentificadorDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.ProductoDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.DonadorDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.DonadorStatsDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.EntidadBeneficaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.NecesidadMaterialDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.InsigniaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.PaqueteDTO;
import ar.edu.utn.dds.k3003.clientes.DonacionesClient;
import ar.edu.utn.dds.k3003.clientes.DonadoresYEntidadesClient;

import ar.edu.utn.dds.k3003.clientes.IncentivosClient;
import ar.edu.utn.dds.k3003.clientes.LogisticaClient;
import ar.edu.utn.dds.k3003.dtosBot.InsigniasResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class Fachada {

    private final DonadoresYEntidadesClient donadoresYEntidadesClient;
    private final DonacionesClient donacionesClient;
    private final IncentivosClient incentivosClient;
    private final LogisticaClient logisticaClient;

    public Fachada(DonadoresYEntidadesClient donadoresYEntidadesClient, DonacionesClient donacionesClient, IncentivosClient incentivosClient, LogisticaClient logisticaClient) {
        this.donadoresYEntidadesClient = donadoresYEntidadesClient;
        this.donacionesClient = donacionesClient;
        this.incentivosClient = incentivosClient;
        this.logisticaClient = logisticaClient;
    }

    // ---------------------------------
    // ----- Donadores y Entidades -----
    // ---------------------------------
    public DonadorDTO registrarDonador(DonadorDTO donadorDTO) {
        return donadoresYEntidadesClient.agregarDonador(donadorDTO).getBody();
    }

    public String obtenerEstadisticasDonador(String id) {
        try {

            DonadorStatsDTO estadisticas = donadoresYEntidadesClient.estadisticas(id).getBody();

            if (estadisticas == null) {
                return "No se encontraron estadísticas para el donador con ID " + id;
            }

            String insignias = estadisticas.insigniasID() != null
                    && !estadisticas.insigniasID().isEmpty()
                    ? String.join(", ", estadisticas.insigniasID())
                    : "Sin insignias";

            String mision = estadisticas.misionActualID() != null
                    ? estadisticas.misionActualID()
                    : "Sin misión activa";

            return "Estadísticas del donador\n\n"
                    + "ID: " + estadisticas.id() + "\n"
                    + "Nombre: " + estadisticas.nombre() + " "
                    + estadisticas.apellido() + "\n"
                    + "Edad: " + estadisticas.edad() + " años\n"
                    + "Estado: " + estadisticas.estado() + "\n"
                    + "Categoría: " + estadisticas.categoria() + "\n"
                    + "Misión actual: " + mision + "\n"
                    + "Insignias: " + insignias;

        } catch (NumberFormatException e) {
            return "El ID del donador no es válido.";
        }
    }

    public DonadorDTO buscarDonador(String id) {
        return donadoresYEntidadesClient.buscarDonador(id).getBody();
    }

    public List<DonadorDTO> obtenerDonadores() {
        return donadoresYEntidadesClient.obtenerDonadores().getBody();
    }

    public EntidadBeneficaDTO registrarEntidad(EntidadBeneficaDTO entidadDTO) {
        return donadoresYEntidadesClient.agregarEntidad(entidadDTO).getBody();
    }

    public EntidadBeneficaDTO modificarEntidad(String id, EntidadBeneficaDTO entidadDTO) {
        return donadoresYEntidadesClient.modificarEntidad(id, entidadDTO).getBody();
    }

    public List<EntidadBeneficaDTO> obtenerEntidades() {
        return donadoresYEntidadesClient.obtenerEntidades().getBody();
    }

    public EntidadBeneficaDTO buscarEntidad(String id) {
        return donadoresYEntidadesClient.buscarEntidad(id).getBody();
    }

    public NecesidadMaterialDTO obtenerNecesidad(String id) {
        return donadoresYEntidadesClient.obtenerNecesidad(id).getBody();
    }

    public NecesidadMaterialDTO registrarNecesidad(NecesidadMaterialDTO necesidadDTO) {
        return donadoresYEntidadesClient.registrarNecesidad(necesidadDTO).getBody();
    }

    public String eliminarNecesidad(String id) {
        try {
            donadoresYEntidadesClient.eliminarNecesidad(id);
            return "La necesidad con ID " + id + " fue eliminada correctamente.";
        } catch (Exception e) {
            return "No se pudo eliminar la necesidad con ID " + id + ".";
        }
    }

    public NecesidadMaterialDTO modificarNecesidad(String id, NecesidadMaterialDTO necesidadDTO) {
        return donadoresYEntidadesClient.modificarNecesidad(id, necesidadDTO).getBody();
    }


    // ----------------------
    // ----- Incentivos -----
    // ----------------------
    public InsigniaDTO registrarInsignia(InsigniaDTO insignia) {
        return (InsigniaDTO) incentivosClient.postInsignia(insignia).getBody();
    }

    public String eliminarInsignia(String id){
        try {
            incentivosClient.deleteInsignia(id);
            return "La insignia con ID " + id + " fue eliminada correctamente.";

        } catch (Exception e) {
            return "No se pudo eliminar la insignia con ID " + id + ".";
        }
    }

    public InsigniaDTO modificarInsignia(InsigniaDTO insignia){
        return (InsigniaDTO) incentivosClient.putInsignia(insignia.id(), insignia).getBody();
    }

    public ResponseEntity<?> buscarInsignia(String id){
        return incentivosClient.getInsigniaById(id);
    }

    public InsigniasResponse obtenerInsignias(){
        return incentivosClient.getInsignias();
    }

    // ---------------------
    // ----- Logistica -----
    // ---------------------

    public Optional<PaqueteDTO> buscarPaquete(String id){
        return logisticaClient.buscarPaquete(id);
    }

    public List<PaqueteDTO> obtenerPaquetes(){
        return logisticaClient.getPaquetes();
    }

    // ----------------------
    // ----- Donaciones -----
    // ----------------------

    // ----- Donaciones
    public ResponseEntity<?> registrarDonacion(DonacionDTO donacionDTO) {
        return donacionesClient.postDonacion(donacionDTO);
    }

    public ResponseEntity<?> buscarDonacion(String id){
        return donacionesClient.getDonacionById(id);
    }

    public ResponseEntity<List<DonacionDTO>> obtenerDonaciones(){
        return donacionesClient.getDonaciones();
    }

    public ResponseEntity<DonacionDTO> eliminarDonacion(String id){
        return donacionesClient.deleteDonacion(id);
    }

    public ResponseEntity<?> findByDonadorAndFechaInicio(String donadorID, LocalDate fecha){
        return donacionesClient.findByDonadorAndFechaInicio(donadorID, fecha);
    }

    public ResponseEntity<?> cambiarEstadoDeDonacion(String donacionID, EstadoDonacionEnum estado){
        return donacionesClient.cambiarEstadoDeDonacion(donacionID, estado);
    }

    public ResponseEntity<?> registrarQuejaEnDonacion(String donacionID, String descripcion){
        return donacionesClient.registrarQuejaEnDonacion(donacionID, descripcion);
    }

    // ----- Productos
    public ResponseEntity<?> registrarProducto(ProductoDTO productoDTO) {
        return donacionesClient.postProducto(productoDTO);
    }

    public String eliminarProducto(String id){
        try {
            donacionesClient.deleteProducto(id);
            return "El producto con ID " + id + " fue eliminado correctamente.";
        } catch (Exception e) {
            return "No se pudo eliminar el producto con ID " + id + ".";
        }
    }

    public ResponseEntity<ProductoDTO> modificarProducto(ProductoDTO productoDTO, String productoID){
        return donacionesClient.putProducto(productoID, productoDTO);
    }

    public ResponseEntity<?> buscarProducto(String id){
        return donacionesClient.getProductoByID(id);
    }

    public ResponseEntity<List<ProductoDTO>> obtenerProductos(){
        return donacionesClient.getProductos();
    }

    // ----- Identificadores
    public ResponseEntity<IdentificadorDTO> registrarIdentificador(IdentificadorDTO identificadorDTO){
        return donacionesClient.postIdentificador(identificadorDTO);
    }

    public ResponseEntity<List<IdentificadorDTO>> obtenerIdentificadores(){
        return donacionesClient.getIdentificadores();
    }

    public String eliminarIdentificador(String id){
        try {
            donacionesClient.deleteIdentificador(id);
            return "El identificador con ID " + id + " fue eliminado correctamente.";
        } catch (Exception e) {
            return "No se pudo eliminar el identificador con ID " + id + ".";
        }
    }

    // ----- Categorias
    public ResponseEntity<CategoriaDTO> registrarCategoria(CategoriaDTO categoriaDTO){
        return donacionesClient.postCategoria(categoriaDTO);
    }

    public ResponseEntity<List<CategoriaDTO>> obtenerCategorias(){
        return donacionesClient.getCategorias();
    }

    public String eliminarCategoria(String id){
        try {
            donacionesClient.deleteCategoria(id);
            return "La categoria con ID " + id + " fue eliminada correctamente.";
        } catch (Exception e) {
            return "No se pudo eliminar la categoria con ID " + id + ".";
        }
    }

    // ----- DonacionesHist
    public ResponseEntity<List<DonacionHistDTO>> obtenerDonacionesHist(){
        return donacionesClient.getDonacionesHist();
    }

    public ResponseEntity<List<DonacionHistDTO>> buscarDonacionesHist(String donacionID){
        return donacionesClient.getDonacionesHistPorID(donacionID);
    }
}