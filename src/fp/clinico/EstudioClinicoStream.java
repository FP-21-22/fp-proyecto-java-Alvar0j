package fp.clinico;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class EstudioClinicoStream implements EstudioClinico {

	
	public List<PacienteEstudio> pacientesEstudio;
	
	public EstudioClinicoStream (List<PacienteEstudio> pacientes) {
		this.pacientesEstudio = pacientes;
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
		// TODO Auto-generated method stub
		return null;
	}

	//-------------------------------------------------------------------//
	
	@Override
	public Boolean existeAlgunPacienteDelTipo(TipoResidencia tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	//-------------------------------------------------------------------//
	
	@Override
	public Integer numeroPacientesFactorRiesgo() {
		// TODO Auto-generated method stub
		return null;
	}

	//-------------------------------------------------------------------//
	
	@Override
	public Double edadMediaPacientesConFactorRiesgo() {
		// TODO Auto-generated method stub
		return null;
	}

	//-------------------------------------------------------------------//
	
	@Override
	public List<PacienteEstudio> filtraPacientesPorEdad(Double edad) {
		// TODO Auto-generated method stub
		return null;
	}

	//-------------------------------------------------------------------//
	
	@Override
	public Map<String, List<PacienteEstudio>> agruparPacientesEdadMayorQuePorGenero(Double edad) {
		// TODO Auto-generated method stub
		return null;
	}

	//-------------------------------------------------------------------//
	
	@Override
	public Map<String, Long> numeroPacientesPorGenero() {
		// TODO Auto-generated method stub
		return null;
	}

	//-------------------------------------------------------------------//
	
	@Override
	public Map<String, Double> edadMediaPacientesPorPorGenero() {
		// TODO Auto-generated method stub
		return null;
	}

}
