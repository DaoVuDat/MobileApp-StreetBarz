package daovudat.finalproject.fragment;

/**
 * Created by Dao Vu Dat on 8/30/2016.
 */
public class ItemObject {

    private int screenShot;
    private String Name;
    private String Description;

    public ItemObject(int screenShot, String Name, String Description) {
        this.screenShot = screenShot;
        this.Name = Name;
        this.Description = Description;
    }

    public int getScreenShot() {
        return screenShot;
    }

    public String getMusicName() {
        return Name;
    }

    public String getMusicAuthor() {
        return Description;
    }
}