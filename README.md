# Proyecto del Segundo Cuatrimestre Fundamentos de Programación (Curso  \<XX\>/\<YY\>)
Autor: Álvaro Jiménez Osuna   uvus:alvjimosu

Aquí debes añadir la descripción del dataset y un enunciado del dominio del proyecto.


## Estructura de las carpetas del proyecto

* **/src**: Contiene los diferentes archivos que forman parte del proyecto. Debe estar estructurado en los siguentes paquetes
  * **fp.\<factoriamedicamentos\>**: Paquete que contiene los tipos del proyecto.
  * **fp.\<factoriavacunaciones\>**: Paquete que contiene los tipos del proyecto.
  * **fp.\<clinico\>**: Paquete que contiene los tipos del proyecto.
  * **fp.\<farmaceutico\>**: Paquete que contiene los tipos del proyecto.
  * **fp.\<farmaceutico\>.test**: Paquete que contiene las clases de test de "fp.farmacutico.
  * **fp.utiles**:  Paquete que contiene las clases de utilidad. 
  * **fp.\<vacunas\>**: Paquete que contiene el módulo "Vacunacion".
* **/data**: Contiene el dataset o datasets del proyecto
    * **\<cca_vacunas_3.csv\>**: Informacion sobre la vacunacion de las comunidades autónomas.
    * **\<estudio_clinico.csv\>**: Estudio clínico de la población.
    * **\<medicamentos.csv\>**: Informacion sobre medicamentos.
     
## Estructura del *dataset*

El dataset está compuesto por \<\> columnas, con la siguiente descripción:

* **\<cca_vacunas_3.csv\>**:
	* **\<columna 1>**: de tipo LocalDate \<fecha_publicacion\>.
	* **\<columna 2>**: de tipo String \<CCAA\>.
	* **\<columna 3>**: de tipo Integer \<Pfizer\>.
	* **\<columna 4>**: de tipo Integer \<Moderna\>.
	* **\<columna 5>**: de tipo Integer \<AstraZeneca\>.
	* **\<columna 6>**: de tipo Integer \<Janssen\>.
	* **\<columna 7>**: de tipo Integer \<Personas_pauta_completa\>.

* **\<estudio_clinico.csv\>**:
	* **\<columna 1>**: de tipo Integer \<Id\>.
	* **\<columna 2>**: de tipo String \<Genero\>.
	* **\<columna 3>**: de tipo Integer \<Edad\>.
	* **\<columna 4>**: de tipo Boolean \<Hipertension\>.
	* **\<columna 5>**: de tipo Boolean \<FactordeRiesgo\>.
	* **\<columna 6>**: de tipo Enum \<TipoResidencia\>.
	* **\<columna 7>**: de tipo Double \<Glucosa\>.

* **\<medicamentos.csv\>**:
	* **\<columna 1>**: de tipo String \<Nombre_medicamento\>.
	* **\<columna 2>**: de tipo String \<Tipo_medicamento\>.
	* **\<columna 3>**: de tipo String \<Codigo_enfermedad\>.
	* **\<columna 4>**: de tipo String \<Farmaceutica\>.
	* **\<columna 5>**: de tipo Double \<Puntuacion\>.
	* **\<columna 6>**: de tipo Integer \<Indice_somatico\>.
	* **\<columna 7>**: de tipo LocalDate \<Fecha_catalogo\>.
	
## Tipos implementados

Los tipos implementados son:

	* **Tipo Checkers:** Class.
	* **Tipo EstudioClinico:** Interface.
	* **Tipos EstudioClinicoBucles:** Class.
	* **Tipos EstudioClinicoStream:** Class.
    * **Tipo Paciente:** Record.
    * **Tipo PacienteEstudio:** Record.
    * **Tipo Persona:** Record.
    * **Tipos TestEstudioClinicoBucles:** Módulo test de la clase EstudioClinicoBucles.
	* **Tipos TestEstudioClinicoStream:** Módulo test de la clase EstudioClinicoStream.
    * **Tipo TipoResidencia:** Enumerado.
    * **Tipo FactoriaMedicamentos:** Clase, contiene el parseo de la clase Medicamento.
    * **Tipo Medicamento:** Clase.
    * **Tipo TipoMedicamento:** Enumerado.
    * **Tipo TestMedicamentos:** Clase. Modulo test de la clase Medicamento y FactoriaMedicamentos.
    * **Tipo Vacunacion:** Record

