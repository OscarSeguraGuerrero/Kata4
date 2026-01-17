package software.ulpgc.application;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import software.ulpgc.architecture.viewmodel.Histogram;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    public static Display create(){
        return new Display();
    }
    private Display(){
        this.setTitle("Histograma");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

    }

    public Display show(Histogram histogram){
        this.getContentPane().add(chartPannelOf(histogram));
        return this;
    }

    private Component chartPannelOf(Histogram histogram) {
        return new ChartPanel(chartOf(histogram));
    }

    private JFreeChart chartOf(Histogram histogram) {
        return ChartFactory.createHistogram(
                histogram.tittle(),
                histogram.x(),
                histogram.y(),
                datasetOf(histogram)
        );
    }

    private XYSeriesCollection datasetOf(Histogram histogram) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesIn(histogram));
        return dataset;
    }

    private XYSeries seriesIn(Histogram histogram) {
        XYSeries series = new XYSeries(histogram.leyend());
        for(int bin: histogram){
            series.add(bin, histogram.count(bin));
        }
        return series;
    }
}
