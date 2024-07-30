### Descripción 
Nutrichef es una aplicación móvil diseñada para ayudar a los usuarios a mejorar su salud y bienestar a través de la alimentación. La aplicación ofrece una gama de características que permiten a los usuarios:
Explorar y descubrir recetas saludables.
Crear planes de alimentación personalizados.
Obtener información detallada sobre ingredientes y valores nutricionales.
Acceder a una base de datos robusta y actualizada
### Definición de Proyecto
El objetivo general del proyecto es desarrollar una aplicación móvil intuitiva y completa de cocina/receta/nutrición. Para ayudar a los usuarios a mejorar su alimentación y estilo de vida mediante la exploración, creación y personalización de recetas saludables.
Además la aplicación proporcionará información detallada sobre ingredientes y valores nutricionales asi como la capacidad de personalizar planes de alimentación
Definición de Sistema
El sistema integrará una interfaz respaldada por una base de datos robusta y actualizada que permitirá a los usuarios encontrar recetas fácilmente adecuadas a sus necesidades personales.
Se desarrollará utilizando tecnologías front-end como XML y android SDK y back-end utilizando java, sql y sqlite. 

## Requisitos:
• Dispositivo móvil compatible con Android.
• Android 4.1
• 40 megas de espacio.

<br><br>


Propuesta funcional: 
Registro y perfiles de usuario: los usuarios pueden crear perfiles personalizados donde ingresan información relevante como edad, género, peso, altura etc.
Exploración de recetas: los usuarios pueden explorar una amplia variedad de recetas saludables filtradas por categorías de comida. Estas recetas incluyen imágenes, ingredientes, instrucciones y valores nutricionales.
Creación de planes de alimentación: los usuarios pueden crear planes de alimentación adaptados a sus necesidades y objetivos. Pueden seleccionar recetas, ajustar proporciones. El sistema calculará los valores nutricionales de cada plan.
Notificaciones y recordatorios: la app puede enviar notificaciones y recordatorios según los objetivos seleccionados por el usuario.

### Bases de datos: 
Realizamos la normalización de la Base de DATOS para organizar los datos de manera eficiente, minimizar la redundancia y evitar anomalías de actualización, eliminación e inserción, lo que garantiza la integridad de los datos y facilita su mantenimiento

### API
La api de la aplicion fue realizada en un servidor local mediante el uso de node-red y mySQL.En nuestro caso en particular está  diseñado para capturar los datos de una receta en formato JSON, transformarlos en una consulta SQL y finalmente, guardar esos datos en la base de datos MySQL.
El nodo, denominado, "Post Receta", es el punto de inicio del flujo. Este recibe datos de una solicitud HTTP POST, donde se envían los detalles de una nueva receta en formato JSON. El mismo actúa como un disparador, iniciando el flujo cuando recibe nuevos datos.
Nodo "json": este toma los datos entrantes en formato JSON y los parsea, convirtiéndolos en un objeto JavaScript que puede ser manipulado por los nodos posteriores.
Nodo "Formatear SQL": el cual es crucial para la interacción con la base de datos. Este toma los datos de la receta (ahora en formato JavaScript) y los transforma en una consulta SQL válida para insertar una nueva fila en la tabla de recetas de la base de datos. 
Nodo "Guardar en MySQL": Este ejecuta la consulta SQL generada en el punto anterior. Se conecta a la base de datos MySQL y ejecuta la sentencia INSERT para agregar la nueva receta.
Nodos "Debug": Este se utilizan para visualizar los datos en cada etapa del flujo, lo cual permite inspeccionar los datos JSON, la consulta SQL generada y el resultado de la ejecución de la consulta. Asi tambien son útiles para depurar el flujo y asegurarse de que los datos se están procesando correctamente.
