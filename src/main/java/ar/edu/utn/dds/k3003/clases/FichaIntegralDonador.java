package ar.edu.utn.dds.k3003.clases;

import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.EntidadBeneficaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.EstadoDonadorEnum;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.QuejaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.InsigniaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.incentivos.MisionDTO;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//@Document(collection = "Ficha-Integral-Donador")
public class FichaIntegralDonador {
    private String idDonador;
    private EstadoDonadorEnum estado;
    private String categoria;
    private List<InsigniaDTO> insignias;
    private List<MisionDTO> misiones;
    private Integer donacionesPorEstado;
    private List<QuejaDTO> quejas;
    private EntidadBeneficaDTO entidades;

    public String getIdDonador() {
        return idDonador;
    }
    public void setIdDonador(String idDonador) {
        this.idDonador = idDonador;
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

    public EntidadBeneficaDTO getEntidades() {
        return entidades;
    }
    public void setEntidades(EntidadBeneficaDTO entidades) {
        this.entidades = entidades;
    }
}