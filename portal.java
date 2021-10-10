package AP;

import java.util.ArrayList;
import java.util.Scanner;

class Vaccine {
    private String name;
    private int doses;// num od doses
    private final int gap;

    public Vaccine(String s, int d, int g) {// constructor //ip method
        name = s;
        doses = d;
        gap = g;
    }

    public int getdoses() {
        return doses;
    }

    public int getgap() {
        return gap;
    }

    public String getname() {
        return name;
    }

    public void verify() {// for outpur message at the end of add vaccine //op method
        System.out.println("Vaccine Name: " + name + ", Number of Doses: " + doses + ",Gap Between Doses: " + gap);
    }
}

class Hospitals {
    private final int uniqID;
    private final String name;
    private final String pincode;

    public int checkDuplicates(String n, String dpc) {
        if (name.equals(n) && pincode.equals(dpc)) {
            return 1;
        } else {
            return 0;
        }
    }

    public Hospitals(int uid, String n, String pc) {
        uniqID = uid;
        name = n;
        pincode = pc;
    }

    public int getid() {
        return uniqID;
    }

    public String getpc() {
        return pincode;
    }

    public void sbAreaInfo() {
        System.out.println(uniqID + " " + name);
    }

    public void verify() {// for outpur message at the end //op method
        System.out.println("Hospital Name: " + name + ", PinCode: " + pincode + ", Unique ID: " + uniqID);
    }
}

class Citizen {
    private String name;
    private int age;
    private final String id;
    private String status;
    private int doses;// num of doses taken
    private String vac;// which vaccine
    private int due;// next vaccine due date

    public int checkDuplicates(String s) {
        if (id.equals(s)) {
            return 1;
        } else {
            return 0;
        }
    }

    public Citizen(String n, int a, String idn) {
        name = n;
        age = a;
        id = idn;
        status = "REGISTERED";
        doses = 0;
    }

    public int checkpid(String s) {
        if (s.equals(id)) {
            return 1;
        }
        return 0;
    }

    public void statusUpdate(String vname, Vaccine v) {
        status = "PARTIALLY VACCINATED";
        vac = vname;
        doses++;
        if (doses == v.getdoses()) {
            status = "FULLY VACCINATED";

        }
        // auto update till vaccinated wala mod
    }

    public int checkStatus() {
        if (status.equals("REGISTERED")) {
            return 1;
        } else if (status.equals("PARTIALLY VACCINATED")) {
            return 2;
        } else {
            return 3;
        }

    }

    public void displayStatus() {
        System.out.println(status);
        System.out.println("Vaccine Given: " + vac);
        System.out.println("Number of Doses given: " + doses);
        if (status.equals("PARTIALLY VACCINATED")) {
            System.out.println("Next Dose due Date: " + due);
        }
    }

    public void vacmsg() {
        System.out.println(name + " vaccinated with " + vac);
    }

    public void setdue(int duedate) {
        due = duedate;
    }

    public int getdue() {
        return due;
    }

    public String getvac() {
        return vac;
    }

    public void verify() {// for outpur message at the end //op method
        System.out.println("Citizen Name: " + name + ", Age: " + age + ", Unique ID: " + id);
    }

}

class Slots {
    private int hID;
    private int dnum;// day number
    private int quan;// quantity
    private String vacName;// name of vaccine
    private String pincode;
    private int vindex;

    public Slots(int h, int d, int q, String vn, String pc, int vi) {
        hID = h;
        dnum = d;
        quan = q;
        vacName = vn;
        pincode = pc;
        vindex = vi;
    }

    public void modify() {
        quan--;// check if quan not less than 0
    }

    public int gethid() {
        return hID;
    }

    public String getpc() {
        return pincode;
    }

    public int getday() {
        return dnum;
    }

    public int getvindex() {
        return vindex;
    }

    public int checkquan() {
        if (quan == 0) {
            return 0;
        }
        return 1;
    }

    public void slotInfo() {
        System.out.println("Day: " + dnum + " Vaccine: " + vacName + " Available Qty: " + quan);
    }

