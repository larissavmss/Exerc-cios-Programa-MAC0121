/*
Uso: java-introcs Ortho ./arquivo1.txt < arquivo2.txt > arquivoFinal.txt
O arquivoFinal.txt deve passar a conter as palavras em arquivo2.txt que não ocorrem em arquivo1.txt 
*/
public class Ortho {
    public static String[] words(String s) {
        String r = "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
        s = s.replaceAll("[^A-Za-z" + r + "\\s]", " ");
        s = s.trim();
        String[] w = s.split("\\s+");
        return w;
    }

    public static void main(String[] args) {
        //Ler arquivos e separar palavras em um array:
        In in = new In(args[0]);
        String palavrasDic = in.readAll();
        String[] dic = palavrasDic.split("\n");

        String palavrasText = StdIn.readAll();
        String [] texto = words(palavrasText);

        
        for(int i =0; i< texto.length;i++){
            String palavra = texto[i];

            //Busca binária com a palavra original:
            int ini = 0;
            int fim = dic.length -1;
            Boolean pertence = false;

            while (ini<=fim){
                int meio = (ini + fim)/2;
                
                if (dic[meio].compareTo(palavra) == 0){
                    //Palavra pertence ao dicionário:
                    pertence = true;
                    ini = fim + 1;
                }
                
                else if (dic[meio].compareTo(palavra)>0)
                    fim = meio - 1;
                else 
                    ini = meio+1;
                
            }
             
            if (pertence == false ) {
                //Busca binária botando o resto da palavra em minusculo menos primeira letra:
                String primeira = palavra.substring(0, 1);
                String resto = palavra.substring(1, palavra.length()).toLowerCase();
                String palavram = primeira + resto;

                ini = 0;
                fim = dic.length - 1;
                
                while (ini<=fim){
                    int meio = (ini + fim)/2;
                    
                    if (dic[meio].compareTo(palavram) == 0){
                        pertence = true;
                        ini = fim + 1;
                    }
                    
                    else if (dic[meio].compareTo(palavram)>0)
                        fim = meio - 1;
                    else
                        ini = meio+1;
                    
                }
            }

            if (pertence == false) {
                //Busca com toda a palavra em minúsculo:
                
                String palavramin = palavra.toLowerCase();
                ini = 0;
                fim = dic.length - 1;

                while (ini<=fim){
                    int meio = (ini + fim)/2;
                    
                    if (dic[meio].compareTo(palavramin) == 0){
                        pertence = true;
                        ini = fim + 1;
                    }
                    
                    else if (dic[meio].compareTo(palavramin)>0)
                        fim = meio - 1;
                    else
                        ini = meio+1;
                    
                }
            }


            if (pertence == false)
                StdOut.println(texto[i]);
                
        }

    }

 
        
}
