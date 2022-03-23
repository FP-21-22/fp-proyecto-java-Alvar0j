package fp.farmaceutico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fp.utiles.Checkers;

public class FactoriaMedicamento {

	public static Medicamento parseaMedicamente(String linea) {
		
		Checkers.checkNoNull("Linea vacia", linea);
		String [] trozos = linea.split(",");
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
