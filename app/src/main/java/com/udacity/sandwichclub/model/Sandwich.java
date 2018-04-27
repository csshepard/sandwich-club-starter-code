package com.udacity.sandwichclub.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.udacity.sandwichclub.utils.JsonUtils.parseStringJSONArray;

public class Sandwich {

    private String mainName;
    private List<String> alsoKnownAs = null;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
    }

    public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients) {
        this.mainName = mainName;
        this.alsoKnownAs = alsoKnownAs;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
    }

    public Sandwich(JSONObject json) {
        try {
            if ( json.has("name") ) {
                JSONObject nameObject = json.getJSONObject("name");
                if ( nameObject.has("mainName") ) {
                    this.mainName = nameObject.getString("mainName");
                }
                if ( nameObject.has("alsoKnownAs") ) {
                    JSONArray akaArray = nameObject.getJSONArray("alsoKnownAs");
                    this.alsoKnownAs = parseStringJSONArray(akaArray);
                }
            }
            if ( json.has("placeOfOrigin") ) {
                this.placeOfOrigin = json.getString("placeOfOrigin");
            }
            if ( json.has("description") ) {
                this.description = json.getString("description");
            }
            if ( json.has("image") ) {
                this.image = json.getString("image");
            }
            if ( json.has("ingredients") ) {
                JSONArray ingredientsArray = json.getJSONArray("ingredients");
                this.ingredients = parseStringJSONArray(ingredientsArray);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
