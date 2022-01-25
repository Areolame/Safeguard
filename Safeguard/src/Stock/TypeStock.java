package Stock;
import Stock.*;
import Stock.Enums.*;

public enum TypeStock {
    Vaccine,
    Mask,
    Gel,
    Test,
	Other;


    public int getID()
    {
        switch (this)
        {
            case Gel:
                return 0;
            case Mask:
                return 1;
            case Vaccine:
                return 2;
            case Test:
                return 3;
        }
        return -1;
    }
    
    public static String getFrom(String nomMateriel)
    {
        if(nomMateriel.equalsIgnoreCase(EnumGel.Hydroalcoholic.getName()))
        {
            return "Gel";
        }
        else if(nomMateriel.equalsIgnoreCase(EnumVaccin.AstraZeneca.getName())
                || nomMateriel.equalsIgnoreCase(EnumVaccin.Pfizer.getName())
                || nomMateriel.equalsIgnoreCase(EnumVaccin.Moderna.getName()))
        {
            return "Vaccine";
        }
        else if(nomMateriel.equalsIgnoreCase(EnumTest.Antigenic.getName())
                || nomMateriel.equalsIgnoreCase(EnumTest.PCR.getName()))
        {
            return "Test";
        }
        else if(nomMateriel.toLowerCase().equalsIgnoreCase(EnumMasque.FFP1.getName())
                || nomMateriel.toLowerCase().equalsIgnoreCase(EnumMasque.FFP2.getName())
                || nomMateriel.toLowerCase().equalsIgnoreCase(EnumMasque.Tissue.getName())
                || nomMateriel.toLowerCase().equalsIgnoreCase(EnumMasque.FFP3.getName()))
        {
            return "Mask";
        }
        else
        {
            return "Other";
        }
    }
}
