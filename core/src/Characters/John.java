package Characters;

import Characters.Attacks.SuperAttack;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Screens.GameScreen;

public class John extends Character {
    public John(World world, GameScreen screen, SuperAttack super1, SuperAttack super2, int inicialX, int inicialY, boolean facingRight){
        super(world, screen,  super1, super2, inicialX, inicialY, "John/pomoTexture");
        this.movementSpeed = 8;
        this.strenght = 58;
        this.life = 7500;
        this.facingRight = facingRight;

        this.name = "JuanTroll";
    }
}
