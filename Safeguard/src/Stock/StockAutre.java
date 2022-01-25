package Stock;

import Stock.Enums.EnumGel;

public class StockAutre extends Stock{
	int numero;

    public StockAutre(String autre, int nbStock, int num)
    {
        super(autre, nbStock);
        this.numero = num;
    }
    
    public int getNumero() {
    	return this.numero;
    }

    @Override
    public boolean isAutre() {
        return true;
    }
    
}
