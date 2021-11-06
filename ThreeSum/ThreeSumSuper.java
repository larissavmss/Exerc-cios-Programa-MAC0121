/*
Exemplo: java-algs4 RandomInts 10 20 121 | java-algs4 ThreeSumSuper
Devolve as triplas (i,j,k) com i<j<k e ai + aj + ak = 0 com tempo Θ(N²)
*/
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class ThreeSumSuper {
    public static void printAll(int[] a) {
        Arrays.sort(a);
        int N= a.length;
        for (int i = 0; i<N-1;i++){
            int x = 0 - a[i];
            int lo = i+1, hi = N - 1;
	        while (lo < hi) {
	            if (a[hi] + a[lo] == x) {
                    StdOut.println(a[i] + " " + a[lo] + " " + a[hi]);
                    int h = hi;
                    int l = lo;
                    while (lo < h-1 && a[h] == a[h-1]){
                        h--;
                        StdOut.println(a[i] + " " + a[hi] + " " + a[lo]);
                    }
                    while (l+1 < hi && a[l] == a[l+1] ){
                        l++;
                        StdOut.println(a[i] + " " + a[hi] + " " + a[lo]);
                    }
		            hi--; lo++;
		            continue;
	            }

	            else if (a[hi] + a[lo] < x) lo++;
	            else hi--;
            }
        }
    } 

    public static int count(int[] a){
        Arrays.sort(a);
        int c = 0;
        int N= a.length;
        for (int i = 0; i<N-1;i++){
            int x = 0 - a[i];
            int lo = i+1, hi = N - 1;
	        while (lo < hi) {
	            if (a[hi] + a[lo] == x) {
		            c++;
                    int h = hi;
                    int l = lo;
                    while (lo < h-1 && a[h] == a[h-1]){
                        h--;
                        c++;
                    }
                    while (l+1 < hi && a[l] == a[l+1] ){
                        l++;
                        c++;
                    }
		            hi--; lo++;
		            continue;
	            }

	            else if (a[hi] + a[lo] < x) lo++;
	            else hi--;
            }
        }
        
        return c;
    }
    
    public static void main(String[] args) {
        int[] a = StdIn.readAllInts();
	    int count = count(a);
        StdOut.println(count);
        if (count < 30) printAll(a);
    }

}
