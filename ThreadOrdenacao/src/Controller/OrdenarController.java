package Controller;

import java.util.Arrays;

public class OrdenarController extends Thread {

	private int[] vet;
	private int inicio;
	private int fim;
	private int op;

	public OrdenarController(int[] vet, int inicio, int fim, int op) {
		this.vet = vet;
		this.inicio = inicio;
		this.fim = fim;
		this.op = op;
	}

	public void run() {
		if (op == 1) {
			long tempoI = System.nanoTime();
			int[] vetOrd = quickSort(vet, inicio, fim);
			long tempoF = System.nanoTime();
			long duracao = tempoF - tempoI;
			System.out.println("Tempo para ordenar por QuickSort: " + duracao / 100000 + "ms");
			System.out.println("Vetor ordenado por QuickSort: " + Arrays.toString(vetOrd));
		} else {
			long tempoI = System.nanoTime();
			int[] vetOrd = mergeSort(vet, inicio, fim);
			long tempoF = System.nanoTime();
			long duracao = tempoF - tempoI;
			System.out.println("Tempo para ordenar por MergeSort: " + duracao / 100000 + "ms");
			System.out.println("Vetor ordenado por MergeSort: " + Arrays.toString(vetOrd));
		}
	}

	public int[] quickSort(int[] vet, int inicio, int fim) {
		if (inicio < fim) {
			int pivo = dividir(vet, inicio, fim);
			quickSort(vet, inicio, pivo - 1);
			quickSort(vet, pivo + 1, fim);
		}
		return vet;
	}

	private int dividir(int[] vet, int inicio, int fim) {
		int pivo = vet[fim];
		int i = (inicio - 1);
		for (int j = inicio; j < fim; j++) {
			if (vet[j] <= pivo) {
				i++;
				trocar(vet, i, j);
			}
		}
		trocar(vet, i + 1, fim);
		return i + 1;
	}

	private void trocar(int[] vet, int i, int j) {
		int aux = vet[i];
		vet[i] = vet[j];
		vet[j] = aux;
	}

	public int[] mergeSort(int[] vet, int inicio, int fim) {
		if (inicio < fim) {
			int meio = (inicio + fim) / 2;
			mergeSort(vet, inicio, meio);
			mergeSort(vet, meio + 1, fim);
			merge(vet, inicio, meio, fim);
		}
		return vet;
	}

	private void merge(int[] vet, int inicio, int meio, int fim) {
		int n1 = meio - inicio + 1;
		int n2 = fim - meio;

		int[] esq = new int[n1];
		int[] dir = new int[n2];

		for (int i = 0; i < n1; ++i)
			esq[i] = vet[inicio + i];
		for (int j = 0; j < n2; ++j)
			dir[j] = vet[meio + 1 + j];

		int i = 0, j = 0;
		int k = inicio;
		while (i < n1 && j < n2) {
			if (esq[i] <= dir[j]) {
				vet[k] = esq[i];
				i++;
			} else {
				vet[k] = dir[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			vet[k] = esq[i];
			i++;
			k++;
		}

		while (j < n2) {
			vet[k] = dir[j];
			j++;
			k++;
		}
	}
}
