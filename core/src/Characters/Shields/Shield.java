package Characters.Shields;

public abstract class Shield {
    protected boolean state;

    public Shield(){
        this.state = false;
    }
    public double defend(double inicialDamage){
        if (this.state){
            return inicialDamage * 0.5;
        }
        return inicialDamage;
    }

    public void activateShield(){
        this.state = true;
    }

    public void deactivateShield(){
        this.state = false;
    }
}
