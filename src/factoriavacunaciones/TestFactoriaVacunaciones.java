package factoriavacunaciones;

import java.util.List;
import fp.vacunas.*;


public class TestFactoriaVacunaciones {

	public static void main(String[] args) {
		String ruta = "data/ccaa_vacunas_3.csv";
		List<Vacunacion> lista = FactoriaVacunaciones.leeFichero(ruta);
		for(Vacunacion e:lista) {
			System.out.println(e);
		}
	}
}