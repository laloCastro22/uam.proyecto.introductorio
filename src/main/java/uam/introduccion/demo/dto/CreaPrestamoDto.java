package uam.introduccion.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreaPrestamoDto {

    private String fechaInicio;
    private String fechaEntrega;
    private Integer libroCodigo;
    private Integer estudianteCarnet;
}
