package Stock.Enums;

public enum EnumGel {
	Hydroalcoholic;

    public String getName()
    {
        switch (this)
        {
            case Hydroalcoholic:
                return "Hydroalcoholic";
        }
        return "Error";
    }
    
    public static EnumGel from(String name)
    {
        if(name.toLowerCase().contains("hydroalcoholic"))
        {
            return EnumGel.Hydroalcoholic;
        }
        return null;
    }
}
