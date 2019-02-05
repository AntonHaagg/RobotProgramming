package impl;

import java.awt.*;

public interface Room {
    /**
     *
     * @return Returns the start position of the robot
     */
    Point getStartPosition();

    /**
     * Sets the start position of the robot
     * @param startingPoint Point object of the start position
     */
    void setStartPosition(Point startingPoint);

    /**
     * @param position the new position of the robot
     * @return true/false depending on if the given position is within the room
     */
    boolean contains(Point position);

}
