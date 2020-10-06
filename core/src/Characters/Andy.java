package Characters;

import Characters.Attacks.SuperAttack;
import Characters.Shields.BasicShield;
import com.badlogic.gdx.physics.box2d.World;

public class Andy extends Character {

    public Andy(World world, SuperAttack super1, SuperAttack super2, int inicialX, int inicialY){
        super(world, super1, super2, inicialX, inicialY);

        this.movementSpeed = 6;
        this.strenght = 65;
        this.life = 8000;
        this.shield = new BasicShield();

        this.name = "AndyBot";
    }

}
