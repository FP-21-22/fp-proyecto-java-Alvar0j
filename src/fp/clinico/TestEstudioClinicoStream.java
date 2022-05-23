package fp.clinico;


public class TestEstudioClinicoStream {

	public static void main(String[] args) {
		EstudioClinico estudio = new EstudioClinicoStream();
		EstudioClinico estudiob = estudio.of("data/estudio_clinico.csv");
		System.out.println(estudiob);
		
		System.out.println(estudiob.numeroPacientesFactorRiesgo());
		System.out.println(estudiob.edadMediaPacientesConFactorRiesgo());
		System.out.println(estudiob.todosPacienteSonDelTipo(TipoResidencia.RURAL));
		System.out.println(estudiob.existeAlgunPacienteDelTipo(TipoResidencia.URBANA));
		System.out.println(estudiob.filtraPacientesPorEdad(35.0));
		System.out.println(estudiob.agruparPacientesEdadMayorQuePorGenero(35.0));
		System.out.println(estudiob.numeroPacientesPorGenero());
		System.out.println(estudiob.edadMediaPacientesPorPorGenero());
		
		

	}

}
