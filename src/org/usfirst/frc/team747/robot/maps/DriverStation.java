package org.usfirst.frc.team747.robot.maps;

public enum DriverStation {
    JOYSTICK,
    GAMEPAD;

    public enum Controller {
        DRIVER_LEFT(0),
        DRIVER_RIGHT(1),
        OPERATOR(2);

        private int value;

        private Controller(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Logitech Extreme 3D Pro Joystick Mapping
     */
    public enum Joystick {
        // Button Addresses
        BUTTON_1(1),
        BUTTON_2(2),
        BUTTON_3(3),
        BUTTON_4(4),
        BUTTON_5(5),
        BUTTON_6(6),
        BUTTON_7(7),
        BUTTON_8(8),
        BUTTON_9(9),
        BUTTON_10(10),
        BUTTON_11(11),
        BUTTON_12(12),

        // Axis Addresses
        AXIS_X(0),
        AXIS_Y(1),
        AXIS_Z(2),
        AXIS_THROTTLE(3),
        AXIS_HAT_X(4),
        AXIS_HAT_Y(5);

        private int value;

        private Joystick(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * "Controller Gamepad (F310)" Controller Mapping
     */
    public enum GamePad {
        // Button Addresses
        BUTTON_A(1),
        BUTTON_B(2),
        BUTTON_X(3),
        BUTTON_Y(4),
        BUTTON_LB(5),
        BUTTON_RB(6),
        BUTTON_BACK(7),
        BUTTON_START(8),
        STICK_LEFT(9),
        STICK_RIGHT(10),

        // Axis Addresses
        AXIS_LEFT_X(0),
        AXIS_LEFT_Y(1),
        TRIGGER_LEFT(2),
        TRIGGER_RIGHT(3),
        AXIS_RIGHT_X(4),
        AXIS_RIGHT_Y(5);

        private int value;

        private GamePad(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
