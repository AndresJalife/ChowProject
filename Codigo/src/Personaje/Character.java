package Personaje;

import Ataques.SuperAttack;
import Escudo.Shield;

import java.util.ArrayList;

public abstract class Character {
    protected double life;
    protected ArrayList<SuperAttack> superAttacks;
    protected int movementSpeed;
    protected int strenght;
    protected Shield shield;

    protected int xpos; // DETERMINAR COMO TIENE QUE SER LA POSICION
    protected int ypos;
    protected int xdir;

    public Character(SuperAttack super1, SuperAttack super2){
        this.addSuperAttack(super1);
        this.addSuperAttack(super2);
    }
    private double getActualDamage(){
        return this.life * this.strenght;
    }
    public void addSuperAttack(SuperAttack nuevoSuperAtaque){
        superAttacks.add(nuevoSuperAtaque);
    }
    public ArrayList<SuperAttack> getSuperAttacks(){
        return this.superAttacks;
    }

    public boolean inRange(int xposEnemy, int yposEnemy){
        if (1 + 1 == 2){
            return true;
        }
        return false;
        ///////REFACTOR////////////
    }

    ///////////////////////////Ataques Normales ////////////////////////////////
    public void upperCat(Character enemy) {
        enemy.hurt(this.getActualDamage() * 0.0015);
    }

    public void jabPunch(Character enemy) {
        enemy.hurt(this.getActualDamage() * 0.0018);
    }

    /////////////////////////Super Ataques////////////////////////////
    public void superUpperCat(Character enemy, SuperAttack superAttack){
        enemy.hurt(superAttack.use(this.getActualDamage() * 0.0015));
    }

    public void superJab(Character enemy, SuperAttack superAttack){
        enemy.hurt(superAttack.use(this.getActualDamage() * 0.0018));
    }

    //////////////////////////Defensa////////////////////////////////
    public void activateShield(){
        this.shield.activateShield();
    }

    public void deactivateShield(){
        this.shield.deactivateShield();
    }

    ///////////////////////////
    public double getLife(){
        return this.life;
    }


    public void hurt(double damage){
        this.life -= shield.defend(damage);
    }



}
