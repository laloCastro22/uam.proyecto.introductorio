package uam.introduccion.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uam.introduccion.demo.modelo.Libro;
@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
}
