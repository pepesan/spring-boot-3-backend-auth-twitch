# Proyecto de ejemplo de un api rest Spring Boot 3.1

## Arranque
### Requisitos
- Maven
- JDK 17
### Lanzamiento
- mvn spring-boot:run

## Objetivos
Crear un gestor de temarios de cursos y para ello necvesitamos un gestor
de Auth y Authz para los microservicios

## Ámbito
Aplicación Web basada en microservicios

## Backend:
- **Gestor Auth/Auth**
- Gestor de Temarios 
## Frontend:
- Gestor de temarios
- Cliente Auth

## Requisitos 
### Auth/Authz
- Control de Acceso: que permita controlar
  quien puede acceder o no a la aplicación y
  quien puede o no usar determinadas partes
  de la aplicación
- Gestionar un conjunto de usuarios y sus
  permisos asociados
- Usuarios:
  * Admin : puede todo
  * Gestor: Puede modificar y ver cosas
  * Profesor: Puede añadir contenido de un temario
### Gestionar los temarios:
#### Temarios
* Título: cadena de caracteres
* Duración de la formación: horas
* Categoría: Tags
* Público Destinatario: Listado de personas que
  podría interesarles la formación, strings
* Objetivos Formativos: Listado de cosas que
  se van a enseñar en el curso, de tipo texto cada
#### Objetivos
* Requisitos de los alumnos: Listado de requisitos,
  cadenas de caracteres
* Requisitos del aula: Listado de requisitos de aula,
  cadenas de caracteres
* Requisitos de los ordenadores: características del
  Ordenador a nivel de hardware o software,
  listado de cadenas
* Unidades didácticas: Listado de unidades del temario
#### Unidades Didáctica
- Título
- Orden (primera, segunda, etc..)
- Listado de puntos a tratar
- Duración en horas
- Actividades
#### Punto de Unidad/Actividad
- Título
- Duración
- Tipo de Actividad
- Descripción