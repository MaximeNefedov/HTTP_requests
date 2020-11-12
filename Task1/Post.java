package Task1;

public class Post {
    private String _id;
    private String text;
    private String type;
    private Object user;
    private int upvotes;
    private String userUpvotes;

    public Post(String _id, String text, String type, String user, int upvotes, String userUpvotes) {
        this._id = _id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
        this.userUpvotes = userUpvotes;
    }

    @Override
    public String toString() {
        return "id is: " + _id + "\ntext is: " + text + "\ntype is: " + type
                + "\nuser is: " + user + "\nunvotes is: " + upvotes + "\nuserUpvotes is: " + userUpvotes;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public String getUserUpvotes() {
        return userUpvotes;
    }

    public void setUserUpvotes(String userUpvotes) {
        this.userUpvotes = userUpvotes;
    }
}
