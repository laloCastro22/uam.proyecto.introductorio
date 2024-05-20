package uam.introduccion.demo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import uam.introduccion.demo.dto.CreaPrestamoDto;
import uam.introduccion.demo.dto.ListaPrestamosDto;
import uam.introduccion.demo.enums.Estado;
import uam.introduccion.demo.modelo.Prestamo;
import uam.introduccion.demo.repository.EncargadoRepository;
import uam.introduccion.demo.repository.EstudianteRepository;
import uam.introduccion.demo.repository.LibroRepository;
import uam.introduccion.demo.repository.PrestamoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PrestamoService {

    private final EstudianteRepository estudianteRepository;
    private final LibroRepository libroRepository;
    private final PrestamoRepository prestamoRepository;
    private final EncargadoRepository encargadoRepository;

    public Prestamo creaPrestamo(CreaPrestamoDto creaPrestamo) {
        log.info("Creando préstamo: {}", creaPrestamo);
        // Reglas de negocio
        // 1. El estudiante debe existir
        if (!validarEstudiante(creaPrestamo.getEstudianteCarnet())) throw new IllegalArgumentException("El estudiante no existe");
        // 2. El libro debe existir
        if (!validarLibro(creaPrestamo.getLibroCodigo())) throw new IllegalArgumentException("El libro no existe");


        // Crear prestamo
        try {
            Prestamo prestamo = crearPrestamo(creaPrestamo);
            return prestamoRepository.save(prestamo);  // Devuelve true si el préstamo se creó exitosamente
        } catch (Exception e) {
            // Log the exception
            log.error("Error al crear préstamo: {}", e.getMessage());
        }
        return null;
    }
    @Transactional
    public void borraPrestamo(Integer idPrestamo){
        log.info("Borrando préstamo: {}", idPrestamo);
        // Regla de negocio:
        // 1. El prestamo debe existir en la base de datos
        var prestamo = prestamoRepository.findById(idPrestamo).orElseThrow(() -> new IllegalArgumentException("El prestamo no existe"));
        // 2. El prestamo debe estar en estado activo
        if (!prestamo.getEstado().equals(String.valueOf(Estado.ACTIVO)))
            throw new IllegalArgumentException("El préstamo no está activo");

        // Se borra el prestamo
        prestamoRepository.delete(prestamo);
    }
    public List<ListaPrestamosDto> obtenerPrestamos(){
        log.info("Obteniendo préstamos");
        var prestamos = prestamoRepository.findAll();
        return prestamos.stream().map(prestamo -> ListaPrestamosDto.builder()
                .id(prestamo.getId())
                .fechaInicio(prestamo.getFechaInicio())
                .fechaEntrega(prestamo.getFechaEntrega())
                .libroCodigo(prestamo.getLibroCodigo())
                .estudianteCarnet(prestamo.getEstudianteCarnet())
                .estado(prestamo.getEstado())
                .build()).toList();
    }

    private Prestamo crearPrestamo(CreaPrestamoDto creaPrestamo) {
        return Prestamo.builder()
                .fechaInicio(creaPrestamo.getFechaInicio())
                .fechaEntrega(creaPrestamo.getFechaEntrega())
                .libroCodigo(creaPrestamo.getLibroCodigo())
                .estudianteCarnet(creaPrestamo.getEstudianteCarnet())
                .estado(String.valueOf(Estado.ACTIVO))
                .build();
    }

    private Boolean validarEstudiante(Integer carnet) {
        return estudianteRepository.findById(carnet).isPresent();
    }

    private Boolean validarLibro(Integer codigo) {
        return libroRepository.findById(codigo).isPresent();
    }
}