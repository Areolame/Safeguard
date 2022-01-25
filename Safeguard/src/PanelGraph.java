import Stock.Enums.EnumMasque;
import Stock.Enums.EnumVaccin;
import Stock.Stock;
import Stock.StockGel;
import Stock.StockMasque;
import Stock.StockVaccin;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PanelGraph extends JPanel {

    private JLabel labelGraph;
    private int origineY = getHeight()/2+150, origineX = getWidth()/2+20;
    private int stepX = 25;
    private Graphics g;

    public static ArrayList<StockMasque> LogMasque = new ArrayList<>();
    public static ArrayList<StockVaccin> LogVaccin = new ArrayList<>();
    public static ArrayList<StockGel> LogGel = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.g = g;
        paintMasque(g);
        paintVaccin(g);
        paintGel(g);

        paintOrigine(g);
    }

    private void paintMasque(Graphics g)
    {
        g.setColor(Color.BLUE);
        int origine = origineX;
        for(int i = 1; i < LogMasque.size(); ++i)
        {
            g.drawLine(origine+stepX*(i - 1), origineY-LogMasque.get(i - 1).getNombreDeStock(),
                    origine+stepX*(i), origineY-LogMasque.get(i).getNombreDeStock());
        }
    }

    private void paintVaccin(Graphics g)
    {
        g.setColor(Color.RED);
        int origine = origineX;
        for(int i = 1; i < LogVaccin.size(); ++i)
        {
            System.out.println( i < LogVaccin.size());
            g.drawLine(origine+stepX*(i - 1), origineY-LogVaccin.get(i - 1).getNombreDeStock(),
                    origine+stepX*(i), origineY-LogVaccin.get(i).getNombreDeStock());
        }
    }

    private void paintGel(Graphics g)
    {
        g.setColor(Color.CYAN);
        int origine = origineX;
        for(int i = 1; i < LogGel.size(); ++i)
        {
            g.drawLine(origine+stepX*(i - 1), origineY+LogGel.get(i - 1).getNombreDeStock(),
                    origine+stepX*(i), origineY+LogGel.get(i).getNombreDeStock());
        }
    }

    private void paintOrigine(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawString("Quantit�", origineX-10, origineY-275);
        g.drawString("Num�ro d'ajout", origineX+280, origineY);
        g.drawLine(origineX, origineY, origineX+275, origineY);
        g.drawLine(origineX, origineY, origineX, origineY-500);
    }

    public void addLog(Stock stock)
    {
        if(stock.isVaccin())
        {
          LogVaccin.add((StockVaccin) stock);
          paintVaccin(g);
        }
        else if(stock.isGel())
        {
            LogGel.add((StockGel) stock);
            paintGel(g);
        }
        else if(stock.isMasque())
        {
            LogMasque.add((StockMasque) stock);
            paintMasque(g);
        }
    }

    public PanelGraph() {

        this.setSize(400,350);
        //cr�ation du layout
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
