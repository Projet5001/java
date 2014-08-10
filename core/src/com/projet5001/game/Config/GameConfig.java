package com.projet5001.game.Config;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;

/**
 * Created by macmata on 10/08/14.
 */
public class GameConfig {
    public static float unitScale = 1/32f;
    public static boolean devMode = false;
    public static boolean debugMode = false;
    public static boolean joypadView = false;
    public static boolean joypadConfigPosition = false;
    public static boolean joypadConfigSize = false;
    public static boolean isWindows = UIUtils.isWindows;
    public static boolean isPosix = UIUtils.isLinux|UIUtils.isMac;
    public static boolean isAndroid = Gdx.app.getType() == Application.ApplicationType.Android;

}
