package Stock;

public enum TypeStock {
    Vaccin,
    Masque,
    Gel;


    public int getID()
    {
        switch (this)
        {
            case Gel:
                return 0;
            case Masque:
                return 1;
            case Vaccin:
                return 2;
        }
        return -1;
    }
}
