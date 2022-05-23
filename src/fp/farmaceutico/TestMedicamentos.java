package fp.farmaceutico;

import java.time.LocalDate;
import java.util.List;

import factoriamedicamentos.FactoriaMedicamentos;

public class TestMedicamentos {

	public static void main(String[] args) {
		List<Medicamento> lista = FactoriaMedicamentos.leeFichero("data/medicamentos.csv");
		ListadoMedicamentos med = new ListadoMedicamentos (lista.stream());
		
		System.out.println("# Existe un medicamento de tipo Anatomico posterior al 1/02/2020: \n" + 
		med.existeMedicamentoSegunTipoAnteriorA(TipoMedicamento.Anatomico, LocalDate.of(2020, 02, 1)));
		
		System.out.println("");
		
		System.out.println("# Nombre medicamento con una puntuación mayor a 129: \n" +
		med.nombreMedicamentosPuntuacionMayorA(129));
		
		System.out.println("");
		
		System.out.println("# Nombre del medicamento con mayor índice somático: \n" +
		med.nombreMedicamentoMayorIndiceSomaticoSegunTipoMedicamento(TipoMedicamento.Quimico));
		
		System.out.println("");
		
		System.out.println("# Tipos de medicamento  y su puntuación media: \n"+
		med.agrupaTipoMedicamentoSegunPuntuacionMedia());
		
		System.out.println("");
		
		System.out.println("# Fecha del Catalogo que mas se repite = " +
		med.fechaCatalogoMasFrecuente());
		

	}

}
