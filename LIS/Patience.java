/*
Exemplo: java-algs4 RandomSeq 10 20 121 | java-algs4 Patience
Retorna o tamanho da LIS da entrada com complexidade de tempo O(N logN)
Caso tenha também o argumento 
"+": retorna o tamanho e uma subsequência crescente mais longa
"++": retorna os anteriores e a distribuição dos números nas pilhas formadas no jogo de paciência
*/
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Patience {
    public static Stack<Integer>[] initialize(int N) {
        Stack<Integer>[] s = (Stack<Integer>[]) new Stack[N];
        for (int i = 0; i < N; i++) s[i] = new Stack<Integer>();
        return s; 
        }
    
    public static void main(String[] args) {
        int[] in = StdIn.readAllInts();
        int N = in.length;
        int[] setas = new int[N]; //vetor para armazenar para qual elemento aponta na pilha anterior

        Stack<Integer>[] s = initialize(N);
        s[0].push(0);
        int maior = 0;

        for (int i = 1; i < N; i++){
            int n = in[i];
            boolean achou = false;
            //Busca binária:
            int ini = 0, fim = maior;
            while (ini <= fim && !achou){
                int meio = (ini + fim)/2;
                if (in[s[meio].peek()] == n ){
                    s[meio].push(i);
                    if (meio>0) setas[i] = s[meio-1].peek()+1; 
                    achou = true;
                }
                else if(in[s[meio].peek()] > n){
                    if (meio == 0 || in[s[meio-1].peek()]<n){
                        s[meio].push(i);
                        if (meio>0) setas[i] = s[meio-1].peek()+1; 
                        achou = true;
                    }
                    else 
                        fim = meio - 1;
                }
                else 
                    ini = meio + 1;
            }
            if (!achou){ //"Cria" nova pilha
                maior++;
                s[maior].push(i);
                setas[i] = s[maior-1].peek()+1;
            }
        }

        if (args.length>0){
            if (args[0].compareTo("++") == 0){
                Stack<Integer> output = new Stack<Integer>(); //Pilha para os números na sequência desejada
                for (int i = 0; i<maior+1 ; i++){
                    for (Integer x : s[i]){
                        output.push(x);
                    }
                    StdOut.print(i + ": ");
                    for (Integer y : output){
                        StdOut.print(in[y]+" ");
                        output.pop();
                    }
                    StdOut.println("");
                }
            }

            Stack<Integer> SSCML = new Stack<Integer>(); //Pilha para os elementos da SSCML (si)
            int[] pos = new int[maior+1]; // Array para armazenar o i dos elementos da SSCML
            SSCML.push(in[N-1]);
            int k = maior;
            pos[k] = N-1;
            k--;
            int ant = setas[N-1] - 1;
            while (ant >= 0){
                SSCML.push(in[ant]);
                pos[k] = ant;
                k--;
                ant = setas[ant] - 1;
            }
            StdOut.println("LIS: ");
            for (int i =0; i <= maior; i++){
                int x = SSCML.pop();
                StdOut.println(i+": "+ pos[i] + " / "+ x);
            }
        }

        StdOut.println("LIS: "+ (maior+1) + " elements");
    }
}
