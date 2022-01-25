package Stock;

import Stock.Enums.EnumTest;

public class StockTest extends Stock {
    private EnumTest enumTest;

    public StockTest(EnumTest test, int nbStock)
    {
        super(test.getName(), nbStock);
        this.enumTest = test;
    }


    public EnumTest getTypeGel()
    {
        return enumTest;
    }

    @Override
    public boolean isTest()
    {
        return true;
    }

    @Override
    public int getID() {
        return TypeStock.Test.getID();
    }

}
