package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ChowFight extends ApplicationAdapter {
	public SpriteBatch batch;
	Texture img;
	private OrthographicCamera camera;

	Sprite sprite;
	Rectangle bucket;
	private Texture boyImage;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
//		sprite = new Sprite(img);
//		sprite.setPosition(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2, Gdx.graphics.getHeight()/2 - sprite.getHeight()/2);

		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;

		boyImage = new Texture("boyimage.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
//		batch.draw(andy, andy.getX(), andy.getY());
		batch.draw(boyImage, bucket.x, bucket.y);
//		batch.draw(sprite, sprite.getX(), sprite.getY());
		batch.end();

//		if(Gdx.input.isTouched()) {
//			Vector3 touchPos = new Vector3();
//			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			camera.unproject(touchPos);
//			bucket.x = touchPos.x - 64 / 2;
//		}

		/////////Keyboard
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) bucket.y -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) bucket.y += 200 * Gdx.graphics.getDeltaTime();

		///Bucket within limits
		if(bucket.x < 0) bucket.x = 0;
		if(bucket.x > 800 - 64) bucket.x = 800 - 64;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		boyImage.dispose();

	}
}
