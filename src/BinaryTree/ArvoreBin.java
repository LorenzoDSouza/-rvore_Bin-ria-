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
	
	/**
	 * realiza uma chamada recursiva visitando primeiro o no raiz, depois o no da esquerda e por ultimo o da direita. 
	 * é fundamental para percorrer a arvore de modo completo.
	 */
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
	
	/**
	 * percorre todos os nos da arvore na ordem central, buscando primeiro os nos da esquerda seguindo pelo nó atual e passando para o da direita
	 * @param raiz
	 */
	private void central(Nodo raiz) {
		if (raiz != null) {
			central(raiz.esq);
			System.out.print(raiz.info + " ");
			central(raiz.dir);
		}
	}
	
	/**
	 * realiza uma chamada recursiva visitando primeiro o no da esquerda, depois o no da direita e por ultimo a raiz. 
	 * é fundamental para percorrer a arvore de modo completo.
	 */
	public void posOrdem() {
		posOrdem(raiz);
	}

	/**
	 * realiza o percurso em pos ordem na arvore
	 * @param raiz 
	 * 
	 */
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
	
	/**
	 * percorre a arvore da raiz em direcao a esquerda ate encontrar o menor valor
	 * feito de forma iterativa, nao recursiva
	 * @param raiz
	 * @return
	 */
	private Nodo buscarMin(Nodo raiz) {
		Nodo atual = raiz;
		while (atual.esq != null)
			atual = atual.esq;
		return atual;
	}

	public void gerarArqDot(String filename) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write("digraph ArvoreBin {\n");
            escreverPreOrdemDot(raiz, out);
            out.write("}\n");
            out.close();
            System.out.println("\nArquivo " + filename + " gerado na pasta do arquivo");
        } catch (IOException e) {
            System.err.println("erro ao escrever arquivo " + filename + ": " + e.getMessage());
        }
    }

    private void escreverPreOrdemDot(Nodo raiz, BufferedWriter out) throws IOException {
        if (raiz != null) {
            out.write("  " + raiz.info + ";\n");
            if (raiz.esq != null) {
                out.write("  " + raiz.info + " -> " + raiz.esq.info + " [label=\"esq\"];\n");
                escreverPreOrdemDot(raiz.esq, out);
            }
            if (raiz.dir != null) {
                out.write("  " + raiz.info + " -> " + raiz.dir.info + " [label=\"dir\"];\n");
                escreverPreOrdemDot(raiz.dir, out);
            }
        }
    }
}

	
