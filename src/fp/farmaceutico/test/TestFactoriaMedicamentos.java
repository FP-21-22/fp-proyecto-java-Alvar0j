package fp.farmaceutico.test;

import fp.farmaceutico.FactoriaMedicamento;
import fp.farmaceutico.Medicamento;


public class TestFactoriaMedicamentos {

	public static void main(String[] args) {
		Medicamento p1 = FactoriaMedicamento.parseaMedicamente("efavirenz,Anatomico,Y212XXA,Actavis Mid Atlantic LLC,90.0,1848,04/12/2019");
		System.out.println(p1);

	}

}
