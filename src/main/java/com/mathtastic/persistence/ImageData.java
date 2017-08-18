package com.mathtastic.persistence;

import com.mathtastic.entity.Image;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Accesses image data in the database.
 * Created by Holli on 7/14/2017.
 */
public class ImageData {

    /**
     * Gets an image by id.
     * @param id The image id.
     * @return The image.
     */
    public Image getImageById(int id) {
        String sql = "SELECT * FROM AppleImage WHERE AppleImageId = " + id + ";";
        return sendQuery(sql);
    }

    /**
     * Sends user related queries to the SQLData class.
     * @param sql The select statement.
     * @return The image requested.
     */
    private Image sendQuery(String sql) {
        Database database = Database.getInstance();
        Image image = null;
        SQLData query = new SQLData();

        try {
            ResultSet results = query.executeQuery(sql);
            if (results.next()) {
                image = createQuestionFromResults(results);
            }
            database.disconnect();

        } catch (SQLException e) {
            System.out.println("Image.getImageById()...SQLException: " + e);
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("Image.getImageId()...Exception: " + e);
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Creates image information from query results.
     * @param results The query results.
     * @return The image requested.
     * @throws SQLException Results do not match.
     */
    private Image createQuestionFromResults(ResultSet results) throws SQLException {
        return new Image(results.getInt("AppleImageId"), results.getString("Name"));
    }
}
