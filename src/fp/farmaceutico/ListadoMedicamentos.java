package fp.farmaceutico;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListadoMedicamentos {

	//Atributo
	public List<Medicamento> medicamentos;

	//Constructores
	public ListadoMedicamentos(Stream<Medicamento> medicamentos){
		this.medicamentos = medicamentos.toList();
	}

	//-------------------------------------------------------------------//

	//Métodos
	public Boolean existeMedicamentoSegunTipoAnteriorA(TipoMedicamento tipo, LocalDate fecha) {
		return this.medicamentos.stream().anyMatch(x->x.tipoMedicamento.equals(tipo) && x.fechaCatalogo.isAfter(fecha));
	}	
	
	//-------------------------------------------------------------------//
	
	public Set<String> nombreMedicamentosPuntuacionMayorA(Integer puntuacion) {
		return this.medicamentos.stream().
				filter(x->x.puntuacion > puntuacion).
				map(Medicamento::getNombre).collect(Collectors.toSet());
	}
	
	//-------------------------------------------------------------------//
	
	public String nombreMedicamentoMayorIndiceSomaticoSegunTipoMedicamento(TipoMedicamento tipo) {
		return this.medicamentos.stream(). 
				filter(x->x.tipoMedicamento.equals(tipo)). 
				collect(Collectors.maxBy(
						Comparator.comparing(x->x.indiceSom))).toString();
	}
	
	//-------------------------------------------------------------------//
	
	public Map<String, Double> agrupaTipoMedicamentoSegunPuntuacionMedia(){
		return this.medicamentos.stream().
				collect(Collectors.groupingBy(
						x->x.tipoMedicamento.toString(),
						Collectors.averagingDouble(
								Medicamento::getPuntuacion)));
	}
	
	//-------------------------------------------------------------------//
	
	public LocalDate fechaCatalogoMasFrecuente() {
		Map<Object, Long> mapaux = this.medicamentos.stream().
				collect(Collectors.groupingBy(
						x->x.fechaCatalogo,
						Collectors.counting()));
		Comparator<Map.Entry<Object,Long>> cmp = (x,y) -> x.getValue().compareTo(y.getValue());
		return (LocalDate) mapaux.entrySet().stream().max(cmp).get().getKey();
	}
	
	//-------------------------------------------------------------------//
	
}




