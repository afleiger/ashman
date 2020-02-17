/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFleigerFinal;

import java.io.Serializable;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Villegard
 */
public class EmptyTile implements Tile, Serializable
{
    @Override
    public void draw(GraphicsContext gc)
    {
        //do nothing, is empty
    }
    public boolean isWall()
    {
        return false;
    }
    public boolean isCake()
    {
        return false;
    }
}
