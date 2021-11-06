/*
Uso: java-introcs ABPerms N a b
O programa retorna as (a,b)-permutações das N primeiras letras do alfabeto
*/
public class ABPerms {
    static int M = 0;
    static boolean verbose = false;
    static int[][] matrizLIS;
    static int[][] matrizLDS;
    
    public static int atualizaLCS(String Alfabeto, int n, String seq, int tipo) {
        if (tipo ==0){ //LIS
            matrizLIS[0][n+1] = 0;
            int N = matrizLIS.length-1;
            for (int i = 1; i<=N; i++){
                if (Alfabeto.charAt(i-1) == seq.charAt(n)) matrizLIS[i][n+1] = matrizLIS[i-1][n] + 1;
                else{
                    matrizLIS[i][n+1] = Math.max(matrizLIS[i-1][n+1], matrizLIS[i][n]);
                }
            }
            return matrizLIS[N][n+1];
        }
        else {  //LDS
            matrizLDS[0][n+1] = 0;
            int N = matrizLDS.length - 1;
            for (int i = 1; i<=N; i++){
                if (Alfabeto.charAt(51-N+i) == seq.charAt(n)) matrizLDS[i][n+1] = matrizLDS[i-1][n] + 1;
                else{
                    matrizLDS[i][n+1] = Math.max(matrizLDS[i-1][n+1], matrizLDS[i][n]);
                }
            }
            return matrizLDS[N][n+1];
        }
    }
    
    public static boolean backtrack(char[] s, int n, int a, int b) {
        char[] seqArray = new char[n+1];
        for (int x =0; x <= n;x++){
            seqArray[x] = s[x];
        }
        String seq = new String(seqArray);
        int Increasing = atualizaLCS("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",n, seq, 0);
        int Decreasing = atualizaLCS("ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba", n, seq, 1);

        if (Increasing > a || Decreasing > b)
            return true;

        return false;
    }
    
    public static int[][] inicializaMatriz(int N) {
        int[][] retorno= new int[N+1][N+1];
        
        for (int i = 0; i <= N; i++){
            for (int j =0; j<=N;j++) 
                retorno[i][j] = 0;
            }
        return retorno;
    }

    public static void perm2(String s, int a, int b) {
        int N = s.length();
        char[] sArray = s.toCharArray();
        matrizLIS = inicializaMatriz(N);
        matrizLDS = inicializaMatriz(N);
        perm2(sArray, 0, a ,b);
    }

    
    private static void perm2(char[] s, int n, int a, int b) {
	int N = s.length;
        if (n == N) {
            M++;
            if (verbose) StdOut.println(new String(s));
            return;
        }

        for (int i = n; i < N; i++) {
            swap(s, n, i);
            if(!backtrack(s,n, a,b)) 
                perm2(s, n + 1,a ,b);
            swap(s, n, i);
        }
    }  

    private static void swap(char[] a, int i, int j) 
    {   char c = a[i];  a[i] = a[j];  a[j] = c;   }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int a = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]);
        if (args.length>3) verbose = true;
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String elements = alphabet.substring(0, n);
        perm2(elements, a , b);
        if (!verbose) StdOut.println(M);
    }
}
