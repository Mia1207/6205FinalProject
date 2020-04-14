import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DrawMath {

    public static void main(String[] args) {
        double u1=1.0628573;
        double u2=1.565862;
        XYSeries series = new XYSeries("xySeries");
        for (int x = 0; x < 5; x++) {
            double y = (Math.pow(u1,x)/factorial(x)) * Math.exp(-u1);
//            double y = Math.exp( -(u1+u2)) * Math.pow(u1/u2,x/2) *
            series.add(x, y);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Possion", // chart title
                "Score", // x axis label
                "Pro", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                false, // include legend
                false, // tooltips
                false // urls
        );

        ChartFrame frame = new ChartFrame("my picture", chart);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static long factorial(long number) {
        if (number <= 1)
            return 1;
        else
            return number * factorial(number - 1);
    }
}