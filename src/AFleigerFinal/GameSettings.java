/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFleigerFinal;


import java.util.prefs.Preferences;
import javafx.scene.paint.Color;

/**
 *
 * @author Villegard
 */
public class GameSettings
{
    public static int initialGhosts = 3;
    public static int addedGhosts = 2;
    
    public static ColorPalette  palette = new ColorPalette("Standard", Color.BLACK, Color.GRAY, Color.GREEN, Color.RED, Color.WHITE);
    
    public static void storePreferences(Class c)
    {
        Preferences pref = Preferences.userNodeForPackage(c);
        pref.putInt("ini", initialGhosts);
        pref.putInt("add", addedGhosts);
        pref.put("pal", palette.getName());
    }
    public static void readPreferences(Class c)
    {
        Preferences pref = Preferences.userNodeForPackage(c);
        initialGhosts = pref.getInt("ini", initialGhosts);
        addedGhosts = pref.getInt("add", addedGhosts);
        palette = PaletteFactory(pref.get("pal", palette.getName()));
    }
    
    public static ColorPalette PaletteFactory(String name)
    {
        if(name.equals("Standard"))
        {
            return new ColorPalette(name, Color.BLACK, Color.GRAY, Color.GREEN, Color.RED, Color.WHITESMOKE);
        }
        if(name.equals("Vomit Rainbow"))
        {
            return new ColorPalette(name, Color.YELLOW, Color.PINK, Color.RED, Color.BLUEVIOLET, Color.AQUA);
        }
        if(name.equals("Heavenly"))
        {
            return new ColorPalette(name, Color.INDIANRED, Color.DARKSLATEGRAY, Color.BLANCHEDALMOND, Color.AQUA, Color.BROWN);
        }
        if(name.equals("Grieving"))
        {
            return new ColorPalette(name, Color.DARKGRAY, Color.BLACK, Color.RED, Color.YELLOW, Color.DARKRED);
        }
        if(name.equals("Eye Damage"))
        {
            return new ColorPalette(name, Color.LEMONCHIFFON, Color.YELLOW, Color.PINK, Color.CORAL, Color.LIGHTBLUE);
        }
        if(name.equals("Neon"))
        {
            return new ColorPalette(name, Color.BLACK, Color.HOTPINK, Color.AQUA, Color.YELLOW, Color.WHITE);
        }
        if(name.equals("Grayscale"))
        {
            return new ColorPalette(name, Color.WHITE, Color.GRAY, Color.BLACK, Color.RED, Color.BLACK);
        }
        return new ColorPalette(name, Color.BLACK, Color.GRAY, Color.GREEN, Color.RED, Color.WHITESMOKE);
    }
}
