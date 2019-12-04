import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Created by mattperlick on 3/15/17.
 */

public class Main {
    public static void main (String [] args) {
        Population x = new Population(50,50,0.0);
        x.run();
    }

    public static class MyCanvas extends JComponent {

        BufferedReader br = null;
        FileReader fr = null;
        int count;

public MyCanvas(int count) {
    this.count = count;
}

        public void paint(Graphics g) {
            int r1 = 15;
            int r2 = 70;
            g.fillOval(186,186,128,128);
            // a2 g.drawRect(340,140,5,5);

            g.setColor(Color.black);

            try {

                fr = new FileReader("memory.txt");
                br = new BufferedReader(fr);

                String sCurrentLine;

                br = new BufferedReader(new FileReader("memory.txt"));
                int line=1;

                if (count==-1){
                    while ((sCurrentLine = br.readLine()) != null) {
                        g.fillRect(
                                (int) (Double.parseDouble(sCurrentLine.substring(0, sCurrentLine.indexOf(','))) + 250)
                                , (int) ((-1 * Double.parseDouble(sCurrentLine.substring(sCurrentLine.indexOf(',') + 2, sCurrentLine.length()))) + 250)
                                , 3, 3);
                    }
                }

                while ((sCurrentLine = br.readLine()) != null) {
                    if(line == count && line%2==0) {
                            g.fillOval(
                                    (int) (Double.parseDouble(sCurrentLine.substring(0, sCurrentLine.indexOf(','))) + 245)
                                    , (int) ((-1 * Double.parseDouble(sCurrentLine.substring(sCurrentLine.indexOf(',') + 2, sCurrentLine.length()))) + 245)
                                    , 10, 10);

                        g.setColor(Color.red);
                        g.drawOval((int) (Double.parseDouble(sCurrentLine.substring(0, sCurrentLine.indexOf(',')))) + 250-r1
                                , (int) ((-1*Double.parseDouble(sCurrentLine.substring(sCurrentLine.indexOf(',') + 2, sCurrentLine.length()))))+250 -r1,
                                2*r1, 2*r1);
                        g.drawOval((int) (Double.parseDouble(sCurrentLine.substring(0, sCurrentLine.indexOf(',')))) + 250-r2
                                , (int) ((-1*Double.parseDouble(sCurrentLine.substring(sCurrentLine.indexOf(',') + 2, sCurrentLine.length()))))+250 -r2
                                , 2*r2, 2*r2);
                        g.setColor(Color.black);
                    }
                    if(line == count && line%2==1) {
                            g.fillRect(
                                    (int) (Double.parseDouble(sCurrentLine.substring(0, sCurrentLine.indexOf(','))) + 250)
                                    , (int) ((-1 * Double.parseDouble(sCurrentLine.substring(sCurrentLine.indexOf(',') + 2, sCurrentLine.length()))) + 250)
                                    , 3, 3);
                    }

                        line++;
                    }

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                try {

                    if (br != null)
                        br.close();

                    if (fr != null)
                        fr.close();

                } catch (IOException ex) {

                    ex.printStackTrace();

                }
            }
        }
    }
}
