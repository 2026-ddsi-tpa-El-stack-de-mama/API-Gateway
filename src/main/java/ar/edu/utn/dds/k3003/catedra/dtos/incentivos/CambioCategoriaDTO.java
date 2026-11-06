package ar.edu.utn.dds.k3003.catedra.dtos.incentivos;

import java.time.LocalDateTime;

public record CambioCategoriaDTO(
    String categoriaAnterior,
    String categoriaNueva,
    LocalDateTime fecha
){}
