/** the planet class */
public class Planet {

    private static final double G =  6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    // the constructor of empty planet
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    
    //the constructor of a planet
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }


    /**the method of distance between two Planets (instances method) */
    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double r = Math.sqrt(dx*dx +dy*dy);
        return r;
    }

    /**return the force exerted on this planet by the given planet */
    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        return (G * this.mass * p.mass) / (r * r);
    }

    /**return the F of x direction  */
    public double calcForceExertedByX(Planet p) {
        double F_total = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        return (F_total * dx) / r;
    }

    /**return the F of y direction  */
    public double calcForceExertedByY(Planet p) {
        double F_total = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        return (F_total * dy) / r;
    }

    /** return the net force of x direction */
    public double calcNetForceExertedByX(Planet [] allPlanets) {
        double net_force_x = 0;
        for(int i = 0; i < allPlanets.length; i++){
            if(this.equals(allPlanets[i]) == false){
                net_force_x += calcForceExertedByX(allPlanets[i]);
            }
        }
        return net_force_x;
    }

    /** return the net force of y direction */
    public double calcNetForceExertedByY(Planet [] allPlanets) {
        double net_force_y = 0;
        for(int i = 0; i < allPlanets.length; i++){
            if(this.equals(allPlanets[i]) == false){
                net_force_y += calcForceExertedByY(allPlanets[i]);
            }
        }
        return net_force_y;
    }

    /** update the location of planet  */
    public void update(double dt, double fX, double fY) {
        double a_x = fX / this.mass;
        double a_y = fY / this.mass;
        this.xxVel += dt * a_x;
        this.yyVel += dt * a_y;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    //* draw a planet img */
    public void draw() {
        double x = this.xxPos;
        double y = this.yyPos;
        String name = "images/" + this.imgFileName;
        StdDraw.picture(x, y, name);
    }
}