MATERIA: Programación en JAVA -- EVIDENCIA FINAL 

Autor: @alexpardox

Estructura del proyecto evidencia1: 

	/evidencia1
	│
	├─ /src
	│   ├─ Main.java
	│   ├─ Usuario.java
	│   ├─ Admin.java
	│   ├─ Doctor.java
	│   ├─ Paciente.java
	│   ├─ Cita.java
	│   ├─ SistemaCitas.java
	│   ├─ CSVManager.java
	│   ├─ CSVFileManager.java
	│   └─ ValidadorRecursos.java
	│
	└─ /db
 		├─ .gitignore
		├─ admins.csv
 		├─ doctors.csv
		├─ patients.csv
 		└─ citas.csv

- Clases utilizadas
	
- Main: 
Contiene el punto de entrada main(). Controla el flujo: invoca a ValidadorRecursos, carga datos, pide login de administrador y despliega el menú para dar de alta doctores, pacientes, crear citas, listar citas y salir (guardando los cambios).

- Usuario (abstracta):
Define los atributos comunes a todos los usuarios (id y nombre) y sirve como clase base para Admin, Doctor y Paciente.
	
- Admin:
Extiende de Usuario. Añade el campo password y representa a un administrador autorizado a acceder al sistema.

- Doctor:
Extiende de Usuario. Agrega el atributo especialidad y métodos para convertir el objeto a CSV y mostrar información legible.
	
- Paciente:
Extiende de Usuario. Solo contiene los datos básicos (id y nombre) y un método para exportarse a CSV.
	
- Cita:
Encapsula la información de una cita médica: id, fecha y hora, motivo, y referencias a un Doctor y un Paciente. Incluye conversión a/de CSV.
	
- CSVManager (interfaz): 
Declara los métodos genéricos leer(String ruta): List<String> y escribir(String ruta, List<String>) para manejar archivos CSV sin atarse a una implementación concreta.
	
- CSVFileManager:
Implementa CSVManager usando la API de java.nio.file. Se encarga de leer líneas de un CSV y escribir listas de cadenas en un archivo, creando carpetas si es necesario.
	
- ValidadorRecursos:
Clase utilitaria para asegurar que exista la carpeta db y los archivos CSV (admins.csv, doctors.csv, patients.csv, citas.csv). Si faltan, los crea automáticamente (incluyendo un admin por defecto).
	
- SistemaCitas (singleton):
Gestiona en memoria las listas de administradores, doctores, pacientes y citas. Se encarga de cargar/guardar cada listado en su CSV correspondiente, validar credenciales de admin y ofrecer métodos para crear nuevas entidades o buscar por id.
