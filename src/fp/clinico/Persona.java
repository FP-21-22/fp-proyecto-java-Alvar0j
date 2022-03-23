package fp.clinico;

import java.time.LocalDate;

import java.util.Objects;


//Propiedades
public record Persona(
		String nombre, 
		String apellidos,
		String dni,
		LocalDate fechaDeNacimiento) implements Comparable<Persona> {

	//Métodos propiedades - derivadas + operaciones

	public  Integer getEdad() {
		//LocalDate hoy = LocalDate.now();
		//Integer res = this.until(hoy).getYears();
		Integer res = ((LocalDate) this.fechaDeNacimiento).until(LocalDate.now()).getYears();
		return res;
	}

	//-------------------------------------------------------------------//

	//Restricciones

	public Persona{

		Checkers.check("El dni debe ser una cadena con ocho dígitos y seguidos de una letra.", dni.length()== 9 && (dni.charAt(dni.length()-1)>= 'A' && dni.charAt(dni.length()-1) <= 'Z'));	
		Checkers.check("La fecha de nacimiento debe ser anterior a la fecha actual",fechaDeNacimiento.isBefore(LocalDate.now()));
	}

	//-------------------------------------------------------------------//

	//Representación como cadena -  (por defecto asociado al record)

	public String formatoEspecifico(){
		String res = "'" + this.nombre() + "," + this.apellidos() 
		+ "," + this.dni() + this.getEdad()+"'";
		return res;

	}

	//-------------------------------------------------------------------//

	//Criterio de igualdad - (por defecto asociado al record)

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, dni, fechaDeNacimiento, nombre);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(dni, other.dni)
				&& Objects.equals(fechaDeNacimiento, other.fechaDeNacimiento)
				&& Objects.equals(nombre, other.nombre);
	}

	//-------------------------------------------------------------------//

	//(c) Orden natural - (por dni)

	@Override
	public int compareTo(Persona o) {
		int res = this.dni().compareTo(o.dni());
		if (res == 0);{
			return res;
		}
	}

	//-------------------------------------------------------------------//

	//Comentarios

	//Método static Parse

	public static Persona parseo(String linea) {
		Checkers.checkNoNull("Linea vacia",linea);
		String[] trozos = linea.split(",");
		Checkers.check("Error parametros", trozos.length==4);
		String nombre = trozos[0];
		String apellidos = trozos[1];
		String dni = trozos[2];
		LocalDate fechaDeNacimiento = LocalDate.parse(trozos[3]);
		return new Persona (nombre, apellidos, dni, fechaDeNacimiento);

	}

	//Método static of

	public static Persona of(String nombre, String apellidos, String dni, LocalDate fechaDeNacimiento) {

		Persona res = new Persona(nombre,apellidos,dni,fechaDeNacimiento);
		return res;

	}
	
	//Método Main
	
	public static void main (String[] args) {
		Persona p1 = parseo("Alvaro,Jimenez Osuna,23456789A,2003-02-03");
		System.out.println(p1);
		
	}
}
