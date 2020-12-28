public class NBody {
    public static double readRadius(String path) {
        In in = new In(path);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }

    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int N = in.readInt();
        double R = in.readDouble();
        Planet[] ps = new Planet[N];
        int counter = 0;
        while (counter<N) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Planet p = new Planet(xp,yp,xv,yv,mass,img);
            ps[counter] = p;
            counter++;
        }
        return ps;
    }


    public static void main (String[] args) {
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];

        String dataPath = "data/planets.txt";
        double uniRadius = readRadius(dataPath);
        Planet[] ps = readPlanets(filename);
        String imageField = "images/starfield.jpg";
        StdDraw.setScale(-uniRadius, uniRadius);
        StdDraw.picture(0, 0, imageField);
        for (Planet p:ps) {
            p.draw();
        }
        StdDraw.show();

        StdDraw.enableDoubleBuffering();
        for (int i=0;i<T;i+=dt) {
            double[] xForces = new double[ps.length];
            double[] yForces = new double[ps.length];
            for (int j=0;j<ps.length;j++) {
                xForces[j] = ps[j].calcNetForceExertedByX(ps);
                yForces[j] = ps[j].calcNetForceExertedByY(ps);
            }
            for (int j=0;j<ps.length;j++) {
                ps[j].update(dt,xForces[j],yForces[j]);
//                StdDraw.pause(10);
            }
            StdDraw.picture(0, 0, imageField);
            for (Planet p:ps) {
                p.draw();
            }
            StdDraw.show();
        }

        StdOut.printf("%d\n", ps.length);
        StdOut.printf("%.2e\n", uniRadius);
        for (int i = 0; i < ps.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                    ps[i].yyVel, ps[i].mass, ps[i].imgFileName);
        }
    }
}
