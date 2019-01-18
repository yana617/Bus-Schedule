import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
    public static ArrayList<Ride> readFromFile(String fileName){
        ArrayList<Ride> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNext()) {
                String str = sc.nextLine();
                String[]busInfo = str.split(" ");
                if(busInfo.length!=3) throw new Error("What is this? Not 3 things around space?");
                Ride ride = new Ride(busInfo[0], busInfo[1], busInfo[2]);
                list.add(ride);
            }
            sc.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return list;
    }
    public static void writeToFile(String fileName, ArrayList<Ride> list){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            boolean i = true;
            for(Ride ride: list){
                if(ride.getStatus()) {
                    if(i && ride.getCompany() == Company.Grotty) {
                        out.newLine();
                        i = false;
                    }
                    out.write(ride.toString());
                    out.newLine();
                }
            }
            out.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    public static void main(String[]args){
        ArrayList<Ride> list = readFromFile("input.txt");

        for (int i=0;i<list.size();i++) {
            if(list.get(i).getStatus()) {
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getStatus() && i != j) {
                        if (list.get(i).isBetter(list.get(j))) {
                            list.get(j).setStatus(false);
                        }
                    }
                }
            }
        }

        Collections.sort(list);
        writeToFile("output.txt", list);
    }
}