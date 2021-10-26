import java.util.ArrayList;
import java.util.Scanner;

class player {
    private String name;
    private int points;
    private int location;

    public void setname(String n) {
        name = n;
    }

    public String get_name() {
        return name;
    }

    public int get_points() {
        return points;
    }

    public void updatepoint(int p) {
        points = points + p;
    }

    public int get_location() {
        return location;
    }

    public void updatelocation(int l) {// update is better so you can actually
        location = location + l;// l can be actua
    }

    public void player() {// for player take input separately and pass ip as arg
        points = 0;
        location = 0;
        // setname(n);
    }
}

class snake {
    private int point_deduction;
    private int location_deduction;

    public int get_point_deduction() {
        return this.point_deduction;
    }

    public int get_location_deduction() {
        return this.location_deduction;
    }

    public void set(int pd, int ld) {
        this.point_deduction = pd;
        this.location_deduction = ld;
    }

    public snake() {
        this.point_deduction = -2;
        this.location_deduction = -4;
    }

}

class KingCobra extends snake {
    private int point_deduction;
    private int location_deduction;

    @Override
    public int get_point_deduction() {
        return this.point_deduction;
    }

    public int get_location_deduction() {
        return this.location_deduction;
    }

    public void set(int pd, int ld) {
        this.point_deduction = pd;
        this.location_deduction = ld;
    }

    public KingCobra() {
        this.point_deduction = -4;
        this.location_deduction = -8;
    }
}

class ladder {
    private int point_given;
    private int location_bonus;

    public int get_point_awarded() {
        return this.point_given;
    }

    public int get_location_awarded() {
        return this.location_bonus;
    }

    public void set(int pg, int lg) {
        this.point_given = pg;
        this.location_bonus = lg;
    }

    public ladder() {
        this.point_given = 2;
        this.location_bonus = 4;
    }
}

class elevator extends ladder {
    private int point_given;
    private int location_bonus;

    @Override
    public int get_point_awarded() {
        return this.point_given;
    }

    public int get_location_awarded() {
        return this.location_bonus;
    }

    public void set(int pg, int lg) {
        this.point_given = pg;
        this.location_bonus = lg;
    }

    public elevator() {
        this.point_given = 4;
        this.location_bonus = 8;
    }
}

class floor {
    private int type;// 0 is empty, 1 is snake ,2 is KC, 3 is ladder ,4 is elevator;
    private int location;
    private int update_for_location;// how much do we need to chnage location
    private int points_given;

    public void settype(int t) {
        type = t;
    }

    public void setlocation(int l) {
        location = l;
    }

    public int get_location() {
        return location;
    }

    public int get_update_location() {
        return update_for_location;
    }

    public int get_points_given() {
        return points_given;
    }

    public int get_type() {
        return type;
    }

    public void setpoints_given() {// just setting up the mat nothing else
        if (type == 0) {
            points_given = 1;// we need to call setpoint after input for type
            update_for_location = 0;
        } else if (type == 1) {
            snake sn = new snake();
            points_given = sn.get_point_deduction();
            update_for_location = sn.get_location_deduction();
        } else if (type == 2) {
            KingCobra kc = new KingCobra();
            points_given = kc.get_point_deduction();
            update_for_location = kc.get_location_deduction();
        } else if (type == 3) {
            ladder la = new ladder();
            points_given = la.get_point_awarded();
            update_for_location = la.get_location_awarded();
        } else if (type == 4) {
            elevator el = new elevator();
            points_given = el.get_point_awarded();
            update_for_location = el.get_location_awarded();
        } else {

            points_given = 0;
            update_for_location = 0;
        }

    }

    // can be used to update stuff
    // but for now i will hardcode the mat
}

public class game {
    static ArrayList<floor> flr = new ArrayList<floor>();
    // static dice d = new dice();

    static public int getface() {
        return ((int) ((Math.random() * (2)) + 1));
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 14; i++) {// sets floor
            flr.add(new floor());

            if (i == 2) {
                flr.get(flr.size() - 1).settype(4);
            } else if (i == 5) {
                flr.get(flr.size() - 1).settype(1);
            } else if (i == 8) {
                flr.get(flr.size() - 1).settype(3);
            } else if (i == 11) {
                flr.get(flr.size() - 1).settype(2);
            } else if (i == 0) {
                flr.get(flr.size() - 1).settype(-1);
            } else {
                flr.get(flr.size() - 1).settype(0);
            }
            flr.get(flr.size() - 1).setlocation(i);
            flr.get(flr.size() - 1).setpoints_given();

        }
        player p = new player();
        System.out.println("Enter player name and hit Enter");
        p.setname(scan.nextLine());
        System.out.println("The game setup is ready!");
        int n = 0;
        int game_start = 0;
        int count = 0;

