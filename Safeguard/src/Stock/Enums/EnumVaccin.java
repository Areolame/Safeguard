package Stock.Enums;

import Stock.Stock;

public enum EnumVaccin {
    Pfizer,
    Moderna,
    AstraZeneca;

    public String getName()
    {
        switch (this)
        {
            case AstraZeneca:
                return "AstraZeneca";
            case Pfizer:
                return "Pfizer";
            case Moderna:
                return "Moderna";
        }
        return "Error";
    }

    public int getID()
    {
        switch (this)
        {
            case AstraZeneca:
                return 0;
            case Pfizer:
                return 1;
        }
        return -1;
    }
}
