package impl;

public interface Robot {


    /**
     *
     * @return Returns the tobots current position
     */
    String getCurrentPosition();

    /**
     * @param command the command to move the robot
     * @return new coordinates of the robot
     */
    String move(String command);

}
