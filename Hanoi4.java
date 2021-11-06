/*
Uso: java-introcs Hanoi4 <numDiscos>
Retorna os movimentos realizados para mover os discos para outra torre
Uso: java-introcs Hanoi4 <numDiscos> .
Retorna também o número de movimentos realizados
*/
//Para conseguir executar no meu computador preciso adicionar -Xmx2G

public class Hanoi4 {
    public static void hanoi3 (int n, int soma, int ini, int fim, int primeiro, int[] m){
        if (n == 1) {
            m[0]++;

            //Salvar o movimento realizado no array "m" como um inteiro:
            String p = Integer.toString(primeiro);
            String f = Integer.toString(fim);
            String movimento = p + f;
            int mx = Integer.parseInt(movimento);
            m[m[0]] = (mx);

            return;
        }
        else{
            int other = soma - (ini+fim);
            hanoi3(n-1, soma, ini, other, primeiro,m);
            m[0]++;

            //Salvar o movimento realizado no array "m" como um inteiro:
            String p = Integer.toString(primeiro+n-1);
            String f = Integer.toString(fim);
            String movimento = p + f;
            int mx = Integer.parseInt(movimento);
            m[m[0]] = (mx);

            hanoi3(n-1, soma,other, fim, primeiro,m);
            return;
        }
    }
    
    public static void mov (int n, int ini, int fim, int[] m){
        if (n == 1) { 
            m[0]++;

            //Salvar o movimento realizado no array "m" como um inteiro:
            String f = Integer.toString(fim);
            String movimento = "1" + f;
            int mx = Integer.parseInt(movimento);
            m[m[0]] = (mx);

            return;
        }

        else {
        //Frame-Stewart algorithm:
            int k = (int)(n + 1 - Math.sqrt(2*n + 1) + 0.5); 
            int r = n - k;
            int other; //Posição diferente da inicial e final

            
            if (ini == 0){
                if (fim == 1) other = 2;    
                else if (fim == 2) other = 3;
                else  other = 1;
            }
            else if (ini == 1){
                if (fim == 0) other = 2;
                else if (fim == 2) other = 3;
                else other = 0;
            }
            else if (ini == 2){
                if (fim == 1)  other = 0;                   
                else if (fim == 0) other = 3; 
                else other = 1;
            }
            else {  
                if (fim == 1) other = 2;  
                else if (fim == 2) other = 0;  
                else other = 1;
            }
            
            int sumPoles = 6 - other;
            int first = k + 1;

            mov(k, ini, other, m);
            hanoi3(r,sumPoles,ini, fim, first,m);
            mov(k,other, fim, m);

            return;
        }
    }
    public static void main(String[] args) {
    //Ler o número de discos:
        int n = Integer.parseInt(args[0]);
    //Criar array para armazenar os movimentos:
        int[] m = new int[385875970]; //Com o array desse tamanho é possível executar até N=300 (usando o tamanho da cota superior, rapidamente a memória estoura)
        m[0] = 0; //Em m[0] guardarei a quantidade de movimentos
    
        mov(n,0,1,m); //Chama função recursiva que move os n discos da posição 0 para 1

        if (args.length > 1) StdOut.println(m[0] + " moves"); //Modo de execução 2

    //Imprimir os movimentos realizados na função mov:
        for(int i = 1; i <= m[0]; i++){
            int num = m[i];
            int peça = num/10;
            int pole = num%10;

            StdOut.print(peça + " "+ pole);
            StdOut.print(" ");
        }
        StdOut.println();
        
    }
    
}
