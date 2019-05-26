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

    // Retorna quanta memória disponível há na lista, checando todos os nós
    public int getMemoriaDisponivel() {
        int memoriaDisponivel = 0;
        for (No aux = this.inicio; aux != null; aux = aux.getproxEnd()) {
            memoriaDisponivel += aux.getTam();
        }
        return memoriaDisponivel;
    }

    // Atualiza o endereço inicial do bloco, baseando-se em uma quantidade de memoria que foi removida dele
    public void atualizaNo(No melhorNo, int qtdAlocada) {
        melhorNo.setInicioDoEndereco(melhorNo.getInicioDoEndereco() + qtdAlocada);
        melhorNo.reduzTamanho(qtdAlocada);
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

    // Remove um dado bloco
    public No removeProcesso(int blocoProcurado) {
        No anterior = null;
        // Busca em toda a lista
        for (No aux = this.inicio; aux != null; anterior = aux, aux = aux.getproxEnd()) {
            // Caso o nó atual seja o procurado
            if (aux.getNumeroDoBloco() == blocoProcurado) {
                // Caso seja o primeiro nó da lista
                if (this.inicio.getNumeroDoBloco() == blocoProcurado) {
                    // Remove o primeiro nó transformando o início no proximo bloco da lista
                    this.inicio = aux.getproxEnd();
                    // Caso não seja
                } else {
                    // Remove o bloco atual, indicando que seu anterior deve
                    // apontar para seu próximo
                    anterior.setproxEnd(aux.getproxEnd());
                }
                this.qtdBlocos--;
                // Retorna o nó removido
                return aux;
            }
        }
        // Retorna nulo caso o bloco dado não seja encontrado
        return null;
    }

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
        this.id++;
        this.qtdBlocos++;
        if (ant == null)// insere  no inicio
        {
            this.inicio = novo;
        } else {
            ant.setproxEnd(novo);
        }
    }

    // Retorna o melhor nó possível para alocar uma certa quantidade de memória
    public No buscaMelhorNo(int qtdAlocada) {
        //                   | Inicializa com o maior número de memória possível
        int sobra, menorSobra = this.getMemoriaDisponivel();
        No melhorNo = null;
        // Checa todos os nós da lista
        for (No aux = this.inicio; aux != null; aux = aux.getproxEnd()) {
            // Caso o tamnho do nó atual seja suficiente pra alocar a memória desejada
            if (aux.getTam() >= qtdAlocada) {
                // Calcula quanto de sobra esse nó vai deixar
                sobra = aux.getTam() - qtdAlocada;
                // Caso esse valor seja menor que o anterior
                if (sobra < menorSobra) {
                    // Atualiza o menor valor possível
                    menorSobra = sobra;
                    // Atualiza o melhor nó possível
                    melhorNo = aux;
                }
            }
        }
        return melhorNo;
    }

    // Checa e forma, se possível, blocos de memória contínuos(contiguos)
    public void checaContigua() {
        No atual = this.inicio;
        No ant = atual;
        // Estrutura basica de busca em lista ligada    | Atualiza as variáveis de controle
        for (No prox = atual.getproxEnd(); prox != null; ant = atual, atual = prox, prox = prox.getproxEnd()) {
            if (atual.getFinalDoEndereco() == prox.getInicioDoEndereco()) {
                // Calcula o tamanho do novo bloco de memória continuo
                int novoTamanho = (atual.getTam() + prox.getTam());
                // Construtor 2:     Inicio do Endereço     |     Final do Endereco    |     Prox nó      |   Tamanho  |   Id
                No novo = new No(atual.getInicioDoEndereco(), prox.getFinalDoEndereco(), prox.getproxEnd(), novoTamanho, this.id);
                // Reduz a quantidade de blocos na lista
                this.qtdBlocos--;
                // Caso o atual seja o início da lista, põe o novo nó no lugar dele
                if (atual == this.inicio) {
                    this.inicio = novo;
                } // Caso não, manda o anterior apontar pro nó novo 
                else {
                    ant.setproxEnd(novo);
                }

            }
        }
    }

}
