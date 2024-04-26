package BinaryTree;

import java.util.*;

 class Main {
	 
	 public static void main(String[] args) {
	        ArvoreBin arvore1 = new ArvoreBin();
	        arvore1.inserir(10);
	        arvore1.inserir(5);
	        arvore1.inserir(20);
	        arvore1.inserir(3);
	        arvore1.inserir(7);

			
			arvore1.gerarArqDot("arvoreBin1.dot");
			
			arvore1.remover(10);
			
			arvore1.gerarArqDot("arvoreBin1remove10.dot");
			
			ArvoreBin arvore2 = new ArvoreBin();
			int i = 0;
			
			while(i<30) {
				Random random = new Random();
				int valor = random.nextInt(50) + 1;
				arvore2.inserir(random.nextInt(50) + 1);
				i++;
			}

			arvore2.gerarArqDot("arvoreBin2RandomN");
	    }
}
