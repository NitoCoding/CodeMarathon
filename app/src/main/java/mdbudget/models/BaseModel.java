package mdbudget.models;

public abstract class BaseModel {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BaseModel(int id){
        this.id = id;
    }

    public BaseModel(){};

    public abstract void displayInfo();
}
