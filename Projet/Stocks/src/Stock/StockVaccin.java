package Stock;

import Stock.Enums.EnumVaccin;

import java.time.LocalDateTime;

public class StockVaccin extends Stock {

    private EnumVaccin enumVaccin;
    private LocalDateTime dateExpiration;

    public StockVaccin(EnumVaccin vaccin, int nbStock)
    {
        super(vaccin.getName(), nbStock);
        enumVaccin = vaccin;
    }

    public StockVaccin(EnumVaccin vaccin, int nbStock, LocalDateTime dateExpiration)
    {
        this(vaccin, nbStock);
        this.dateExpiration = dateExpiration;
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
