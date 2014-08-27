package com.projet5001.game.controleur;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.projet5001.game.Projet5001;
import com.projet5001.game.actors.MyActor;
import com.projet5001.game.actors.Npc;
import com.projet5001.game.collisions.WorldCollector;

public class MapControleur {
    public static final int FRAME_NUMBER = 7;
    public static final float ANIMATION_SPEED = 0.063f;
    public static final int IDLE_ANIMATION = 4;
    public TiledMap tiledmap;
    public MapProperties properties;
    public MapProperties mapProperties;
    public OrthogonalTiledMapRenderer renderer;
    public MapLayers mapLayers;
    public MapLayer mapItems;
    public MapLayer mapTrigger;
    public MapLayer mapActors;
    public MapLayer mapCollidable;
    private TiledMapTileLayer tileMapLayerGround;
    private TiledMapTileLayer tileMapLayerGroundStatic;
    private TiledMapTileLayer tileMapLayerGroundTop;
    private TiledMapTileLayer tileMapLayerGroundBase;

    public MapControleur(){
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        parameters.generateMipMaps = true;
    }

    public MapControleur(FileHandleResolver fileHandleResolver, String mapFilePath) {
        this();
        tiledmap = new TmxMapLoader(fileHandleResolver).load(mapFilePath);
        mapProperties = tiledmap.getProperties();
        renderer = new OrthogonalTiledMapRenderer(tiledmap);
        extractMapLayer();
        processMapLayer();
    }


    private void extractMapLayer() {

        mapLayers = tiledmap.getLayers();

        //layer object
        mapCollidable = mapLayers.get("obstacles");
        mapItems =  mapLayers.get("items");
        mapTrigger = mapLayers.get("trigger");
        mapActors = mapLayers.get("actors");

        //layermap
        tileMapLayerGround = (TiledMapTileLayer) mapLayers.get("ground");
        tileMapLayerGroundBase = (TiledMapTileLayer) mapLayers.get("objets_static_base");
        tileMapLayerGroundTop = (TiledMapTileLayer) mapLayers.get("objets_static_top");
        tileMapLayerGroundStatic = (TiledMapTileLayer) mapLayers.get("top-objet");
    }

    private void processMapLayer() {
        if (mapCollidable != null) {
            MapObjects mapObjects = mapCollidable.getObjects();
            for (MapObject mapObject : mapObjects) {
                RectangleMapObject rect = ((RectangleMapObject) mapObject);
                WorldCollector.collection().add(rect);
            }
        }
        if (mapItems != null) {
            MapObjects mapObjects = mapItems.getObjects();
            for (MapObject mapItem : mapObjects) {
                MapProperties mapItemProperties = mapItem.getProperties();
                //todo do something here
            }
        }
        if (mapActors != null) {
            MapObjects mapObjects = mapActors.getObjects();
            for (MapObject mapActor : mapObjects) {
                MapProperties mapActorProperties = mapActor.getProperties();
                String type = (String) mapActorProperties.get("type");

                switch (type) {
                    case "player":
                        extractPlayer(mapActor);
                        break;

                    case "npc":
                        extractNpc(mapActor);
                        break;
                    case "itemActor":

                }
            }
        }
    }

    private void extractItemActor(MapObject mapActor) {
        System.out.println("we have found the itemsActor");
        MyActor itemActor = new MyActor();
        //determiner si il y a animation ou pas.
        //itemActor.setAnimationControleur(new AnimationControleur(mapActor.getName(), 7, 0.033f, 4));
        itemActor.setPosition(((RectangleMapObject) mapActor).getRectangle().getX(),
                ((RectangleMapObject) mapActor).getRectangle().getY());
        Projet5001.worldDirector.addActor(itemActor);
    }

    private void extractNpc(MapObject mapActor) {
        System.out.println("we have found the others");
        MyActor npc = new Npc();

        npc.setAnimationControleur(new AnimationControleur(mapActor.getName(), FRAME_NUMBER, ANIMATION_SPEED, IDLE_ANIMATION));
        npc.setPosition(((RectangleMapObject) mapActor).getRectangle().getX(),
                ((RectangleMapObject) mapActor).getRectangle().getY());
        Projet5001.worldDirector.addActor(npc);
    }

    private void extractPlayer(MapObject mapActor) {
        System.out.println("we have found the master");

        MyActor player = new MyActor();
        player.setAnimationControleur(new AnimationControleur(mapActor.getName(), FRAME_NUMBER, ANIMATION_SPEED,  IDLE_ANIMATION));
        player.setPosition(((RectangleMapObject) mapActor).getRectangle().getX(),
                ((RectangleMapObject) mapActor).getRectangle().getY());
        Projet5001.worldDirector.player = player;
        Projet5001.worldDirector.addActor(player);
        Projet5001.Keyboard.register(player);
        Projet5001.joyPadControleur.register(player);
    }

    public void setView(OrthographicCamera camera) {
        this.renderer.setView(camera);
    }

    public void renderGround() {
        this.renderer.getSpriteBatch().begin();
        if (this.tileMapLayerGround!=null)this.renderer.renderTileLayer(this.tileMapLayerGround);
        if (this.tileMapLayerGroundBase!=null)this.renderer.renderTileLayer(this.tileMapLayerGroundBase);
        if (this.tileMapLayerGroundStatic!=null)this.renderer.renderTileLayer(tileMapLayerGroundStatic);
        this.renderer.getSpriteBatch().end();
    }
    public void renderTop(){
        this.renderer.getSpriteBatch().begin();
        if (this.tileMapLayerGroundTop!=null)this.renderer.renderTileLayer(this.tileMapLayerGroundTop);
        this.renderer.getSpriteBatch().end();

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
