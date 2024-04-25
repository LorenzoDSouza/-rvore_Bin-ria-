package BinaryTree;

 class Main {
	 
	 public static void main(String[] args) {
	        ArvoreBin arvore = new ArvoreBin();
	        arvore.inserir(10);
	        arvore.inserir(5);
	        arvore.inserir(20);
	        arvore.inserir(3);
	        arvore.inserir(7);

	        System.out.println("Caminhamento pré-ordem:");
	        arvore.preOrdem();

	        System.out.println("\nCaminhamento em ordem:");
	        arvore.central();

	        System.out.println("\nCaminhamento pós-ordem:");
	        arvore.posOrdem();
			
			arvore.gerarArqDot("arvoreBin.dot");

	    }
}
