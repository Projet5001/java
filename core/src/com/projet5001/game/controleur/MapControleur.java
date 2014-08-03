package com.projet5001.game.controleur;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.projet5001.game.Projet5001;
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


    public MapControleur(String mapFilePath){

        tiledmap = new TmxMapLoader(new InternalFileHandleResolver()).load(mapFilePath);
        properties = tiledmap.getProperties();
        renderer = new OrthogonalTiledMapRenderer(tiledmap);
        getMapLayers();
        processMapLayer();

    }

    private void getMapLayers(){

        mapLayers = tiledmap.getLayers();
        mapCollidable = (MapLayer)mapLayers.get("collidables");
        mapItems = (MapLayer)mapLayers.get("items");
        mapTrigger = (MapLayer)mapLayers.get("trigger");
        mapActors = (MapLayer)mapLayers.get("actors");
    }

    private void processMapLayer(){
        if (mapCollidable != null){
            MapObjects mapObjects = mapCollidable.getObjects();
            for (MapObject mapObject : mapObjects) {
                String s =(String) mapObject.getName();
                switch (s) {
                    case "water":
                        System.out.println(s);
                        RectangleMapObject rect = ((RectangleMapObject) mapObject);
                        WorldCollector.collection().add(rect);
                        break;

                    case "item":
                        System.out.println(s);
                        RectangleMapObject rect2 = ((RectangleMapObject) mapObject);
                        break;
                    default:
                        System.out.println(s);
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
}
