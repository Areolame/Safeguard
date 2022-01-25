package Stock.Enums;

public enum EnumTest {
    PCR,
    Antigenique;


    public String getName()
    {
        switch (this)
        {
            case PCR:
                return "PCR";
            case Antigenique:
                return "Antigenique";
        }
        return "Error";
    }

    public static EnumTest from(String name)
    {
        if(name.toLowerCase().contains("antigenique"))
        {
            return EnumTest.Antigenique;
        }
        else if(name.toLowerCase().contains("pcr"))
        {
            return EnumTest.PCR;
        }
        return null;
    }
}
