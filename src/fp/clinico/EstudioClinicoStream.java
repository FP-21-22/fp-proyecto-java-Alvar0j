package fp.clinico;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EstudioClinicoStream implements EstudioClinico {

	
	public List<PacienteEstudio> pacientesEstudio;
	
	public EstudioClinicoStream (List<PacienteEstudio> pacientes) {
		this.pacientesEstudio = pacientes;
	}
	public EstudioClinicoStream() {
		
	}
	
	//-------------------------------------------------------------------//
	
	@Override
	public Integer numeroPacientes() {
		return this.pacientesEstudio.size() ;
	}

	//-------------------------------------------------------------------//
	
	@Override
	public void incluyePaciente(PacienteEstudio paciente) {
		this.pacientesEstudio.add(paciente);

	}

	//-------------------------------------------------------------------//
	
	@Override
	public void incluyePacientes(Collection<PacienteEstudio> pacientes) {
		this.pacientesEstudio.addAll(pacientes);

	}

	//-------------------------------------------------------------------//
	
	@Override
	public void eliminaPaciente(PacienteEstudio paciente) {
		this.pacientesEstudio.remove(paciente);

	}

	//-------------------------------------------------------------------//
	
	@Override
	public Boolean estaPaciente(PacienteEstudio paciente) {
		return this.pacientesEstudio.contains(paciente);
	}

	//-------------------------------------------------------------------//
	
	@Override
	public void borraEstudio() {
		this.pacientesEstudio.clear();

	}

	//-------------------------------------------------------------------//
	
	@Override
	public EstudioClinico of(String nombreFichero) {
		List<PacienteEstudio> pacientes= leeFichero(nombreFichero);
		return new EstudioClinicoStream(pacientes);
	}

	//-------------------------------------------------------------------//
	
	@Override
	public List<PacienteEstudio> leeFichero(String nombreFichero) {
		List<PacienteEstudio> res = new ArrayList<>();
		List<String> aux = null;
		try {
			aux = Files.readAllLines(Paths.get(nombreFichero));

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Hay que saltarse la primera lineA
		for(String e:aux) {
			PacienteEstudio p = parseo(e);
			res.add(p);
		}
		return res;
	}
	
	public static PacienteEstudio parseo(String linea) {
		//		36306;Male;80;false;false;URBANA;83.84
		Checkers.checkNoNull("Linea vacia", linea);
		String [] trozos = linea.split(";");
		Checkers.check("Error parametros", trozos.length==7);
		String id = trozos[0];
		String genero = trozos[1];
		Double edad = Double.parseDouble(trozos[2]);
		Boolean hipertension = Boolean.parseBoolean(trozos[3]);
		Boolean enfermedadDelCorazon = Boolean.parseBoolean(trozos[4]);
		TipoResidencia tipoResidencia = TipoResidencia.valueOf(trozos[5]);
		Double glucosa = Double.parseDouble(trozos[6]);

		return new PacienteEstudio (id,genero,edad,hipertension,enfermedadDelCorazon,tipoResidencia,glucosa);
	}

	//-------------------------------------------------------------------//
	
	@Override
	public Boolean todosPacienteSonDelTipo(TipoResidencia tipo) {
		Predicate <PacienteEstudio> pr = x->x.tipoResidencia().equals(tipo);
		return this.pacientesEstudio.stream().allMatch(pr);
	}
 
	//-------------------------------------------------------------------//
	
	@Override
	public Boolean existeAlgunPacienteDelTipo(TipoResidencia tipo) {
		Predicate <PacienteEstudio> pr = x->x.tipoResidencia().equals(tipo);
		return this.pacientesEstudio.stream().anyMatch(pr);
	}

	//-------------------------------------------------------------------//
	
	@Override
	public Integer numeroPacientesFactorRiesgo() {
		Long aux = this.pacientesEstudio.stream().count();
		return aux.intValue();
	}

	//-------------------------------------------------------------------//
	
	@Override
	public Double edadMediaPacientesConFactorRiesgo() {
		
		return this.pacientesEstudio.stream().
				filter(x->x.factorDeRiesgo() == true).
				mapToDouble(x->x.edad()).
				average().
				orElse(0);
	}

	//-------------------------------------------------------------------//
	
	@Override
	public List<PacienteEstudio> filtraPacientesPorEdad(Double edad) {
		Predicate <PacienteEstudio> pr = x->x.edad().equals(edad);
		
		return this.pacientesEstudio.stream().filter(pr).collect(Collectors.toList());
	}

	//-------------------------------------------------------------------//
	
	//Devuelve Map que agrupa
	@Override
	public Map<String, List<PacienteEstudio>> agruparPacientesEdadMayorQuePorGenero(Double edad) {
		return this.pacientesEstudio.stream().
				filter(x->x.edad() >= edad).
				collect(Collectors.groupingBy(
						x->x.genero()));
	}

	//-------------------------------------------------------------------//
	
	//Devuelve Map que realiza un cálculo
	@Override
	public Map<String, Long> numeroPacientesPorGenero() {
		return this.pacientesEstudio.stream().
				collect(Collectors.groupingBy(
						x->x.genero(), 
						Collectors.counting()));
	}

	//-------------------------------------------------------------------//
	
	@Override
	public Map<String, Double> edadMediaPacientesPorPorGenero() {
		return this.pacientesEstudio.stream().collect(Collectors.groupingBy(
				x->x.genero(), 
				Collectors.averagingDouble(
						x->x.edad())));
	}

}
