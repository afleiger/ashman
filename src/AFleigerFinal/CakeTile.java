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
public class CakeTile implements Tile, Serializable
{
    @Override
    public void draw(GraphicsContext gc)
    {
        gc.setFill(GameSettings.palette.getCakeColor());
        gc.fillOval(3, 3, 4, 4);
    }
    @Override
    public boolean isWall()
    {
        return false;
    }
    @Override
    public boolean isCake()
    {
        return true;
    }
}
