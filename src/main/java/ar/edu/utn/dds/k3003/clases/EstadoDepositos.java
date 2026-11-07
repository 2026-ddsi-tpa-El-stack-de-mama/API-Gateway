package ar.edu.utn.dds.k3003.clases;

import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.EntidadBeneficaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.AsignacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.logistica.TipoAlgoritmoEnum;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//Document(collection = "Estado-Deposito")
public class EstadoDepositos {
    //@Id
    private String idDeposito;
    private Integer ocupacion;
    private TipoAlgoritmoEnum algoritmo;
    private List<AsignacionDTO> asignaciones;
    private Integer tiempoIngresoAEntrega;
    private List<EntidadBeneficaDTO> entidades;

    public String getIdDeposito() {
        return idDeposito;
    }
    public void setIdDeposito(String idDeposito) {
        this.idDeposito = idDeposito;
    }

    public Integer getOcupacion() {
        return ocupacion;
    }
    public void setOcupacion(Integer ocupacion) {
        this.ocupacion = ocupacion;
    }

    public TipoAlgoritmoEnum getAlgoritmo() {
        return algoritmo;
    }
    public void setAlgoritmo(TipoAlgoritmoEnum algoritmo) {
        this.algoritmo = algoritmo;
    }

    public List<AsignacionDTO> getAsignaciones() {
        return asignaciones;
    }
    public void setAsignaciones(List<AsignacionDTO> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public Integer getTiempoIngresoAEntrega() {
        return tiempoIngresoAEntrega;
    }
    public void setTiempoIngresoAEntrega(Integer tiempoIngresoAEntrega) {
        this.tiempoIngresoAEntrega = tiempoIngresoAEntrega;
    }

    public List<EntidadBeneficaDTO> getEntidades() {
        return entidades;
    }
    public void setEntidades(List<EntidadBeneficaDTO> entidades) {
        this.entidades = entidades;
    }
}