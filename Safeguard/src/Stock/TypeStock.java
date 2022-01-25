package Stock;
import Stock.*;
import Stock.Enums.*;

public enum TypeStock {
    Vaccin,
    Masque,
    Gel,
    Test,
	Autre;


    public int getID()
    {
        switch (this)
        {
            case Gel:
                return 0;
            case Masque:
                return 1;
            case Vaccin:
                return 2;
            case Test:
                return 3;
        }
        return -1;
    }
    
    public static String getFrom(String nomMateriel)
    {
        if(nomMateriel.equalsIgnoreCase(EnumGel.Hydroalcoolique.getName()))
        {
            return "Gel";
        }
        else if(nomMateriel.equalsIgnoreCase(EnumVaccin.AstraZeneca.getName())
                || nomMateriel.equalsIgnoreCase(EnumVaccin.Pfizer.getName())
                || nomMateriel.equalsIgnoreCase(EnumVaccin.Moderna.getName()))
        {
            return "Vaccin";
        }
        else if(nomMateriel.equalsIgnoreCase(EnumTest.Antigenique.getName())
                || nomMateriel.equalsIgnoreCase(EnumTest.PCR.getName()))
        {
            return "Test";
        }
        else if(nomMateriel.toLowerCase().equalsIgnoreCase(EnumMasque.FFP1.getName())
                || nomMateriel.toLowerCase().equalsIgnoreCase(EnumMasque.FFP2.getName())
                || nomMateriel.toLowerCase().equalsIgnoreCase(EnumMasque.Tissu.getName())
                || nomMateriel.toLowerCase().equalsIgnoreCase(EnumMasque.FFP3.getName()))
        {
            return "Masque";
        }
        else
        {
            return "Autre";
        }
    }
}
