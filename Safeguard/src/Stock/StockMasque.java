package Stock;

import Stock.Enums.EnumMasque;

public class StockMasque extends Stock {

    private EnumMasque enumMasque;

    public StockMasque(EnumMasque masque, int nbStock)
    {
        super(masque.getName(), nbStock);
        enumMasque = masque;
    }

    public StockMasque(String masque, int nbStock)
    {
        super(masque, nbStock);
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
        return TypeStock.Mask.getID();
    }
}
