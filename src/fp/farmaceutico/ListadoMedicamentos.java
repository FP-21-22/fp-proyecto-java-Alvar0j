
package fp.farmaceutico;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListadoMedicamentos {

	//Atributo
	
	public List<Medicamento> medicamentos;

	//Constructores

	public ListadoMedicamentos(Stream<Medicamento> medicamentos){
		this.medicamentos = medicamentos.toList();
	}

	//Métodos
	
	// 1) Existe Medicamentos segun tipo anterior a una fecha dada:
	
	public Boolean existeMedicamentoSegunTipoAnteriorA(TipoMedicamento tipo, LocalDate fecha) {
		return this.medicamentos.stream().anyMatch(x->x.tipoMedicamento.equals(tipo) && x.fechaCatalogo.isAfter(fecha));
	}	
	
	// 2) Nombre medicamentos con una puntuación mayor a la dada:
	
	public Set<String> nombreMedicamentosPuntuacionMayorA(Integer puntuacion) {
		return this.medicamentos.stream().
				filter(x->x.puntuacion > puntuacion).
				map(Medicamento::getNombre).collect(Collectors.toSet());
	}
	
	// 3) Nombre medicamentos con mayor índice somático según el tipo de medicamento:
	
	public String nombreMedicamentoMayorIndiceSomaticoSegunTipoMedicamento(TipoMedicamento tipo) {
		return this.medicamentos.stream(). 
				filter(x->x.tipoMedicamento.equals(tipo)). 
				max(Comparator.comparing(x->x.indiceSom)).
				orElseThrow(()-> new IllegalArgumentException("NO HAY NINGUNO")).nombre;
	}
	
	// 4) Agrupa tipo de medicamentos segun su puntuación media:
	
	public Map<String, Double> agrupaTipoMedicamentoSegunPuntuacionMedia(){
		return this.medicamentos.stream().
				collect(Collectors.groupingBy(
						x->x.tipoMedicamento.toString(),
						Collectors.averagingDouble(
								Medicamento::getPuntuacion)));
	}
	
	// 5) Fecha de catálogo más frecuente:
	
	public LocalDate fechaCatalogoMasFrecuente() {
		Map<Object, Long> mapaux = this.medicamentos.stream().
				collect(Collectors.groupingBy(
						x->x.fechaCatalogo,
						Collectors.counting()));
		Comparator<Map.Entry<Object,Long>> cmp = (x,y) -> x.getValue().compareTo(y.getValue());
		return (LocalDate) mapaux.entrySet().stream().max(cmp).get().getKey();
	}

}

