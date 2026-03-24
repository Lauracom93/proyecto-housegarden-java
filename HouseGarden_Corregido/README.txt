PROYECTO: Sistema de Gestión Web para Vivero HouseGarden
Evidencia: GA7-220501096-AA2-EV02
Aprendiz: Laura Angélica Cómbita - Diana Carina Amaya
Ficha: 3118327
Instructora: Diana Patricia Galviz
Programa: Análisis y Desarrollo de Software - SENA
Fecha: Marzo 2026

=============================================================
DESCRIPCIÓN
=============================================================
Aplicación web Java desarrollada con Servlets, JSP y JDBC que
permite gestionar los módulos del sistema HouseGarden:
  - Productos (plantas y herramientas del vivero)
  - Clientes
  - Proveedores
  - Órdenes de compra

Cada módulo implementa operaciones CRUD completas:
  - Crear (formulario HTML + método POST)
  - Listar (método GET + JSP con JSTL)
  - Editar (método GET para cargar + POST para guardar)
  - Eliminar (método GET con confirmación)

=============================================================
TECNOLOGÍAS USADAS
=============================================================
- Java 11
- Jakarta Servlet API 6.0  (Tomcat 10 / Jakarta EE)
- Jakarta JSP API 3.1
- Jakarta JSTL 3.0
- JDBC con MySQL Connector/J 8.3
- Maven (gestión de dependencias)
- MySQL 8 (base de datos: housegarden)
- Apache Tomcat 10.x (servidor de aplicaciones)
- NetBeans IDE

=============================================================
ESTRUCTURA DEL PROYECTO
=============================================================
src/main/
  java/com/mycompany/housegarden/
    dao/         → ConexionDB, ProductoDAO, ClienteDAO, ProveedorDAO, OrdenDAO
    model/       → Producto, Cliente, Proveedor, Orden
    servlet/     → ProductoServlet, ClienteServlet, ProveedorServlet, OrdenServlet
  webapp/
    css/         → estilos.css
    views/       → productos/, clientes/, proveedores/, ordenes/ (lista.jsp + formulario.jsp)
    index.jsp    → Dashboard principal
    WEB-INF/     → web.xml
pom.xml          → dependencias Maven

=============================================================
CÓMO EJECUTAR
=============================================================
1. Importar el proyecto en NetBeans como "Maven Web Application"
2. Instalar Apache Tomcat 10.x desde https://tomcat.apache.org
3. Configurar Tomcat en NetBeans: Tools > Servers > Add Server
4. Importar la base de datos:
     mysql -u root -p < respaldo_housegarden.sql
5. Verificar conexión en: ConexionDB.java
     URL:      jdbc:mysql://127.0.0.1:3306/housegarden
     Usuario:  root
     Password: (vacío por defecto, ajustar si es necesario)
6. Clic derecho en el proyecto > Run (Tomcat debe estar configurado)
7. Abrir en el navegador: http://localhost:8080/housegarden/

=============================================================
NOTA IMPORTANTE - VERSIÓN TOMCAT
=============================================================
Este proyecto usa Jakarta EE (jakarta.servlet.*) compatible con:
  ✅ Tomcat 10.x
  ✅ Tomcat 11.x

NO es compatible con Tomcat 9 o anterior (que usa javax.servlet.*)
Si necesitas usar Tomcat 9, cambia en pom.xml las dependencias
jakarta.* por javax.* y actualiza los imports en los servlets.
