package uam.introduccion.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uam.introduccion.demo.dto.CreaPrestamoDto;
import uam.introduccion.demo.dto.ListaPrestamosDto;
import uam.introduccion.demo.modelo.Prestamo;
import uam.introduccion.demo.service.PrestamoService;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoService prestamoService;

    @PostMapping("/prestamos")
    public ResponseEntity<?> creaPrestamo(@RequestBody CreaPrestamoDto creaPrestamo) {
        try {
            Prestamo prestamoCreado = prestamoService.creaPrestamo(creaPrestamo);
            return ResponseEntity.status(HttpStatus.CREATED).body(prestamoCreado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el préstamo: " + e.getMessage());
        }
    }

    @DeleteMapping("/prestamos/{id}")
    public ResponseEntity<?> borraPrestamo(@PathVariable(value = "id") Integer idPrestamo) {
        try {
            prestamoService.borraPrestamo(idPrestamo);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Préstamo no encontrado: " + e.getMessage());
        }
    }

    @GetMapping("/prestamos")
    public ResponseEntity<List<ListaPrestamosDto>> obtenerPrestamos() {
        List<ListaPrestamosDto> prestamos = prestamoService.obtenerPrestamos();
        if (prestamos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(prestamos);
    }
}
