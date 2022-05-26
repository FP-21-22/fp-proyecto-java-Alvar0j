package fp.vacunas;

import java.time.LocalDate;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Vacunaciones {

	//Atributos
	public List<Vacunacion> vacunacion;

	//Constructores
	public Vacunaciones (Stream<Vacunacion> vacunacion) {
		this.vacunacion = vacunacion.toList();
	}


	//Métodos

	public void anyadeVacunacion (Vacunacion vacunacion) {
		this.vacunacion.add(vacunacion);
	}

	//-------------------------------------------------------------------//

	public List<Vacunacion> vacunacionesEntreFechas (LocalDate fecha1, LocalDate fecha2) {
		return this.vacunacion.stream().
				filter(x->x.fecha().isAfter(fecha1) && x.fecha().isBefore(fecha2)).
				toList();
	}

	//-------------------------------------------------------------------//

	public Boolean existeNumPersonasPautaCompletaPorEncimaDe (String comunidad, Integer valor ) {
		Predicate<Vacunacion> pr = x->x.comunidad().equals(comunidad) && x.numeroPersonas()>valor;
		return this.vacunacion.stream().anyMatch(pr);
	}

	//-------------------------------------------------------------------//

	public LocalDate diaMasVacunacionesEn (String comunidad) {
		Map<LocalDate, Long> mapaux = this.vacunacion.stream().
				filter(x->x.comunidad().equals(comunidad)).
				collect(Collectors.groupingBy(
						Vacunacion::fecha,
						Collectors.counting()
						));

		Comparator<Map.Entry<LocalDate, Long>> cmp = (x,y) -> x.getValue().compareTo(y.getValue());

		return mapaux.entrySet().stream().
				max(cmp).
				get().getKey();

	}

	//-------------------------------------------------------------------//

	public Map<String,List<Vacunacion>> vacunacionesPorFecha() {
		return this.vacunacion.stream().
				collect(Collectors.groupingBy(
						x->x.fecha().toString()));

	}

	//-------------------------------------------------------------------//

	public Map<Object, Integer> maximoNumTotalVacunasporComunidad() {
		return this.vacunacion.stream().
				collect(Collectors.groupingBy(Vacunacion::comunidad,
						Collectors.collectingAndThen(
								Collectors.maxBy(
										Comparator.comparing(
												Vacunacion::numeroTotal)),
								opt->opt.get().numeroTotal())));

		//				.collect(Collectors.groupingBy(x->x.comunidad(),
		//						Collectors.(x->x.numeroTotal().intValue())));

	}

}


