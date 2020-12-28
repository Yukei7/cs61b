public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double GRAVITY_CONSTANT = 6.67430e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return GRAVITY_CONSTANT * mass * p.mass / Math.pow(distance,2);
    }

    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p);
        return force * (p.xxPos-this.xxPos)/calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double force = calcForceExertedBy(p);
        return force * (p.yyPos-this.yyPos)/calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double netForceX = 0.0;
        for (Planet p:ps) {
            if(this.equals(p)) {
                continue;
            }
            netForceX += calcForceExertedByX(p);
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double netForceY = 0.0;
        for (Planet p:ps) {
            if(this.equals(p)) {
                continue;
            }
            netForceY += calcForceExertedByY(p);
        }
        return netForceY;
    }

    public void update(double time, double forceX, double forceY) {
        double accX = forceX / mass;
        double accY = forceY / mass;
        xxVel = xxVel + time * accX;
        yyVel = yyVel + time * accY;
        xxPos = xxPos + time * xxVel;
        yyPos = yyPos + time * yyVel;
    }
}
