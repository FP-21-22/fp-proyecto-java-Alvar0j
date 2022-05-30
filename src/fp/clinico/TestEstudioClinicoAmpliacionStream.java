package fp.clinico;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class TestEstudioClinicoAmpliacionStream {

	public static void main(String[] args) {
		
		EstudioClinico aux = new EstudioClinicoStream();
		EstudioClinico lista = aux.of("data/estudio_clinico.csv");
		EstudioClinicoAmpliacion lista1 = new EstudioClinicoAmpliacionStream(lista.getPacientesEstudio());		
		System.out.println(lista1);
		
		Map<TipoResidencia,Integer> res = lista1.agruparNumeroPacientesPorTipoResidencia();	
		System.out.println(" 1) Número de pacientes por tipo de residencia: "+res);
		
		Map<TipoResidencia, Double> res2 = lista1.agruparNivelMedioGlucosaMedioPorTipoResidencia();
		System.out.println(" 2) Nivel medio de glucosa por tipo de residencia: "+res2);
		
		Map<TipoResidencia, PacienteEstudio> res3 =  lista1.agruparNivelMedioGlucosaMaximoPorTipoResidencia();
		System.out.println(" 3) Número de pacientes por tipo de residencia (2): "+res3);
		
		Map<String, List<PacienteEstudio>> res4 = lista1.agrupaPacientesPorGenero();
		System.out.println(" 4) Pacientes por genero: "+res4);
		
		Map<String, Set<PacienteEstudio>> res5 = lista1.agrupaPacientesPorPorGeneroEnConjunto();
		System.out.println(" 5) Pacientes por genero en conjunto: "+res5);
		
		//TODO
		Map<String, SortedSet<PacienteEstudio>> res6 = lista1.agrupaPacientesPorPorGeneroEnConjuntoOrdenado();
		System.out.println(" 6) Pacientes por genero en conjunto ordenado: "+res6);
		
		Map<String, PacienteEstudio> res7= lista1.pacienteEdadMaximaPacientesPorGenero();
		System.out.println(" 7) Paciente por genero con mayor edad: "+res7);
		
		Map<String, List<Double>> res8 = lista1.listaEdadesPorGenero();
		System.out.println(" 8) Lista de edades por genero: "+res8);
		
		//TODO
		Map<String, Double> res9 = lista1.edadMaximaPacientesPorGenero();
		System.out.println(" 9) Lista de edades por genero: "+res9);
		
		String res10 = lista1.generoEdadMaximaPacientesPorGenero();
		System.out.println(" 10) Genero con mayor edad: "+res10);
		
		
	}
}
