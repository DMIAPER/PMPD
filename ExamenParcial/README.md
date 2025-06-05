# Examen Parcial: Desarrollo de Aplicación Android de Gestión de Vuelos

## Objetivo
Desarrollar una aplicación Android para la gestión de vuelos que permita registrar y visualizar información de vuelos comerciales.

## Requisitos Técnicos
- Android Studio
- Nivel mínimo de API: 24
- Lenguaje: Java
- Base de datos: SQLite

## Estructura del Proyecto (25 puntos)

### 1. Modelo de Datos (5 puntos)
Crear la clase `Vuelo` con los siguientes atributos:
- `id` (Integer)
- `origen` (String)
- `destino` (String)
- `cantidad` (Integer) - número de pasajeros

### 2. Base de Datos (10 puntos)
Implementar la clase `BDVuelos` que extienda de `SQLiteOpenHelper`:
- Crear tabla **"vuelos"** con los campos correspondientes
- Métodos **CRUD** para gestionar los vuelos
- Método para consultar todos los vuelos

### 3. Interfaces (10 puntos)
Crear los siguientes layouts:
- `activity_main.xml`: Pantalla principal
- `activity_registros.xml`: Lista de vuelos
- `activity_reserva.xml`: Formulario de reserva
- `lista_registro.xml`: Template para cada item de la lista

## Funcionalidades (50 puntos)

### 1. MainActivity (15 puntos)
- Diseño con logo de la aplicación
- Botón para acceder a registros
- Botón para nueva reserva

### 2. ActivityRegistros (20 puntos)
- Implementar **ListView** personalizado
- Mostrar lista de vuelos con:
  - ID del vuelo
  - Origen
  - Destino
  - Número de pasajeros
- Menú con opción de cerrar aplicación

### 3. ActivityReserva (15 puntos)
- Formulario con:
  - Campo para origen
  - Campo para destino
  - Campo para número de pasajeros
- Botón para guardar reserva

## Implementación del Adaptador (15 puntos)
Crear clase `Adaptador` personalizado:
- Extender de `ArrayAdapter`
- Implementar método `onEntrada`
- Gestionar la vista personalizada de cada item

## Requisitos Adicionales (10 puntos)
- Comentarios y documentación del código
- Gestión correcta de errores
- Uso de recursos `strings.xml`
- Implementación de mejores prácticas de Android

## Entrega
- Código fuente completo
- APK generado
- Documentación básica de uso

## Tiempo de Realización
**2 horas**

## Criterios de Evaluación
- **Funcionalidad completa**: 60%
- **Calidad del código**: 20%
- **Diseño de interfaz**: 10%
- **Documentación**: 10%
