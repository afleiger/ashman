/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFleigerFinal;

import java.io.Serializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Villegard
 */
public class WallTile implements Tile, Serializable
{
    @Override
    public void draw(GraphicsContext gc)
    {
        gc.setFill(GameSettings.palette.getWallColor());
        gc.fillRect(0, 0, 10, 10);
    }
    public boolean isWall()
    {
        return true;
    }
    public boolean isCake()
    {
        return false;
    }
}
