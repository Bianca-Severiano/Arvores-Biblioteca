package br.edu.fateczl.arvore.ArvoreChar;

public class ArvoreChar {

	NoArvoreChar raiz;

	public ArvoreChar() {
		raiz = null;
	}
	
	public void insert(char caractere) {
		caractere = Character.toUpperCase(caractere);
		NoArvoreChar no = new NoArvoreChar();
		no.dado = caractere;
		no.esquerda = null;
		no.esquerda = null;
		insertleaf(no, raiz);
	}
	
	private void insertleaf(NoArvoreChar no, NoArvoreChar raizSubArvore) {
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
	
	public void search (char caractere) throws Exception {
		caractere = Character.toUpperCase(caractere);
		try {
			NoArvoreChar no = nodeSearch(raiz, caractere);
			int level = nodeLevel(raiz, caractere);
			System.out.println(no.dado + ": Nível "+level);
		} catch (Exception e) {
			throw new Exception(caractere+": Não existente");
		}
	}

	private int nodeLevel(NoArvoreChar raizSubArvore, char caractere) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		else if (raizSubArvore.dado > caractere) {
			return 1 + nodeLevel(raizSubArvore.esquerda, caractere);
		}
		else if (raizSubArvore.dado < caractere) {
			return 1 + nodeLevel(raizSubArvore.direita, caractere);
		}
		else {
			return 0;
		}
	}

	private NoArvoreChar nodeSearch(NoArvoreChar raizSubArvore, char caractere) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		else if (raizSubArvore.dado > caractere) {
			return nodeSearch(raizSubArvore.esquerda, caractere);
		}
		else if (raizSubArvore.dado < caractere) {
			return nodeSearch(raizSubArvore.direita, caractere);
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

	private void prefix(NoArvoreChar raizSubArvore) throws Exception {
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

	private void infix(NoArvoreChar raizSubArvore) throws Exception {
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

	private void postfix(NoArvoreChar raizSubArvore) throws Exception {
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
	
	public void remove(char caractere) throws Exception {
		caractere = Character.toUpperCase(caractere);
		try {
			removeChild(raiz, caractere);
		} catch (Exception e) {
			throw new Exception("Valor não existente");
		}
	}

	private void removeChild(NoArvoreChar raizSubArvore, char caractere) throws Exception{
		NoArvoreChar no  = nodeSearch(raiz, caractere);
		NoArvoreChar pai = nodeParent(null, raiz, caractere);
		if(no.direita != null && no.esquerda != null) {
			NoArvoreChar noTroca = no.esquerda;
			while (noTroca.direita!= null) {
				noTroca = noTroca.direita;
			}
			pai = nodeParent(null, raiz, noTroca.dado);
			no.dado = noTroca.dado;
			noTroca.dado = caractere;
			removeOneOrZeroLeaf(pai, noTroca);
		}
		else {
			removeOneOrZeroLeaf(pai, no);
		}
	}
	

	private NoArvoreChar nodeParent(NoArvoreChar pai, NoArvoreChar raizSubArvore, char caractere) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore vazia");
		}
		else if (raizSubArvore.dado > caractere) {
			return nodeParent(raizSubArvore, raizSubArvore.esquerda, caractere);
		}
		else if (raizSubArvore.dado < caractere) {
			return nodeParent(raizSubArvore, raizSubArvore.direita, caractere);
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

	private void removeOneOrZeroLeaf(NoArvoreChar pai, NoArvoreChar no) {
		if (no.esquerda == null && no.direita == null) {
			change(pai, no, null);
		} else if (no.esquerda == null) {
			change(pai, no, no.direita);
		} else if (no.direita == null) {
			change(pai, no, no.esquerda);
		}
			
	}

	private void change(NoArvoreChar pai, NoArvoreChar no, NoArvoreChar novoNo) {
		if (pai.esquerda != null && pai.esquerda.dado == no.dado) {
			pai.esquerda = novoNo;
		} else if (pai.direita.dado == no.dado) {
			pai.direita = novoNo;
		}
	}
}
