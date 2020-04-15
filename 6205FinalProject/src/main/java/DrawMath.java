import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DrawMath {
    private static  int n = 100000;

    public void skellamDistribution(double u1, double u2,String homeName, String awayName){
        XYSeries series = new XYSeries("xySeries");
        double sum = 0;
        for (int x = -6; x < 7; x++) {
            double a1 = 3.1415926453;
            double b1 = 0;
            double result1 = getDefiniteIntegralByTrapezium(u1, u2,x, b1, a1);
            double a2 = 100000;
            double b2 = 0;
            double result2 = getDefiniteIntegralByTrapezium2(u1, u2,x, b2, a2);
            float I = (float) (((1/3.1415926453) * result1) - (((Math.sin(Math.abs(x) * 3.1415926453))/3.1415926453) * result2));
            double y = Math.exp(-(u1+u2)) * Math.pow(u1/u2,x/2) * I;
            series.add(x, y);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                homeName + " vs "+ awayName, // chart title
                "Goal Difference of this match", // x axis label
                "Probabiltiy Density Function", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                false, // include legend
                false, // tooltips
                false // urls
        );

        ChartFrame frame = new ChartFrame("Skellam Distribution", chart);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public double theProbabiltiyOfResult(Match match, String Result){
        double P = 0;
        double u1 = match.getPHS();
        double u2 = match.getPAS();
        if(Result.equals("HW")){
            for (int x =1; x<7 ;x++){
                double a1 = 3.1415926453;
                double b1 = 0;
                double result1 = getDefiniteIntegralByTrapezium(u1, u2,x, b1, a1);
                double a2 = 100000;
                double b2 = 0;
                double result2 = getDefiniteIntegralByTrapezium2(u1, u2,x, b2, a2);
                float I = (float) (((1/3.1415926453) * result1) - (((Math.sin(Math.abs(x) * 3.1415926453))/3.1415926453) * result2));
                double y = Math.exp(-(u1+u2)) * Math.pow(u1/u2,x/2) * I;
                P += y;
            }
        }
        if(Result.equals("Draw")){
                double a1 = 3.1415926453;
                double b1 = 0;
                double result1 = getDefiniteIntegralByTrapezium(u1, u2,0, b1, a1);
                double a2 = 100000;
                double b2 = 0;
                double result2 = getDefiniteIntegralByTrapezium2(u1, u2,0, b2, a2);
                float I = (float) (((1/3.1415926453) * result1) - (((Math.sin(Math.abs(0) * 3.1415926453))/3.1415926453) * result2));
                double y = Math.exp(-(u1+u2)) * Math.pow(u1/u2,0/2) * I;
                P = y;
        }
        if(Result.equals("AW")){
            for (int x =-6; x<0 ;x++){
                double a1 = 3.1415926453;
                double b1 = 0;
                double result1 = getDefiniteIntegralByTrapezium(u1, u2,x, b1, a1);
                double a2 = 100000;
                double b2 = 0;
                double result2 = getDefiniteIntegralByTrapezium2(u1, u2,x, b2, a2);
                float I = (float) (((1/3.1415926453) * result1) - (((Math.sin(Math.abs(x) * 3.1415926453))/3.1415926453) * result2));
                double y = Math.exp(-(u1+u2)) * Math.pow(u1/u2,x/2) * I;
                P += y;
            }
        }
        return P;
    }

    public static long factorial(long number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }

    public static double f1(double u1, double u2,int x, double x1) {
        return ((Math.exp(2 * Math.sqrt(u1*u2) * Math.cos(x1))) * Math.cos(Math.abs(x)*x1));
    }

    public static double f2(double u1, double u2,int x, double x2) {
        return Math.exp((-2*Math.sqrt(u1*u2))* Math.cosh(x2 - (Math.abs(x) *x2)));
    }

    public static double getDefiniteIntegralByTrapezium(double u1, double u2, int x, double x0, double xn) {
        double h = Math.abs(xn - x0) / n;
        double sum = 0;
        for (double xi = 0; xi <= xn; xi = xi + h) {
            sum += (f1(u1,u2,x, xi) + f1(u1,u2,x,xi + h)) * h / 2;
        }
        return sum;
    }

    public static double getDefiniteIntegralByTrapezium2(double u1, double u2, int x, double x0, double xn) {
        double h = Math.abs(xn - x0) / n;
        double sum = 0;
        for (double xi = 0; xi <= xn; xi = xi + h) {
            sum += (f2(u1,u2,x, xi) + f2(u1,u2,x,xi + h)) * h / 2;
        }
        return sum;
    }
}