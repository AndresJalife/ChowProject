package Characters;

import Attacks.SuperAttack;
import Shields.BasicShield;

public class Andy extends Character {

    public Andy(SuperAttack super1, SuperAttack super2){
        super(super1, super2);

        this.movementSpeed = 6;
        this.strenght = 65;
        this.life = 8000;
        this.shield = new BasicShield();
    }

}
