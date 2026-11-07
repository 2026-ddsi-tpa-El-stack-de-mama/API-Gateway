package ar.edu.utn.dds.k3003.clases;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.CategoriaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.EntidadBeneficaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.NecesidadMaterialDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.TipoNecesidadMaterialEnum;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.DepositoDTO;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

//@Document(collection = "Necesidad-Urgente-Sin-Cubrir")
public class NecesidadesUrgentesSinCubrir {
    //@Id
    private String idNecesidad;
    private NecesidadMaterialDTO necesidadUrgente;
    private TipoNecesidadMaterialEnum tipoNecesidadMaterial;
    private CategoriaDTO categoria;
    private EntidadBeneficaDTO entidad;
    private Integer cantidadFaltante;
    private Map<String, Integer> stock;

    public NecesidadMaterialDTO getNecesidadUrgente() {
        return necesidadUrgente;
    }
    public void setNecesidadUrgente(NecesidadMaterialDTO necesidadUrgente) {
        this.necesidadUrgente = necesidadUrgente;
    }

    public TipoNecesidadMaterialEnum getTipoNecesidadMaterial() {
        return tipoNecesidadMaterial;
    }
    public void setTipoNecesidadMaterialEnum(TipoNecesidadMaterialEnum tipoNecesidadMaterialEnum) {
        this.tipoNecesidadMaterial = tipoNecesidadMaterialEnum;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }
    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public EntidadBeneficaDTO getEntidad() {
        return entidad;
    }
    public void setEntidad(EntidadBeneficaDTO entidad) {
        this.entidad = entidad;
    }

    public Integer getCantidadFaltante() {
        return cantidadFaltante;
    }
    public void setCantidadFaltante(Integer cantidadFaltante) {
        this.cantidadFaltante = cantidadFaltante;
    }

    public Map<String, Integer> getStock() {
        return stock;
    }
    public void setStock(Map<String, Integer> stock) {
        this.stock = stock;
    }

}