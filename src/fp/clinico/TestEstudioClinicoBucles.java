package fp.clinico;

public class TestEstudioClinicoBucles {

	public static void main(String[] args) {
		EstudioClinico estudio = new EstudioClinicoBucles();
		EstudioClinico estudiob = estudio.of("data/estudio_clinico.csv");
		System.out.println(estudiob);
		
		System.out.println(" 1) Número de Pacientes = " + estudiob.numeroPacientes());
		System.out.println(" 2) Pacientes del Tipo Rural = " + estudiob.todosPacienteSonDelTipo(TipoResidencia.RURAL));
		System.out.println(" 3) Existe Paciente Tipo Urbana = " + estudiob.existeAlgunPacienteDelTipo(TipoResidencia.URBANA));
		System.out.println(" 4) Número de Pacientes Factor de Riesgo = " + estudiob.numeroPacientesFactorRiesgo());
		System.out.println(" 5) Edad Media de Pacientes con Factor de Riesgo = " + estudiob.edadMediaPacientesConFactorRiesgo());
		System.out.println(" 6) NúmeroPacientes Con 63 Años = " + estudiob.filtraPacientesPorEdad(63D));
		System.out.println(" 7.1) Pacientes mayores de 18 Años Hombres = " + estudiob.agruparPacientesEdadMayorQuePorGenero(18D).get("Male"));
		System.out.println(" 7.2) Pacientes mayores de 18 Años Mujeres = " + estudiob.agruparPacientesEdadMayorQuePorGenero(18D).get("Female"));
		System.out.println(" 8.1) Número de Pacientes Hombres = " + estudiob.numeroPacientesPorGenero().get("Male"));
		System.out.println(" 8.2) Número de Pacientes Mujeres = " + estudiob.numeroPacientesPorGenero().get("Female"));
		System.out.println(" 9.1) Edad Media de Pacientes Hombres = " + estudiob.edadMediaPacientesPorPorGenero().get("Male"));
		System.out.println(" 9.2) Edad Media de Pacientes Mujeres = " + estudiob.edadMediaPacientesPorPorGenero().get("Female"));
		
		
	}
}



	




