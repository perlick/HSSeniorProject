import javax.swing.*;

/**
 * Created by mattperlick on 3/15/17.
 */
public class Population {
    Individual[] indivs, matingPool;
    int  Generation, iterations;
    double mutateChance;
    Save h1;



    public Population(int Popsize, int iterations, double mutateChance){
        indivs = new Individual[Popsize];
        matingPool = new Individual[1000];
        this.iterations = iterations;
        this.mutateChance=mutateChance;


        for (int i = 0; i < Popsize; i++) {
            Individual newIndividual = new Individual(30,-39,true);
            indivs[i] = newIndividual;
        }

        Generation = 1;
        h1 = new Save(this);
    }


    public void run(){
        for(int i=0;i< iterations;i++){
            fitness.fitRank(this);
            fitPercentCalc();
            setmatingPool();
            nextGen(mutateChance);
         }

        h1.saveIndividual(this,mostFit());


        JFrame trail = new JFrame();
        trail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        trail.setBounds(530, 30, 500, 500);
        trail.getContentPane().add(new Main.MyCanvas(-1));
        trail.setVisible(true);

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(30, 30, 500, 500);



        for (int i =0;i<this.mostFit().route.length;i++) {
            Main.MyCanvas temp = new Main.MyCanvas(i);
            window.getContentPane().add(temp);
            window.setVisible(true);

            if(i>4) {
                window.getContentPane().remove(0);
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    //returns number of individuals
    public int getSize(){
        return indivs.length;
    }

    //returns individual at position in parameter
    public Individual getIndividual(int place){
        return indivs[place];
    }

    //returns generation number
    public int getGeneration(){
        return Generation;
    }

    //returns the array of individuals
    public Individual[] getPopulationArray(){
        return indivs;
    }

    public Individual mostFit(){
        int high=0;
        for(int i =1;i<getSize();i++){
            if(getIndividual(i).getFitness()>getIndividual(high).getFitness())
                high=i;
        }
        return getIndividual(high);
    }






    //looks at the total fitness to determin a percent chance of reproduction
    public void fitPercentCalc(){
        int sum = 0;
        for(int i = 0; i < getSize();i++){
            sum+=getIndividual(i).getFitness();
        }
        for(int i = 0; i < getSize();i++) {
            getIndividual(i).setFitpercent(getIndividual(i).getFitness()/sum);
        }


    }

    //creates mating pool based on fit pecent of each individual
    public void setmatingPool(){
        int place = 0;
        while(place<1000){
            for(int i = 0;i<getSize();i++) {
                for (int j = 0; j < getIndividual(i).getFitpercent() * 100 && place < 1000; j++) {
                    this.matingPool[place] = indivs[i];
                    place++;
                }
            }
        }
    }

    //takes from matingpool and crosses, fills, and mutates indiv array
    public void nextGen(double mutatechance) {
        for (Individual i : indivs){
            i.crossover(
                    matingPool[(int)Math.floor(Math.random()*1000)],
                    matingPool[(int)Math.floor(Math.random()*1000)]
            );
            i.mutate(mutatechance);
        }

        Generation++;
    }

}