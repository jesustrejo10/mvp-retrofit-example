Documento general para describir la arquitectura de la aplicación movil.

Capa de Vista: Responsabilidad Principal, Mostrar la informacion en la pantalla, recibir los Listener (acciones que se ejecutan al tocar la pantalla). En esta capa se encuentran Las Activities y Fragments
	Clases: 		Principal Activity: Actividad principal de la aplicación, su responsabilidad es manejar el cambio entre fragmentos y manejar los eventos del menu 

		MoviesListFragment: Fragmento encargado de mostrar la lista de pelicula, este fragmento es re-utilizado para mostrar todos los tipos de peliculas.

		DetailMovieFragment: Fragmento encargado de mostrar el detalle de una pelicula.

		MoviesAdapter: Adapter encargado de manejar las peliculas dentro de la lista de peliculas, setea los valores en el detalle de cada pelicula y maneja el evento del click en cualquiera de las peliculas para verla a detalle.

	Interfaces:
		IdetailMovieFragment: Interface que se comporta como un contrato, entre la vista DetailMovieFragment y su respectivo presentador. Muestra cuales son los metodos que se pueden invocar desde el presentador.

		ImoviesFragment: Interface que se comporta como un contrato, entre la vista MoviesListFragment y su respectivo presentador. Indica cuales son los metodos disponibles por la vista a ser usados por el presentador.


Capa de Presentacion: Capa Logica. Conectada con la Vista.
Responsabilidad principal, Entregar la informacion necesaria para dar sentido a las vistas. Ademas es el encargado de conectarse con la capa REST (servicios web) y a la capa del  DAO (Data Access Object).
La informacion recibida es entregada a la capa de presentacion.

	Clases
		DetailMoviePresentator: Presentador encargado de realizar las operaciones necesarias por la vista DetailMovieFragment. Su responsabilidad principal es, conectarse con la capa REST o Modelo dependiendo sea el caso, solicitar un objeto de tipo Movie y entregarle a la vista la informacion necesaria para mostrar el detalle de la pelicula en la vista.

		MoviesListPresentator: Presentador encargado de realizar las operaciones necesarias por la vista MoviesListFragment. Su responsabilidad principal es, conectarse con la capa REST o Modelo dependiendo sea el caso. Solicitar un objeto de tipo List<MoviesResponse>, dicho objeto es parseado para pasarle al Adapter una List<Movie>, la cual sera mostrado en la vista.

		Presentator: Presentador base, su responsabilidad principal es almacenar el API_Key del servicio web, todos los presentadores heredan de Presentator.

		Transporter: Implementacion breve de un Bus utilizando la librería Otto, no fue implementado en su totalidad ya que esta funcionalidad es cubierta con los contratos.

	Interfaces

		IdetailMoviePresentator: Interface que funciona como contrato de la clase DetailMoviePresentator, indica cuales son los metodos publicos que pueden ser utilizados por las otras capas de la aplicación.

		IMoviesListPresentator: Interface que funciona como contrato de la clase MoviesListPresentator, indica cuales son los metodos publicos que pueden ser utilizados por las otras capas de la aplicación.


Capa Rest: Responsabilidad principal, manejar la conexión entre la aplicación y los servicios web externos a la misma.

	Clases
		ApiClient: Clase encargada de realizar la instancia del cliente Retrofit, a partir de esta clase se realizaran las llamas del api. Tambien almacena la URL BASE del servicio web.

	Interfaces
		MyApiEndPointInterface: Interface que sirve como contrato del cliente retrofit, se espeficican cuales seran los puntos que consumira el api, su ruta y sus parametros.

Capa de Modelo: En esta capa se encuentra el Modelo de dominio es decir aquellas clases necesarias en nuestro modelo de negocios y el DAO. Encargado de realizar operaciones contra la base de datos del tlf.
	Modelo de Dominio
		
		Movie. Clase encargada de almacenar la informacion a detalle de una pelicula.
		
		MoviesReponse. Clase encargada de almacenar la respuesta general del servicio web, a partir de este modelo se obtienen las peliculas.

	DAO (data access object)
	
		DAO, Clase encargada de realizar toda la configuracion necesaria para instanciar, conectar y alterar la base de datos del telefono.

		DAOMovie, clase encargada de realizar todas las operaciones que tienen que ver con base de datos de la clase movie, se encarga de almacenar, alterar y obtener las peliculas a la base de datos.
		
		IDAOMovie, Interface encargada de funcionar como contrato entre la capa del modelo o Acceso a datos y el presentador de la aplicación.

		
Se implemento el Patron MVP.
Permitiendo separar las responsabilidades dentro de la aplicación en las capas previamente mencionadas.
