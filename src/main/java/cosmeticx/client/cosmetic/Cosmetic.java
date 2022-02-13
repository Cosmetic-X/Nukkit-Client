package cosmeticx.client.cosmetic;

public class Cosmetic {

    public String id;
    public String name;
    public String display_name;
    private boolean isPublic;

    public Cosmetic(String name, String display_name, String id, CosmeticState state) {
        this.id = id;
        this.name = name;
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

    public boolean isSlot() {
        return !isPublic;
    }
}
enum CosmeticState {
    PUBLIC,
    SLOT
}
