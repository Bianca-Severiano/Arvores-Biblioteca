package br.edu.fateczl.arvore.ArvoreInt;

public class ArvoreInt {

	NoArvore raiz;

	public ArvoreInt() {
		raiz = null;
	}
	
	public void insert(int dado) {
		NoArvore  no = new NoArvore ();
		no.dado = dado;
		no.esquerda = null;
		no.esquerda = null;
		insertleaf(no, raiz);
	}
	
	private void insertleaf(NoArvore  no, NoArvore  raizSubArvore) {
		if (raiz==null) {
			raiz = no;
		}
		else {
			if (no.dado < raizSubArvore.dado) {
				if (raizSubArvore.esquerda == null) {
					raizSubArvore.esquerda = no;
				}else {
					insertleaf(no, raizSubArvore.esquerda);
				}
			}
			if (no.dado > raizSubArvore.dado) {
				if (raizSubArvore.direita == null) {
					raizSubArvore.direita = no;
				}else {
					insertleaf(no, raizSubArvore.direita);
				}
			}
		}
		
	}
	
	public void search (int valor) throws Exception {
		try {
			NoArvore  no = nodeSearch(raiz, valor);
			int level = nodeLevel(raiz, valor);
			System.out.println(no.dado + ": Nível "+level);
		} catch (Exception e) {
			throw new Exception(valor+": Não existente");
		}
	}

	private int nodeLevel(NoArvore  raizSubArvore, int valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		else if (raizSubArvore.dado > valor) {
			return 1 + nodeLevel(raizSubArvore.esquerda, valor);
		}
		else if (raizSubArvore.dado < valor) {
			return 1 + nodeLevel(raizSubArvore.direita, valor);
		}
		else {
			return 0;
		}
	}

	private NoArvore  nodeSearch(NoArvore  raizSubArvore, int valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		else if (raizSubArvore.dado > valor) {
			return nodeSearch(raizSubArvore.esquerda, valor);
		}
		else if (raizSubArvore.dado < valor) {
			return nodeSearch(raizSubArvore.direita, valor);
		}
		else {
			return raizSubArvore;
		}
	}
	
	public void prefixSearch() throws Exception {
		System.out.print("Pré-ordem: ");
		prefix(raiz);
		System.out.println("");
	}

	private void prefix(NoArvore  raizSubArvore) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		System.out.print(raizSubArvore.dado+" ");
		if (raizSubArvore.esquerda!=null) {
			prefix(raizSubArvore.esquerda);
		}
		if (raizSubArvore.direita!=null) {
			prefix(raizSubArvore.direita);
		}
	}
	
	public void infixSearch() throws Exception {
		System.out.print("Em ordem: ");
		infix(raiz);
		System.out.println("");
	}

	private void infix(NoArvore  raizSubArvore) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		if (raizSubArvore.esquerda!=null) {
			infix(raizSubArvore.esquerda);
		}
		System.out.print(raizSubArvore.dado+" ");
		if (raizSubArvore.direita!=null) {
			infix(raizSubArvore.direita);
		}
	}
	
	public void postfixSearch() throws Exception {
		System.out.print("Pós-ordem: ");
		postfix(raiz);
		System.out.println("");
	}

	private void postfix(NoArvore  raizSubArvore) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		if (raizSubArvore.esquerda!=null) {
			postfix(raizSubArvore.esquerda);
		}
		if (raizSubArvore.direita!=null) {
			postfix(raizSubArvore.direita);
		}
		System.out.print(raizSubArvore.dado+" ");
	}
	
	public void remove(int valor) throws Exception {
		try {
			removeChild(raiz, valor);
		} catch (Exception e) {
			throw new Exception("Valor não existente");
		}
	}

	private void removeChild(NoArvore  raizSubArvore, int valor) throws Exception{
		NoArvore  no  = nodeSearch(raiz, valor);
		NoArvore  pai = nodeParent(null, raiz, valor);
		if(no.direita != null && no.esquerda != null) {
			NoArvore  noTroca = no.esquerda;
			while (noTroca.direita!= null) {
				noTroca = noTroca.direita;
			}
			pai = nodeParent(null, raiz, noTroca.dado);
			no.dado = noTroca.dado;
			noTroca.dado = valor;
			removeOneOrZeroLeaf(pai, noTroca);
		}
		else {
			removeOneOrZeroLeaf(pai, no);
		}
	}
	

	private NoArvore  nodeParent(NoArvore  pai, NoArvore  raizSubArvore, int valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		else if (raizSubArvore.dado > valor) {
			return nodeParent(raizSubArvore, raizSubArvore.esquerda, valor);
		}
		else if (raizSubArvore.dado < valor) {
			return nodeParent(raizSubArvore, raizSubArvore.direita, valor);
		}
		else {
			if(pai == null) {
				return raizSubArvore;
			}
			else {
				return pai;
			}
		}
	}

	private void removeOneOrZeroLeaf(NoArvore  pai, NoArvore  no) {
		if (no.esquerda == null && no.direita == null) {
			change(pai, no, null);
		} else if (no.esquerda == null) {
			change(pai, no, no.direita);
		} else if (no.direita == null) {
			change(pai, no, no.esquerda);
		}
			
	}

	private void change(NoArvore  pai, NoArvore  no, NoArvore  novoNo) {
		if (pai.esquerda != null && pai.esquerda.dado == no.dado) {
			pai.esquerda = novoNo;
		} else if (pai.direita.dado == no.dado) {
			pai.direita = novoNo;
		}
	}

}