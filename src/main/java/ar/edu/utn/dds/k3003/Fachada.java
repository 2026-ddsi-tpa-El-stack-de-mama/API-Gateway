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
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.HistorialEstadoDonadorDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.NecesidadMaterialDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.QuejaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.InsigniaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.MisionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.CambioCategoriaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.CambioCategoriaRequest;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.AsignacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.AsignacionDirecta;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.PaqueteDTO;
import ar.edu.utn.dds.k3003.clientes.DonacionesClient;
import ar.edu.utn.dds.k3003.clientes.DonadoresYEntidadesClient;

import ar.edu.utn.dds.k3003.clientes.IncentivosClient;
import ar.edu.utn.dds.k3003.clientes.LogisticaClient;
import ar.edu.utn.dds.k3003.dtosBot.InsigniasResponse;
import ar.edu.utn.dds.k3003.model.Asignacion;
import ar.edu.utn.dds.k3003.model.AsignacionesHistorial;
import ar.edu.utn.dds.k3003.model.Deposito;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    // ----- Donador
    public ResponseEntity<DonadorDTO> registrarDonador(DonadorDTO donadorDTO) {
        return donadoresYEntidadesClient.agregarDonador(donadorDTO);
    }

    public ResponseEntity<DonadorStatsDTO> obtenerEstadisticasDonador(String id) {
       return donadoresYEntidadesClient.estadisticas(id);
    }

    public ResponseEntity<DonadorDTO> buscarDonador(String id) {
        return donadoresYEntidadesClient.buscarDonador(id);
    }

    public ResponseEntity<List<DonadorDTO>> obtenerDonadores() {
        return donadoresYEntidadesClient.obtenerDonadores();
    }

    public ResponseEntity<Boolean> puedeDonar(String id){
        return donadoresYEntidadesClient.puedeDonar(id);
    }


    public QuejaDTO registrarQueja(String id, QuejaDTO quejaDTO){
        return donadoresYEntidadesClient.agregarQueja(id, quejaDTO);
    }

    public ResponseEntity<List<QuejaDTO>> obtenerQuejas(String id){
        return donadoresYEntidadesClient.obtenerQuejas(id);
    }

    public List<HistorialEstadoDonadorDTO> obtenerHistorialEstado(String id){
        return donadoresYEntidadesClient.obtenerHistorialEstado(id);
    }

    // ----- Entidad
    public ResponseEntity<EntidadBeneficaDTO> registrarEntidad(EntidadBeneficaDTO entidadDTO) {
        return donadoresYEntidadesClient.agregarEntidad(entidadDTO);
    }

    public ResponseEntity<EntidadBeneficaDTO> modificarEntidad(String id, EntidadBeneficaDTO entidadDTO) {
        return donadoresYEntidadesClient.modificarEntidad(id, entidadDTO);
    }

    public ResponseEntity<List<EntidadBeneficaDTO>> obtenerEntidades() {
        return donadoresYEntidadesClient.obtenerEntidades();
    }

    public ResponseEntity<EntidadBeneficaDTO> buscarEntidad(String id) {
        return donadoresYEntidadesClient.buscarEntidad(id);
    }


    // ----- Necesidad
    public ResponseEntity<NecesidadMaterialDTO> buscarNecesidad(String id) {
        return donadoresYEntidadesClient.obtenerNecesidad(id);
    }

    public ResponseEntity<List<NecesidadMaterialDTO>> obtenerNecesidades() {
        return donadoresYEntidadesClient.obtenerNecesidades();
    }
    public ResponseEntity<NecesidadMaterialDTO> registrarNecesidad(NecesidadMaterialDTO necesidadDTO) {
        return donadoresYEntidadesClient.registrarNecesidad(necesidadDTO);
    }

    public ResponseEntity<List<NecesidadMaterialDTO>> obtenerNecesidadesInsatisfechasDe(String productoID){
        return donadoresYEntidadesClient.obtenerNecesidadesInsatisfechasDe(productoID);
    }

    public ResponseEntity<?> eliminarNecesidad(String id) {
        return donadoresYEntidadesClient.eliminarNecesidad(id);
    }

    public ResponseEntity<NecesidadMaterialDTO> modificarNecesidad(String id, NecesidadMaterialDTO necesidadDTO) {
        return donadoresYEntidadesClient.modificarNecesidad(id, necesidadDTO);
    }

    public ResponseEntity<?> satisfacerNecesidad(String necesidadID, Integer cantidad){
        return donadoresYEntidadesClient.satisfacerNecesidad(necesidadID, cantidad);
    } 
    // ----------------------
    // ----- Incentivos -----
    // ----------------------
    
    // ----- Insignias
    public ResponseEntity<?> registrarInsignia(InsigniaDTO insignia) {
        return incentivosClient.postInsignia(insignia);
    }

    public ResponseEntity<?> eliminarInsignia(String id){  
        return incentivosClient.deleteInsignia(id);
        
    }

    public ResponseEntity<?> modificarInsignia(String insigniaID,InsigniaDTO insigniaDTO){
        return incentivosClient.putInsignia(insigniaID, insigniaDTO);
    }

    public ResponseEntity<?> buscarInsignia(String id){
        return incentivosClient.getInsigniaById(id);
    }

    // Antes el tipo que devolvia era InsigniasResponse
    public ResponseEntity<List<InsigniaDTO>> obtenerInsignias(){
        return incentivosClient.getInsignias();
    }

    public ResponseEntity<?> asignarInsigniaADonador(String insigniaID, String donadorID){
        return incentivosClient.asignarInsigniaADonador(insigniaID, donadorID);
    }

    public ResponseEntity<?> getInsigniasDeDonador(String donadorID){
        return incentivosClient.getInsigniasDeDonador(donadorID);
    }

    // Donadores (Desde Incentivos)
    public ResponseEntity<?> registrarCambioCategoria(String donadorID, CambioCategoriaRequest request){
        return incentivosClient.registrarCambioCategoria(donadorID,request);
    }

    public ResponseEntity<List<CambioCategoriaDTO>> historialCategorias(String donadorID){
        return incentivosClient.historialCategorias(donadorID);
    }

    // Misiones
    public ResponseEntity<?> registrarMision( MisionDTO misionDTO){
        return incentivosClient.postMision(misionDTO);
    }

    public ResponseEntity<?> modificarMision(String misionID, MisionDTO misionDTO){
        return incentivosClient.putMision(misionID,misionDTO);
    }

    public ResponseEntity<?> eliminarMision( String misionID){
        return incentivosClient.deleteMision(misionID);
    }

    public ResponseEntity<List<MisionDTO>> obtenerMisiones(){
        return incentivosClient.getMisiones();
    }

    public ResponseEntity<?> buscarMision(String misionID){
        return incentivosClient.getMisionById(misionID);
    }

    public ResponseEntity<?> asignarMisionADonador(String misionID,String donadorID){
        return asignarMisionADonador(misionID, donadorID);
      }

    public ResponseEntity<?> getMisionEnCursoDeDonador( String donadorID){
        return getMisionEnCursoDeDonador(donadorID);
    }

    public ResponseEntity<?> procesarDonador( String donadorID){
        return procesarDonador(donadorID);
    }

    public ResponseEntity<?> historialMisiones( String donadorID){
        return historialMisiones(donadorID);
    }
    
    // ---------------------
    // ----- Logistica -----
    // ---------------------
    
    public Asignacion getAsignacion( String id){
        return logisticaClient.getAsignacion(id);
    }
    
    public List<Asignacion> getAsignaciones() {
        return logisticaClient.getAsignaciones();
    }

    public List<AsignacionesHistorial> getAsignacionesHistorial(){
        return logisticaClient.getAsignacionesHistorial();
    }

    public AsignacionDTO crearAsignacion( AsignacionDirecta asignacionDirecta){
        return logisticaClient.crearAsignacion(asignacionDirecta);
    }

    public Asignacion postAsignacionesDirecta( AsignacionDirecta asignacionDirecta){
        return logisticaClient.postAsignacionesDirecta(asignacionDirecta);
    }
    
    public Optional<PaqueteDTO> buscarPaquete(String id){
        return logisticaClient.buscarPaquete(id);
    }

    public List<PaqueteDTO> obtenerPaquetes(){
        return logisticaClient.getPaquetes();
    }

    // Deposito
    public Deposito getDeposito(@PathVariable String id){
        return logisticaClient.getDeposito(id);
    }

    public ResponseEntity<String> deleteDeposito(@PathVariable String id) {
        return logisticaClient.deleteDeposito(id);
    }

    public List<Deposito> getDepositos(){
        return logisticaClient.getDepositos();
    }

    public Deposito postDeposito(@RequestBody Deposito deposito) {
        return logisticaClient.postDeposito(deposito);
    }

    public String postDonacion(@PathVariable String id, @RequestBody PaqueteDTO paquete){
        return logisticaClient.postDonacion(id, paquete);
    }

    public String postEntrega(@RequestBody PaqueteDTO paquete){
        return logisticaClient.postEntrega(paquete);
    }

    public String getStock(@PathVariable String productoID){return logisticaClient.getStock(productoID);}

    public Integer postStock(@PathVariable String productoID, @RequestBody Integer cantidad){
        return logisticaClient.postStock(productoID, cantidad);
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

    public ResponseEntity<?> eliminarProducto(String id){
        return donacionesClient.deleteProducto(id);
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

    public ResponseEntity<?> eliminarIdentificador(String id){
        return donacionesClient.deleteIdentificador(id);
    }

    // ----- Categorias
    public ResponseEntity<CategoriaDTO> registrarCategoria(CategoriaDTO categoriaDTO){
        return donacionesClient.postCategoria(categoriaDTO);
    }

    public ResponseEntity<List<CategoriaDTO>> obtenerCategorias(){
        return donacionesClient.getCategorias();
    }

    public ResponseEntity<?> eliminarCategoria(String id){
        return donacionesClient.deleteCategoria(id);
    }

    // ----- DonacionesHist
    public ResponseEntity<List<DonacionHistDTO>> obtenerDonacionesHist(){
        return donacionesClient.getDonacionesHist();
    }

    public ResponseEntity<List<DonacionHistDTO>> buscarDonacionesHist(String donacionID){
        return donacionesClient.getDonacionesHistPorID(donacionID);
    }
}