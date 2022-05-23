package fp.vacunas;


import java.time.LocalDate;
import java.util.Objects;
import fp.clinico.Checkers;


//Propiedades
public record Vacunacion(
		LocalDate fecha,
		String comunidad,
		Integer pfizer,
		Integer astrazeneca,
		Integer moderna,
		Integer janssen,
		Integer numeroPersonas) {

	//Propiedades derivadas

	public Integer numeroTotal() {
		Integer res = this.pfizer() + this.moderna() + 
				this.janssen() + this.astrazeneca();
		return res;
	}

	//-------------------------------------------------------------------//

	// Restricciones

	public Vacunacion{

		Checkers.check("La fecha debe ser posterior al 02/01/2021", fecha.isAfter(LocalDate.of(2021, 01, 02)));
	}

	//-------------------------------------------------------------------//

	// Representación como cadena - (por defecto asociado al record)

	public String formatoEspecifico(){
		String res = "'" + this.fecha() + "," + this.comunidad() 
		+ "," + this.pfizer() + this.astrazeneca() + "," + this.moderna() 
		+ "," + this.janssen() + this.numeroPersonas() + this.numeroTotal() + "'";
		return res;

	}

	//-------------------------------------------------------------------//

	//Criterio de igualdad - (por defecto asociado al record)

	@Override
	public int hashCode() {
		return Objects.hash(astrazeneca, comunidad, fecha, janssen, moderna, numeroPersonas, pfizer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vacunacion other = (Vacunacion) obj;
		return Objects.equals(astrazeneca, other.astrazeneca) && Objects.equals(comunidad, other.comunidad)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(janssen, other.janssen)
				&& Objects.equals(moderna, other.moderna) && Objects.equals(numeroPersonas, other.numeroPersonas)
				&& Objects.equals(pfizer, other.pfizer);
	}

	//-------------------------------------------------------------------//

	//Orden natural - (por comunidad y en caso de igualdad por fecha)

	public int compareTo(Vacunacion p) {
		int res = this.comunidad().compareTo(p.comunidad());
		if (res == 0);{
			res = this.fecha().compareTo(p.fecha());
			if (res == 0);{
				return res;
			}
		}
	}


	//-------------------------------------------------------------------//


	//Metodo static of:

	public static Vacunacion of( LocalDate fecha,String comunidad,Integer pfizer,
			Integer astrazeneca,Integer moderna,Integer janssen,Integer numeroPersonas) {
		Vacunacion res = new Vacunacion(fecha,comunidad,pfizer,astrazeneca,moderna,janssen,numeroPersonas);
		return res;
	}

	//Metodo static parse:

	public static Vacunacion parseo(String linea) {
		Checkers.checkNoNull("Linea vacia",linea);
		String [] trozos = linea.split(";");
		Checkers.check("Error parametros", trozos.length==7);
		LocalDate fecha = LocalDate.parse(trozos[0]);
		String comunidad = trozos[1];
		Integer pfizer = Integer.parseInt(trozos[2]);
		Integer astrazeneca = Integer.parseInt(trozos[3]);
		Integer moderna = Integer.parseInt(trozos[4]);
		Integer janssen = Integer.parseInt(trozos[5]);
		Integer numeroPersonas = Integer.parseInt(trozos[6]);
		return new Vacunacion (fecha,comunidad,pfizer,
				astrazeneca,moderna,janssen,numeroPersonas);

	}



	//-------------------------------------------------------------------//

	public static void main (String[] args) {
		Vacunacion p1 = parseo("2021-02-02;Algeciras;2;0;0;0;20");
		System.out.println(p1);

	}

}
