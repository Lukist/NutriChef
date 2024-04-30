# NutriChef

### Descripción del Proyecto

NutriChef es una aplicación móvil diseñada para ayudar a los usuarios a mejorar su salud y bienestar a través de la alimentación. La aplicación ofrece una gama de características que permiten a los usuarios.

### Objetivo del Proyecto

-   Explorar y descubrir recetas saludables.
    
-   Crear planes de alimentación personalizados.
    
-   Obtener información detallada sobre ingredientes y valores nutricionales.
    
-   Acceder a una base de datos robusta y actualizada

### Definición de Proyecto

El objetivo general del proyecto es desarrollar una aplicación móvil intuitiva y completa de cocina/receta/nutrición. Para ayudar a los usuarios a mejorar su alimentación y estilo de vida mediante la exploración, creación y personalización de recetas saludables.  
Además la aplicación proporcionará información detallada sobre ingredientes y valores nutricionales asi como la capacidad de personalizar planes de alimentación.

### Definición de Sistema

El sistema integrará una interfaz intuitiva y completa respaldada por una base de datos robusta y actualizada que permitirá a los usuarios encontrar recetas fácilmente adecuadas a sus necesidades personales.  
Se desarrollará utilizando tecnologías front-end como XML y Android SDK y back-end utilizando java, SQL y SQLite.

### Requisitos:  

• Dispositivo móvil compatible con Android.  
• Android 4.1  
• 40 megas de espacio.

### Propuesta funcional:

Registro y perfiles de usuario: los usuarios pueden crear perfiles personalizados donde ingresan información relevante como edad, género, peso, altura etc.  
Exploración de recetas: los usuarios pueden explorar una amplia variedad de recetas saludables filtradas por categorías de comida. Estas recetas incluyen imágenes, ingredientes, instrucciones y valores nutricionales.  
Creación de planes de alimentación: los usuarios pueden crear planes de alimentación adaptados a sus necesidades y objetivos. Pueden seleccionar recetas, ajustar proporciones. El sistema calculará los valores nutricionales de cada plan.

Notificaciones y recordatorios: la app puede enviar notificaciones y recordatorios según los objetivos seleccionados por el usuario.

### Propuesta Técnica del Proyecto:

1. *Arquitectura Cliente-Servidor:* Utilizar una arquitectura cliente-servidor donde la aplicación móvil actúa como cliente y se comunica con un servidor remoto que aloja la base de datos de recetas, ingredientes y perfiles de usuario.

2. *Desarrollo de API:* Desarrollar una API RESTful para que la aplicación móvil pueda realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la base de datos remota, permitiendo así la gestión de recetas, perfiles de usuario y planes de alimentación.

3. *Base de Datos:* Utilizar una base de datos relacional como SQLite para almacenar localmente datos temporales en el dispositivo móvil, como perfiles de usuario, planes de alimentación y datos de configuración.

4. *Base de Datos Remota:* Implementar una base de datos remota utilizando SQL para almacenar de manera segura y eficiente las recetas, ingredientes, información nutricional y datos de usuarios, permitiendo así el acceso desde múltiples dispositivos y la sincronización de datos.

5. *Seguridad:* Implementar medidas de seguridad robustas, como encriptación de datos, autenticación de usuarios y autorización de acceso, para proteger la privacidad y seguridad de la información de los usuarios.

6. *Desarrollo de Interfaces de Usuario:* Utilizar XML y Android SDK para desarrollar interfaces de usuario intuitivas y atractivas que cumplan con los principios de diseño de Material Design, garantizando una experiencia de usuario consistente y de alta calidad.

7. *Pruebas Automatizadas:* Implementar pruebas unitarias utilizando JUnit para probar la lógica de negocio y asegurar la calidad del código. Además, utilizar Espresso para realizar pruebas de interfaz de usuario y garantizar el correcto funcionamiento de la aplicación en diferentes dispositivos y versiones de Android.

8. *Gestión de Versiones:* Utilizar Git como sistema de control de versiones para el seguimiento de cambios en el código y la colaboración entre desarrolladores. Alojar el repositorio en plataformas como GitHub o GitLab para facilitar la colaboración y el seguimiento del progreso del proyecto.

9. *Despliegue y Mantenimiento:* Desplegar la aplicación en Google Play Store para que los usuarios puedan descargarla e instalarla en sus dispositivos Android. Realizar actualizaciones periódicas para agregar nuevas funcionalidades, mejorar el rendimiento y corregir posibles errores.

10. *Monitorización y Análisis:* Integrar herramientas de análisis y monitorización, como Google Analytics o Firebase Analytics, para rastrear el comportamiento de los usuarios, recopilar métricas de uso y realizar análisis de datos que permitan mejorar continuamente la aplicación.
