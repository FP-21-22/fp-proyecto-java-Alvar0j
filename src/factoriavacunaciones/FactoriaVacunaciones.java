package factoriavacunaciones;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fp.utiles.Checkers;
import fp.vacunas.Vacunacion;

public class FactoriaVacunaciones {

	public static List<Vacunacion> leeFichero(String fichero){
		
		List<Vacunacion> res =new ArrayList<Vacunacion>();
		List<String> aux = null;
		
		try {
			aux = Files.readAllLines(Paths.get(fichero));

		}catch(IOException e) {
			e.printStackTrace();
		}
		int cont = 0;
		for(String e:aux) {
			if(cont>0) {
				Vacunacion p = parseo(e);
				res.add(p);
			}
			cont++;
		}
		return res;

	}

	private static Vacunacion parseo(String cadena) {
		//		Fecha_publicación;CCAA;Pfizer;Moderna;AstraZeneca;Janssen;Personas_pauta_completa
		//		04/01/2021;Andalucía;140295;0;0;0;0
		Checkers.checkNoNull("Línea vacía", cadena);
		String[] trozos = cadena.split(";");
		Checkers.check("Error parametros", trozos.length==7);

		LocalDate fecha = LocalDate.parse(trozos[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String comunidad = trozos[1];
		Integer pfizer = Integer.parseInt(trozos[2]);
		Integer moderna = Integer.parseInt(trozos[3]);
		Integer astrazeneca = Integer.parseInt(trozos[4]);
		Integer janssen = Integer.parseInt(trozos[5]);
		Integer numeroPersonas = Integer.parseInt(trozos[6]);
		return new Vacunacion (fecha,comunidad,pfizer,astrazeneca,moderna,janssen,numeroPersonas);
	}
	
	public Integer numeroPacientes() {
		
		try {

			Path file = Paths.get("ccaa_vacunas_3");

			long count = Files.lines(file).count();
			System.out.println("Total Lineas: " + count);

		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
}


