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


}