    public void verify() {// for outpur message at the end //op method
        System.out.println(" Slot added by Hospital " + hID + " for Day: " + dnum + ", Available Quantity: " + quan
                + " of Vaccine " + vacName);
    }

    // Slot added by Hospital 111111 for Day: 3, Available Quantity: 10 of Vaccine
    // Covax
}

public class portal {
    // static Vaccine vacs[] =new Vaccine[50];

    // kind of databse for all classes
    static int count = 1;
    static ArrayList<Vaccine> vac = new ArrayList<Vaccine>();
    static ArrayList<Hospitals> hos = new ArrayList<Hospitals>();
    static ArrayList<Slots> slot = new ArrayList<Slots>();
    static ArrayList<Citizen> cit = new ArrayList<Citizen>();

    static int uniqIdGenerator() {
        count++;
        return 101010 + count;
    }

    static int checkHID(int hid) {// returns index at which match found else 0
        for (int i = 0; i < hos.size(); i++) {
            if (hid == hos.get(i).getid()) {
                // System.out.println(hos.get(i).getid());
                return i;
            }
        }
        return -1;
    }

    static void sbArea(String pid) {// search by area

        // searching for slots nearby

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Pincode: ");
        String str = scan.nextLine();
        if (str.length() != 6) {
            System.out.println("Pincode length should be 6 digits!");
            return;
        }
        // for(int i=0;i<slot.size();i++){//for pc check
        // if(slot.get(i).getpc().equals(str)){

        // hos.get(checkHID(slot.get(i).gethid())).sbAreaInfo();
        // }
        // }
        int flag = 0;

        // displaying found slots

        for (int i = 0; i < hos.size(); i++) {// for pc check
            if (hos.get(i).getpc().equals(str)) {
                flag++;
                hos.get(checkHID(slot.get(i).gethid())).sbAreaInfo();
            }
        }

        if (flag == 0) {
            System.out.println("No Hospital Found with Given Pincode !!");
            return;
        }

        System.out.println("Enter Hospital ID: ");
        int hosid = scan.nextInt();
        if (hosid < 101010) {
            System.out.println("Incorrect hospital ID");
            return;
        }
        for (int i = 0; i < slot.size(); i++) {// for finding possible slots with matching hid
            if (slot.get(i).gethid() == hosid) {
                System.out.print(i + " ");// here i is the index at which we found hid
                slot.get(i).slotInfo();
            }

        }

        System.out.println("Choose Slot: ");
        int opt = scan.nextInt();// now we know opt is the index
        if (opt > slot.size() - 1) {
            System.out.println("Incorrect Input");
            return;
        }
        // if correct index
        // search citizen by pid and check if earlier vaccinated or not
        // add to citizens info if quan not 0 for chosen slot
        if (slot.get(opt).checkquan() != 0) {
            // citizen check
            for (int m = 0; m < cit.size(); m++) {
                if (cit.get(m).checkpid(pid) == 1) {
                    // update this citizens profile
                    if (cit.get(m).checkStatus() == 1) {
                        cit.get(m).statusUpdate(vac.get(slot.get(opt).getvindex()).getname(),
                                vac.get(slot.get(opt).getvindex()));
                        slot.get(opt).modify();
                        cit.get(m).setdue(slot.get(opt).getday() + vac.get(slot.get(opt).getvindex()).getgap());
                        cit.get(m).vacmsg();
                    } else if (cit.get(m).checkStatus() == 3) {
                        System.out.println(" Already Completely Vaccinated ,No further doses needed !");
                    } else {
                        if (cit.get(m).getdue() > slot.get(opt).getday()) {
                            System.out.println("Cannot be vaccinated before due date!");
                        } else {

                            if (cit.get(m).getvac().equals(vac.get(slot.get(opt).getvindex()).getname())) {/// no mix
                                cit.get(m).statusUpdate(vac.get(slot.get(opt).getvindex()).getname(),
                                        vac.get(slot.get(opt).getvindex()));
                                slot.get(opt).modify();
                                cit.get(m).setdue(slot.get(opt).getday() + vac.get(slot.get(opt).getvindex()).getgap());
                                cit.get(m).vacmsg();
                            } else {
                                System.out.println("Cannot Choose different vaccine once Partially Vaccinated!");
                                return;
                            }
                        }
                    }

                }
            }

        } else {
            System.out.println("No Vaccine doses available for chosen vaccine name and slot!");
        }
        // remove vaccine from slot

    }

