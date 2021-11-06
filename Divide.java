/*
Uso: java-introcs Divide < arquivo.txt
Esse programa recebe um arquivo com N inteiros e retorna uma divisão entre 3 pessoas na qual cada uma recebe um valor total que é pelo menos igual à ⌊S/3⌋, onde S é igual à soma de todos os inteiros da entrada
*/
import java.util.Arrays;

public class Divide {
    public static int[] b;
    public static int A=0, B=0,C=0;
    public static int sum;
    public static boolean solucao = false;
    public static int sum(int[] pecas){
        int N = pecas.length;
        int soma = 0;
        for (int i=0; i<N;i++) soma+=pecas[i];
        return soma;
    }

    public static boolean backtrack(int max) {
        if (A> max) return true;
        if (B>max) return true;
        if (C> max) return true;
        
        return false;
    }

    public static void enumerate(int[] pecas, int[] subset, int k, int max){
        int N = pecas.length;
        
        if (k==N){
            if(A>=sum/3 && B>=sum/3 && C>=sum/3){
                solucao = true;
                b = Arrays.copyOf(subset, subset.length);
            }
            return;
        }
        subset[k] = 0;
        A+=pecas[k];
        if (!backtrack(max) && !solucao) 
            enumerate(pecas, subset, k+1,max);
        A-=pecas[k];
        
        subset[k] = 1;
        B+=pecas[k];
        if (!backtrack(max) && !solucao)
            enumerate(pecas, subset, k+1,max);
        B-=pecas[k];
        
        subset[k] = 2;
        C+=pecas[k];
        if(!backtrack(max) && !solucao)
            enumerate(pecas, subset, k+1,max);
        C-=pecas[k];
    }

    public static void scheduleBrute(int[] pecas, int max){
        int[] a = new int[pecas.length];
        enumerate(pecas, a, 0, max);
        return;
    }
    
    public static void show(int[] pecas){
        int N=b.length;
        
        for (int i = 0; i < 3; i++){
            StdOut.print(i+": ");
            int s = 0;
            for (int j=0;j<N;j++){
                if(b[j] == i) {
                    StdOut.print(pecas[j]+" ");
                    s+=pecas[j];
                }
            }
            StdOut.println("(sum: " + s+ ")");
        }
    }

    public static void main(String[] args){
        int[] pecas = StdIn.readAllInts();
        sum = sum(pecas);
        int maxBacktrack = sum/3 + sum%3;
        scheduleBrute(pecas, maxBacktrack);
        if (solucao) StdOut.println("There is a solution");
        else StdOut.println("There is no solution");
        if (args.length>0){
            if (solucao) {
                StdOut.println("Optimal value: "+sum/3+"(sum: "+sum+" / want: "+sum/3+" / sum mod 3 = "+sum%3+")");
                show(pecas);}
            else {
                StdOut.println("Best I found (not necessarily optimal): 0(sum: "+sum+" / want: "+sum/3+" / sum mod 3 = "+sum%3+")");
                StdOut.print("0: ");
                for (int i =0; i<pecas.length;i++) StdOut.print(pecas[i]+" ");
                StdOut.println("(sum: "+sum+")");
                StdOut.println("1: (sum: 0)");
                StdOut.println("2: (sum: 0)");
            }
        }
    }
}
