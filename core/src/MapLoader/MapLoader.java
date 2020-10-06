package MapLoader;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapLoader {
    private TmxMapLoader mapLoader;

    public TiledMap createTiledMap(String mapFile){
        TiledMap map;
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Maps/" + mapFile);
        return map;
    }
}