    static void sbVaccine(String pid) {// search by vaccine
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Vaccine name: ");
        String str = scan.nextLine();
        int flag = 0;
        for (int i = 0; i < slot.size(); i++) {// for pc check
            if (vac.get(slot.get(i).getvindex()).getname().equals(str)) {
                flag++;
                if (slot.get(i).checkquan() != 0) {
                    hos.get(checkHID(slot.get(i).gethid())).sbAreaInfo();
                }
            }
        }
        if (flag == 0) {
            System.out.println("No Hospital Found with Given Vaccine name!!");
            return;
        }
        // for(int i=0;i<hos.size();i++){//for pc check
        // if(hos.get(i).getpc().equals(str)){

        // hos.get(checkHID(slot.get(i).gethid())).sbAreaInfo();
        // }
        // }

        System.out.println("Enter Hospital ID: ");
        int hosid = scan.nextInt();
        if (hosid < 101010) {
            System.out.println("Incorrect hospital ID");
            return;
        }
        for (int i = 0; i < slot.size(); i++) {// for finding possible slots with matching hid
            if (slot.get(i).gethid() == hosid && vac.get(slot.get(i).getvindex()).getname().equals(str)) {
                System.out.print(i + " ");// here i is the index at which we found hid
                slot.get(i).slotInfo();
            }

        }
        System.out.println("Choose Slot: ");
        int opt = scan.nextInt();// now we know opt is the index
        if (opt > slot.size() - 1) {
            System.out.println("Incorrect Input");
            return;
        }
        // if correct index
        // search citizen by pid
        // add to citizens info if quan not 0 for chosen slot
        if (slot.get(opt).checkquan() != 0) {

            for (int m = 0; m < cit.size(); m++) {
                if (cit.get(m).checkpid(pid) == 1) {
                    // update this citizens profile

                    if (cit.get(m).checkStatus() == 1) {
                        cit.get(m).statusUpdate(vac.get(slot.get(opt).getvindex()).getname(),
                                vac.get(slot.get(opt).getvindex()));
                        slot.get(opt).modify();
                        cit.get(m).setdue(slot.get(opt).getday() + vac.get(slot.get(opt).getvindex()).getgap());
                        cit.get(m).vacmsg();
                    } else if (cit.get(m).checkStatus() == 3) {
                        System.out.println(" Already Completely Vaccinated ,No further doses needed !");
                    } else {
                        if (cit.get(m).getdue() > slot.get(opt).getday()) {
                            System.out.println("Cannot be vaccinated before due date!");
                        } else {

                            if (cit.get(m).getvac().equals(vac.get(slot.get(opt).getvindex()).getname())) {/// no mix
                                cit.get(m).statusUpdate(vac.get(slot.get(opt).getvindex()).getname(),
                                        vac.get(slot.get(opt).getvindex()));
                                slot.get(opt).modify();
                                cit.get(m).setdue(slot.get(opt).getday() + vac.get(slot.get(opt).getvindex()).getgap());
                                cit.get(m).vacmsg();
                            } else {
                                System.out.println("Cannot Choose different vaccine once Partially Vaccinated!");
                                return;
                            }
                        }
                    }

                    // cit.get(m).statusUpdate(vac.get(slot.get(opt).getvindex()).getname(),
                    // vac.get(slot.get(opt).getvindex()));
                    // slot.get(opt).modify();
                    // cit.get(m).setdue(slot.get(opt).getday() +
                    // vac.get(slot.get(opt).getvindex()).getgap());
                    // cit.get(m).vacmsg();
                }
            }

        } else {
            System.out.println("No Vaccine doses available for chosen vaccine name and slot!");
        }
        // remove vaccine from slot

    }

