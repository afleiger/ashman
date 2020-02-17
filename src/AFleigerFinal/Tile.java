/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFleigerFinal;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Villegard
 */
public interface Tile
{
    public void draw(GraphicsContext gc);
    public boolean isWall();
    public boolean isCake();
}
