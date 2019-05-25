package simuladorgerenciamentodememoria;

/*
    APLICAR CONDICOES NOS METODOS DEPENDENDO DA LISTA
 */
public class listaLigada {

    private No inicio;// endereço inicial da
    private int id = 1;
    private int qtdBlocos = 1;
    private String tipo;

    public listaLigada(int tamanho, String lista) {
        this.tipo = lista;
        if (lista == "listaBlocosAlocados") {
            this.inicio = null;
        } else {
            this.inicio = new No(0, null, tamanho, id);
        }
    }

    public No getInicio() {
        return this.inicio;
    }

    public void atualizaInicio(int qtdAlocada) {
        this.inicio.setInicioDoEndereco(this.inicio.getInicioDoEndereco() + qtdAlocada);
        this.inicio.reduzTamanho(qtdAlocada);
    }

    // APAGAR SE NAO USAR
    public void addInicio(int elemento, int tamanho) {
        this.inicio = new No(elemento, this.inicio, tamanho, id);
        this.id++;
        this.qtdBlocos++;
    }

    // APAGAR SE NAO USAR
    public int remInicio() {
        //Antes de remover devemos verificar se temos pelo menos um Nó na lista,
        // não faz sentido remover algo que não existe, 
        if (!this.isEmpty()) { // se a lista nao esta vazia
            No ant = this.inicio; // guarda a celula que serah removida
            // avançar a referência que aponta para o primeiro  Nó (inicio) 
            // para o próximo Nó da lista
            this.inicio = this.inicio.getproxEnd();
            this.qtdBlocos--;
            return ant.getInicioDoEndereco();
        } else {
            throw new RuntimeException("lista vazia!");
        }

    }

    public boolean isEmpty() {
        return this.inicio == null;
    }

    @Override
    public String toString() {
        return this.inicio + "";
    }

    // versao iterativa
    public int getTamDoEndereco(int endereco) {
        for (No aux = this.inicio; aux != null; aux = aux.getproxEnd()) {
            if (aux.getInicioDoEndereco() == endereco) {
                return aux.getTam();
            }
        }
        return 0;
    }

    public No removeProcesso(int processoProcurado) {
        No anterior = null;
        for (No aux = this.inicio; aux != null; aux = aux.getproxEnd()) {
            if (aux.getNumeroDoBloco() == processoProcurado) {
                if (processoProcurado == 1) {
                    this.inicio = aux.getproxEnd();
                } else {
                    anterior.setproxEnd(aux.getproxEnd());
                }
                this.qtdBlocos--;
                return aux;
            } else {
                anterior = aux;
            }
        }
        return null;
    }

    // APAGAR SE NAO USAR
    // versao recursiva
//    public boolean buscaRec(int x) {
//        return busca(x, this.inicio);
//    }
//    private boolean busca(int x, No aux) {
//        // condicoes de parada
//        if (aux == null) {
//            return false;
//        }
//
//        if (aux.getEndereco() == x) {
//            return true;
//        }
//
//        return busca(x, aux.getproxEnd());
//    
    public void addOrdenado(No removido) {
        No aux = this.inicio;
        No ant = null;
        while (aux != null && aux.getInicioDoEndereco() < removido.getInicioDoEndereco()) {
            ant = aux;
            aux = aux.getproxEnd();
        }
        this.id++;
        this.qtdBlocos++;
        No novo = new No(removido.getInicioDoEndereco(), aux, removido.getTam(), id);
        if (ant == null)// insere  no inicio
        {
            this.inicio = novo;
        } else {
            ant.setproxEnd(novo);
        }

    }

    public void addFim(int endereco, int tam) {
        No aux = this.inicio;
        No ant = null;
        while (aux != null) {
            ant = aux;
            aux = aux.getproxEnd();
        }
        No novo = new No(endereco, null, tam, id);
        id++;
        qtdBlocos++;
        if (ant == null)// insere  no inicio
        {
            this.inicio = novo;
        } else {
            ant.setproxEnd(novo);
        }
    }

//    public boolean busca(int x) {
//
//        for (No aux = this.inicio; aux != null; aux = aux.getProx()) {
//            if (aux.getElemento() == x) {
//                return true;
//            }
//        }
//
//        return false;
//    }
    public void chegaContigua() {   
        if(qtdBlocos >= 2){
            No atual = this.inicio;
            for(No prox = atual.getproxEnd(); prox != null; atual = prox, prox = prox.getproxEnd()){
                if(atual.getFinalDoEndereco() == prox.getInicioDoEndereco()){
                    // TODO junta num no novo
                }                
            }            
        }
       

    }
}
