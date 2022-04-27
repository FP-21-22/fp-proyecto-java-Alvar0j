package FactoriaMedicamentos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fp.farmaceutico.FactoriaMedicamento;
import fp.farmaceutico.Medicamento;
import fp.farmaceutico.TipoMedicamento;
import fp.utiles.Checkers;
import fp.vacunas.Vacunacion;

public class FactoriaMedicamentos {

	public static List<Medicamento> leeFichero(String fichero){

		List<Medicamento> res = new ArrayList<Medicamento>();
		List<String> aux = null;
		try {
			aux = Files.readAllLines(Paths.get(fichero));

		}catch(IOException e) {
			e.printStackTrace();
		}
		int cont = 0;
		for(String e:aux) {
			if(cont>0) {
				Medicamento p = parseo(e);
				res.add(p);
			}
			cont++;

		}
		return res;

	}
	private static Medicamento parseo(String cadena) {
		//		Nombre_medicamento,Tipo_medicamento,Codigo_enfermedad,Farmaceutica,Puntuacion,Indice_somatico,Fecha_catalogo
		//		efavirenz,Anatomico,Y212XXA,Actavis Mid Atlantic LLC,90.0,1848,04/12/2019

		Checkers.checkNoNull("Linea vacia", cadena);
		String [] trozos = cadena.split(",");
		Checkers.check("Error de parametros", trozos.length==7);
		String nombre = trozos[0];
		TipoMedicamento tipoMedicamento = TipoMedicamento.valueOf(trozos[1]);
		String codigoEnfermedad = trozos [2];
		String farmaceutica = trozos[3];
		Double puntuacion = Double.parseDouble(trozos [4]);
		Integer indiceSom = Integer.parseInt(trozos [5]);
		LocalDate fechaCatalogo = LocalDate.parse(trozos[6], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		return new Medicamento(nombre,tipoMedicamento,codigoEnfermedad,farmaceutica,
				puntuacion,indiceSom,fechaCatalogo);
	}
}


