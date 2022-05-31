package fp.clinico;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EstudioClinicoAmpliacionStream extends EstudioClinicoStream implements EstudioClinicoAmpliacion {

	public EstudioClinicoAmpliacionStream() {
		super();
	}
	public EstudioClinicoAmpliacionStream(List<PacienteEstudio> lista) {
		super(lista);
	}
	public EstudioClinicoAmpliacionStream(Stream<PacienteEstudio> st) {
		super(st);
	}

	// 1)
	
	@Override
	public Map<TipoResidencia, Integer> agruparNumeroPacientesPorTipoResidencia() {
		return super.pacientesEstudio.stream().collect(
				Collectors.groupingBy(PacienteEstudio::tipoResidencia,
						Collectors.collectingAndThen(Collectors.counting(), 
								Long::intValue)));
	}

	// 2)
	
	@Override
	public Map<TipoResidencia, Double> agruparNivelMedioGlucosaMedioPorTipoResidencia() {
		return super.pacientesEstudio.stream().
				collect(Collectors.groupingBy(
								x->x.tipoResidencia(),
								Collectors.collectingAndThen(
										Collectors.averagingDouble(x->x.glucosa()),
										Double::doubleValue)));
	}

	// 3)
	
	@Override
	public Map<TipoResidencia, PacienteEstudio> agruparNivelMedioGlucosaMaximoPorTipoResidencia() {
		return super.pacientesEstudio.stream().
				collect(Collectors.groupingBy(x->x.tipoResidencia(),
								Collectors.collectingAndThen(
										Collectors.maxBy(Comparator.comparing(x->x.glucosa())),
										x->x.get()))) ;
	}

	// 4)
	
	@Override
	public Map<String, List<PacienteEstudio>> agrupaPacientesPorGenero() {
		return super.pacientesEstudio.stream().
				collect(Collectors.groupingBy(
						x->x.genero()));
	}

	// 5)
	
	@Override
	public Map<String, Set<PacienteEstudio>> agrupaPacientesPorPorGeneroEnConjunto() {
		return super.pacientesEstudio.stream().
				collect(Collectors.groupingBy(
						x->x.genero(),
						Collectors.toSet()));
	}

	// 6)
	
	@Override
	public Map<String, SortedSet<PacienteEstudio>> agrupaPacientesPorPorGeneroEnConjuntoOrdenado() {
		return super.pacientesEstudio.stream().
				collect(Collectors.groupingBy(PacienteEstudio::genero,
						Collectors.toCollection(TreeSet::new)));
	}
	
	// 7)

	@Override
	public Map<String, PacienteEstudio> pacienteEdadMaximaPacientesPorGenero() {
		
		return super.pacientesEstudio.stream().
				collect(Collectors.groupingBy(x->x.genero(),
						Collectors.collectingAndThen(
								Collectors.maxBy(Comparator.comparing(x->x.edad())),
								x->x.get())));
	}

	// 8)
	
	@Override
	public Map<String, List<Double>> listaEdadesPorGenero() {
		return super.pacientesEstudio.stream().collect(
				Collectors.groupingBy(x->x.genero(),
						Collectors.mapping(x->x.edad(),Collectors.toList())));
	}

	// 9)
	
	@Override
	public Map<String, Double> edadMaximaPacientesPorGenero() {
		return super.pacientesEstudio.stream().
				collect(Collectors.groupingBy(PacienteEstudio::genero,
						Collectors.collectingAndThen(
								Collectors.maxBy(
										Comparator.comparing(PacienteEstudio::edad)), 
								x->x.get().edad())));
	}
	
	// 10)

	@Override
	public String generoEdadMaximaPacientesPorGenero() {
		
		Map<String, Long> mapaux = this.pacientesEstudio.stream().
				collect(Collectors.groupingBy(
						PacienteEstudio::genero,
						Collectors.counting()
						));
		Comparator<Map.Entry<String, Long>> cmp = (x,y)->x.getValue().
				compareTo(y.getValue());
		return mapaux.entrySet().stream().
				max(cmp).
				get().
				getKey();
	}
}
