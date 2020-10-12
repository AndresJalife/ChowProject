package com.mygdx.game.Screens;

import BodyCreator.BodyCreator;
import Characters.Andy;
import Characters.Attacks.SuperAttackBot;
import Characters.John;
import MapLoader.MapLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Scenes.Hud;

public class GameScreen implements Screen {
    final ChowFightMain game;

    private OrthographicCamera gameCam;
    private FitViewport gamePort;
    private Hud hud;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Andy player1;
    private John player2;

    private TextureAtlas atlas;

    public GameScreen(ChowFightMain game) {
        this.game = game;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(ChowFightMain.V_WIDTH / ChowFightMain.PPM, ChowFightMain.V_HEIGHT / ChowFightMain.PPM, gameCam);
        hud = new Hud(game.batch);

        map = this.mapLoader("try2.tmx");

        renderer = new OrthogonalTiledMapRenderer(map, 1 / ChowFightMain.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10 ), true);
        createBodies();
        b2dr = new Box2DDebugRenderer();
//        atlas = new TextureAtlas("Textures/John/pomoPack.txt");
//        atlas = new TextureAtlas("Mario_and_Enemies.pack");
        atlas = new TextureAtlas("Textures/PiskelTry/PiskelTry.txt");

        //TODO: ask for two different characters. Here im just seetting by default one Andy and one John
        player1 = new Andy(world, this,  new SuperAttackBot(), new SuperAttackBot(), 60, 32, true);
//        player2 = new John(world, this,  new SuperAttackBot(), new SuperAttackBot(), 80, 32, false);


    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    private TiledMap mapLoader(String mapfile){
         MapLoader loader = new MapLoader();
         return loader.createTiledMap(mapfile);
    }

    private void createBodies(){
        BodyCreator bcreator = new BodyCreator(map, world);
        for (int layer = 2; layer < 3 ; layer++){
            world = bcreator.createBody(layer);
        }
    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f, 6, 2);

        player1.update(dt);

        gameCam.position.x = player1.b2body.getPosition().x;

        gameCam.update();
        renderer.setView(gameCam);

    }

    private void handleInput(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            player1.b2body.applyLinearImpulse(new Vector2(0, 2f), player1.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player1.b2body.getLinearVelocity().x <= 1   ){
            player1.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player1.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player1.b2body.getLinearVelocity().x >= -1){
            player1.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player1.b2body.getWorldCenter(), true);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player1.draw(game.batch);
        game.batch.end();

        b2dr.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
