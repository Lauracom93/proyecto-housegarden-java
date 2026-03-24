PROYECTO: Sistema de Gestión de Productos para Vivero HouseGarden
Evidencia: GA7-220501096-AA3-EV01
Aprendices: Laura Angélica Cómbita - Diana Carina Amaya
Ficha: 3118327
Instructora: Diana Patricia Galviz Galviz
Programa: Análisis y Desarrollo de Software - SENA
Fecha: Marzo 2026

=============================================================
FRAMEWORK UTILIZADO
=============================================================
Spring Boot 3.2.3 - Framework principal
Spring Data JPA  - Acceso a base de datos con Hibernate
Spring MVC       - Controladores web con @Controller
Thymeleaf        - Motor de plantillas HTML
Spring Validation - Validación de formularios
MySQL Connector  - Driver para base de datos MySQL

=============================================================
DESCRIPCIÓN
=============================================================
Aplicación web desarrollada con Spring Boot que gestiona los
módulos del sistema HouseGarden para un vivero:
  - Productos (plantas y herramientas)
  - Clientes
  - Proveedores
  - Órdenes de compra
  - Categorías

Cada módulo implementa CRUD completo usando:
  - @Controller con métodos GET y POST
  - Spring Data JPA (JpaRepository)
  - Thymeleaf para las vistas HTML
  - Validaciones con @Valid y Bean Validation

=============================================================
CÓMO EJECUTAR
=============================================================
1. Tener Java 17 instalado
2. Tener MySQL corriendo con la base de datos 'housegarden'
3. Verificar credenciales en: src/main/resources/application.properties
4. En NetBeans: clic derecho → Run (Spring Boot inicia su propio servidor)
5. Abrir en el navegador:
   http://localhost:8080/housegarden-spring/

NOTA: Este proyecto usa el Tomcat embebido de Spring Boot.
NO necesita Tomcat externo instalado.

=============================================================
DIFERENCIA CON LA EVIDENCIA ANTERIOR (AA2-EV02)
=============================================================
AA2-EV02 usaba: Servlets + JSP + JDBC manual
AA3-EV01 usa:   Spring Boot + Spring Data JPA + Thymeleaf
El framework automatiza la conexión a BD, el mapeo de objetos
y la gestión de las peticiones HTTP.
