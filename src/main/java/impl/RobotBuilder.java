package impl;

import impl.builder.BuilderResultImpl;

public interface RobotBuilder {
    /**
     *  Sets the room that the robot should assume it's in
     *
     * @param room room with specifik attributes
     */
    public RobotBuilder setRoom(Room room);

    /**
     *  Returns a builder result of a robot
     *
     * @return a buidler result depending on the outcome of the robot
     */
    public BuilderResultImpl<Robot> build();
}
