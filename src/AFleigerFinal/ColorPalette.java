package AFleigerFinal;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Villegard
 */
public class ColorPalette
{
    private String name;
    private Color back;
    private Color wall;
    private Color ash;
    private Color ghost;
    private Color cake;
    
    public ColorPalette(String name, Color b, Color w, Color a, Color g, Color c)
    {
        this.name = name;
        back = b;
        wall = w;
        ash = a;
        ghost = g;
        cake = c;
    }
    
    public String getName()
    {
        return name;
    }
    public Paint getBackGroundColor()
    {
        return back;
    }
    public Paint getWallColor()
    {
        return wall;
    }
    public Paint getAshColor()
    {
        return ash;
    }
    public Paint getGhostColor()
    {
        return ghost;
    }
    public Paint getCakeColor()
    {
        return cake;
    }
}
