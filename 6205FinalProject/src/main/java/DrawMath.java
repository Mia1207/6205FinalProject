import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DrawMath {
    /**
     * This class is used to define a Skellam distribution and Poisson Distribution which is ideal distribution to simulate the Goal Difference in a match.
     * @skellamDistribution (double u1, double u2,String homeName, String awayName) It was defined to draw a Skellam Distribution with two teams' Possible Goal Difference.
     * @saveAsFile (JFreeChart chart, String outputPath, int weight, int height) It was designed to store all the distribution graphs into png file.
     * @poissonDistribution (double u1, double u2,String homeName, String awayName) It was defined to draw a Poisson Distribution with winner team's Possible Goal Difference.\
     * @theProOfResultWithPo (Match match) Return the probability of winner wins this game or draws.
     * @theProbabiltiyOfResult (Match match, String Result) Return the different probability with different result in one match.
     * @factorial (long number) Used to calculate the the factorial of number.
     * @f1 (double u1, double u2,int x, double x1) Defined as a specific function which is used in the Skellam Distribution.
     * @f2 (double u1, double u2,int x, double x1) Defined as a specific function which is used in the Skellam Distribution.
     * @getDefiniteIntegralByTrapezium (double u1, double u2, int x, double x0, double xn) Used to calculate definite integral of f1.
     * @getDefiniteIntegralByTrapezium2 (double u1, double u2, int x, double x0, double xn) Used to calculate definite integral of f2.
     * @getPath() Get the path where to store the distribution graphs.
     */
    private static  int n = 100000;
    private  static  int i = 1;

    //
    public void skellamDistribution(double u1, double u2,String homeName, String awayName){
        XYSeries series = new XYSeries("xySeries");
        double sum = 0;
        if( u1 < 0 && u2 <0){
            u1 = 1+u1;
            u2 = 1+u2;
        }
        for(int x = -7 ; x<8 ; x++){
            double a1 = Math.PI;
            double b1 = 0;
            double y = 0;
            double result1 = getDefiniteIntegralByTrapezium(u1, u2,x, b1, a1);
            double a2 = 100000;
            double b2 = 0;
            double result2 = getDefiniteIntegralByTrapezium2(u1, u2,x, b2, a2);
            float I = (float) (((1/Math.PI) * result1) - (((Math.sin(Math.abs(x) * Math.PI))/Math.PI) * result2));
            y = Math.exp(-(u1+u2)) * Math.pow(u1/u2,x/2) * I;
            series.add(x, y);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Home Name " +homeName + " vs "+ " Away Name " + awayName, // chart title
                "Goal Difference of this match", // x axis label
                "Probabiltiy Density Function", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                false, // include legend
                false, // tooltips
                false // urls
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(0xFF, 0xFF, 0xFF));
        plot.setDomainGridlinePaint(new Color(0x00, 0x00, 0xff));
        plot.setRangeGridlinePaint(new Color(0xff, 0x00, 0x00));
        ChartFrame frame = new ChartFrame("Skellam Distribution", chart);
        frame.pack();
        frame.setVisible(true);
        String path = getPath();
        saveAsFile(chart, path, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void saveAsFile(JFreeChart chart, String outputPath, int weight, int height) {
        FileOutputStream out = null;
        try {
            File outFile = new File(outputPath);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputPath);
            ChartUtilities.writeChartAsPNG(out, chart, 600, 350);
            //ChartUtilities.writeChartAsJPEG(out, chart, 500, 400);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    public void poissonDistribution(double u1, double u2,String homeName, String awayName){
        XYSeries series = new XYSeries("xySeries");
        double sum = 0;
        double u = u1 - u2;
        int x = 0;
        double y = 0;
        while(y >= 0){
            y = Math.pow(u,x) * Math.exp(-u) * 1/factorial(x);
            series.add(x, y);
            x++;
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Winner Name "+ homeName + " vs Lost Team "+awayName, // chart title
                "Goal Difference of this match", // x axis label
                "Probabiltiy Density Function", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                false, // include legend
                false, // tooltips
                false // urls
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(0xFF, 0xFF, 0xFF));
        plot.setDomainGridlinePaint(new Color(0x00, 0x00, 0xff));
        plot.setRangeGridlinePaint(new Color(0xff, 0x00, 0x00));
        ChartFrame frame = new ChartFrame("Skellam Distribution", chart);
        frame.pack();
        frame.setVisible(true);
        String path = getPath();
        saveAsFile(chart, path, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public double theProOfResultWithPo(Match match){
        double P = 0;
        if(match.getPHS() < 0 && match.getPAS()>0){
            double u = match.getPAS() - match.getPHS();
            int x = 1;
            double y = 0;
            while(y >= 0){
                y = Math.pow(u,x) * Math.exp(-u) * 1/factorial(x);
                P += y;
                x++;
            }
            if( P>(1-P)){
                match.setResult("A");
            }else{
                match.setResult("D");
            }
            System.out.println( "The probabiltiy of "+ match.getHomeTeam() + " win this match with " + match.getAwayTeam()+" is approximately 0%"  );
            System.out.println( "The probabiltiy of " + match.getHomeTeam() + " draw this match with " + match.getAwayTeam() + " is approximately " + (1-P));
            System.out.println( "The probabiltiy of " + match.getHomeTeam() + " loss this match with " + match.getAwayTeam() + " is approximately " + P);
        }else if (match.getPAS() < 0 && match.getPHS() >0){
            double u = match.getPHS() - match.getPAS();
            int x = 1;
            double y = 0;
            while(y >= 0){
                y = Math.pow(u,x) * Math.exp(-u) * 1/factorial(x);
                P += y;
                x++;
            }
            if( P>(1-P)){
                match.setResult("H");
            }else{
                match.setResult("D");
            }
            System.out.println( "The probabiltiy of "+ match.getHomeTeam() + " win this match with " + match.getAwayTeam()+" is approximately " + P  );
            System.out.println( "The probabiltiy of " + match.getHomeTeam() + " draw this match with " + match.getAwayTeam() + " is approximately " + (1-P));
            System.out.println( "The probabiltiy of " + match.getHomeTeam() + " loss this match with " + match.getAwayTeam() + " is approximately 0%" );
        }
        return P;
    }


    public double theProbabiltiyOfResult(Match match, String Result){
        double P = 0;
        double u1 = match.getPHS();
        double u2 = match.getPAS();
        if( u1 < 0 && u2 <0){
            u1 = 1+u1;
            u2 = 1+u2;
        }
        if(Result.equals("HW")){
            for(int x = 1 ; x<8 ; x++){
                double a1 = Math.PI;
                double b1 = 0;
                double y = 0;
                double result1 = getDefiniteIntegralByTrapezium(u1, u2,x, b1, a1);
                double a2 = 100000;
                double b2 = 0;
                double result2 = getDefiniteIntegralByTrapezium2(u1, u2,x, b2, a2);
                float I = (float) (((1/Math.PI) * result1) - (((Math.sin(Math.abs(x) * Math.PI))/Math.PI) * result2));
                y = Math.exp(-(u1+u2)) * Math.pow(u1/u2,x/2) * I;
                P += y;
            }
        }
        if(Result.equals("Draw")){
                double a1 = Math.PI;
                double b1 = 0;
                double result1 = getDefiniteIntegralByTrapezium(u1, u2,0, b1, a1);
                double a2 = 100000;
                double b2 = 0;
                double result2 = getDefiniteIntegralByTrapezium2(u1, u2,0, b2, a2);
                float I = (float) (((1/Math.PI) * result1) - (((Math.sin(Math.abs(0) * Math.PI))/Math.PI) * result2));
                double y = Math.exp(-(u1+u2)) * Math.pow(u1/u2,0/2) * I;
                if(y<0){
                    y =0;
                }
                P = y;
        }
        if(Result.equals("AW")){
            for(int x = -7 ; x<0 ; x++){
                double a1 = Math.PI;
                double b1 = 0;
                double y = 0;
                double result1 = getDefiniteIntegralByTrapezium(u1, u2,x, b1, a1);
                double a2 = 100000;
                double b2 = 0;
                double result2 = getDefiniteIntegralByTrapezium2(u1, u2,x, b2, a2);
                float I = (float) (((1/Math.PI) * result1) - (((Math.sin(Math.abs(x) * Math.PI))/Math.PI) * result2));
                y = Math.exp(-(u1+u2)) * Math.pow(u1/u2,x/2) * I;
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
        return Math.exp((-2*Math.sqrt(u1*u2))* Math.cosh(x2) - (Math.abs(x) *x2));
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

    public static String getPath(){
        String parent = "main/resources/Distribution Graph/";
        String file = Integer.toString(i) + ".png";
        String path = parent + file;
        i++;
        return path;
    }
}