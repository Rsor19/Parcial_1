# Gestión de gimnasio

Aplicación de escritorio JavaFX para gestionar planes de gimnasio. El proyecto usa Maven y sigue su estructura estándar.

## Requisitos

- JDK 21 o compatible.
- Apache Maven 3.9 o compatible.

## Estructura

```text
src/
├── main/
│   ├── java/
│   │   ├── app/Main.java
│   │   ├── controller/  # Controladores JavaFX
│   │   └── model/       # Entidades y lógica del dominio
│   └── resources/
│       └── view/        # Vistas FXML
└── test/java/           # Pruebas automatizadas (si se agregan)
```

## Ejecutar

Desde la raíz del proyecto:

```bash
mvn clean javafx:run
```

Para compilar sin iniciar la interfaz:

```bash
mvn clean package
```

Maven resuelve las dependencias JavaFX declaradas en `pom.xml`.
