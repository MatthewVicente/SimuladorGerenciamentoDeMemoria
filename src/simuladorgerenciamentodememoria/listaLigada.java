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

    public int getMemoriaDisponivel() {
        int memoriaDisponivel = 0;
        for (No aux = this.inicio; aux != null; aux = aux.getproxEnd()) {
            memoriaDisponivel += aux.getTam();
        }
        return memoriaDisponivel;
    }

    public void atualizaNo(No melhorNo, int qtdAlocada) {

        melhorNo.setInicioDoEndereco(melhorNo.getInicioDoEndereco() + qtdAlocada);
        melhorNo.reduzTamanho(qtdAlocada);
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
                if (this.inicio.getNumeroDoBloco() == processoProcurado) {
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

    public void apagaVazio() {

        for (No aux = this.inicio; aux != null; aux = aux.getproxEnd()) {
            if (aux.getTam() == 0) {
                this.removeProcesso(aux.getNumeroDoBloco());
            }
        }
    }

    public No buscaMelhorNo(int qtdAlocada) {
        int sobra = 0, menorSobra = 4000;
        No melhorNo = null;
        for (No aux = this.inicio; aux != null; aux = aux.getproxEnd()) {
            if (aux.getTam() >= qtdAlocada) {
                sobra = aux.getTam() - qtdAlocada;
                if (sobra < menorSobra) {
                    menorSobra = sobra;
                    melhorNo = aux;
                }
            }
        }
        return melhorNo;
    }

    public void checaContigua() {
        this.apagaVazio();
        if (this.qtdBlocos >= 2) {
            No atual = this.inicio;
            No ant = atual;
            for (No prox = atual.getproxEnd(); prox != null; ant = atual, atual = prox, prox = prox.getproxEnd()) {
                if (atual.getFinalDoEndereco() == prox.getInicioDoEndereco()) {
                    int novoTamanho = (atual.getTam() + prox.getTam());
                    // Construtor 2:     Inicio do Endereço     |     Final do Endereco    |     Prox nó      |   Tamanho  |   Id
                    No novo = new No(atual.getInicioDoEndereco(), prox.getFinalDoEndereco(), prox.getproxEnd(), novoTamanho, this.id);
                    this.qtdBlocos--;
                    if (this.qtdBlocos == 2) {
                        this.inicio = novo;
                    } else {
                        ant.setproxEnd(novo);
                    }
                }
            }
        }
    }
}
