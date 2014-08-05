package com.projet5001.game.controleur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.projet5001.game.Projet5001;
import com.projet5001.game.actors.MyActor;
import com.projet5001.game.collisions.WorldCollector;

public class MapControleur {
    public TiledMap tiledmap;
    public MapProperties properties;
    public OrthogonalTiledMapRenderer renderer;
    public MapLayers mapLayers;
    public MapLayer mapItems;
    public MapLayer mapTrigger;
    public MapLayer mapActors;
    public MapLayer mapCollidable;
    private MyActor player;

    public MapControleur(String mapFilePath){

        tiledmap = new TmxMapLoader(new InternalFileHandleResolver()).load(mapFilePath);
        properties = tiledmap.getProperties();
        renderer = new OrthogonalTiledMapRenderer(tiledmap);
        getMapLayers();
        processMapLayer();

    }

    public MapControleur(ExternalFileHandleResolver externalFileHandleResolver,String mapFilePath){

        tiledmap = new TmxMapLoader(externalFileHandleResolver).load(mapFilePath);
        properties = tiledmap.getProperties();
        renderer = new OrthogonalTiledMapRenderer(tiledmap);
        getMapLayers();
        processMapLayer();

    }
    public MyActor getPlayer(){
        return player;
    }
    private void getMapLayers(){

        mapLayers = tiledmap.getLayers();
        mapCollidable = (MapLayer)mapLayers.get("obstacles");
        mapItems = (MapLayer)mapLayers.get("items");
        mapTrigger = (MapLayer)mapLayers.get("trigger");
        mapActors = (MapLayer)mapLayers.get("actors");
    }

    private void processMapLayer(){
        if (mapCollidable != null){
            MapObjects mapObjects = mapCollidable.getObjects();
            for (MapObject mapObject : mapObjects) {
                String s = mapObject.getName();
                System.out.println(s);
                RectangleMapObject rect = ((RectangleMapObject) mapObject);
                WorldCollector.collection().add(rect);
            }
        }
        if (mapItems != null){
            MapObjects mapObjects = mapItems.getObjects();
            for (MapObject mapItem : mapObjects) {
                MapProperties mapItemProperties= mapItem.getProperties();
            }
        }
        if (mapActors != null){
            MapObjects mapObjects = mapActors.getObjects();
            for (MapObject mapActor : mapObjects) {
                MapProperties mapActorProperties= mapActor.getProperties();
                String type =(String) mapActorProperties.get("type");

                if(type.equalsIgnoreCase("player")){
                    System.out.println("we have found the master");
                    player = new MyActor(new Texture(Gdx.files.internal("data/sprites/perso.png")));
                    Projet5001.worldDirector.addActor(player);
                    Projet5001.Keyboard.register(player);
                    Projet5001.joyPadControleur.register(player);
                }
                if(type.equalsIgnoreCase("npc")){
                    System.out.println("we have found the others");
                    MyActor npc = new MyActor(new Texture(Gdx.files.internal("data/sprites/perso.png")));
                    npc.setPosition(((RectangleMapObject)mapActor).getRectangle().getX(),
                                    ((RectangleMapObject)mapActor).getRectangle().getY());
                    Projet5001.worldDirector.addActor(npc);
                }
            }
        }
    }

    public void setView(OrthographicCamera camera){
        this.renderer.setView(camera);
    }
    public void render(){
        this.renderer.render();
    }

    public void debug(Camera camera) {
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        MapObjects mapObjects = mapCollidable.getObjects();
        for (MapObject mapObject : mapObjects) {
            RectangleMapObject rectObj = ((RectangleMapObject) mapObject);
            Rectangle rectangle = rectObj.getRectangle();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(1, 1, 0, 1);
            shapeRenderer.rect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            shapeRenderer.end();
        }
    }
}
