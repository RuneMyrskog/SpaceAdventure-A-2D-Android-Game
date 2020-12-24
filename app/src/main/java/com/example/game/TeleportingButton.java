package com.example.game;

class TeleportingButton extends GameObject {

    /**
     * Constructs a new button object
     * @param xCoordinate horizontal component of the buttons position
     * @param yCoordinate vertical component of the buttons position
     * @param radius      size of the button from its center to its outer edge
     */
    TeleportingButton(int xCoordinate, int yCoordinate, int radius) {
        super(xCoordinate, yCoordinate, radius);
    }

    @Override
    void update() {
        // intentionally left empty. Teleporting button takes no action upon update command.
    }
}
