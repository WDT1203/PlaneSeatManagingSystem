import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Ticket {

    private String row;
    private int seat;
    private double price;
    Person person;

    public Ticket(String row, int seat, double price, Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public String getRow(){return this.row;}
    public int getSeat(){return this.seat;}
    public double getPrice(){return this.price;}
    public Person getPerson(){return this.person;}

    public void setRow(String row){this.row = row;}
    public void setSeat(int seat){this.seat = seat;}
    public void setPrice(double price){this.price = price;}
    public void setPerson(Person person){this.person = person;}

    public String toString(){
        return "Seat is " + row + seat + "\nPrice: " + price + "\n" + person.personInfo();
    }

    /**
     * Save each sold ticket information in a file.
     */
    public void save(){
        String filename = getRow()+getSeat()+".txt";

        while (true){
            File file = new File(filename);
            try {
                boolean fileCreated = file.createNewFile();
                if (fileCreated){
                    System.out.println("Saving file: " + filename);
                    FileWriter writer = new FileWriter(file,false);
                    writer.write("Seat: "+this.getRow()+this.getSeat()+
                                 "\nPrice: "+this.getPrice()+"\n"
                                 +this.getPerson().personInfo());
                    writer.close();
                    break;
                }
                else {
                    if (file.exists()){
                        file.delete();
                    }
                }
            }
            catch (IOException ex){
                System.out.println("Error");
            }
        }
    }
}
