package Stock;

import Stock.Enums.EnumMasque;

public class StockMasque extends Stock {

    private EnumMasque enumMasque;

    public StockMasque(EnumMasque masque, int nbStock)
    {
        super(nbStock);
        enumMasque = masque;
    }


    public EnumMasque getTypeMasque()
    {
        return enumMasque;
    }

    @Override
    public boolean isMasque()
    {
        return true;
    }


    @Override
    public int getID() {
        return TypeStock.Masque.getID();
    }
}
