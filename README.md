# Proyecto del Segundo Cuatrimestre Fundamentos de Programación (Curso  \<XX\>/\<YY\>)
Autor: Álvaro Jiménez Osuna   uvus:alvjimosu

Aquí debes añadir la descripción del dataset y un enunciado del dominio del proyecto.


## Estructura de las carpetas del proyecto

* **/src**: Contiene los diferentes archivos que forman parte del proyecto. Debe estar estructurado en los siguentes paquetes
  * **fp.\<FactoriaMedicamentos\>**: Paquete que contiene los tipos del proyecto.
  * **fp.\<FactoriaVacunaciones\>**: Paquete que contiene los tipos del proyecto.
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

    * **Tipo Paciente: Record.
    * **Tipo PacienteEstudio: Record.
    * **Tipo Persona: Record.
    * **Tipo TipoResidencia: Enumerado.
    * **Tipo FactoriaMedicamentos: Clase, contiene el parseo de la clase Medicamento.
    * **Tipo Medicamento: Clase.
    * **Tipo TipoMedicamento: Enumerado.
    * **Tipo TestMedicamentos: Clase. Modulo test de la clase Medicamento y FactoriaMedicamentos.
    * **Tipo Vacunacion: Record

### Tipo Persona (Record)


**Propiedades**:

   - nombre, de tipo String.
   - apellidos, de tipo String.
   - dni, de tipo String.
   - fecha de nacimiento, de tipo LocalDate.
   - edad, de tipo Integer. (derivada a partir de la fecha de nacimiento).

**Constructores**: 

- C1: Descripción del constructor 1.
- C2: Descripción del constructor 2.
- ...

**Restricciones**:
 

  - R1: La fecha de nacimiento debe ser anterior a la fecha actual.
  - R2: El dni debe ser una cadena con ocho digitos y seguidos de una letra.

**Criterio de igualdad**:  Por defecto asociado al record.

**Criterio de ordenación**:Por dni.

**Otras operaciones**:

   - Metodo static of: recibe nombre, apellidos, dni y fecha de nacimiento y devuelve una persona.
   - Metodo static parse: Recibe una cadena con un formato específico y devuelve una persona.
   - Metodo main para comprobar el correcto funcionamiento del metodo parse.
Tipo Persona (Record)

Propiedades:

    nombre, de tipo String.
    apellidos, de tipo String.
    dni, de tipo String.
    fecha de nacimiento, de tipo LocalDate.
    edad, de tipo Integer. (derivada a partir de la fecha de nacimiento).

Restricciones:

    R1: La fecha de nacimiento debe ser anterior a la fecha actual.
    R2: El dni debe ser una cadena con ocho digitos y seguidos de una letra.

Criterio de igualdad: Por defecto asociado al record.

Criterio de ordenación: Por dni.

Otras operaciones:

    Metodo static of: recibe nombre, apellidos, dni y fecha de nacimiento y devuelve una persona.
    Metodo static parse: Recibe una cadena con un formato específico y devuelve una persona.
    Metodo main para comprobar el correcto funcionamiento del metodo parse.

Tipo Paciente (Record)

Propiedades:

    persona, de tipo Persona.
    codigo de ingreso, de tipo String.
    fecha y hora de ingreso, de tipo LocalDateTime.
    fecha de ingreso, de tipo LocalDate. (Derivada a partir de la fecha y hora de ingreso).
    hora de ingreso, de tipo String. (Derivada a partir de la fecha y hora de ingreso).

Restricciones:

    R1: La fecha y hora de ingreso debe ser anterior o igual a la fecha actual.

Representación como cadena: Por defecto asociado al record.

Criterio de igualdad: Por defecto asociado al record.

Otras operaciones:

    Metodo static of: recibe nombre, apellidos, dni y fecha de nacimiento, codigo y fecha y hora de ingreso y devuelve un paciente.
    Metodo static of: recibe un objeto persona, un codigo y una fecha y hora de ingreso y devuelve un paciente.

Tipo PacienteEstudio (Record)

Propiedades:

    id, de tipo String.
    genero, de tipo String.
    edad, de tipo Double.
    hipertension, de tipo Boolean.
    enfermedad del corazon, de tipo Boolean.
    tipo de residencia, enumerado TipoResidencia, cuyos valores son rural o urbana.
    nivel medio de glucosa, de tipo Double.
    factor de riesgo, de tipo Boolean. (Derivada, si tiene hipertension y mas de 40 años se considerara que tiene factor de riesgo).

Restricciones:

    R1: La edad tiene que ser mayor o igual que cero y menor o igual que 130.
    R2: El nivel medio de glucosa tiene que ser mayor o igual que cero.

Representación como cadena: Por defecto asociado al record.

Criterio de igualdad: Por defecto asociado al record.

Criterio de orden: Segun la edad y el id.

Otras operaciones:

    Metodo static of: Recibe valores para cada propiedad básica y devuelve un objeto del tipo.
    Metodo static parse: Recibe una cadena con un formato especifico y devuelve un objeto del tipo.
    Metodo main para comprobar el correcto funcionamiento del metodo parse.

Tipo Vacunacion (Record)

Propiedades:

    fecha, de tipo LocalDate.
    comunidad, de tipo String.
    pfizer, de tipo Integer.
    moderna, de tipo Integer.
    astrazeneca, de tipo Integer.
    janssen, de tipo Integer.
    numero de personas, de tipo Integer.
    numero total, de tipo integer (Derivada, siendo la suma de dosis de Pfizer, moderna, astrazeneca y janssen).

Restricciones:

    R1: La fecha debe ser posterior al 10/02/2021.

Representación como cadena: Por defecto asociado al record.

Criterio de igualdad: Por defecto asociado al record.

Criterio de orden: Por comunidad y en caso de igualdad por fecha.

Otras operaciones:

    Metodo static of: Recibe valores para cada propiedad básica y devuelve un objeto del tipo.
    Metodo static parse: Recibe una cadena con un formato especifico y devuelve un objeto del tipo.
    Metodo main para comprobar el correcto funcionamiento del metodo parse.

Tipo Medicamento (Clase)

Propiedades:

    nombre del medicamento, de tipo String, observable.
    tipo de medicamento, enumerado de tipo TipoMedicamento, observable. Los valores del enumerado son anatomico, quimico y terapeutico.
    codigo de la enfermedad, de tipo String, observable.
    farmaceutica, de tipo String, observable.
    puntacion, de tipo Double, observable.
    indice somatico, de tipo Integer, observable.
    fecha de catalogo, de tipo LocalDate, observable y modificable.
    tratar enfermedad, de tipo Boolean. (Derivada, siendo cierta si el codigo de la enfermedad coincide con un parametro de tipo cadena que recibe como argumento la propiedad).

Restricciones:

    R1: La puntacion debe ser mayor estricta que cero.
    R2: El indice somatico tiene que ser mayor o igual que 1000.
    R3: La fecha de catalogo tiene que ser posterior al 01/01/2015.

Representacion como cadena: Segun el nombre del medicamento y de la farmaceutica.

Criterio de igualdad: Por nombre del medicamento y farmaceutica.

Orden natural: Por nombre del medicamento y en caso de igualdad por la farmaceutica.

Otras operaciones:

    Clase FactoriaMedicamentos: Se ha programado una clase FactoriaMedicamentos que incluye, de momento, un metodo static de nombre parseaMedicamento, que recibe una cadena con un formato especifico y devuelve un objeto del tipo Medicamento.
    Clase TestFactoriaMedicamentos: Se ha implementado tambien una clase de nombre TestFactoriaMedicamentos que comprueba el correcto funcionamiento del metodo anterior.

