package Characters;

import Attacks.SuperAttack;

public class John extends Character {
    public John(SuperAttack super1, SuperAttack super2){
        super(super1, super2);
        this.movementSpeed = 8;
        this.strenght = 58;
        this.life = 7500;

    }

    @Override
    public void upperCat(Character enemigo) {
        enemigo.hurt(this.life * this.strenght * 0.0015);
    }

    @Override
    public void jabPunch(Character enemigo) {
        enemigo.hurt(this.life * this.strenght * 0.0018);
    }
}
