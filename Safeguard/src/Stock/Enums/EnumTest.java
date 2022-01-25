package Stock.Enums;

public enum EnumTest {
    PCR,
    Antigenic;


    public String getName()
    {
        switch (this)
        {
            case PCR:
                return "PCR";
            case Antigenic:
                return "Antigenic";
        }
        return "Error";
    }

    public static EnumTest from(String name)
    {
        if(name.toLowerCase().contains("antigenic"))
        {
            return EnumTest.Antigenic;
        }
        else if(name.toLowerCase().contains("pcr"))
        {
            return EnumTest.PCR;
        }
        return null;
    }
}
