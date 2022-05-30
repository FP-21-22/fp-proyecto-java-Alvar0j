package fp.vacunas;

import java.time.LocalDate;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
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
	
	// 1) Añade Vacunación:

	public void anyadeVacunacion (Vacunacion vacunacion) {
		this.vacunacion.add(vacunacion);
	}

	// 2) Vacunaciones entre fechas:

	public List<Vacunacion> vacunacionesEntreFechas (LocalDate fecha1, LocalDate fecha2) {
		return this.vacunacion.stream().
				filter(x->x.fecha().isAfter(fecha1) && x.fecha().isBefore(fecha2)).
				toList();
	}

	// 3) Existe número de personas con la pauta completa por encima de un valor:

	public Boolean existeNumPersonasPautaCompletaPorEncimaDe (String comunidad, Integer valor ) {
		Predicate<Vacunacion> pr = x->x.comunidad().equals(comunidad) && x.numeroPersonas()>valor;
		return this.vacunacion.stream().anyMatch(pr);
	}

	// 4) Día con más vacunaciones en una comunidad:

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

	// 5) Vacunaciones por fechas:

	public Map<String,List<Vacunacion>> vacunacionesPorFecha() {
		return this.vacunacion.stream().
				collect(Collectors.groupingBy(
						x->x.fecha().toString()));

	}

	// 6) Máximo número total de vacunas por comunidad:

	public Map<Object, Integer> maximoNumTotalVacunasporComunidad() {
		return this.vacunacion.stream().
				collect(Collectors.groupingBy(Vacunacion::comunidad,
						Collectors.collectingAndThen(
								Collectors.maxBy(
										Comparator.comparing(
												Vacunacion::numeroTotal)),
								opt->opt.get().numeroTotal())));
	}
}