### Tipo Persona:
	* Persona:
		• Propiedades:
			- nombre, de tipo String.
			- apellidos, de tipo String.
			- dni, de tipo String.
			- fecha de nacimiento, de tipo LocalDate.
			- edad, de tipo Integer. (Derivada a partir de la fecha de nacimiento).
		• Restricciones:
			- La fecha de nacimiento debe ser anterior a la fecha actual.
			- El dni debe ser una cadena con ocho dígitos y seguidos de una letra.
		• Representación como cadena: por defecto asociado al record.
		• Criterio de igualdad: por defecto asociado al record.
		• Orden natural: por dni.
		• Comentarios: añada los siguientes métodos de factoría:
    
			- Método static of: Recibe nombre, apellidos, dni y fecha de nacimiento y devuelve una persona.
			- Método static parse: Recibe una cadena con un formato específico y devuelve una persona.
				Ejemplo de cadena: “Juan, García Rodríguez, 12755078Z, 20/03/1965”.
			- Incluya un método main para comprobar el correcto funcionamiento del método
				parse. public static void main(String[] args){ ... }

### Tipo Paciente:
	* Paciente:
		• Propiedades:
			- persona, de tipo Persona.
			- código de ingreso, de tipo String.
			- fecha y hora de ingreso, de tipo LocalDateTime.
			- fecha de ingreso, de tipo LocalDate. (Derivada a partir de la fecha y hora de ingreso)
			- hora de ingreso, de tipo String. (Derivada a partir de la fecha y hora de ingreso).
				Ejemplo de cadena: “15:03”. Es decir, la hora y los minutos tienen que tener dos
				dígitos. No valdría escribir “15:3”.
		• Restricciones:
			- La fecha y hora de ingreso debe ser anterior o igual a la fecha actual.
		• Representación como cadena: por defecto asociado al record.
		• Criterio de igualdad: por defecto asociado al record.
		• Comentarios: añada los siguientes métodos de factoría:
			- Método static of: Recibe nombre, apellidos, dni, fecha de nacimiento, código y fecha y hora de ingreso y devuelve un paciente.
			- Método static of: Recibe un objeto persona, un código y una fecha y hora de ingreso y devuelve un paciente.

### Tipo PacienteEstudio:
	* PacienteEstudio:
		• Propiedades:
			- id, de tipo String.
			- genero, de tipo String.
			- edad, de tipo Double.
			- hipertensión, de tipo Boolean.
			- enfermedad del corazón, de tipo Boolean.
			- tipo de residencia, enumerado TipoResidencia, cuyos valores son rural o urbana.
			- nivel medio de glucosa, de tipo Double.
			- factor de riesgo, de tipo Boolean. (Derivada, si tiene hipertensión y más de 40 años se
				considerará que tiene factor de riesgo).
		• Restricciones:
			- La edad tiene que ser mayor o igual que cero y menor o igual que 130.
			- El nivel medio de glucosa tiene que ser mayor o igual que cero.
		• Representación como cadena: informa del id y la edad del paciente.
		• Criterio de igualdad: por defecto asociado al record.
		• Criterio de orden: según la edad y el id.
		• Comentarios: añada los siguientes métodos de factoría:
			- Método static of: Recibe valores para cada propiedad básica y devuelve un objeto del tipo.
			- Método static parse: Recibe una cadena con un formato especificado y y devuelve un objeto del
									tipo. Ejemplo de cadena: “6306;Male;80;false;false;URBANA;83.84”
			- Incluya un método main para comprobar el correcto funcionamiento del método
				parse. public static void main(String[] args){ ... }

### Tipo Vacunacion:
	*Vacunacion:
		• Propiedades:
			- fecha, de tipo LocalDate.
			- comunidad, de tipo String.
			- pfizer, de tipo Integer.
			- moderna, de tipo Integer.
			- astrazeneca, de tipo Integer.
			- janssen de tipo Integer.
			- número de personas, de tipo Integer.
			- número total, de tipo Integer. (Derivada, siendo la suma de dosis de Pfizer, moderna, astrazeneca y janssen).
		• Restricciones:
			- La fecha de debe ser posterior al 01/02/2021.
		• Representación como cadena: por defecto asociado al record.
		• Criterio de igualdad: por defecto asociado al record.
		• Orden natural: por comunidad y en caso de igualdad por fecha.
		• Comentarios: añada los siguientes métodos de factoría:
			- Método static of: Recibe valores para cada propiedad básica y devuelve un objeto del tipo.
			- Método static parse: Recibe una cadena con un formato específico y devuelve un objeto del tipo.
				Ejemplo de cadena: “04/01/2021;Andalucía;140295;0;0;0;0”.
			- Incluya un método main para comprobar el correcto funcionamiento del método
				parse. public static void main(String[] args){ ... }
				
				
				
