/*
Uso: java-introcs Join < strings.txt > arquivoFinal.txt
Esse programa recebe um arquivo com duas strings nele e devolve a junção econômica dessas strings, ou seja, uma string final que tanto s quanto t são subsequências.
*/
public class Join {
    public static String lcs (String S1, String S2, int m, int n){
        /* Longest Common Subsequence Algorithm*/
        int[][] opt = new int[m+1][n+1];

        for(int i=0; i<=m; i++){
            for(int j=0; j<=n;j++){
                if(i==0 || j==0)
                    opt[i][j] =0;
                else if (S1.charAt(i-1)==S2.charAt(j-1)){
                    opt[i][j] = opt[i-1][j-1] + 1;
                }
                else {
                    opt[i][j] = Math.max(opt[i-1][j], opt[i][j-1]);
                }
            }
        }

        //Obter os caracteres da subsequência fazendo o caminho de volta da matriz:
        char[] subseq = new char[opt[m][n]];
        int atual = opt[m][n]-1;
        int i=m; 
        int j=n;
        while (i>0 && j >0){
            if (S1.charAt(i - 1) == S2.charAt(j - 1)){
                subseq[atual] = S1.charAt(i-1);
                atual--;
                i--;
                j--;
            }
            else if(opt[i-1][j] < opt[i][j-1]){
                j--;
            }
            else
                i--;
        }   

        return String.valueOf(subseq);
    }

    public static void juncao(String S1, String S2, String lcs, int m, int n) {
        /*Imprime o join econômico*/
        char[] join = new char[m+n- lcs.length()+1]; //Array de tamanho da cota máxima
        int s1 = 0; 
        int s2 = 0; 
        int i = 0; 
        int k =0; 
        while (s1 < m || s2 < n){ 
            if (i<lcs.length()){
                while(s1 < m && lcs.charAt(i) != S1.charAt(s1)){
                    join[k] = S1.charAt(s1); //Adiciona os caracteres que so pertencem a S1
                    s1++;
                    k++;
                }
                while(s2 < n && lcs.charAt(i) != S2.charAt(s2)){
                    join[k] = S2.charAt(s2); //Adiciona os caracteres que so pertencem a S2
                    s2++;
                    k++;
                }
                join[k] = lcs.charAt(i); //Adiciona um caracter que pertence às duas strings
                k++;
                s1++;
                s2++;
                i++;
            }
            else{
                while(s1<m){
                    join[k] = S1.charAt(s1); //Adiciona os caracteres que so pertencem a S1
                    s1++;
                    k++;
                }
                while (s2<n){
                    join[k] = S2.charAt(s2); //Adiciona os caracteres que so pertencem a S2
                    s2++;
                    k++;
                }
            }
        }

        for(i = 0; i < k; i++){     //Imprime o join encontrado
            StdOut.print(join[i]); 
        }
        StdOut.println("");

      }

      public static void main(String[] args) {
        String S1 = StdIn.readString();
        String S2 = StdIn.readString();
        int m = S1.length();
        int n = S2.length();
        String lcs = lcs(S1, S2, m, n); 
        juncao(S1, S2, lcs, m, n);
      }
    
}

