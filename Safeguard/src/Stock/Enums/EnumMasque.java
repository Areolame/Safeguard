package Stock.Enums;

import Stock.Stock;

public enum EnumMasque {
    FFP1,
    FFP2,
    FFP3,
    Tissu;

    public String getName()
    {
        switch (this)
        {
            case FFP1:
                return "FFP1";
            case FFP2:
                return "FFP2";
            case FFP3:
                return "FFP3";
            case Tissu:
                return "Tissu";
        }
        return "Error";
    }
    
    public static EnumMasque from(String name)
    {
        if(name.toLowerCase().contains("ffp1"))
        {
            return EnumMasque.FFP1;
        }
        else if(name.toLowerCase().contains("ffp2"))
        {
            return EnumMasque.FFP2;
        }
        else if(name.toLowerCase().contains("ffp3"))
        {
            return EnumMasque.FFP3;
        }
        else if(name.toLowerCase().contains("tissu"))
        {
            return EnumMasque.Tissu;
        }
        return null;
    }


}

