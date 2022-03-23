package fp.clinico;

import java.util.Objects;

//Propiedades
public record PacienteEstudio(
		String id, 
		String genero,
		Double edad, 
		Boolean hipertension,
		Boolean enfermedadDelCorazon,
		TipoResidencia tipoResidencia,
		Double glucosa) implements Comparable<PacienteEstudio> {

	//Propiedades Derivadas

	//	public TipoResidencia tipoResidencia(){
	//		TipoResidencia res = null;
	//		if () {
	//			res = TipoResidencia.RURAL;
	//		} else {
	//			res = TipoResidencia.URBANA;
	//		}
	//		return res;
	//	}

	public Object factorDeRiesgo() {
		boolean factorDeRiesgo = false;
		if (hipertension == true && edad >= 40) {
			factorDeRiesgo = true;
		}
		return (Boolean) factorDeRiesgo;
	}

	//-------------------------------------------------------------------//

	//Restricciones

	public PacienteEstudio{
		Checkers.check("La edad tiene que ser mayor o "
				+ "igual que cero y menor o igual que 130."
				,edad <= 130 && edad >= 0);
		Checkers.check("El nivel medio de glucosa tiene que ser mayor o igual que cero."
				,glucosa >= 0);
	}


	//-------------------------------------------------------------------//

	// Representación como cadena - (informa del id y la edad del paciente)

	public String representacionCadena() {
		String res = this.id()+ "," + this.edad();
		return res;
	}


	//-------------------------------------------------------------------//

	// Criterio de igualdad - (por defecto asociado al record)
	@Override
	public int hashCode() {
		return Objects.hash(edad, enfermedadDelCorazon, genero, glucosa, hipertension, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PacienteEstudio other = (PacienteEstudio) obj;
		return Objects.equals(edad, other.edad) && Objects.equals(enfermedadDelCorazon, other.enfermedadDelCorazon)
				&& Objects.equals(genero, other.genero) && Objects.equals(glucosa, other.glucosa)
				&& Objects.equals(hipertension, other.hipertension) && Objects.equals(id, other.id);
	}

	//-------------------------------------------------------------------//

	// Orden natural - (según la edad y el id)

	@Override
	public int compareTo(PacienteEstudio o) {
		int res = this.id().compareTo(o.id());
		if (res == 0);{
			return res;
		}
	}
	public int compareTo1(PacienteEstudio e) {
		int res = this.edad().compareTo(e.edad());
		if (res == 0);{
			return res;
		}
	}

	//Comentarios

	// Metodo static of:

	public static PacienteEstudio of (String id, String genero, Double edad,
			Boolean hipertension, Boolean enfermedadDelCorazon,TipoResidencia tipoResidencia,Double glucosa ) {
		PacienteEstudio res = new PacienteEstudio(id, genero, edad, hipertension, enfermedadDelCorazon, tipoResidencia, glucosa);
		return res;

	}

	// Metodo static parse:

	public static PacienteEstudio parseo(String linea) {
		Checkers.checkNoNull("Linea vacia", linea);
		String [] trozos = linea.split(";");
		Checkers.check("Error parametros", trozos.length==7);
		String id = trozos[0];
		String genero = trozos[1];
		Double edad = Double.parseDouble(trozos[2]);
		Boolean hipertension = Boolean.parseBoolean(trozos[3]);
		Boolean enfermedadDelCorazon = Boolean.parseBoolean(trozos[4]);
		TipoResidencia tipoResidencia = TipoResidencia.valueOf(trozos[5]);
		Double glucosa = Double.parseDouble(trozos[6]);

		return new PacienteEstudio (id,genero,edad,hipertension,enfermedadDelCorazon,tipoResidencia,glucosa);
	}
	
	//Metodo Main del Parseo

	public static void main (String[] args) {
		PacienteEstudio p1 = parseo("1234;Macho;13;true;true;URBANA;80.83");
		System.out.println(p1);

	}
}
