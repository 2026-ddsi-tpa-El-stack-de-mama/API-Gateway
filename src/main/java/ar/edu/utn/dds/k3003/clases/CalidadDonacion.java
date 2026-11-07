package ar.edu.utn.dds.k3003.clases;

import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.CategoriaDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donaciones.DonacionDTO;
import ar.edu.utn.dds.k3003.catedra.dtos.donadoresYEntidades.DonadorDTO;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//@Document(collection = "Calidad-Donacion")
public class CalidadDonacion {
    //@Id
    private String id;
    private CategoriaDTO categoria;
    private List<DonacionDTO> donacionesConQueja;
    private List<DonadorDTO> donadorConQuejas;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }
    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public List<DonacionDTO> getDonacionesConQueja() {
        return donacionesConQueja;
    }
    public void setDonacionesConQueja(List<DonacionDTO> donacionesConQueja) {
        this.donacionesConQueja = donacionesConQueja;
    }

    public List<DonadorDTO> getDonadorConQuejas() {
        return donadorConQuejas;
    }
    public void setDonadorConQuejas(List<DonadorDTO> donadorConQuejas) {
        this.donadorConQuejas = donadorConQuejas;
    }
}