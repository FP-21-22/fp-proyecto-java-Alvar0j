package fp.farmaceutico;

import java.time.LocalDate;
import java.util.Objects;

import fp.utiles.Checkers;

public class Medicamento {
	
	//Atributos
	
	public String nombre;
	public TipoMedicamento tipoMedicamento;
	public String codigoEnfermedad;
	public String farmaceutica;
	public Double puntuacion;
	public Integer indiceSom;
	public LocalDate fechaCatalogo;
	
	//Propiedades Derivadas
	
	public Boolean tratarEnfermedad(String cadena) {
		Boolean res = false;
		if (cadena.equals(getCodigoEnfermedad())) {
			res = true;
		}
		return res;
	}

	//Metodo propiedades basicas
	
	public LocalDate getFechaCatalogo() {
		return fechaCatalogo;
	}
	public void setFechaCatalogo(LocalDate fechaCatalogo) {
		this.fechaCatalogo = fechaCatalogo;
	}
	public String getNombre() {
		return nombre;
	}
	public TipoMedicamento getTipoMedicamento() {
		return tipoMedicamento;
	}
	public String getCodigoEnfermedad() {
		return codigoEnfermedad;
	}
	public String getFarmaceutica() {
		return farmaceutica;
	}
	public Double getPuntuacion() {
		return puntuacion;
	}
	public Integer getIndiceSom() {
		return indiceSom;
	}

	//-------------------------------------------------------------------//
	
	//Constructor

	public Medicamento(String nombre, TipoMedicamento tipoMedicamento, String codigoEnfermedad, String farmaceutica,
			Double puntuacion, Integer indiceSom, LocalDate fechaCatalogo) {
		//Restricciones
		Checkers.check("La puntación tiene que ser mayor estricta "
				+ "que cero.", puntuacion >0 );
		Checkers.check("El índice somático tiene que ser mayor "
				+ "o igual que 1000.", indiceSom >=1000 );
		Checkers.check("La fecha de catálogo tiene que ser "
				+ "posterior al 01/01/2015.", fechaCatalogo.isAfter(LocalDate.of(2015, 01, 01)));
		
		this.nombre = nombre;
		this.tipoMedicamento = tipoMedicamento;
		this.codigoEnfermedad = codigoEnfermedad;
		this.farmaceutica = farmaceutica;
		this.puntuacion = puntuacion;
		this.indiceSom = indiceSom;
		this.fechaCatalogo = fechaCatalogo;

	}
	
	//-------------------------------------------------------------------//
	
	// Representación como cadena: según el nombre del medicamento y de la farmacéutica.

	
	public String formatoEspecifico(){
		String res = "'" + this.getNombre() + "," + this.getFarmaceutica() + "'";
		return res;

	}
	
	@Override
	public String toString() {
		return "Medicamento [ nombre = " + nombre + ", tipoMedicamento = " + tipoMedicamento + ", codigoEnfermedad = "
				+ codigoEnfermedad + ", farmaceutica = " + farmaceutica + ", puntuacion = " + puntuacion + ", indiceSom = "
				+ indiceSom + ", fechaCatalogo = " + fechaCatalogo + " ]";
	}
	
	//-------------------------------------------------------------------//

	//Criterio de igualdad: por nombre del medicamento y farmacéutica.
	
	@Override
	public int hashCode() {
		return Objects.hash(farmaceutica, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		return Objects.equals(farmaceutica, other.farmaceutica) && Objects.equals(nombre, other.nombre);
	}
	
	//-------------------------------------------------------------------//
	
	//Orden natural: por nombre del medicamento y en caso de igualdad por la farmacéutica.
	
	public int compareTo(Medicamento p) {
		int res = this.getNombre().compareTo(p.getNombre());
		if (res == 0);{
			res = this.getFarmaceutica().compareTo(p.getFarmaceutica());
			if (res == 0);{
				return res;
			}
		}
	}
	
	//-------------------------------------------------------------------//
	
	//Comentarios
	
	

}
