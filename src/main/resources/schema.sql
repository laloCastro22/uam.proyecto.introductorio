CREATE TABLE IF NOT EXISTS Encargado (
    id INT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS Estudiante (
    carnet INT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS Libro (
    titulo VARCHAR(255),
    codigo INT PRIMARY KEY,
    estado VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Prestamo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha_inicio DATE,
    fecha_entrega DATE,
    libro_codigo INT,
    estudiante_carnet INT,
    estado VARCHAR(255),
    FOREIGN KEY (libro_codigo) REFERENCES Libro(codigo),
    FOREIGN KEY (estudiante_carnet) REFERENCES Estudiante(carnet)
);
