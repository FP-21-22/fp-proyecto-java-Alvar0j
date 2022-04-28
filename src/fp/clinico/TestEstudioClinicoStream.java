package fp.clinico;

public class TestEstudioClinicoStream {

	public static void main(String[] args) {
		EstudioClinico estudio = new EstudioClinicoBucles();
		EstudioClinico estudiob = estudio.of("data/estudio_clinico.csv");
		System.out.println(estudiob);
		
	
			
		

	}

}
