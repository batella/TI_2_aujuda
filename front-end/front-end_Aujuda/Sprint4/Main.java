class Cell { 
    public int element;
    public Cell inferior, superior, esquerda, direita;
    
    public Cell() { 
        this(0, null, null, null, null);
    }

    public Cell(int element) { 
        this(element, null, null, null, null);
    }

    public Cell(int element, Cell inferior, Cell superior, Cell esquerda, Cell direita) { 
        this.element = element;
        this.inferior = inferior;
        this.superior = superior;
        this.esquerda = esquerda;
        this.direita = direita;
    }
}
 
class MatrizFlex { 
    private Cell comeco; 
    private int linhas, colunas;
 
    public MatrizFlex() { 
        this(3, 3);
    }

    public MatrizFlex(int linhas, int colunas) { 
        this.linhas = linhas;
        this.colunas = colunas;
        this.comeco = new Cell();
        Cell temp = this.comeco;
        for(int i = 1; i < this.linhas; i++) { 
            temp.inferior = new Cell();
            temp.inferior.superior = temp;
            temp = temp.inferior;
        }
        temp = null;
        for(int i = 0; i < this.linhas; i++) { 
            temp = this.comeco;
            for(int j = 0; j < i; j++) {
                temp = temp.inferior;
            }

            for(int k = 1; k < this.colunas; k++) { 
                temp.direita = new Cell();
                temp.direita.esquerda = temp;
                temp = temp.direita;
            }
        }
        temp = null;
        if(this.linhas > 1) { 
            temp = this.comeco.inferior;
            for(int i = 1; i < this.linhas; i++) { //
                Cell superior = temp.superior;
                Cell inferior = temp;
                while(superior != null && inferior != null) {
                    superior.inferior = inferior; 
                    inferior.superior = superior;
                    superior = superior.direita;
                    inferior = inferior.direita;
                }
                temp = temp.inferior;
            }
        }
    }

    public MatrizFlex soma(MatrizFlex x) { 
        MatrizFlex resp = new MatrizFlex(x.linhas, x.colunas);
        Cell a = resp.comeco;
        for(int i = 0; i < this.linhas; i++) {
            Cell b = a; 
            for(int j = 0; j < this.colunas; j++) {
                b.element = somar(this, x, j, i);
                b = b.direita;
            }
            a = a.inferior;
        }

        return resp;
    }

    private int somar(MatrizFlex a, MatrizFlex b, int x, int y) {
        int soma;
        Cell c = a.comeco;
        Cell d = b.comeco;

        for(int i = 0; i < x; i++) {
            c = c.direita;
            d = d.direita;
        }

        for(int i = 0; i < y; i++) {
            c = c.inferior;
            d = d.inferior;
        }

        soma = c.element + d.element;

        return soma;
    }

    public MatrizFlex multiplicacao(MatrizFlex x) {
        MatrizFlex resp = new MatrizFlex(x.linhas, x.colunas);
        Cell a = resp.comeco;
        int i = 0;
        while(a != null && i < this.linhas) {
            Cell b = a;
            int j = 0;
            while(b != null && j < this.colunas) {
                b.element = multiplicar(this, x, i, j);
                b = b.direita;
                j++;
            }
            a = a.inferior;
            i++;
        }

        return resp;
    }

    private int multiplicar(MatrizFlex a, MatrizFlex b, int x, int y) {
        int multiplicacao = 0;
        Cell c = a.comeco;
        Cell d = b.comeco;

        for(int i = 0; i < x; i++) {
            c = c.inferior;
        }

        for(int i = 0; i < y; i++) {
            d = d.direita;
        }

        int e, f;
        while(c != null && d != null) {
            e = c.element;
            f = d.element;
            multiplicacao += e * f;
            c = c.direita;
            d = d.inferior;
        }

        return multiplicacao;
    }

    public void diagonalPrincipal() { 
        if(this.linhas == this.colunas) { 
            Cell i = comeco;
            while(i != null) {
                MyIO.print(i.element + " ");

                i = i.direita;
                if(i != null) {
                    i = i.inferior; 
                }
            }
            MyIO.println("");
        }
    }

    public void diagonalSecundaria() { 
        if (this.linhas == this.colunas) {
            Cell i;

            for(i = comeco; i.direita != null; i = i.direita);

            while(i != null) {
                MyIO.print(i.element + " ");
                i = i.inferior;
                if (i != null) {
                    i = i.esquerda;
                }
            }
            MyIO.println("");
        }
    }

    public static void main(String[] args) {
        int operacoes = MyIO.readInt();
        for(int i = 0; i < operacoes; i++) {
            int linhas = MyIO.readInt();
            int colunas = MyIO.readInt();
            MatrizFlex Aux1 = new MatrizFlex(linhas, colunas);
            for (Cell aux = Aux1.comeco; aux != null; aux = aux.inferior){ 
                for (Cell aux2 = aux; aux2 != null; aux2 = aux2.direita) {
                    aux2.element = MyIO.readInt();
                }
            }

            linhas = MyIO.readInt();
            colunas = MyIO.readInt();
            MatrizFlex Aux2 = new MatrizFlex(linhas, colunas);
            for (Cell aux = Aux2.comeco; aux != null; aux = aux.inferior) {
                for (Cell aux2 = aux; aux2 != null; aux2 = aux2.direita) {
                    aux2.element = MyIO.readInt(); 
                }
            }

            MatrizFlex soma = Aux1.soma(Aux2); 

            MatrizFlex multiplicacao = Aux1.multiplicacao(Aux2);

            Aux1.diagonalPrincipal(); 
            Aux1.diagonalSecundaria();
            for (Cell aux = soma.comeco; aux != null; aux = aux.inferior) { 
                for (Cell aux2 = aux; aux2 != null; aux2 = aux2.direita) {
                    MyIO.print(aux2.element + " ");
                }
                MyIO.println("");
            }
            for (Cell aux = multiplicacao.comeco; aux != null; aux = aux.inferior) {
                for (Cell aux2 = aux; aux2 != null; aux2 = aux2.direita) {
                    MyIO.print(aux2.element + " ");
                }
                MyIO.println("");
            }
        }
    }
}