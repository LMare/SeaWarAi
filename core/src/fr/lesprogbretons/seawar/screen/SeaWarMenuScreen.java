package fr.lesprogbretons.seawar.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.lesprogbretons.seawar.assets.Assets;
import fr.lesprogbretons.seawar.model.Partie;
import fr.lesprogbretons.seawar.model.map.Grille;
import fr.lesprogbretons.seawar.screen.manager.EditeurMapManager;
import fr.lesprogbretons.seawar.screen.manager.GameMapManager;
import fr.lesprogbretons.seawar.utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static fr.lesprogbretons.seawar.SeaWar.*;

/**
 * Classe qui permet d'afficher un menu
 * <p>
 * Inspiré par PixelScientists
 */
public class SeaWarMenuScreen extends ScreenAdapter {

    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Sprite menu;
    private Music music;

    public SeaWarMenuScreen() {
        music = (Music) assets.get(Assets.menuMusic);
        music.setLooping(true);
    }

    @Override
    public void show() {
        Gdx.graphics.setWindowedMode(800, 480);

        Skin skin = (Skin) assets.get(Assets.skin);

        music.play();
        music.setVolume(.5f);

        //Declare a new slider for volume
        Slider s = new Slider(0, 100, 1, false, skin);
        s.setColor(Color.BLUE);
        s.setValue(music.getVolume()*100);
        s.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                music.setVolume(s.getValue()/100);
            }
        });

        menu = new Sprite((Texture) assets.get(Assets.menu));
        menu.setPosition(-menu.getWidth() / 2, -menu.getHeight() / 2);

        camera = new OrthographicCamera();
        viewport = new FitViewport(menu.getWidth(), menu.getHeight(), camera);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        /*Debut code pour la fenêtre "jouer/selectionner une carte" */

        List mapSauvegardes = new List(skin);
        stage.addActor(mapSauvegardes);
        FileHandle[] mapSaves = Gdx.files.local(String.valueOf(Gdx.files.internal("saves/cartes"))).list();
        String[] cartes = new String[mapSaves.length + 1];
        cartes[0] = "Default Map";
        int i = 1;
        for (FileHandle file : mapSaves) {
            cartes[i] = file.nameWithoutExtension();
            i = i + 1;
        }
        mapSauvegardes.setItems(cartes);

        Table tableMapSave = new Table();


        tableMapSave.add(mapSauvegardes);
        tableMapSave.row();

        TextButton utiliserButton = new TextButton("Use this map", skin, "default");
        utiliserButton.setWidth(150);
        utiliserButton.setHeight(50);
        
        
        // asini: sorry guys I removed all menu .... no need for AI experiment

        /* Bouton "annuler" du JouerScreen */
        tableMapSave.add(utiliserButton);

                seaWarController.startIA();
                newGame(cartes, skin, tableMapSave);


    }

    private void newGame(String[] cartes, Skin skin, Table tableMapSave) {
        if (cartes.length == 1) {
            seaWarController.nouvellePartie();
            game.setScreen(new SeaWarMapScreen(new GameMapManager()));
        } else {
            Dialog d = new Dialog("Choose a map to play", skin, "dialog")
                    .text("");
            d.add(tableMapSave);
            d.show(stage);
        }
    }


    @Override
    public void hide() {
        music.stop();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void render(float delta) {
        Utils.clearScreen();
        spriteBatch.setProjectionMatrix(camera.combined);

        stage.act(delta);

        spriteBatch.begin();
        menu.draw(spriteBatch);
        spriteBatch.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        music.stop();
        music.dispose();
    }
}
