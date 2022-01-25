package Stock;

public enum TypeStock {
    Vaccin,
    Masque,
    Gel,
    Test;


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
            case Test:
                return 3;
        }
        return -1;
    }
}
