package Stock.Enums;

public enum EnumGel {
	Hydroalcoolique;

    public String getName()
    {
        switch (this)
        {
            case Hydroalcoolique:
                return "Hydroalcoolique";
        }
        return "Error";
    }
    
    public static EnumGel from(String name)
    {
        if(name.toLowerCase().contains("hydroalcoolique"))
        {
            return EnumGel.Hydroalcoolique;
        }
        return null;
    }
}
