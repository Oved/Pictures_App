# Pictures_App

Se crea una aplicación movil que realiza una petición al servidor para obtener un listado de imágenes, al obtenerlas se guardan de manera local usando Room y se muestran en recycler view; se crea pantalla adicional para mostrar el detalle de la fotografía con la 
opción de eliminarla tanto de la base de datos como realizar la petición al servidor de eliminar, si no se obtiene correctamente la eliminación al servidor se guarda el id para después realizar nuevamente la petición. Se realiza todo el trabajo con el patrón MVVM
y aplicando un poco clean arquitecture. Cuando el usuario ingresa a la aplicación y no tiene internet, esta trae las imagenes guardas localmente y las muestra al usuario.
La aplicación cuenta con un splash inicial y una corta animación.
