package impl.robot;

import impl.Robot;
import impl.RobotBuilder;
import impl.RobotService;
import impl.Room;
import impl.builder.BuilderResultImpl;
import impl.service.RobotServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class RobotImpl implements Robot {

    final static Logger LOG = LoggerFactory.getLogger(RobotImpl.class);
    private static final String TURN_LEFT_SV = "V";
    private static final String TURN_RIGHT_SV = "H";
    private static final String MOVE_FORWARD_SV = "G";
    private static final String TURN_LEFT_EN = "L";
    private static final String TURN_RIGHT_EN = "R";
    private static final String MOVE_FORWARD_EN = "F";
    private static final String[] COORDINATES = {"N", "Ö", "S", "V"};
    private final int NORTH = 0;
    private final int EAST = 1;
    private final int SOUTH = 2;
    private final int WEST = 3;
    private static final String REGEX_SPLIT = "(?<!^)";
    private Room room;
    private RobotService robotService;
    private Point currentPosition;
    //Point of the compass
    private int potc = 0;

    public static class RobotBuilderImpl implements RobotBuilder {
        private Room room;

        public RobotBuilder setRoom(Room room) {
            this.room = room;
            return this;
        }

        public BuilderResultImpl<Robot> build() {
            LOG.info("Creating new robot");
            RobotImpl robot = new RobotImpl();
            if(room == null){
                LOG.error("Unable to create robot, room was not specified");
                return new BuilderResultImpl<Robot>(null, 999, "Unable to create robot, room was not specified");
            }

            robot.room = this.room;
            if(room.getStartPosition() == null){
                LOG.error("Unable to create robot, starting position was not specified");
                return new BuilderResultImpl<Robot>(null, 999, "Unable to create robot, starting position was not specified");
            }
            robot.currentPosition = room.getStartPosition();

            robot.robotService = new RobotServiceImpl();
            LOG.info("Created new robot with starting position: " + room.getStartPosition().x + ", " + room.getStartPosition().y);
            return new BuilderResultImpl<Robot>(robot, 0, "");
        }

    }

    private RobotImpl(){

    }

    public String move(String command) {
        LOG.info("Moving the robot with command:"+ command);

        //Split on every character
        String[] commands = command.split(REGEX_SPLIT);

        //loop through all characters, skips unvalid characters
        for(String moveCommand : commands){
            if(StringUtils.equals(moveCommand, MOVE_FORWARD_EN) || StringUtils.equals(moveCommand, MOVE_FORWARD_SV)){
                moveForward();
            } else if(StringUtils.equals(moveCommand, TURN_LEFT_EN) || StringUtils.equals(moveCommand, TURN_LEFT_SV)){
                turnLeft();
            } else if(StringUtils.equals(moveCommand, TURN_RIGHT_EN) || StringUtils.equals(moveCommand, TURN_RIGHT_SV)){
                turnRight();
            }
        }

        //The robot has finished moving, generate a new coordinate result
        try {
            return generateResult();
        } catch(Exception e){
            LOG.error("Unable to generate position of the robot", e);
            return "";
        }
    }

    public String getCurrentPosition() {
        return generateResult();
    }

    /**
     *  Makes the robot turn left
     *  if it doesn't hit a wall it updates the robots position and point of the compass
     */
    private void turnLeft() {
        int tempPotc = robotService.iterate(potc, COORDINATES.length, RobotService.POTCUse.SUBTRACTION);
        LOG.info("Attempting to move the robot right in a "+ COORDINATES[tempPotc] + " direction");
        if(!updatePosition(tempPotc)){
            LOG.info("Unable to turn the robot left due to it hitting a wall");
        }else {
            LOG.info("Success");
        }
    }

    /**
     *  Makes the robot turn right
     *  if it doesn't hit a wall it updates the robots position and point of the compass
     */
    private void turnRight() {
        int tempPotc = robotService.iterate(potc, COORDINATES.length, RobotService.POTCUse.ADDITION);
        LOG.info("Attempting to move the robot right in a "+ COORDINATES[tempPotc] + " direction");
        if(!updatePosition(tempPotc)){
            LOG.info("Unable to turn the robot left due to it hitting a wall");
        }else {
            LOG.info("Success");
        }
    }

    /**
     *  Makes the robot move forward
     *  if it doesn't hit a wall it updates the robots position
     */
    private void moveForward() {
        LOG.info("Attempting to move the robot forward in a "+ COORDINATES[potc] + " direction");
        if(!updatePosition(potc)){
            LOG.info("Unable to move the robot forward due to it hitting a wall");
        }else {
            LOG.info("Success");
        }
    }

    /**
     *  Generates a result of the robots position and where it is pointing
     */
    private String generateResult(){
        return currentPosition.x+" "+currentPosition.y+" "+ COORDINATES[potc];
    }

    /**
     *  Checks which direction the point of the compass is pointing
     *  Updates the robots position if the new position is still within the room
     * @return true/false depending on if the robot moved or not
     */
    private boolean updatePosition(int potc){

        //Fetch current x and y position for the robot
        int tempX = currentPosition.x;
        int tempY = currentPosition.y;

        //Checks if the new point of the compass is south or north then increase/decrease in correct direction
        if(potc == NORTH)
            tempY++;
        else if(potc == EAST)
            tempX++;
        else if(potc == SOUTH)
            tempY--;
        else if(potc == WEST)
            tempX--;

        //Generate a new point and make sure that it is within the room
        Point tempPoint = new Point(tempX, tempY);
        if(room.contains(tempPoint)){
            this.currentPosition = tempPoint;
            this.potc = potc;
            return true;
        }
        return false;
    }
}
