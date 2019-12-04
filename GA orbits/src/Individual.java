import java.util.ArrayList;

/**
 * Created by mattperlick on 3/15/17.
 */
public class Individual {
    double Fitness,fitpercent;
    double [] DNA;
    double [][] route;

    //(create) boolean in parameter that controls random
    public Individual(double velocity, double direction, boolean random){
        DNA = new double[2];
        if(random){
            DNA[0]=Math.random()*50;
            DNA[1]=(Math.random()*360)-180;
        }
        else {
            DNA[0] = velocity;
            DNA[1] = direction;
        }
    }

    public void setFitness(double fitness){
        this.Fitness = fitness;
    }

    public double getFitness(){
        return Fitness;
    }

    public void setFitpercent(double x){
        fitpercent = x;
    }

    public double getFitpercent(){
        return fitpercent;
    }

    public void setRoute(ArrayList<Double> arr){
        route = new double[arr.size()/2][];
        for (int i=0;i<arr.size()/2;i++){
            route[i]= new double[2];
            route[i][0]=arr.get(i * 2);
            route[i][1]=arr.get((i * 2)+1);
        }
    }


    //(create) sets individual to the mix of two
    public void crossover(Individual x, Individual y){
        int type = (int) Math.ceil(Math.random()*4);
        if(type==1) {
            DNA[0] = x.DNA[0];
            DNA[1] = y.DNA[1];
        }
        else if(type==2){
            DNA[0] = x.DNA[0];
            DNA[1] = x.DNA[1];
        }
        else if(type==3){
            DNA[0] = y.DNA[0];
            DNA[1] = y.DNA[1];
        }
        else if(type==4){
            DNA[0] = y.DNA[0];
            DNA[1] = x.DNA[1];
        }
    }

    //(create) changes individual in some small way mutation chance at every element
    public void mutate(double percentchance){
        for(int i=0;i<DNA.length;i++) {
            if (Math.random() <= percentchance) {
                if(i==0){
                    DNA[i]=Math.random()*100;
                }
                else if(i==1){
                    DNA[i]=(Math.random()*360)-180;
                }
            }
        }
    }
}
