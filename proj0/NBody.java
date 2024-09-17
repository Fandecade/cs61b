public class NBody {
    
    /** return the r by reading the file */
    public static double readRadius(String file) {
        In in = new In(file);

        int planet_num = in.readInt();
        double universe_r = in.readDouble();
        return universe_r;
    }

    /** return an array of planets corresponding to the planets in the file */
    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        
        int planet_num = in.readInt();
        double universe_r = in.readDouble();
        Planet[] planets = new Planet[planet_num];  //创建数组（每个数组都是planet类） 
        for(int i = 0; i < planet_num; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return planets;
    }


    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double r = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        //drawing the background
        StdDraw.setScale(-r, r);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");

        
        StdDraw.enableDoubleBuffering();    //double buffering
        double waittime = 0;
        while(waittime < T){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];;
           
            for(int i =0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                planets[i].update(dt,xForces[i],yForces[i]);
            }
           
            StdDraw.picture(0,0,"images/starfield.jpg");
            
            for(Planet planet : planets) {
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            waittime += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }

    }
}