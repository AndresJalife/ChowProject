package Characters;

import Animator.AnimatorFromSprite;
import Characters.Attacks.SuperAttack;
import Characters.Shields.BasicShield;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Screens.ChowFightMain;
import com.mygdx.game.Screens.GameScreen;

import java.util.ArrayList;

public class Andy extends Character {

    public Andy(World world, GameScreen screen,  SuperAttack super1, SuperAttack super2, int inicialX, int inicialY, boolean facinRight){
        super(world, screen,  super1, super2, inicialX, inicialY, "PiskelStand");

        this.name = "AndyBot";
        this.movementSpeed = 6;
        this.strenght = 65;
        this.life = 8000;
        this.shield = new BasicShield();

        this.facingRight = facinRight;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;

        characterStand = new TextureRegion(getTexture(), 0, 0, 20, 20);
        setBounds(0, 0, 20 / ChowFightMain.PPM, 20 / ChowFightMain.PPM );
        setRegion(characterStand);

        AnimatorFromSprite animator = new AnimatorFromSprite(this);
        Array<TextureRegion> frames = animator.animate(0, 20, 20, 20, false, 0, 2);
        characterRun = new Animation(0.15f, frames);
        frames.clear();

    }


}