## Tipo Medicamento: (Clase)
	* Medicamento:
		• Propiedades:
			- nombre del medicamento, de tipo String, observable.
			- tipo de medicamento, enumerado de tipo TipoMedicamento, observable. Los valores
			del enumerado son anatómico, químico y terapéutico.
			- código de la enfermedad, de tipo String, observable.
			- farmacéutica, de tipo String, observable.
			- puntación, de tipo Double, observable.
			- índice somático, de tipo Integer, observable.
			- fecha de catálogo, de tipo LocalDate, observable y modificable.
			- tratar enfermedad, de tipo Boolean. (Derivada, siendo cierta si el código de la
			enfermedad coincide con un parámetro de tipo cadena que reciben como argumento
			la propiedad).
		• Restricciones:
			- La puntación tiene que ser mayor estricta que cero.
			- El índice somático tiene que ser mayor o igual que 1000.
			- La fecha de catálogo tiene que ser posterior al 01/01/2015.
		• Representación como cadena: según el nombre del medicamento y de la farmacéutica.
		• Criterio de igualdad: por nombre del medicamento y farmacéutica.
		• Orden natural: por nombre del medicamento y en caso de igualdad por la farmacéutica.
		
### Otras operaciones:

    *Clase FactoriaMedicamentos: Se ha programado una clase FactoriaMedicamentos que incluye, de momento, un metodo static de nombre parseaMedicamento, que recibe una cadena con un formato especifico y devuelve un objeto del tipo Medicamento.
    *Clase TestFactoriaMedicamentos: Se ha implementado tambien una clase de nombre TestFactoriaMedicamentos que comprueba el correcto funcionamiento del metodo anterior.


## Tipo EstudioClinico: (Interfaz)
	* EstudioClinico:
		• Estos son los métodos/operaciones a realizar:
		
		public interface EstudioClinico { // Propiedades de lista
			Integer numeroPacientes();
			void incluyePaciente(PacienteEstudio paciente);
			void incluyePacientes(Collection<PacienteEstudio> pacientes);
			void eliminaPaciente(PacienteEstudio paciente);
			Boolean estaPaciente(PacienteEstudio paciente);
			void borraEstudio();
	
			// Método de factoría
			EstudioClinico of(String nombreFichero);
			List<PacienteEstudio> leeFichero(String nombreFichero);
	
			// Tratamientos secuenciales: implementaciónn funcional vs. imperativa
			//existe, paraTodo
			Boolean todosPacienteSonDelTipo(TipoResidencia tipo);
			Boolean existeAlgunPacienteDelTipo(TipoResidencia tipo);
	
			//contador, suma, media
			Integer numeroPacientesFactorRiesgo();
			Double edadMediaPacientesConFactorRiesgo();
			
			//filtrado
			List<PacienteEstudio> filtraPacientesPorEdad(Double edad);
	
			//devuelve Map que agrupa
			Map<String,List<PacienteEstudio>> agruparPacientesEdadMayorQuePorGenero(Double edad); //devuelve Map que realiza un cálculo
			Map< ,Long> numeroPacientesPorGenero();
			Map< ,Double> edadMediaPacientesPorPorGenero();
 }

## Tipo EstudioClinicoBucles: (Clase)
	* EstudioClinicoBucles:
		- Métodos de propiedades de listas.
		- Métodos de tratamientos secuenciales.
		- Métodos para la construcción de diccionarios o mapas
	
## Tipo EstudioClinicoStream: (Clase)
	* EstudioClinicoStream
		- Métodos de propiedades de listas.
		
## Tipo FactoriaMedicamentos: (Clase)
	* FactoriaMedicamentos:
	- Contiene un método de nombre leeFichero que, 
		dada una cadena con el nombre del fichero, devuelve una
		lista de objetos Medicamento.
	
## Tipo FactoriaVacunacion: (Clase)
	* FactoriaVacunacion:
	- Contiene un método de nombre leeFichero que, 
		dada una cadena con el nombre del fichero, devuelve una
		lista de objetos Vacunacion.
		











