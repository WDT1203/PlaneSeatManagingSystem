
public class Person {
    private String name;
    private String surname;
    private String email;

    public Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }



    public String getName(){return this.name;}
    public String getSurname(){return this.surname;}
    public String getEmail(){return this.email;}
    public void setName(String name){this.name = name;}
    public void setSurname(String surname){this.surname = surname;}
    public void setEmail(String email){this.email = email;}



    public String personInfo(){
        return "Full Name: " + name + " " + surname + "\nEmail: " + email;
    }



}
