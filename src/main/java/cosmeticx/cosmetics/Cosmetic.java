/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.cosmetics;

import cosmeticx.utils.Image;

public class Cosmetic {

    private String id;
    private String name;
    private String display_name;
    private Image image;
    private boolean isPublic;

    public Cosmetic(String name, String display_name, String id, CosmeticState state, Image image) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.display_name = display_name;
        this.isPublic = state == CosmeticState.PUBLIC;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return display_name;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public Image getImage() { return image; }

    public boolean isSlot() {
        return !isPublic;
    }
}
