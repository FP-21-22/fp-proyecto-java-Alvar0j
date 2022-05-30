package fp.clinico;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstudioClinicoBucles implements EstudioClinico {

	//Atributos

	public List<PacienteEstudio> pacientesEstudio;

	// Constructores

	public EstudioClinicoBucles (List<PacienteEstudio> pacientes) {
		this.pacientesEstudio= pacientes;
	}
	
	public EstudioClinicoBucles() {

	}

	//---------------------- Propiedades de lista ----------------------//
	
	// 1) Número Pacientes:

	public Integer numeroPacientes() {
		int list= this.pacientesEstudio.size();
		return list;

	}
	
	// 2) Incluye Paciente:

	@Override
	public void incluyePaciente(PacienteEstudio paciente) {
		this.pacientesEstudio.add(paciente);

	}
	
	// 3) Incluye Pacientes:

	@Override
	public void incluyePacientes(Collection<PacienteEstudio> pacientes) {
		this.pacientesEstudio.addAll(pacientes);

	}
	
	// 4) Elimina Paciente:

	@Override
	public void eliminaPaciente(PacienteEstudio paciente) {
		this.pacientesEstudio.remove(paciente);

	}

	// 5) Esta Paciente:
	
	public Boolean estaPaciente(PacienteEstudio paciente) {
		return this.pacientesEstudio.contains(paciente);
	}

	// 6) Borra Estudio:

	@Override
	public void borraEstudio() {
		this.pacientesEstudio.clear();

	}

	//---------------------- Método de factoría ----------------------//
	
	// 7) Método Of:
	
	@Override
	public EstudioClinico of(String nombreFichero) {
		List<PacienteEstudio> pacientes= leeFichero(nombreFichero);
		return new EstudioClinicoBucles(pacientes);
	}

	// 8) Método LeeFichero:
	
	public List<PacienteEstudio> leeFichero(String nombreFichero){
		//	
		List<PacienteEstudio> res = new ArrayList<>();
		List<String> aux = null;
		try {
			aux = Files.readAllLines(Paths.get(nombreFichero));

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Hay que saltarse la primera linea
		for(String e:aux) {
			PacienteEstudio p = parseo(e);
			res.add(p);
		}
		return res;
	}
	
	// Parsealinea
	
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
	
	

	//---------------------- Tratamientos Secuenciales ----------------------//
	
		// Existe para todo:

	// 9) Todos los Pacientes son del tipo:

	@Override
	public Boolean todosPacienteSonDelTipo(TipoResidencia tipo) {
		boolean res = true;
		for(PacienteEstudio pe: this.pacientesEstudio) {
			if(!pe.tipoResidencia().equals(tipo)) {
				res= false;
			}
		}
		return res;
	}
	
	// 10) Existe algún Paciente del tipo:
	
	@Override
	public Boolean existeAlgunPacienteDelTipo(TipoResidencia tipo) {
		boolean res = false;
		for(PacienteEstudio pe: this.pacientesEstudio) {
			if(pe.tipoResidencia().equals(tipo)) {
				res= true;
			}
		}
		return res;
	}

	// 11) Contador (Número Pacientes factor de riesgo):

	@Override
	public Integer numeroPacientesFactorRiesgo() {
		Integer res = 0;
		for(PacienteEstudio e:this.pacientesEstudio) {
			if(e.factorDeRiesgo().equals(true)) {
				res++;				
			}
		}
		return res;
	}

	// 12) Media (Edad media pacientes con factor riesgo):

	@Override
	public Double edadMediaPacientesConFactorRiesgo() {
		Double edad = 0.0;
		Integer divisor = 0;
		for (PacienteEstudio e:this.pacientesEstudio) {
			if (e.factorDeRiesgo()) {
				edad=edad+e.edad();
				divisor++;
			}
		}
		return edad/divisor;
	}

	// 13) Filtrado (Filtra pacientes por edad):

	@Override
	public List<PacienteEstudio> filtraPacientesPorEdad(Double edad) {
		List<PacienteEstudio> res = new ArrayList<PacienteEstudio>();
		for(PacienteEstudio e:this.pacientesEstudio) {
			if(e.edad().equals(edad)) {
				res.add(e);				
			}
		}
		return res;
	}

	// 14) Map que agrupa (Agrupar pacientes Edad Mayor Que por género).

	@Override
	public Map<String, List<PacienteEstudio>> agruparPacientesEdadMayorQuePorGenero(Double edad) {
		Map<String, List<PacienteEstudio>> res = new HashMap<String,List<PacienteEstudio>>();
		for(PacienteEstudio pe: this.pacientesEstudio) {
			if(pe.edad()>edad) {
				if(res.containsKey(pe.genero())) {
					res.get(pe.genero()).add(pe);
				}else {
					List<PacienteEstudio> pacis = new ArrayList<PacienteEstudio>();
					pacis.add(pe);
					res.put(pe.genero(), pacis);
				}
			}
		}
		return res;
	}

	// 15) Map que realiza un cálculo (Número pacientes por género):
	
	@Override
	public Map<String, Long> numeroPacientesPorGenero() {
		Map<String, Long> res = new HashMap<String,Long>();
		Long h = 0L;
		Long m = 0L;
		for(PacienteEstudio pe: this.pacientesEstudio) {
			if(pe.genero().equals("Male")) {
				h= h+1;
			}else {
				m=m+1;;
			}
		}
		res.put("Male", h);
		res.put("Female", m);
		
		return res;
	}

	// 16) Map que realiza un cálculo (Edad Media pacientes por género):

	@Override
	public Map<String, Double> edadMediaPacientesPorPorGenero() {
		
		Map<String, Double> res = new HashMap<String, Double>();
		Map<String,List<PacienteEstudio>> preRes = agruparPacientesEdadMayorQuePorGenero(0.0);
		Double h = average(preRes.get("Male"));
		Double m = average(preRes.get("Female"));
		res.put("Male", h);
		res.put("Female", m);
		return res;
		
	}
	
	// Función auxiliar:
	
	public Double average(List<PacienteEstudio> lista) {
		Double sum=0.0; 
		for(PacienteEstudio pe: lista) {
			sum = sum + pe.edad();
			
		}
		return sum/lista.size();
	}

	//Comprobacion de lectura de ficheros de Estudio Clinico Ampliacion Stream:
	
	public List<PacienteEstudio> getPacientesEstudio() {
		return pacientesEstudio;
	}


}       
