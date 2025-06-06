1) Instalación y configuración.

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
   
2) Uso del programa.

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

Cada una de estas clases colabora para ofrecer las funcionalidades de alta de doctores/pacientes, creación de citas, control de acceso y persistencia en archivos CSV.

3) Créditos

- MATERIA: Programación en JAVA -- EVIDENCIA FINAL
- Autor: @alexpardox

4) Licencia

MIT License

Copyright (c) 2025 alexpardox

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
