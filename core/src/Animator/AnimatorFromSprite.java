package Animator;

import Characters.Andy;
import Characters.Character;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

//64 de ancho, 100 de alto.
public class AnimatorFromSprite {
    private Character character;

    public AnimatorFromSprite(Character character){
        this.character = character;
    }

    public Array<TextureRegion> animate(int x, int y, int width, int height, boolean horizontal, int from, int to){
        int a = 0, b = 0;

        if (horizontal) a = 1;
        else b = 1;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = from; i < to; i++) {
//            frames.add(new TextureRegion(character.getTexture(), x * i * a, y * i * b, width, height));
            frames.add(new TextureRegion(character.getTexture(), 0, 20 * i, width, height));
        }
        return frames;
    }

}
