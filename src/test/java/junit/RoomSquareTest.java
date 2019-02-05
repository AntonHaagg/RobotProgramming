package junit;

import impl.Room;
import impl.room.RoomSquare;
import org.junit.Test;

import java.awt.*;

public class RoomSquareTest {

    private int START_X = 0;
    private int START_Y = 0;
    private int SQUARE_SIZE = 5;

    @Test
    public void creationOfSquareRoomOk(){
        Room room = new RoomSquare(START_X, START_Y, SQUARE_SIZE);
        Point startPosition = room.getStartPosition();
        assert(startPosition.y == START_X);
        assert(startPosition.x == START_Y);
        assert(room.contains(new Point(4,4)));
        assert(!room.contains(new Point(5,5)));
        assert(!room.contains(new Point(-1,-1)));
    }

    @Test
    public void updateOfCircularRoomOk(){
        Room room = new RoomSquare(START_X, START_Y, SQUARE_SIZE);
        Point newStartPosition = new Point(1,1);
        room.setStartPosition(newStartPosition);
        assert(newStartPosition.x == room.getStartPosition().x);
        assert(newStartPosition.y == room.getStartPosition().y);
    }
}
