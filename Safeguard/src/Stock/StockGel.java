package Stock;

import Stock.Enums.EnumGel;
import Stock.Enums.EnumMasque;

public class StockGel extends Stock {
    private EnumGel enumGel;

    public StockGel(EnumGel gel, int nbStock)
    {
        super(gel.getName(), nbStock);
        enumGel = gel;
    }

    public StockGel(String gel, int nbStock)
    {
        super(gel, nbStock);
    }


    public EnumGel getTypeGel()
    {
        return enumGel;
    }

    @Override
    public boolean isGel()
    {
        return true;
    }

    @Override
    public int getID() {
        return TypeStock.Gel.getID();
    }

}
