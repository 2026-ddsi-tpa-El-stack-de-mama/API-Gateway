package ar.edu.utn.dds.k3003.clases;

import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.EstadoDonadorEnum;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.QuejaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.InsigniaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.MisionDTO;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//@Document(collection = "Ficha-Integral-Donador")
public class FichaIntegralDonador {
    //@Id
    private String idDonador;
    private String nombre;
    private EstadoDonadorEnum estado;
    private String categoria;
    private List<InsigniaDTO> insignias;
    private List<MisionDTO> misiones;
    private Integer progresoActual;
    private Integer progresoRequerido;
    private String incentivosActualizadosEn;
    private Integer donacionesPorEstado;
    private List<QuejaDTO> quejas;


    public String getIdDonador() {
        return idDonador;
    }
    public void setIdDonador(String idDonador) {
        this.idDonador = idDonador;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoDonadorEnum getEstado() {
        return estado;
    }
    public void setEstado(EstadoDonadorEnum estado) {
        this.estado = estado;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<InsigniaDTO> getInsignias() {
        return insignias;
    }
    public void setInsignias(List<InsigniaDTO> insignias) {
        this.insignias = insignias;
    }

    public List<MisionDTO> getMisiones() {
        return misiones;
    }
    public void setMisiones(List<MisionDTO> misiones) {
        this.misiones = misiones;
    }

    public Integer getProgresoActual() {
        return progresoActual;
    }
    public void setProgresoActual(Integer progresoActual) {
        this.progresoActual = progresoActual;
    }

    public Integer getProgresoRequerido() {
        return progresoRequerido;
    }
    public void setProgresoRequerido(Integer progresoRequerido) {
        this.progresoRequerido = progresoRequerido;
    }

    public String getIncentivosActualizadosEn() {
        return incentivosActualizadosEn;
    }
    public void setIncentivosActualizadosEn(String incentivosActualizadosEn) {
        this.incentivosActualizadosEn = incentivosActualizadosEn;
    }

    public Integer getDonacionesPorEstado() {
        return donacionesPorEstado;
    }
    public void setDonacionesPorEstado(Integer donacionesPorEstado) {
        this.donacionesPorEstado = donacionesPorEstado;
    }

    public List<QuejaDTO> getQuejas() {
        return quejas;
    }
    public void setQuejas(List<QuejaDTO> quejas) {
        this.quejas = quejas;
    }

}