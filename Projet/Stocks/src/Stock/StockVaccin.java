package Stock;

import Stock.Enums.EnumVaccin;

public class StockVaccin extends Stock {

    private EnumVaccin enumVaccin;

    public StockVaccin(EnumVaccin vaccin, int nbStock)
    {
        super(nbStock);
        enumVaccin = vaccin;
    }


    public EnumVaccin getTypeMasque()
    {
        return enumVaccin;
    }

    @Override
    public boolean isVaccin() {
        return true;
    }


    @Override
    public int getID() {
        return TypeStock.Vaccin.getID();
    }
}