        while (n == 0) {
            int dise = 0;// dice result
            System.out.println("Hit enter to roll a dice");
            String s = scan.nextLine();
            if (s.equals("")) {
                dise = getface();
                System.out.println("The dice gave " + dise);
                if (p.get_location() == 12 && dise == 2) {
                    System.out.println("Player cannot move!");
                    continue;
                }
                if (((game_start == 0) && dise != 1)) {
                    System.out.println("The game cannot start untill dice gives 1 !");
                    continue;
                } else {
                    count++;

                    game_start = 1;
                    if (count == 1) {
                        p.updatelocation(0);
                        // p.updatepoint(1);
                        System.out.println("Player position floor: 0");
                        System.out.println(p.get_name() + " has reached an Empty Floor!");
                        p.updatepoint(1);
                        // p.updatepoint(flr.get(p.get_location()).get_points_given());// floor se
                        // points leliye
                        System.out.println("Total points: " + p.get_points());
                        continue;
                    } else {
                        p.updatelocation(dise);

                    }

                    System.out.println("Player position floor: " + p.get_location());

                    if (flr.get(p.get_location()).get_type() == 0) {
                        System.out.println(p.get_name() + " has reached an Empty Floor!");

                        p.updatepoint(flr.get(p.get_location()).get_points_given());// floor se points leliye
                        System.out.println("Total points: " + p.get_points());

                    } else if (flr.get(p.get_location()).get_type() == 1) {
                        System.out.println(p.get_name() + " has reached a Snake Floor!");
                        p.updatepoint(flr.get(p.get_location()).get_points_given());// floor se points leliye
                        System.out.println("Total points: " + p.get_points());

                        p.updatelocation(flr.get(p.get_location()).get_update_location());

                        System.out.println("Player position Floor : " + p.get_location());
                        System.out.println(p.get_name() + " has reached an Empty Floor");
                        p.updatepoint(flr.get(p.get_location()).get_points_given());// floor se points leliye
                        System.out.println("Total points: " + p.get_points());

                        p.updatelocation(flr.get(p.get_location()).get_update_location());
                        // System.out.println("Total points : "+p.get_points());

                    } else if (flr.get(p.get_location()).get_type() == 2) {
                        System.out.println(p.get_name() + " has reached a King Cobra Floor!");
                        p.updatepoint(flr.get(p.get_location()).get_points_given());// floor se points leliye
                        System.out.println("Total points: " + p.get_points());

                        p.updatelocation(flr.get(p.get_location()).get_update_location());

                        System.out.println("Player position Floor : " + p.get_location());
                        System.out.println(p.get_name() + " has reached an Empty Floor");
                        p.updatepoint(flr.get(p.get_location()).get_points_given());// floor se points leliye
                        System.out.println("Total points: " + p.get_points());

                        p.updatelocation(flr.get(p.get_location()).get_update_location());
                    }

                    else if (flr.get(p.get_location()).get_type() == 3) {
                        System.out.println(p.get_name() + " has reached a Ladder Floor!");
                        p.updatepoint(flr.get(p.get_location()).get_points_given());// floor se points leliye
                        System.out.println("Total points: " + p.get_points());

                        p.updatelocation(flr.get(p.get_location()).get_update_location());

                        System.out.println("Player position Floor : " + p.get_location());
                        System.out.println(p.get_name() + " has reached an Empty Floor");
                        p.updatepoint(flr.get(p.get_location()).get_points_given());// floor se points leliye
                        System.out.println("Total points: " + p.get_points());

                        p.updatelocation(flr.get(p.get_location()).get_update_location());
                    }

                    else if (flr.get(p.get_location()).get_type() == 4) {
                        System.out.println(p.get_name() + " has reached an Elevator Floor!");
                        p.updatepoint(flr.get(p.get_location()).get_points_given());// floor se points leliye
                        System.out.println("Total points: " + p.get_points());

                        p.updatelocation(flr.get(p.get_location()).get_update_location());

                        System.out.println("Player position Floor : " + p.get_location());
                        System.out.println(p.get_name() + " has reached an Empty Floor");
                        p.updatepoint(flr.get(p.get_location()).get_points_given());// floor se points leliye
                        System.out.println("Total points: " + p.get_points());

                        p.updatelocation(flr.get(p.get_location()).get_update_location());
                    }

                }
                if (p.get_location() == 13) {
                    System.out.println("Game Over!");
                    break;
                }

            } else {
                continue;
            }
        }

    }
}
