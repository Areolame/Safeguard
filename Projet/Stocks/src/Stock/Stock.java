package Stock;

public abstract class Stock {

    protected int nombreDeStock;
    protected String nom;

    public Stock(String nom, int nombreDeStock)
    {
    	this.nom = nom;
        this.nombreDeStock = nombreDeStock;
    }


	public int getNombreDeStock()
    {
        return nombreDeStock;
    }

    public void addStock(int nb)
    {
        nombreDeStock += nb;
    }

    public int getID()
    {
        return -1;
    }

    public boolean isMasque()
    {
        return false;
    }

    public boolean isGel()
    {
        return false;
    }

    public boolean isVaccin()
    {
        return false;
    }

	public String getName() 
	{
		return null;
	}

}
