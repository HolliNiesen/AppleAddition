package com.mathtastic.persistence;

import com.mathtastic.entity.Image;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by Holli on 7/18/2017.
 */
public class ImageDataTest {

    @Test
    public void getImageById() throws Exception {
        ImageData imageData = new ImageData();
        Image image = imageData.getImageById(1);

        System.out.println(image.getName());
        assertNotNull(imageData);
        assertEquals("Image name was not correct", "Apple00.png", image.getName());
    }
}
