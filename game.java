package assignment4;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class CreatedException extends Exception {
    public CreatedException(String s) {
        super(s);
    }
}

class CreatedException2 extends Exception {
    public CreatedException2(String s) {
        super(s);
    }
}

class bucket {
    private int i = 0;
    private ArrayList<String> buck = new ArrayList<String>();

    public void addItems(String s) {
        buck.add(s);
    }

    public void getItems() {
        for (i = 0; i < buck.size(); i++) {
            System.out.println(buck.get(i) + "\n");
        }
    }
}

class randomString {

    private String auc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String alc = "abcdefghijklmnopqrstuvwxyz";

    private String allCharacters = alc + auc;

    public String getrString() {
        StringBuffer randomString = new StringBuffer();
        
        int count = 0;
        while (count < 4) {

            int randomIndex = (int) (Math.random() * allCharacters.length());
            randomString.append(allCharacters.charAt(randomIndex));
            count++;
        }
        return randomString.toString();
    }

}

class random {
    int min;
    int max;
    int rand;

    random(int Max, int Min) {

        min = Min;
        max = Max;

    }

    public int get_random() {
        rand = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return rand;
    }

}

class player {
    private int jumps = 5;
    private bucket b = new bucket();
    private int tile_num;

    
    public void printbucket() {
        b.getItems();
    }

    public void update(String s) {// updates entire player stat;
        if (jumps > 0) {
            jumps--;
            if (s.length() > 0) {
                b.addItems(s);
            }
        }
    }

    public void settile(int i) {
        tile_num = i;
    }

    public void play() {

        // tile_num = rand.get_random();
        System.out.println(tile_num + 1); // rand num is diff from tile num

    }

    public int get_tilenum() {
        return tile_num;
    }

}

class softToys implements Cloneable {
    private String name;

    public void setname(String s) {
        name = s;
    }

