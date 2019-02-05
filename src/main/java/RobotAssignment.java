import impl.Robot;
import impl.Room;
import impl.builder.BuilderResultImpl;
import impl.robot.RobotImpl;
import impl.room.RoomCircle;
import impl.room.RoomSquare;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RobotAssignment {

    final static Logger LOG = LoggerFactory.getLogger(RobotAssignment.class);
    private static final String COMMAND_1 = "HGHGGHGHG";
    private static final String COMMAND_2 = "RRFLFFLRF";
    private static int RADIUS = 10;

    public static void main(String args[]){

        //Example one test
        Room squareRoom = new RoomSquare(1,2,5);
        BuilderResultImpl<Robot> robotBuilderResult = new RobotImpl.RobotBuilderImpl()
                .setRoom(squareRoom)
                .build();
        Robot robot = (Robot) robotBuilderResult.getObject();
        String position1 = robot.move(COMMAND_1);
        LOG.info("Result of out command: " + COMMAND_1 + " in a square room is, " +position1 + "\n");
        //Example two test
        Room circularRoom = new RoomCircle(RADIUS);
        robotBuilderResult = new RobotImpl.RobotBuilderImpl()
                .setRoom(circularRoom)
                .build();
        robot = (Robot) robotBuilderResult.getObject();
        String position2 = robot.move(COMMAND_2);
        LOG.info("Result of out command: " + COMMAND_2 + " in a circular room is, " + position2);

    }
}
