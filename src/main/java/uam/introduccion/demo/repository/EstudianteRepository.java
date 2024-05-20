package uam.introduccion.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uam.introduccion.demo.modelo.Estudiante;
@Repository
public interface EstudianteRepository  extends JpaRepository<Estudiante, Integer> {
}
