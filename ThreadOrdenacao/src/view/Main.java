package view;

import Controller.OrdenarController;

public class Main {
	public static void main(String[] args) {
		int [] vet = new int [1000];
		int tamanho = vet.length;
		for (int i = 0; i < tamanho; i++) {
            vet[i] = (int)((Math.random() * 1000) + 1);
        }
		for (int i = 0; i < 2; i++) {
			OrdenarController ordenar = new OrdenarController(vet, 0, tamanho-1, i+1);
			ordenar.start();
        }
	}
}
