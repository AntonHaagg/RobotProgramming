package junit;

import impl.Robot;
import impl.Room;
import impl.builder.BuilderResultImpl;
import impl.robot.RobotImpl;
import impl.room.RoomCircle;
import impl.room.RoomSquare;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class RobotTest {
    private int RADIUS = 10;
    private int SUCCESS = 0;
    private int FAILURE = 999;
    private static final String COMMAND_1 = "HGHGGHGHG";
    private static final String COMMAND_2 = "RRFLFFLRF";
    private static final String ERROR_MESSAGE_MISSING_ROOM = "Unable to create robot, room was not specified";
    private static final String ERROR_MESSAGE_MISSING_STARTING_POINT = "Unable to create robot, starting position was not specified";
    private static final String SUCCESS_MESSAGE_RIGHT = "1 0 Ö";
    private static final String SUCCESS_MESSAGE_LEFT = "-1 0 V";
    private static final String SUCCESS_MESSAGE_FORWARD = "0 1 N";
    private static final String SUCCESS_MESSAGE_COMMAND_1 = "1 3 N";
    private static final String SUCCESS_MESSAGE_COMMAND_2 = "3 1 Ö";
    private static final String RIGHT = "H";
    private static final String FORWARD = "G";
    private static final String LEFT = "V";

    @Test
    public void creationOfRobotOk(){
        Room room = new RoomCircle(RADIUS);
        BuilderResultImpl<Robot> robotBuilderResult = new RobotImpl.RobotBuilderImpl()
                .setRoom(room)
                .build();

        //Makes sure that everything went ok
        assert(robotBuilderResult.getErrorCode() == SUCCESS);

        //Makes sure that no error message was tagged
        assert(StringUtils.isEmpty(robotBuilderResult.getErrorMessage()));

        //Makes sure that the actual robot is ok
        Robot robot = (Robot) robotBuilderResult.getObject();
        Assert.assertNotNull(robot);
    }

    @Test
    public void missingRoom(){
        BuilderResultImpl<Robot> robotBuilderResult = new RobotImpl.RobotBuilderImpl()
                .setRoom(null)
                .build();

        //Makes sure that everything did not go ok
        assert(robotBuilderResult.getErrorCode() == FAILURE);

        //validate the error message
        assert(StringUtils.equals(ERROR_MESSAGE_MISSING_ROOM, robotBuilderResult.getErrorMessage()));

        //Makes sure that the creation of the robot was aborted
        assert(robotBuilderResult.getObject() == null);
    }

    @Test
    public void missingStartingPoint(){
        Room room = new RoomCircle(RADIUS);
        room.setStartPosition(null);
        BuilderResultImpl<Robot> robotBuilderResult = new RobotImpl.RobotBuilderImpl()
                .setRoom(room)
                .build();

        //validate the error code
        assert(robotBuilderResult.getErrorCode() == FAILURE);

        //validate the error message
        assert(StringUtils.equals(ERROR_MESSAGE_MISSING_STARTING_POINT, robotBuilderResult.getErrorMessage()));

        //Makes sure that the creation of the robot was aborted
        assert(robotBuilderResult.getObject() == null);
    }

    @Test
    public void moveRobotRight(){
        Room room = new RoomCircle(RADIUS);
        BuilderResultImpl<Robot> robotBuilderResult = new RobotImpl.RobotBuilderImpl()
                .setRoom(room)
                .build();
        Robot robot = (Robot) robotBuilderResult.getObject();
        String position = robot.move(RIGHT);
        assert(StringUtils.equals(SUCCESS_MESSAGE_RIGHT, position));
    }

    @Test
    public void moveRobotLeft(){
        Room room = new RoomCircle(RADIUS);
        BuilderResultImpl<Robot> robotBuilderResult = new RobotImpl.RobotBuilderImpl()
                .setRoom(room)
                .build();
        Robot robot = (Robot) robotBuilderResult.getObject();
        String position = robot.move(LEFT);
        assert(StringUtils.equals(SUCCESS_MESSAGE_LEFT, position));
    }

    @Test
    public void moveRobotForward(){
        Room room = new RoomCircle(RADIUS);
        BuilderResultImpl<Robot> robotBuilderResult = new RobotImpl.RobotBuilderImpl()
                .setRoom(room)
                .build();
        Robot robot = (Robot) robotBuilderResult.getObject();
        String position = robot.move(FORWARD);
        assert(StringUtils.equals(SUCCESS_MESSAGE_FORWARD, position));
    }

    @Test
    public void example1Test(){
        Room room = new RoomSquare(1,2,5);
        BuilderResultImpl<Robot> robotBuilderResult = new RobotImpl.RobotBuilderImpl()
                .setRoom(room)
                .build();
        Robot robot = (Robot) robotBuilderResult.getObject();
        String position = robot.move(COMMAND_1);
        assert(StringUtils.equals(SUCCESS_MESSAGE_COMMAND_1, position));
    }

    @Test
    public void example2Test(){
        Room room = new RoomCircle(RADIUS);
        BuilderResultImpl<Robot> robotBuilderResult = new RobotImpl.RobotBuilderImpl()
                .setRoom(room)
                .build();
        Robot robot = (Robot) robotBuilderResult.getObject();
        String position = robot.move(COMMAND_2);
        assert(StringUtils.equals(SUCCESS_MESSAGE_COMMAND_2, position));
    }
}
