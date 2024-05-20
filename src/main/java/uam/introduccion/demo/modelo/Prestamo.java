package uam.introduccion.demo.modelo;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_inicio")
    private String fechaInicio;

    @Column(name = "fecha_entrega")
    private String fechaEntrega;

    @Column(name = "libro_codigo")
    private Integer libroCodigo;

    @Column(name = "estudiante_carnet")
    private Integer estudianteCarnet;

    private String estado;
}
