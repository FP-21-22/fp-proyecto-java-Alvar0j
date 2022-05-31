package fp.vacunas;

import java.time.LocalDate;
import java.util.List;

import factoriavacunaciones.FactoriaVacunaciones;

public class TestVacunaciones {
	
	public static void main(String[] args) {
		List<Vacunacion> lista = FactoriaVacunaciones.leeFichero("data/ccaa_vacunas_3.csv");
		Vacunaciones vac = new Vacunaciones (lista.stream());
		
		System.out.println(" 1) Vacunaciones entre 04/01/2021 y 05/02/2021: "+ 
		vac.vacunacionesEntreFechas(LocalDate.of(2021, 01, 4), LocalDate.of(2021, 02, 5)));
		System.out.println(" 2) Existen personas con la pauta completa por encima de 20 en Andalucía: "+
				vac.existeNumPersonasPautaCompletaPorEncimaDe("Andalucía", 20));
		System.out.println(" 3) Existen personas con la pauta completa por encima de 2000000 en Andalucía: "+
				vac.existeNumPersonasPautaCompletaPorEncimaDe("Andalucía", 2000000));
		System.out.println(" 4) Fecha con mas vacunaciones en Castilla la Mancha: "+
				vac.diaMasVacunacionesEn("Castilla La Mancha"));
		System.out.println(" 5) Vacunaciones asociadas a las fechas: "+
				vac.vacunacionesPorFecha());
		System.out.println(" 6) Numero Total de vacunas puestas en cada comunidad: " +
		vac.maximoNumTotalVacunasporComunidad());
		
	}
}
	

   