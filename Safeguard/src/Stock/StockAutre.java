package Stock;

import Stock.Enums.EnumGel;

public class StockAutre extends Stock{

    public StockAutre(String autre, int nbStock)
    {
        super(autre, nbStock);
    }

    @Override
    public boolean isAutre() {
        return true;
    }
}
