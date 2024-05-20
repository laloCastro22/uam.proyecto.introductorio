package uam.introduccion.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uam.introduccion.demo.modelo.Encargado;
@Repository
public interface EncargadoRepository extends JpaRepository<Encargado,Integer> {
}
