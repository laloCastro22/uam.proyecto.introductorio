package uam.introduccion.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaPrestamosDto {

    private Integer id;
    private String fechaInicio;
    private String fechaEntrega;
    private Integer libroCodigo;
    private Integer estudianteCarnet;
    private String estado;
}
