package cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro;

import androidx.annotation.DrawableRes;

public class Card {
    private boolean isHidden;
    private int value;
    @DrawableRes
    private int image;

    public Card(boolean isHidden, int value, @DrawableRes int image) {
        this.isHidden = isHidden;
        this.value = value;
        this.image = image;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @DrawableRes
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
