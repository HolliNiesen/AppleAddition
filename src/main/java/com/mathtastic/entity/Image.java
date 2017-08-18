package com.mathtastic.entity;

/**
 * Image represents an image used in the game.
 * Created by Holli on 7/14/2017.
 */
public class Image {
    private int appleImageId;
    private String name;

    /**
     * Empty constructor
     */
    public Image() {}

    /**
     * Constructor
     * @param appleImageId the image id in the database
     * @param name the name of the image file
     */
    public Image(int appleImageId, String name) {
        this.appleImageId = appleImageId;
        this.name = name;
    }

    /**
     * Gets the image id in the database
     * @return the image id
     */
    public int getAppleImageId() {
        return appleImageId;
    }

    /**
     * Gets the name of the image file
     * @return the name of the file
     */
    public String getName() {
        return name;
    }
}
