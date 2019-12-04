import java.io.*;

/**
 * Created by mattperlick on 3/15/17.
 */
public class Save {
    File memory;
    FileWriter fw;
    BufferedWriter bw;

    public Save(Population pop){
        try {
            memory = new File("memory.txt");
            memory.createNewFile();
            fw = new FileWriter(memory);
            bw = new BufferedWriter(fw);
            //bw.write("original population"+"\n");
            //for (int i = 0; i<pop.getSize();i++){
            //    bw.write("\n");
           // }
            //bw.append("\n");
            bw.flush();
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public void savePop(Population pop){
        try {
            Writer output;
            output = new BufferedWriter(new FileWriter("memory.txt", true));
            //output.append("Generation: " + pop.getGeneration() + "\n");
            for(Individual i : pop.getPopulationArray())
           //     output.append("\n");
            //output.append("\n");
            output.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveIndividual(Population pop, Individual i){
        try {
            Writer output;
            output = new BufferedWriter(new FileWriter("memory.txt", true));
            //output.append("Generation: " + pop.getGeneration() + "\n");
            // output.append(Double.toString(pop.mostFit().getFitness()));
            output.append(Double.toString(i.route[0][0]) + ", " + Double.toString(i.route[0][1]));
            for (int j=1;j<i.route.length;j++) {
                output.append("\n");
                output.append(Double.toString(i.route[j][0]) + ", " + Double.toString(i.route[j][1]));
            }
            //output.append("\n");
            output.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    }

