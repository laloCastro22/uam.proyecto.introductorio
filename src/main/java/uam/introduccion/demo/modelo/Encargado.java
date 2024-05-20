package uam.introduccion.demo.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Encargado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
