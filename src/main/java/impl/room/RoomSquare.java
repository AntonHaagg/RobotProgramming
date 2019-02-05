package impl.room;

import impl.Room;
import java.awt.*;

public class RoomSquare implements Room {
    private int start_x;
    private int start_y;
    private int squareSize;
    private Point startingPoint;

    public RoomSquare(int start_x, int start_y, int squareSize){
        this.start_x = start_x;
        this.start_y = start_y;
        this.startingPoint = new Point(start_x,start_y);
        this.squareSize = squareSize;
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
        int x = position.x;
        int y = position.y;

        if(x <= 0 || y <= 0 || x >= squareSize || y >= squareSize){
            return false;
        }

        return true;
    }
}
