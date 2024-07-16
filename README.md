<h1 align="center">ForoHub</h1>

<p><em>Descripción</em></p>
<p>ForoHub es una API RESTful desarrollada siguiendo las mejores prácticas del modelo REST. Este proyecto incluye validaciones basadas en reglas de negocio, una base de datos relacional para la persistencia de información y un servicio de autenticación y autorización para restringir el acceso a los datos.</p>

<p><em>Características</em></p>
<ul>
    <li>API RESTful: Implementación de rutas siguiendo las mejores prácticas del modelo REST.</li>
    <li>Validaciones: Realización de validaciones según las reglas de negocio definidas.</li>
    <li>Base de Datos Relacional: Uso de una base de datos relacional para asegurar la persistencia de la información.</li>
    <li>Autenticación y Autorización: Servicio integrado para restringir el acceso a la información a usuarios autenticados y autorizados.</li>
</ul>

<p><em>Requisitos</em></p>
<ul>
    <li>Java 11 o superior</li>
    <li>Un IDE compatible con Java</li>
    <li>Conexión a internet para el uso de la API de autenticación</li>
</ul>

<p><em>Instalación</em></p>
<ol>
    <li>Clona o descarga el proyecto desde el repositorio.</li>
    <li>Abre el proyecto en tu IDE preferido (por ejemplo, IntelliJ IDEA).</li>
    <li>Asegúrate de tener Java 11 o una versión superior instalada.</li>
</ol>

<p><em>Configuración</em></p>
<ol>
    <li>Crea un archivo <code>config.properties</code> en la raíz del proyecto.</li>
    <li>Añade las configuraciones necesarias, incluyendo tu API Key y detalles de la base de datos:</li>
</ol>
<pre><code>apiKey=tu_api_key_aqui
db.url=jdbc:your_database_url
db.username=your_database_username
db.password=your_database_password</code></pre>

<p><em>Uso</em></p>
<ol>
    <li>Compila y ejecuta la aplicación.</li>
    <li>Utiliza herramientas como Postman o cURL para interactuar con las diferentes rutas de la API.</li>
</ol>

<p><em>Funcionalidades de la API</em></p>

<p><strong>Autenticación</strong></p>
<ul>
    <li>POST /login
        <ul>
            <li>Autentica a un usuario y devuelve un token JWT.</li>
            <li><strong>Request Body:</strong></li>
        </ul>
        <pre><code>{
  "login": "user_login",
  "clave": "user_password"
}</code></pre>
    </li>
</ul>

<p><strong>Gestión de Tópicos</strong></p>
<ul>
    <li>POST /topicos
        <ul>
            <li>Registra un nuevo tópico.</li>
            <li><strong>Request Body:</strong></li>
        </ul>
        <pre><code>{
  "titulo": "titulo",
  "mensaje": "mensaje",
  "autorId": "autorId",
  "nombreCurso": "nombreCurso"
}</code></pre>
    </li>
    <li>GET /topicos
        <ul>
            <li>Lista todos los tópicos.</li>
        </ul>
    </li>
    <li>GET /topicos/filtrar1
        <ul>
            <li>Lista tópicos por fecha de manera ascendiente.</li>
        </ul>
    </li>
    <li>GET /topicos/filtrar2
        <ul>
            <li>Lista tópicos por nombre de curso y año específico.</li>
        </ul>
    </li>
    <li>GET /topicos/{id}
        <ul>
            <li>Detalla un tópico específico por su id.</li>
        </ul>
    </li>
    <li>PUT /topicos/{id}
        <ul>
            <li>Actualiza un tópico existente.</li>
            <li><strong>Request Body:</strong></li>
        </ul>
        <pre><code>{
  "titulo": "nuevo_titulo",
  "mensaje": "nuevo_mensaje",
  "status": "nuevo_status",
  "nombreCurso": "nuevo_nombreCurso"
}</code></pre>
    </li>
    <li>DELETE /topicos/{id}
        <ul>
            <li>Elimina un tópico específico por su id.</li>
        </ul>
    </li>
</ul>

<p><em>Base de Datos</em></p>
<p>La base de datos relacional utilizada en este proyecto asegura la persistencia y consistencia de la información de los tópicos y usuarios. Asegúrate de configurar correctamente los detalles de conexión en el archivo <code>config.properties</code>.</p>

<p><em>Autenticación y Autorización</em></p>
<p>El proyecto incluye un servicio de autenticación y autorización basado en JWT. Los endpoints de la API están protegidos para asegurar que solo los usuarios autenticados y autorizados puedan acceder a la información.</p>

<p><em>Desarrollado por</em></p>
<p>Stanley Faria</p>
