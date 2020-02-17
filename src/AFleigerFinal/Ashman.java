/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFleigerFinal;

import java.io.Serializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Villegard
 */
public class Ashman extends Character implements Serializable
{
    private int mIndex;
    private final double[] mMouthArray = new double[]{ 0, 1.66, 3.33, 5, 2.5 };
    
    public Ashman(Point2D pos)
    {
        super(pos);
        mIndex = 0;
    }
    
    @Override
    public void draw(GraphicsContext gc)
    {
        gc.save();
        gc.translate(mPos.getX(), mPos.getY());
        
        gc.setFill(GameSettings.palette.getAshColor());
        gc.fillOval(0, 0, 9, 9);
        gc.setFill(GameSettings.palette.getBackGroundColor());
        gc.translate(5, 5);
        
        switch(mDir)
        {
            case NONE:
                mIndex = 4;
                break;
            case RIGHT:
                gc.fillPolygon(new double[]{0,5,5},
                new double[]{ 0, -mMouthArray[mIndex], mMouthArray[mIndex]}, 3);
                break;
            case LEFT:
                gc.fillPolygon(new double[]{0,-5,-5},
                new double[]{ 0, -mMouthArray[mIndex], mMouthArray[mIndex]}, 3);
                break;
            case UP:
                gc.fillPolygon(new double[]{ 0, -mMouthArray[mIndex], mMouthArray[mIndex]},
                new double[]{ 0, -5, -5}, 3);
                break;
            case DOWN:
                gc.fillPolygon(new double[]{ 0, -mMouthArray[mIndex], mMouthArray[mIndex]},
                new double[]{ 0, 5, 5}, 3);
                break;
        }
        mIndex = (mIndex + 1) % 5;
        gc.restore();
    }
    
}
