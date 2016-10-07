package helper.develop.android.androiddevelophelper.insertcalllog;

public class CallLogInfo {

    private String number;
    private String name;
    private String duration;
    private String type;

    public CallLogInfo() {
    }

    public CallLogInfo(String number, String name, String duration, String type) {
        this.number = number;
        this.name = name;
        this.duration = duration;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CallLogInfo{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
