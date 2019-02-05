package junit;

import impl.Room;
import impl.room.RoomCircle;
import org.junit.Test;

import java.awt.*;

public class RoomCircleTest {

    private int RADIUS = 10;
    private int START_X = 0;
    private int START_Y = 0;

    @Test
    public void creationOfCircularRoomOk(){
        Room room = new RoomCircle(RADIUS);
        Point startPosition = room.getStartPosition();
        assert(startPosition.y == START_X);
        assert(startPosition.x == START_Y);
        assert(room.contains(new Point(1,1)));
    }

    @Test
    public void updateOfCircularRoomOk(){
        Room room = new RoomCircle(RADIUS);
        Point newStartPosition = new Point(1,1);
        room.setStartPosition(newStartPosition);
        assert(newStartPosition.x == room.getStartPosition().x);
        assert(newStartPosition.y == room.getStartPosition().y);
    }
}
