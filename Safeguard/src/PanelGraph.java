import Stock.Stock;
import Stock.StockGel;
import Stock.StockMasque;
import Stock.StockVaccin;
import Stock.StockTest;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelGraph extends JPanel {

	private static final long serialVersionUID = -2913866235715235591L;
    private JLabel labelGraph;
    private int origineY = getHeight()/2+150, origineX = getWidth()/2+20;
    private int stepX = 25;
    private Graphics g;

    public static ArrayList<Integer> LogMasque = new ArrayList<>();
    public static ArrayList<Integer> LogVaccin = new ArrayList<>();
    public static ArrayList<Integer> LogGel = new ArrayList<>();
    public static ArrayList<Integer> LogTest = new ArrayList<>();
    public static ArrayList<Integer> LogAutre = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.g = g;
        paintMasque();
        paintVaccin();
        paintGel();
        paintTest();

        paintOrigine();
    }

    private void paintMasque()
    {
        g.setColor(Color.BLUE);
        int origine = origineX;
        for(int i = 1; i < LogMasque.size(); ++i)
        {
            g.drawLine(origine+stepX*(i - 1), origineY-LogMasque.get(i - 1),
                    origine+stepX*(i), origineY-LogMasque.get(i));
        }
    }

    private void paintTest()
    {
        g.setColor(Color.RED);
        int origine = origineX;
        for(int i = 1; i < LogTest.size(); ++i)
        {
            g.drawLine(origine+stepX*(i - 1), origineY-LogTest.get(i - 1),
                    origine+stepX*(i), origineY-LogTest.get(i));
        }
    }

    private void paintVaccin()
    {
        g.setColor(Color.RED);
        int origine = origineX;
        for(int i = 1; i < LogVaccin.size(); ++i)
        {
            g.drawLine(origine+stepX*(i - 1), origineY-LogVaccin.get(i - 1),
                    origine+stepX*(i), origineY-LogVaccin.get(i));
        }
    }

    private void paintGel()
    {
        g.setColor(Color.CYAN);
        int origine = origineX;
        for(int i = 1; i < LogGel.size(); ++i)
        {
            g.drawLine(origine+stepX*(i - 1), origineY-LogGel.get(i - 1),
                    origine+stepX*(i), origineY-LogGel.get(i));
        }
    }

    private void paintOrigine()
    {
        g.setColor(Color.BLACK);
        g.drawString("Quantité", origineX-10, origineY-275);
        g.drawString("Numéro d'ajout", origineX+280, origineY);
        g.drawLine(origineX, origineY, origineX+275, origineY);
        g.drawLine(origineX, origineY, origineX, origineY-500);
    }

    public void addLog(Stock stock)
    {
        if(stock.isVaccin())
        {
          LogVaccin.add(stock.getNombreDeStock());
          paintVaccin();
        }
        else if(stock.isGel())
        {
        	LogGel.add(stock.getNombreDeStock());
            paintGel();
        }
        else if(stock.isMasque())
        {
            LogMasque.add(stock.getNombreDeStock());
            paintMasque();
        }
        else if(stock.isTest())
        {
            LogTest.add(stock.getNombreDeStock());
            paintTest();
        }
        this.updateUI();
    }

    public PanelGraph() {

        this.setSize(400,350);
        //création du layout
        GridLayout experimentLayout = new GridLayout(0,1);
        this.setLayout(experimentLayout);

        JPanel panel1 = new JPanel();
        //instantiation des  composants graphiques
        labelGraph = new JLabel("Statistic", SwingConstants.CENTER);
        //ajout des composants sur le container
        panel1.add(labelGraph);

        //ajouter une bordure vide de taille constante autour de l'ensemble des composants
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

    }
}
