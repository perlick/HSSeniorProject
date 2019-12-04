import java.util.ArrayList;

/**
 * Created by mattperlick on 3/27/17.
 */
public class fitness {

    public static int fitcheck(double d){
        if (d>15&&d<70){
            return 1;
        }
        else return 0;
    }



    public static void fitRank(Population z){
        for(int i = 0; i< z.getSize(); i++){
            double d,d2,x=100,y=0,vx,vy,v,tv=z.getIndividual(i).DNA[1],
                    mx,my, mv,mvx,mvy,ma,max,may,mta,md,mtv
                    ,ax,ay,a,a2,ax2,ay2,ta,ta2,time=0.1,fitnum=0,count=0;
            ArrayList cords = new ArrayList<Double>();
            //find initial velocity. tv measured from y axis in degrees
            v=z.getIndividual(i).DNA[0];
            vx=Math.sin(Math.toRadians(tv))*v;
            vy=Math.cos(Math.toRadians(tv))*v;


            mx=100;
            my=100;
            mv=30;
            mtv=-45;

            mvx=Math.sin(Math.toRadians(mtv))*mv;
            mvy=Math.cos(Math.toRadians(mtv))*mv;



            d=Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
            d2=Math.sqrt(Math.pow(x-mx,2)+Math.pow(y-my,2));


            while(d>63.71
                    && (count<1000)
                    && d2>5){

                fitnum+=fitcheck(d2);

                //update position
                    x = x + (vx * time);
                    cords.add(x);
                    y = y + (vy * time);
                    cords.add(y);

                    mx = mx + (mvx * time);
                    cords.add(mx);
                    my = my + (mvy * time);
                    cords.add(my);






                //a1
                //calculate total distance acceleration and direction
                d=Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
                a=100000/(Math.pow(d,2));

                //finds angle from planet to object from y axis
                if(x>0)
                    ta=Math.toDegrees(Math.atan2(x,y))-180;
                else if(x==0&&y<0){
                    ta=0;
                }
                else if(x==0&&y>0){
                    ta=180;
                }
                else
                    ta=Math.toDegrees(Math.atan2(x,y))+180;


                //finds x and y parts of acceleration
                ax=Math.sin(Math.toRadians(ta))*a;
                ay=Math.cos(Math.toRadians(ta))*a;








                //a2
                d2=Math.sqrt(Math.pow(x-mx,2)+Math.pow(y-my,2));
                a2=100000/(Math.pow(d2,2));


                //finds angle from planet to object from y axis
                if(x-mx>0)
                    ta2=Math.toDegrees(Math.atan2(x-mx,y-my))-180;
                else if(x-mx==0&&y-my<0){
                    ta2=0;
                }
                else if(x-mx==0&&y-my>0){
                    ta2=180;
                }
                else
                    ta2=Math.toDegrees(Math.atan2(x-mx,y-my))+180;


                ax2=Math.sin(Math.toRadians(ta2))*a2;
                ay2=Math.cos(Math.toRadians(ta2))*a2;


                ax+=ax2;
                ay+=ay2;



                //update velocities with acceleration
                vx=vx+(ax*time);
                vy=vy+(ay*time);


















                //ma1
                //calculate total distance acceleration and direction
                md=Math.sqrt(Math.pow(mx,2)+Math.pow(my,2));
                ma=100000/(Math.pow(md,2));

                //finds angle from planet to object from y axis
                if(mx>0)
                    mta=Math.toDegrees(Math.atan2(mx,my))-180;
                else if(mx==0&&my<0){
                    mta=0;
                }
                else if(x==0&&y>0){
                    mta=180;
                }
                else
                    mta=Math.toDegrees(Math.atan2(mx,my))+180;


                //finds x and y parts of acceleration
                max=Math.sin(Math.toRadians(mta))*ma;
                may=Math.cos(Math.toRadians(mta))*ma;


                mvx=mvx+(max*time);
                mvy=mvy+(may*time);





                count++; 
            }
            z.getIndividual(i).setFitness(fitnum);
            z.getIndividual(i).setRoute(cords);
        }
    }
}