    public String getname() {
        return name;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class TileCarpet implements Cloneable {
    private ArrayList<softToys> carpet = new ArrayList<softToys>();// composition and array index is tile number
    // private softToys st = new softToys();

    public void addtoys(String s) {
        softToys st = new softToys();
        st.setname(s);
        carpet.add(st);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public softToys getToy(int i) throws CloneNotSupportedException {// i is index for array list or tile num
        // softToys sft;
        return (softToys) carpet.get(i).clone();
        // modifications needed for object cloning
    }

    // public Object clone() throws CloneNotSupportedException {
    // return super.clone();
    // }

}

class genCalc<T> {
    T ob1;
    T ob2;

    // T result;
    genCalc(T a, T b) {// basically it generates 2 random numbers and then prints them out

        ob1 = a;
        ob2 = b;
    }

    public int div() {
        try {
            return ((int) ob1 / (int) ob2);
        } catch (ArithmeticException e) {
            System.out.println("System Fault !! Zero Division Error \nYou may press -1 to win the prize!!");
        }
        return -1;
    }

    public String cat() {
        String s = (String) ob1 + (String) ob2;
        return s;
    }
    // public T geta() {
    // return (ob1);
    // }

    // public T getb() {
    // return (ob2);
    // }

}

public class game {
    public static void main(String[] args) throws CloneNotSupportedException {

        TileCarpet tc = new TileCarpet();
        player p = new player();
        random r2 = new random(4000, -4000);
        randomString rs = new randomString();
        Scanner scan = new Scanner(System.in);
        random r1 = new random(25, 0);
        // hardcode

        tc.addtoys("Teddie");
        tc.addtoys("Winnie the Pooh");
        tc.addtoys("Elephant");
        tc.addtoys("Tiger");
        tc.addtoys("Mickey Mouse");
        tc.addtoys("Donald Duck");
        tc.addtoys("Cat");
        tc.addtoys("Black Cat");
        tc.addtoys("Bear");
        tc.addtoys("White Bear");
        tc.addtoys("Rhinoceros");
        tc.addtoys("Eagle");
        tc.addtoys("Pigeon");
        tc.addtoys("Deer");
        tc.addtoys("Peakcock");
        tc.addtoys("Dog");
        tc.addtoys("Dolphin");
        tc.addtoys("Whale");
        tc.addtoys("Cow");
        tc.addtoys("Tweety");
        // System.out.println(tc.carpet.size());

        for (int i = 0; i < 5; i++) {

            int flg2 = 1;
            while (flg2 == 1) {
                System.out.println("\nHit Enter for your " + (i + 1) + "th hop:");
                int flg1 = 1;
                String sup = "-1";
                while (flg1 == 1) {
                    try {
                        sup = scan.nextLine();
                        flg1 = 0;
                    } catch (InputMismatchException e) {
                        System.err.println("INPUT ERROR");
                    }
                }

                if (sup.equals("")) {
                    p.settile(r1.get_random());
                    // p.play();
                    if (p.get_tilenum() > 19) {
                        System.out
                                .println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!\n");

                    } else {
                        if (p.get_tilenum() % 2 == 0) {// even here means odd in reality
                            int f = 1;
                            // System.out.println("check1 ");
                            while (f == 1) {
                                System.out.println("You landed on Tile " + (p.get_tilenum() + 1));
                                System.out.println("Question answer round. Integer or strings?\n");
                                String quest = scan.next();
                                if (quest.equals("string")) {
                                    // flow to tilecarpet
                                    String s1 = rs.getrString();
                                    String s2 = rs.getrString();
                                    System.out.println(s1 + "  " + s2 + "\n");
                                    genCalc<String> genStr = new genCalc(s1, s2);
                                    String result = genStr.cat();
                                    String input = scan.next();
                                    if (input.equals(result)) {
                                        // gives reward;
                                        softToys sft;
                                        try {
                                            sft = tc.getToy(p.get_tilenum());
                                        } catch (CloneNotSupportedException e) {
                                            System.out.println("Unable to Create Clone Object !!");
                                        }

                                        sft = tc.getToy(p.get_tilenum());
                                        p.update(sft.getname());
                                        // System.out.println("You landed on Tile " + (p.get_tilenum() + 1) + "\n");
                                        // softToys sft = tc.getToy(p.get_tilenum());
                                        System.out.println("You won a " + sft.getname() + " Soft toy");
                                    } else {
                                        // bhago u lost
                                        System.out.println("Incorrect answer");
                                        System.out.println("You did not win any soft toy");
                                    }

                                    f = 0;
                                } else if (quest.equals("integer")) {// random int generation wala
                                    // flow to tilecarpet
                                    int a = r2.get_random();
                                    int b = r2.get_random();
                                    System.out.println(
                                            "Calculate Integer Division of " + a + " Divided by  " + b + " :\n");
                                    genCalc<Integer> genInt = new genCalc(a, b);
                                    int result = genInt.div();
                                    int ip = 0;//
                                    int flg4 = 1;
                                    while (flg4 == 1) {
                                        try {
                                            int input = scan.nextInt();
                                            ip = input;
                                            flg4 = 0;
                                        } catch (InputMismatchException e) {
                                            scan.next();
                                            System.err.println("\nINPUT MISMATCH ERROR!! Please enter valid input!\n");

                                        }
                                    }

                                    if (ip == result) {
                                        // gives reward;
                                        softToys sft;
                                        try {
                                            sft = tc.getToy(p.get_tilenum());
                                        } catch (CloneNotSupportedException e) {
                                            System.out.println("Unable to Create Clone Object !!");
                                        }
                                        sft = tc.getToy(p.get_tilenum());
                                        p.update(sft.getname());
                                        
                                        System.out.println("You won a " + sft.getname() + " Soft toy");
                                        // p.update(sft.getname());

                                    } else {
                                        try {
                                            throw new CreatedException2("CreatedException2.0");
                                        } catch (CreatedException2 e) {
                                            System.out.println(
                                                    "\nINCORRECT ANSWER EXCEPTION !! Incorrect Answer \n\nYou didn't win any Soft toys");
                                        }
                                    }

                                    f = 0;
                                } else {
                                    try {
                                        throw new CreatedException("CreatedException");
                                    } catch (CreatedException e) {
                                        System.out.println("\nINVALID INPUT EXCEPTION !! \n\nEnter valid Input:");
                                    }
                                }

                            }

                        } else {
                            // free
                            System.out.println("You landed on Tile " + (p.get_tilenum() + 1) + "\n");
                            softToys sft;
                            try {
                                sft = tc.getToy(p.get_tilenum());
                            } catch (CloneNotSupportedException e) {
                                System.out.println("Unable to Create Clone Object !!");
                            }
                            sft = tc.getToy(p.get_tilenum());
                            System.out.println("You won a " + sft.getname() + " Soft toy");
                            p.update(sft.getname());

                            // tc.getToy(p.get_tilenum());// clonable wala kaam and uske baad update play

                        }
                    }
                    flg2 = 0;
                }
            }
        }
        System.out.println("Game Over");
        System.out.println("Soft toys won by you are:\n");
        p.printbucket();
        
    }

}
