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
}
