package BinaryTree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArvoreBin {
	private Nodo raiz;

	public ArvoreBin() {
		raiz = null;
	}

	/**
	 * 
	 * @param valor valor a ser alocado no nodo
	 * @return retorna o nodo com o valor novo
	 */
	public Nodo alocarNodo(int valor) {
		Nodo novoNodo = new Nodo();
		novoNodo.info = valor;
		novoNodo.esq = novoNodo.dir = novoNodo.pai = null;
		return novoNodo;
	}

	public void inserir(int valor) {
		raiz = inserir(valor, raiz);
	}
	/**
	 * funcao que insere com base no valor da raiz
	 * @param valor é o valor a ser inserido
	 * @param raiz da árvore
	 * @return retorna a raiz que foi inserida
	 */
	private Nodo inserir(int valor, Nodo raiz) {
		if (raiz == null) {
			raiz = alocarNodo(valor);
		} 
		else {
			if (valor > raiz.info) {
				raiz.dir = inserir(valor, raiz.dir);
				raiz.dir.pai = raiz;

			} else {
				raiz.esq = inserir(valor, raiz.esq);
				raiz.esq.pai = raiz;
			}
		}

		return raiz;
	}

	public void preOrdem() {
		preOrdem(raiz);

	}
	
	/**
	 * realiza o percurso em pre ordem na arvore
	 * @param raiz 
	 * 
	 */
	private void preOrdem(Nodo raiz) {
		if (raiz != null) {
			System.out.print(raiz.info + " ");
			preOrdem(raiz.esq);
			preOrdem(raiz.dir);
		}
	}

	public void central() {
		central(raiz);
	}

	private void central(Nodo raiz) {
		if (raiz != null) {
			central(raiz.esq);
			System.out.print(raiz.info + " ");
			central(raiz.dir);
		}
	}

	public void posOrdem() {
		posOrdem(raiz);
	}

	private void posOrdem(Nodo raiz) {
		if (raiz != null) {
			posOrdem(raiz.esq);
			posOrdem(raiz.dir);
			System.out.print(raiz.info + " ");
		}
	}

	public void remover(int valor) {
		raiz = remover(valor, raiz);

	}

	private Nodo remover(int valor, Nodo raiz) {
		if (raiz == null) {
			return raiz;
		}
		
		
		if (valor < raiz.info) {
			raiz.esq = remover(valor, raiz.esq);
		} else if (valor > raiz.info) {
			raiz.dir = remover(valor, raiz.dir);
		} else {
			if (raiz.esq == null) {
				return raiz.dir;
			} else if (raiz.dir == null) {
				return raiz.esq;
			}

			raiz.info = buscarMin(raiz.dir).info;

			raiz.dir = remover(raiz.info, raiz.dir);
		}

		return raiz;
	}

	public Nodo buscar(int valor) {
		return buscar(valor, raiz);
	}

	private Nodo buscar(int valor, Nodo raiz) {
		if (raiz == null || raiz.info == valor) {
			return raiz;}

		
		if (valor < raiz.info) {
			return buscar(valor, raiz.esq);}
		return buscar(valor, raiz.dir);
	}

	private Nodo buscarMin(Nodo raiz) {
		Nodo atual = raiz;
		while (atual.esq != null)
			atual = atual.esq;
		return atual;
	}

	public void gerarArqDot(String filename) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			out.write(" Arvore Binaria \n" + "\n");
			escreverPreOrdemDot(raiz, out);
			out.write("\n");
			out.close();
			System.out.println("arquivo gerado na pasta do projeto");
		} catch (IOException e) {
			System.err.println("erro ao escrever arquivo: " + e.getMessage());
		}
	}

	private void escreverPreOrdemDot(Nodo raiz, BufferedWriter out) throws IOException {
		if (raiz != null) {
			out.write("  " + raiz.info + ";\n");
			
			
			if (raiz.esq != null) {
				out.write("  " + raiz.info + " > " + raiz.esq.info + " referencia = esquerda\n");
				escreverPreOrdemDot(raiz.esq, out);
			}
			if (raiz.dir != null) {
				out.write("  " + raiz.info + " > " + raiz.dir.info + " referencia = direita\n");
				escreverPreOrdemDot(raiz.dir, out);
			}
		}
	}
}
