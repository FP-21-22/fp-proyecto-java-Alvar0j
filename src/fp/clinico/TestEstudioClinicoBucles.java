package fp.clinico;

//import java.util.List;

public class TestEstudioClinicoBucles {

	public static void main(String[] args) {
		EstudioClinico estudio = new EstudioClinicoBucles();
		EstudioClinico estudiob = estudio.of("data/estudio_clinico.csv");
//		String ruta = "data/estudio_clinico.csv";
//		List<PacienteEstudio> lista = estudio.leeFichero(ruta);
//		for(PacienteEstudio e:lista) {
//			System.out.println(estudiob);
			
		
		System.out.println("Número de Pacientes = " + estudiob.numeroPacientes());
		System.out.println("Pacientes del Tipo Rural = " + estudiob.todosPacienteSonDelTipo(TipoResidencia.RURAL));
		System.out.println("Existe Paciente Tipo Urbana = " + estudiob.existeAlgunPacienteDelTipo(TipoResidencia.URBANA));
		System.out.println("Número de Pacientes Factor de Riesgo = " + estudiob.numeroPacientesFactorRiesgo());
		System.out.println("Edad Media de Pacientes con Factor de Riesgo = " + estudiob.edadMediaPacientesConFactorRiesgo());
		System.out.println("NúmeroPacientes Con 63 Años = " + estudiob.filtraPacientesPorEdad(63D));
		System.out.println("Pacientes mayores de 18 Años Hombres = " + estudiob.agruparPacientesEdadMayorQuePorGenero(18D).get("Male"));
		System.out.println("Pacientes mayores de 18 Años Mujeres = " + estudiob.agruparPacientesEdadMayorQuePorGenero(18D).get("Female"));
		System.out.println("Número de Pacientes Hombres = " + estudiob.numeroPacientesPorGenero().get("Male"));
		System.out.println("Número de Pacientes Mujeres = " + estudiob.numeroPacientesPorGenero().get("Female"));
		System.out.println("Edad Media de Pacientes Hombres = " + estudiob.edadMediaPacientesPorPorGenero().get("Male"));
		System.out.println("Edad Media de Pacientes Mujeres = " + estudiob.edadMediaPacientesPorPorGenero().get("Female"));
		
		
	}
}



	




