package Personaje;

import Ataques.SuperAttack;
import Escudo.BasicShield;

public class Andy extends Character {

    public Andy(SuperAttack super1, SuperAttack super2){
        super(super1, super2);

        this.movementSpeed = 6;
        this.strenght = 65;
        this.life = 8000;
        this.shield = new BasicShield();
    }

}
