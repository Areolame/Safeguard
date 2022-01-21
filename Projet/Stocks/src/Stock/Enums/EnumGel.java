package Stock.Enums;

public enum EnumGel {
    Hydroalcoolique;

    private String getName()
    {
        switch (this)
        {
            case Hydroalcoolique:
                return "Hydroalcoolique";
        }
        return "Error";
    }
}
