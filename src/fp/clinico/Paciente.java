package fp.clinico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

//Propiedades
public record Paciente(
		Persona persona, 
		String codigoIngreso,
		LocalDateTime fechaHora){


	//Métodos Derivados

	// Fecha de ingreso, de tipo LocalDate. 
	//(Derivada a partir de la fecha y hora de ingreso)

	public String fechaIngreso (LocalDateTime fechaHora) {
		String res = fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return res;
	}
	public String soloFechaIngreso() {
		String res = this.fechaIngreso(this.fechaHora());
		return res;
	}

	// Hora de ingreso, de tipo String. 
	//(Derivada a partir de la fecha y hora de ingreso).

	public String horaIngreso (LocalDateTime fechaHora) {
		String res = fechaHora.format(DateTimeFormatter.ofPattern("HH:mm"));
		return res;
	}
	public String soloHoraIngreso() {
		String res = this.horaIngreso(this.fechaHora());
		return res;
	}

	//-------------------------------------------------------------------//

	// Restricciones
	public Paciente{
		Checkers.check("La fecha y hora de ingreso debe ser anterior o igual a la fecha actual.",
				fechaHora.isBefore(LocalDateTime.now()));
	}

	//-------------------------------------------------------------------//

	// Representación como cadena - (por defecto asociado al record)

	public String formatoEspecifico(){
		String res = "'" + this.persona() + "," + this.codigoIngreso() 
		+ "," + this.fechaHora() + "'";
		return res;

	}

	//-------------------------------------------------------------------//

	// Criterio de igualdad - (por defecto asociado al record)
	@Override
	public int hashCode() {
		return Objects.hash(persona, codigoIngreso, fechaHora);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(persona, other.persona) && Objects.equals(codigoIngreso, other.codigoIngreso)
				&& Objects.equals(fechaHora, other.fechaHora);

	}

	//-------------------------------------------------------------------//

	//Comentarios

	// Metodo static of:
	public static Paciente of(String nombre, String apellidos, String dni, LocalDate fechaDeNacimiento, String codigoIngreso, LocalDateTime fechaHora) {
		Persona pers = new Persona(nombre,apellidos,dni,fechaDeNacimiento);
		Paciente res = new Paciente(pers,codigoIngreso,fechaHora);
		return res;

	}

	// Metodo static of:
	public static Paciente of(Persona p, String codigoIngreso, LocalDateTime fechaHora) {
		Paciente res = new Paciente(p,codigoIngreso,fechaHora);
		return res;

	}

	

}
