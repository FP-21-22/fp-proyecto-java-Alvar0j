package FactoriaMedicamentos;

import java.util.List;

import fp.farmaceutico.Medicamento;

public class TestFactoriaMedicamentos {
	public static void main(String[] args) {
		String ruta = "data/medicamentos.csv";
		List<Medicamento> lista = FactoriaMedicamentos.leeFichero(ruta);
		for(Medicamento e:lista) {
			System.out.println(e);

		}
	}
}
