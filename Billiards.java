/*
Uso: java-introcs Billiards
Clique na tela para a bolinha se movimentar de acordo com a sua distância ao mouse
*/
public class Billiards {
    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        double rx = 0, ry = 0;  //Posição inicial da bola
        double vx = 0,vy=0;  //Velocidade inicial
        double radius = 0.05;  
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        double atrito = 0.001; 
        double forca = 0.0; 
        double dx=0, dy =0; //Inicialização da distância entre bola e mouse

        while (true) {     
                                    
            // Clique do mouse:
            if (StdDraw.isMousePressed()) {
                //Posição do mouse:
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();    
                //Distância mouse-bola:
                dx =rx - x;
                dy = ry - y;
                double dmouse = Math.sqrt( Math.pow((x-rx),2) + Math.pow((y-ry),2));

                if (dmouse < 0.1) // Evita que a distância tenda a 0 e a força exploda
                    dmouse = 0.1; 

                forca = 0.1/Math.pow(dmouse, 2); //Considerei a força correspondente à aceleração
                    
            }
            //Parar a bolinha:
            if (forca < atrito){ 
                vx = 0;
                vy = 0;
                rx = rx + vx; 
                ry = ry + vy;

                StdDraw.clear(StdDraw.LIGHT_GRAY);
                StdDraw.setPenColor(StdDraw.BLACK); 
                StdDraw.filledCircle(rx, ry, radius); 
                StdDraw.show();
            }

            else { 
                //Mudar direção da bolinha quando bate nas paredes:
                if (Math.abs(rx + vx) > 1.0 - radius) dx = -dx;
                if (Math.abs(ry + vy) > 1.0 - radius) dy = -dy;

                //Atualiza velocidade e posição:
                vx = (dx * forca);
                vy = (dy * forca);
                rx = rx + vx; 
                ry = ry + vy;

                //Desaceleração:
                forca -= atrito;


                StdDraw.clear(StdDraw.LIGHT_GRAY);
                StdDraw.setPenColor(StdDraw.BLACK); 
                StdDraw.filledCircle(rx, ry, radius); 
                StdDraw.show();
                StdDraw.pause(20);
            }

            
            
        }
    }
}
