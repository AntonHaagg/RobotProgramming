package impl.room;

import impl.Room;

import java.awt.*;

import static java.lang.Math.abs;

public class RoomCircle implements Room {
    private int center_x;
    private int center_y;
    private int radius;
    private Point startingPoint;

    public RoomCircle (int radius){
        this.radius=radius;
        this.center_x = 0;
        this.center_y = 0;
        startingPoint = new Point(0,0);
    }
    @Override
    public Point getStartPosition() {
        return startingPoint;
    }

    @Override
    public void setStartPosition(Point startingPoint) {
        this.startingPoint = startingPoint;
    }

    @Override
    public boolean contains(Point position) {
        //Calculate if the point is within the circle using pythagoras
        int x = position.x;
        int y = position.y;
        int dx = abs(x - center_x);
        int dy = abs(y - center_y);

        if(dx + dy < radius){
            return true;
        }
        if(dx >= radius){
            return false;
        }
        if(dy >= radius){
            return false;
        }

        int pytX = dx ^ 2;
        int pytY = dy ^ 2;
        int pytR = radius ^ 2;

        if(pytX+pytY < pytR){
            return true;
        }

        return false;
    }
}
