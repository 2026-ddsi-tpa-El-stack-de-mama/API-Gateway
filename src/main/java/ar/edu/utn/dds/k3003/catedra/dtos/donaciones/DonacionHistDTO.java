package ar.edu.utn.dds.k3003.catedra.dtos.donaciones;
import java.time.LocalDate;
public record DonacionHistDTO(
    String id,
    String donacionID,
    String donadorID,
    String depositoID,
    String descripcion,
    String productoID,
    Integer cantidad,
    EstadoDonacionEnum estado,
    LocalDate fechaInicio) {}