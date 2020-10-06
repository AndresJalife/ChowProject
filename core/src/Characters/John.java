package Characters;

import Characters.Attacks.SuperAttack;
import com.badlogic.gdx.physics.box2d.World;

public class John extends Character {
    public John(World world, SuperAttack super1, SuperAttack super2, int inicialX, int inicialY){
        super(world, super1, super2, inicialX, inicialY);
        this.movementSpeed = 8;
        this.strenght = 58;
        this.life = 7500;

        this.name = "JuanTroll";
    }
}
