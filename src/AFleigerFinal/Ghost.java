/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFleigerFinal;

import java.io.Serializable;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Villegard
 */
public class Ghost extends Character implements Serializable
{    
    public Ghost(Point2D pos, Direction dir)
    {
        super(pos);
        mDir = dir;
    }
    @Override
    public void draw(GraphicsContext gc)
    {
        gc.save();
        gc.translate(mPos.getX(), mPos.getY());
        
        gc.setFill(GameSettings.palette.getGhostColor());
        gc.fillOval(0, 0, 9, 9);
        gc.translate(3,2);
        gc.setFill(GameSettings.palette.getBackGroundColor());
        gc.fillOval(0,0,1,1);
        gc.translate(2, 0);
        gc.fillOval(0,0,1,1);
        gc.translate(-3, 2.5);
        gc.fillRect(0, 0, 4, 1);
        gc.setFill(GameSettings.palette.getGhostColor());
        gc.fillRect(-2, 1, 9, 4);
        
        gc.restore();
    }
}