    // functions for main class
    static void addVaccine() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Vaccine Name: ");
        String str = scan.nextLine();
        System.out.print("Number of Doses: ");
        int dose = scan.nextInt();
        int gap;// dose 0 and busshit wala error for later
        if (dose == 1) {
            gap = 0;
        } else {
            System.out.print("Gap between Doses: ");
            gap = scan.nextInt();
        }
        for (int i = 0; i < vac.size(); i++) {
            if (vac.get(i).getname().equals(str)) {
                System.out.println("Vaccine Already added!");
                return;
            }
        }
        vac.add(new Vaccine(str, dose, gap));
        vac.get(vac.size() - 1).verify();
        // System.out.println(str);
        /*
         * so basically main function calls add vax and it takesinput and stores in in
         * array list of vaccine details
         */

    }

    static void registerHospital() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Hospital Name: ");
        String str = scan.nextLine();
        System.out.print("Pincode: ");
        String pincode = scan.nextLine();
        if (pincode.length() != 6) {
            System.out.println("Pincode length should be 6 digits!");
            return;
        }
        for (int i = 0; i < hos.size(); i++) {
            if (hos.get(i).checkDuplicates(str, pincode) == 1) {
                System.out.println("Hospital Already Registered!");
                return;
            }
        }
        int uid = uniqIdGenerator();
        hos.add(new Hospitals(uid, str, pincode));
        hos.get(hos.size() - 1).verify();
        // just a simple add to database and verify program.

    }

    static void registerCitizen() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Citizen Name: ");
        String str = scan.nextLine();
        System.out.print("Age: ");
        int a = scan.nextInt();
        System.out.print("Unique ID: ");
        String uid = scan.next();
        if (uid.length() != 12) {
            System.out.println("Unique user ID should be of 12 Digits!");
            return;
        }
        for (int i = 0; i < cit.size(); i++) {
            if (cit.get(i).checkDuplicates(uid) == 1) {
                System.out.println("Citizen Already Registered with unique ID!");
                return;
            }
        }
        cit.add(new Citizen(str, a, uid));
        cit.get(cit.size() - 1).verify();
        if (a < 19) {
            cit.remove(cit.size() - 1);
            System.out.println("Only above 18 are allowed");
        }

    }

    // cannot repeat vaccine details once set wala error
    static void createSlots() {
        // regiser number of slots here as we dont need them in slot class
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Hospital ID: ");
        int id = scan.nextInt();
        if (id < 101010) {
            System.out.println("Incorrect hospital ID");
            return;
        }
        int flag = 0;
        for (int i = 0; i < hos.size(); i++) {
            if (hos.get(i).getid() == id) {
                flag++;
            }
        }
        if (flag == 0) {
            System.out.println("Hospital NOT Registered on Portal!!");
            return;
        }
        // System.out.println(checkHID(id));
        // System.out.println(id);

        String pc = hos.get(checkHID(id)).getpc();// added pc
        // check hospital id
        // func to check id correcteness using brute force
        if (checkHID(id) != -1) {// return index at which found matching hid
            System.out.print("Enter number of Slots to be added: ");
            int snum = scan.nextInt();
            for (int i = 0; i < snum; i++) {
                System.out.print("Enter Day Number: ");
                int dnum = scan.nextInt();
                System.out.print("Enter Quantity: ");
                int q = scan.nextInt();
                System.out.println("Select Vaccine ");
                for (int j = 0; j <= vac.size() - 1; j++) {
                    System.out.println(j + ". " + vac.get(j).getname());
                }
                int vacChoice = scan.nextInt();
                if (vacChoice > vac.size() - 1) {
                    System.out.println("Incorrect Choice ! Fill Again");
                    return;
                }
                // loop back if choice of vaccine is not correct
                // assuming correct choice lets fill slots

                slot.add(new Slots(id, dnum, q, vac.get(vacChoice).getname(), pc, vacChoice));// added pc and vachoice
                                                                                              // as parameters later
                slot.get(slot.size() - 1).verify();
            }
        } else {
            System.out.println("Incorrect Hospital ID");
        }
    }

    static void bookSlots() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Patient Unique ID: ");
        String pid = scan.nextLine();// patients id
        if (pid.length() != 12) {
            System.out.println("Unique user ID should be of 12 Digits!");
            return;
        }
        int flag = 0;
        for (int i = 0; i < cit.size(); i++) {
            if (cit.get(i).checkpid(pid) == 1) {
                flag++;
            }
        }
        if (flag == 0) {
            System.out.println("Citizen NOT Registered on Portal!!");
            return;
        }

        // check if correct patient id
        System.out.println("1. Search by Area\n2. Search by Vaccine\n3. Exit");
        System.out.print("Enter option: ");
        int opt = scan.nextInt();
        if (opt == 1) {
            sbArea(pid);
        } else if (opt == 2) {
            sbVaccine(pid);
        } else if (opt == 3) {
            return;
        } else {
            System.out.println("Wrong input!");
            return;
        }
        // else wala drama if ip is dif

    }

    static void hslot() {// search by hospital id
        System.out.print("Enter Hospitals ID: ");
        Scanner scan = new Scanner(System.in);
        int hid = scan.nextInt();
        if (hid < 101010) {
            System.out.println("Incorrect hospital ID");
            return;
        }
        int flag = 0;
        if (checkHID(hid) != -1) {// checks if correct id in records
            for (int i = 0; i < slot.size(); i++) {
                if (slot.get(i).gethid() == hid) {// checks if id matches in slots
                    slot.get(i).slotInfo();
                    flag++;
                }
            }
            if (flag == 0) {
                System.out.println("No slot found for given ID!");
            }
        } else {
            System.out.println("Hospital ID NOT registered !!");
        }

    }

    static void Vstatus() {
        System.out.print("Enter Patient ID: ");
        Scanner scan = new Scanner(System.in);
        String pid = scan.nextLine();
        if (pid.length() != 12) {
            System.out.println("Unique user ID should be of 12 Digits!");
            return;
        }
        int flag = 0;
        for (int i = 0; i < cit.size(); i++) {
            if (cit.get(i).checkpid(pid) == 1) {
                cit.get(i).displayStatus();
                flag++;
            }
        }
        if (flag == 0) {
            System.out.println("Incorrect Patient ID / Patient not Registered on portal!");
        }
    }

    static void menu() {
        System.out.println(
                "1. Add Vaccine\n2. Register Hospital\n3. Register Citizen\n4. Add Slot for Vaccination\n5. Book Slot for Vaccination\n6. List all slots for a hospital\n7. Check Vaccination Status\n8. Exit\n");
    }

    static void separator() {
        System.out.println("----------------------------------------");
    }

    // main class
    public static void main(String[] args) {
        System.out.println("CoWin Portal initialized");
        separator();
        menu();
        separator();
        Scanner scan = new Scanner(System.in);

        int flag = 1;
        while (flag == 1) {
            int input = scan.nextInt();
            if (input == 1) {
                addVaccine();
            } else if (input == 2) {
                registerHospital();
            } else if (input == 3) {
                registerCitizen();
            } else if (input == 4) {
                if (vac.size() > 0) {// if hospitals empty then gives error

                    createSlots();
                } else {
                    System.out.println("No Vaccine Data Added !! Add Vaccine data first");
                    separator();
                    // menu();
                    // separator();
                    // continue;

                }
            } else if (input == 5) {
                if (slot.size() > 0) {
                    bookSlots();
                } else {
                    System.out.println("No Slot Data Added !! Add Slots data first");
                    separator();
                    // menu();
                    // separator();
                    // continue;

                }

            } else if (input == 6) {
                hslot();
            } else if (input == 7) {
                Vstatus();
            } else if (input == 8) {
                flag = 0;
                continue;
            } else {
                System.out.println("Wrong Input!");
                separator();
            }
            separator();
            menu();
            separator();

        }
        // addVaccine();//workes fine
        // addVaccine();
        // addVaccine();
        // registerHospital();
        // createSlots();
        // registerCitizen();
        // bookSlots();
        // hslot();
        // Vstatus();

        // hospital cannot be registered before adding vax
        // if same hospital is added again with same pincode then avoid providing new id
        // and show text error
        // vaccine mixing not allowed

        // uniq ids have to be of specified digits
        // slots only for registered hospitals and citizens
    }
}
