package br.edu.fateczl.arvore.ArvoreChar;

public class ArvoreChar {

	NoArvoreChar raiz;

	public ArvoreChar() {
		raiz = null;
	}
	
	public void insert(char valor) {
		NoArvoreChar no = new NoArvoreChar();
		no.dado = valor;
		no.esquerda = null;
		no.direita = null;
		insertLeaf(raiz, no);
	}

	private void insertLeaf(NoArvoreChar raizSub, NoArvoreChar no) {
		if (raiz == null) {
			raiz = no;
		} else {

			if (no.dado < raizSub.dado) {

				if (raizSub.esquerda == null) {
					raizSub.esquerda = no;
				} else {
					insertLeaf(raizSub.esquerda, no);
				}
			}

			if (no.dado > raizSub.dado) {
				if (raizSub.direita == null) {
					raizSub.direita = no;
				} else {
					insertLeaf(raizSub.direita, no);
				}
			}
		}

	}
	
	public void Search(char valor) throws Exception {
		try {
			NoArvoreChar no = nodeSearch(raiz, valor);
			int level = nodeLevel(raiz, valor);
			System.out.println("Dado: " + no.dado + " - Nível: " + level);
		} catch (Exception e) {
			throw new Exception("Valor não existe");
		}

	}
	
	private int nodeLevel(NoArvoreChar raizSub, char valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Arvore vazia");
		} else if (raizSub.dado > valor) {
			return 1 + nodeLevel(raizSub.esquerda, valor);
		} else if (raizSub.dado < valor) {
			return 1 + nodeLevel(raizSub, valor);
		} else {
			return 0;
		}

	}
	
	private NoArvoreChar nodeSearch(NoArvoreChar raizSub, char valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Arvore vazia");
		} else if (raizSub.dado > valor) {
			return nodeSearch(raizSub.esquerda, valor);
		} else if (raizSub.dado < valor) {
			return nodeSearch(raizSub.direita, valor);
		} else {
			return raizSub;
		}
	}
	
	public void prefixSearch() throws Exception {
		prefix(raiz);
	}
	
	private void prefix(NoArvoreChar raizSub) throws Exception {
		if (raiz == null) {
			throw new Exception("Arvore vazia");
		} else {
			System.out.println(raizSub.dado);
			System.out.println(" ");

			if (raizSub.esquerda != null) {
				prefix(raizSub.esquerda);
			}

			if (raizSub.direita != null) {
				prefix(raizSub.direita);
			}
		}
	}
	
	public void infixSearch() throws Exception {
		prefix(raiz);
	}
	
	private void infix(NoArvoreChar raizSub) throws Exception {
		if (raiz == null) {
			throw new Exception("Arvore vazia");
		} else {

			if (raizSub.esquerda != null) {
				infix(raizSub.esquerda);
			}

			System.out.println(raizSub.dado);
		

			if (raizSub.direita != null) {
				infix(raizSub.direita);
			}
		}
	}
	
	public void posSearch() throws Exception {
		posfix(raiz);
	}
	
	private void posfix(NoArvoreChar raizSub) throws Exception {
		if (raiz == null) {
			throw new Exception("Arvore vazia");
		} else {

			if (raizSub.esquerda != null) {
				posfix(raizSub.esquerda);
			}

			if (raizSub.direita != null) {
				posfix(raizSub.direita);
			}

			System.out.println(raizSub.dado);
			System.out.println(" ");

		}
	}
	
	public void remove(char valor) throws Exception {
		try {
			removeChild(raiz, valor);
		} catch (Exception e) {
			throw new Exception("Valor não existe");
		}
	}
	
	private void removeChild(NoArvoreChar raizSub, char valor) throws Exception {
		NoArvoreChar no = nodeSearch(raiz, valor);
		NoArvoreChar pai = nodeParent(null, raiz, valor);
		if (no.direita != null && no.esquerda != null) {
			NoArvoreChar noTroca = no.esquerda;
			while (noTroca.direita != null) {
				noTroca = noTroca.direita;
			}
			
			pai = nodeParent(null, raiz, noTroca.dado);
			no.dado = noTroca.dado;
			noTroca.dado = valor;
			removeOneZeroLeaf(pai, noTroca);
		} else {
			removeOneZeroLeaf(pai, no);
		}

	}
	
	private void removeOneZeroLeaf(NoArvoreChar pai, NoArvoreChar no) {
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
	
	private NoArvoreChar nodeParent(NoArvoreChar parent, NoArvoreChar raizSub, char valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Arvore vazia");
		}

		if (raizSub.dado > valor) {
			return nodeParent(raizSub, raizSub.esquerda, valor);
		} else if (raizSub.dado < valor) {
			return nodeParent(raizSub, raizSub.direita, valor);

		} else {
			if (parent == null) {
				return raiz;
			} else {
				return parent;
			}
		}
	}
}
