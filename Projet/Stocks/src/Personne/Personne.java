package Personne;

import Stock.Stock;
import Stock.StockGel;
import Stock.StockMasque;
import Stock.StockVaccin;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Personne {

    private String nom, prenom;
    private boolean testPositif;
    private LocalDateTime dateDeNaissance;
    private ArrayList<StockMasque> stockMasques;
    private ArrayList<StockVaccin> stockVaccin;
    private ArrayList<StockGel> stockGels;
    private int id;

    private static int idGlobal = 0;

    public Personne(String nom, String prenom, LocalDateTime dateDeNaissance,
                    ArrayList<StockMasque> stockMasques, ArrayList<StockVaccin> stockVaccin, ArrayList<StockGel> stockGels)
    {
        id = idGlobal;
        ++idGlobal;

        this.stockMasques = stockMasques;
        this.stockGels = stockGels;
        this.stockVaccin = stockVaccin;

        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
    }

    public void addStock(Stock stock)
    {
        if(stock.isGel())
        {
            stockGels.get(stock.getID()).addStock(stock.getNombreDeStock());
        }
        else if(stock.isVaccin())
        {
            stockVaccin.get(stock.getID()).addStock(stock.getNombreDeStock());
        }
        else if(stock.isMasque())
        {
            stockMasques.get(stock.getID()).addStock(stock.getNombreDeStock());
        }
    }


    public int getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDateTime getDateDeNaissance()
    {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDateTime dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public int getBirthdayYear()
    {
        return dateDeNaissance.getYear();
    }
    public int getBirthdayMonth()
    {
        return dateDeNaissance.getMonth().getValue();
    }
    public int getBirthdayDay()
    {
        return dateDeNaissance.getDayOfMonth();
    }

    public void setTestPositif(boolean testPositif) {
        this.testPositif = testPositif;
    }
}
