import java.util.regex.Pattern;
import java.util.regex.Matcher;

enum Company { Grotty, Posh };

public class Ride implements Comparable<Ride>{
    private Company company;
    private int timeFrom;
    private int timeTo;
    private String time;
    private boolean status;

    public Ride(String inputCompany, String timeFrom, String timeTo){
        if(inputCompany.equals("Posh")) {
            this.company = Company.Posh;
        } else if(inputCompany.equals("Grotty")){
            this.company = Company.Grotty;
        } else throw new Error("what is this company? " + inputCompany);
        this.timeFrom = makeNumber(timeFrom);
        this.timeTo = makeNumber(timeTo);
        this.time = timeFrom + " " + timeTo;
        if((this.timeTo-this.timeFrom)>60) this.status = false;
        else this.status = true;
    }

    private int makeNumber(String time){
        String[]minSec = time.split(":");
        if(minSec.length!=2) throw new Error("Time is not mm:ss?");
        if(!isNumber(minSec[0])||!isNumber(minSec[1])) throw new Error("Time is not numbers only?");
        return Integer.parseInt(minSec[0])*60+Integer.parseInt(minSec[1]);
    }

    private boolean isNumber(String line){
        Pattern p = Pattern.compile("^[\\d]+$");
        Matcher m = p.matcher(line);
        return m.matches();
    }

    public boolean isBetter(Ride ride){
        if(this.timeFrom == ride.timeFrom&&this.timeTo==ride.timeTo&&this.company==Company.Posh) return true;
        if(this.timeFrom==ride.timeFrom && this.timeTo<ride.timeTo) return true;
        if(this.timeTo == ride.timeTo && this.timeFrom>ride.timeFrom) return true;
        if(this.timeFrom>ride.timeFrom&&this.timeTo<ride.timeTo) return true;
        return false;
    }

    public boolean getStatus(){
        return this.status;
    }
    public void setStatus(boolean status){
        this.status = status;
    }

    public Company getCompany(){ return this.company; }

    @Override
    public String toString() {
        return this.company+" "+this.time;
    }

    @Override
    public int compareTo(Ride other){
        int com = other.company.compareTo(this.company);
        return com == 0 ? this.timeFrom-other.timeFrom : com;
    }
}