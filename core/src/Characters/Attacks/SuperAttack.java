package Attacks;

public abstract class SuperAttack {
    protected double power;
    protected int hitsUntilUse;

    public double use(double originalDamage){
        hitsUntilUse = 6;
        return originalDamage * power;
    }

    public int getHitsUntilUse(){
        return this.hitsUntilUse;
    }
}
