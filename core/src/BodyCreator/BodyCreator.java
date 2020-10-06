package BodyCreator;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Screens.ChowFightMain;


public class BodyCreator {
    private World world;
    private TiledMap map;

    public BodyCreator(TiledMap map, World world){
        this.world = world;
        this.map = map;
    }

    public World createBody(int layer){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject objet : map.getLayers().get(layer).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) objet).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2) / ChowFightMain.PPM, (rect.getY() + rect.getHeight()/2) / ChowFightMain.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth()/2) / ChowFightMain.PPM, (rect.getHeight()/2) / ChowFightMain.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        return world;
    }
}
