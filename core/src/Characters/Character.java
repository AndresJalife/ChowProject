package Characters;

import Characters.Attacks.SuperAttack;
import Characters.Shields.Shield;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.ChowFight;
import com.mygdx.game.Screens.ChowFightMain;
import com.mygdx.game.Screens.GameScreen;

import java.nio.channels.FileChannel;
import java.util.ArrayList;

public abstract class Character extends Sprite {
    protected double life;
    protected ArrayList<SuperAttack> superAttacks;
    protected int movementSpeed;
    protected int strenght;
    protected Shield shield;
    protected String name;
    protected float stateTimer;
    protected boolean facingRight;

    public World world;
    public Body b2body;

    public enum State { UPPERCAT, JAB, STANDING, RUNNING, JUMPING, FALLING}
    public State currentState;
    public State previousState;

    protected TextureRegion characterStand;
    protected Animation characterRun;
    protected Animation characterJump;
    protected Animation characterJab;
    protected Animation characterUpperCat;
    protected Animation characterFalling;

    public Character(World world, GameScreen gameScreen, SuperAttack super1, SuperAttack super2, int inicialX, int inicialY, String atlasName){
        super(gameScreen.getAtlas().findRegion(atlasName));
        this.superAttacks = new ArrayList<SuperAttack>();
        this.addSuperAttack(super1);
        this.addSuperAttack(super2);
        this.world = world;
        defineCharacter(inicialX, inicialY);
    }

    private void defineCharacter(int x, int y){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x / ChowFightMain.PPM, y / ChowFightMain.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / ChowFightMain.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public String getName(){
        return this.name;
    }

    ///////////////////////MOVEMENT//////////////////
    public boolean inRange(int xposEnemy, int yposEnemy){
        if (1 + 1 == 2){
            return true;
        }
        return false;
        ///////REFACTOR////////////
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    protected TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch (currentState){
//            case JUMPING:
//                region =  characterJump.getKeyFrame(stateTimer);
            case RUNNING:
                region = (TextureRegion) characterRun.getKeyFrame(stateTimer, true);
                break;
            default:
                region = characterStand;
                break;
        }

        if ((b2body.getLinearVelocity().x < 0 || !facingRight) && !region.isFlipX()){
            region.flip(true, false);
            facingRight = false;
        }
        else if ((b2body.getLinearVelocity().x > 0 || facingRight) && region.isFlipX()){
            region.flip(true, false);
            facingRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;

        return region;
    }

    protected State getState(){
        if (b2body.getLinearVelocity().y > 0) {
            return State.JUMPING;
        }
//        if (b2body.getLinearVelocity().y < 0){
//            return State.FALLING;
//        }
        if (b2body.getLinearVelocity().x != 0) {
            return State.RUNNING;
        }
        else
            return State.STANDING;
    }
    /////////////////////////////////////DAMAGE AND ATTACKS/////////////////////////////////////
    private double getActualDamage(){
        return this.life * this.strenght;
    }

    public void hurt(double damage){
        this.life -= shield.defend(damage);
    }

    public void addSuperAttack(SuperAttack nuevoSuperAtaque){
        superAttacks.add(nuevoSuperAtaque);
    }

    public ArrayList<SuperAttack> getSuperAttacks(){
        return this.superAttacks;
    }

    public void upperCat(Character enemy) {
        enemy.hurt(this.getActualDamage() * 0.0015);
    }

    public void jabPunch(Character enemy) {
        enemy.hurt(this.getActualDamage() * 0.0018);
    }

    public void superUpperCat(Character enemy, SuperAttack superAttack){
        enemy.hurt(superAttack.use(this.getActualDamage() * 0.0015));
    }

    public void superJab(Character enemy, SuperAttack superAttack){
        enemy.hurt(superAttack.use(this.getActualDamage() * 0.0018));
    }

    //////////////////////////Defense////////////////////////////////
    public void activateShield(){
        this.shield.activateShield();
    }

    public void deactivateShield(){
        this.shield.deactivateShield();
    }

    ///////////////////////////Life////////////////
    public double getLife(){
        return this.life;
    }

    public boolean itsAlive(){
        return this.life > 0;
    }

}
