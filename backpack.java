package AP;

import java.util.*;

interface classmaterial {
    // String name;
    // Date date_of_upload;
    // String uploaded_by;
    public void setname(String s);

    public void set_upload_date();

    public void set_uploaded_by(String s);

}

interface assessment {
    public void setid(int i);

    // public void settype();//0 for asm and 1 for quiz
    public void setprob(String s);

    public void setmaxg(int i);

    // public void showgrade();
    public void setgrade(int i, int m);

    public void set_close_flag();

}

interface human {
    public void printinfo();

    public int getid();

    public String getname();
}

class Student implements human {

    private String name;
    private int id;

    public Student(String str, int i) {
        name = str;
        id = i;
    }

    @Override
    public void printinfo() {
        System.out.println(id + " " + name);
    }

    public int getid() {
        return id;
    }

    public String getname() {
        return name;
    }

}

class Instructor implements human {

    private String name;
    private int id;

    public Instructor(String str, int i) {
        name = str;
        id = i;
    }

    @Override
    public int getid() {
        return id;
    }

    public String getname() {
        return name;
    }

    public void printinfo() {
        System.out.println(id + " " + name);
    }

}

class Assignment implements assessment {
    private int id;
    private int type;
    private String prob;
    private int max;
    // private int grade;
    private int flag = 0;// 0 is open 1 is closed

    private ArrayList<Solution> soln = new ArrayList<Solution>();

    // fn to set solution
    public int checksoln(int stu_id) {// prints if no soln wrt if
        int c = 0;
        int ind;
        for (int i = 0; i < soln.size(); i++) {
            if (soln.get(i).getid() == stu_id) {
                c++;
                ind = i;
            }
        }
        if (c == 0) {
            view();
            return 1;
        } else {
            return 0;
        }
    }

    public int getflag() {
        return flag;
    }

    public void setsoln(int i, String s, String n) {
        soln.add(new Solution());
        soln.get(soln.size() - 1).putsoln(i, s, n);
    }

    public void viewsolns() {// shows soln of only those which are submitted
        for (int i = 0; i < soln.size(); i++) {
            if (soln.get(i).get_ins().equals("-1")) {
                soln.get(i).show_submission();
            }
        }
    }

    public void view_ans(int soln_id) {
        for (int i = 0; i < soln.size(); i++) {
            if (soln.get(i).getid() == soln_id) {
                soln.get(i).showans();

            }
        }
    }

    public int getid() {
        return id;
    }

    public void view() {
        System.out.println("ID " + id + ", Assignment :" + prob + ", Max Marks: " + max);

    }

    public void showmax() {
        System.out.println("Max Marks: " + max);
    }

    public void set_soln_ins(String st, int soln_id) {
        for (int i = 0; i < soln.size(); i++) {
            if (soln.get(i).getid() == soln_id) {
                soln.get(i).set_ins_name(st);
            }
        }
    }

    public void get_soln_ins(int soln_id) {
        for (int i = 0; i < soln.size(); i++) {
            if (soln.get(i).getid() == soln_id) {
                soln.get(i).get_ins();
            }
        }

    }

    public void search_solution_by_studentId(int sid) {
        for (int i = 0; i < soln.size(); i++) {
            if ((soln.get(i).getid() == sid) && (!soln.get(i).get_ins().equals("-1"))) {
                soln.get(i).showans();
                System.out.println("Marks :" + soln.get(i).getgrade());
                System.out.println("Graded by: " + soln.get(i).get_ins());
                System.out.println("----------");
            }
        }
    }

    public void search_solution_by_studentId_ungraded(int sid) {
        for (int i = 0; i < soln.size(); i++) {
            if ((soln.get(i).getid() == sid) && (soln.get(i).get_ins().equals("-1"))) {
                soln.get(i).showans();

            }
        }
    }

    @Override
    public void setid(int i) {
        id = i;
    }

    // public void settype();//0 for asm and 1 for quiz
    public void setprob(String s) {
        prob = s;
    }

    public void setmaxg(int i) {
        max = i;
    }

    // public void showgrade();
    public void setgrade(int g, int opt) {
        for (int i = 0; i < soln.size(); i++) {
            if (soln.get(i).getid() == opt) {
                soln.get(i).setgrade(g);
            }
        }
        // grade = i;
    }

    public void set_close_flag() {
        flag = 1;
    }
}

class Quiz implements assessment {
    private int id;
    // private int type;
    private String prob;
    private int max;
    // private int grade;
    private int flag = 0;// 0 is open 1 is closed

    private ArrayList<Solution> soln = new ArrayList<Solution>();// association
    // fn to set solution

    public int checksoln(int stu_id) {// prints if no soln wrt if
        int c = 0;
        int ind;
        for (int i = 0; i < soln.size(); i++) {
            if (soln.get(i).getid() == stu_id) {
                c++;
                ind = i;
            }
        }
        if (c == 0) {
            view();
            return 1;
        } else {
            return 0;
        }
    }

    public int getflag() {
        return flag;
    }

    public void setsoln(int i, String s, String n) {
        soln.add(new Solution());
        soln.get(soln.size() - 1).putsoln(i, s, n);
    }

    public void viewsolns() {// shows soln of only those which are submitted
        for (int i = 0; i < soln.size(); i++) {
            if (soln.get(i).get_ins().equals("-1")) {
                soln.get(i).show_submission();
            }
        }
    }

    public void view_ans(int soln_id) {
        for (int i = 0; i < soln.size(); i++) {
            if (soln.get(i).getid() == soln_id) {
                soln.get(i).showans();

            }
        }
    }

    public String getprob() {
        return prob;
    }

    public int getid() {
        return id;
    }

    public void view() {
        System.out.println("ID " + id + ", Question :" + prob);
    }

    public void set_soln_ins(String st, int soln_id) {
        for (int i = 0; i < soln.size(); i++) {
            if (soln.get(i).getid() == soln_id) {
                soln.get(i).set_ins_name(st);
            }
        }
    }

    public void get_soln_ins(int soln_id) {
        for (int i = 0; i < soln.size(); i++) {
            if (soln.get(i).getid() == soln_id) {
                soln.get(i).get_ins();
            }
        }

    }

    public void search_solution_by_studentId(int sid) {
        for (int i = 0; i < soln.size(); i++) {
            if ((soln.get(i).getid() == sid) && (!soln.get(i).get_ins().equals("-1"))) {
                soln.get(i).showans();
                System.out.println("Marks :" + soln.get(i).getgrade());
                System.out.println("Graded by: " + soln.get(i).get_ins());
                System.out.println("----------");
            }
        }
    }

    public void search_solution_by_studentId_ungraded(int sid) {
        for (int i = 0; i < soln.size(); i++) {
            if ((soln.get(i).getid() == sid) && (soln.get(i).get_ins().equals("-1"))) {
                soln.get(i).showans();

            }
        }
    }

    @Override
    public void setid(int i) {
        id = i;
    }

    // public void settype();//0 for asm and 1 for quiz
    public void setprob(String s) {
        prob = s;
    }

    public void setmaxg(int i) {
        max = i;
    }

    // public void showgrade();
    public void setgrade(int g, int opt) {
        for (int i = 0; i < soln.size(); i++) {
            if (soln.get(i).getid() == opt) {
                soln.get(i).setgrade(g);
            }
        }
        // grade = i;
    }

    public void set_close_flag() {
        flag = 1;
    }

}

class Videos implements classmaterial {
    private String name;
    private Date date_of_upload;
    private String uploaded_by;

    private String filename;

    public void setfilename(String s) {
        filename = s;
    }

    public void display() {
        System.out.println("Title of the video : " + name);
        System.out.println("Video file: " + filename);
        System.out.println("Date of upload: " + date_of_upload);
        System.out.println("Uploaded by: " + uploaded_by);

    }

    @Override
    public void setname(String s) {// dependence relation
        name = s;
    }

    public void set_upload_date() {
        date_of_upload = java.util.Calendar.getInstance().getTime();
    }

    public void set_uploaded_by(String s) {
        uploaded_by = s;
    }

}

class Slides implements classmaterial {
    private String name;
    private Date date_of_upload;
    private String uploaded_by;

    private int num_of_slides;
    private ArrayList<String> slide_content = new ArrayList<String>();

    // method to set slides content
    public void setcontent() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < num_of_slides; i++) {
            System.out.print("Content for Slide " + (i + 1) + " :");
            String s = scan.nextLine();
            slide_content.add(s);
        }
    }

    public void setslides(int n) {
        num_of_slides = n;
    }

    public void display() {
        System.out.println("Title: " + name);
        for (int i = 0; i < slide_content.size(); i++) {
            System.out.println("Slide " + (i + 1) + ":" + slide_content.get(i));

        }
        System.out.println("Number of slides: " + num_of_slides);
        System.out.println("Date of upload: " + date_of_upload);
        System.out.println("Uploaded by: " + uploaded_by);
    }

    @Override
    public void setname(String s) {// dependence relation
        name = s;
    }

    public void set_upload_date() {
        date_of_upload = java.util.Calendar.getInstance().getTime();
    }

    public void set_uploaded_by(String s) {
        uploaded_by = s;
    }

}

class Solution {
    private int id;// stu id who submitted
    private String ans;
    private int grade;
    private String stu_name;
    private String ins_name = "-1";

    public int getid() {// student id who submitted
        return id;

    }

    public void setgrade(int g) {
        grade = g;
    }

    public int getgrade() {
        return grade;
    }

    public void set_ins_name(String s) {
        ins_name = s;
    }

    public String get_ins() {
        return ins_name;
    }

    public void showans() {
        System.out.println("Submission: " + ans);
    }

    public void show_submission() {
        System.out.println(id + " " + stu_name);
        System.out.println("----------------");
    }

    public void putsoln(int i, String a, String name) {
        ans = a;
        id = i;
        stu_name = name;
    }

}

class Discussion {
    private String chat;
    private Date date;
    private String author;

    public Discussion(String str, String name) {
        author = name;
        chat = str;
        date = java.util.Calendar.getInstance().getTime();
    }

    public void read() {
        System.out.println(chat + " -" + author);
        System.out.println(date);
    }

    // method to print discussion in a format
}

public class backpack {

    static ArrayList<Student> stu = new ArrayList<Student>();
    static ArrayList<Instructor> ins = new ArrayList<Instructor>();
    static ArrayList<Assignment> asm = new ArrayList<Assignment>();
    static ArrayList<Quiz> quiz = new ArrayList<Quiz>();
    static ArrayList<Slides> slides = new ArrayList<Slides>();
    static ArrayList<Videos> vid = new ArrayList<Videos>();
    static ArrayList<Discussion> discuss = new ArrayList<Discussion>();
    static int count = 0;

    static int uniqIdGenerator() {/// not for prof not for stu ig use it for assessments
        count++;
        return 0 + count;
    }

    static void add_classmaterial(String auth) {// auth is person who uploaded
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        System.out.println("1.Add Lecture Slides\n2.Add Lecture Video");
        int opt1 = scan.nextInt();// opt 1 wrt after instructor menu

        if (opt1 == 1) {// add slides
            System.out.print("Enter topic of Slides :");
            String str = scan2.nextLine();
            System.out.print("Enter number of Slides :");
            int num = scan.nextInt();
            slides.add(new Slides());
            slides.get(slides.size() - 1).setname(str);// slide name
            slides.get(slides.size() - 1).setslides(num);
            slides.get(slides.size() - 1).setcontent();
            slides.get(slides.size() - 1).set_upload_date();
            slides.get(slides.size() - 1).set_uploaded_by(auth);
        } else if (opt1 == 2) {// add video
            System.out.print("Enter topic of Video :");
            String str = scan2.nextLine();
            System.out.println("Enter filename of video :");
            String str2 = scan2.nextLine();
            if ((str2.length() > 4) && (str2.substring(str2.length() - 4)).equals(".mp4")) {
                vid.add(new Videos());
                vid.get(vid.size() - 1).setfilename(str2);
                vid.get(vid.size() - 1).setname(str);
                vid.get(vid.size() - 1).set_upload_date();
                vid.get(vid.size() - 1).set_uploaded_by(auth);
            } else {
                System.out.println("Invalid Filename!");
                return;
            }

        } else {
            System.out.println("wrong option!");
            return;
        }

    }

    static void add_assessment() {
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        System.out.println("1.Add Assignment\n2.Add Quiz");
        int opt = scan.nextInt();
        if (opt == 1) {// assignment
            asm.add(new Assignment());
            System.out.print("Enter problem statement: ");
            asm.get(asm.size() - 1).setprob(scan2.nextLine());
            System.out.print("Enter max marks: ");
            asm.get(asm.size() - 1).setmaxg(scan.nextInt());
            asm.get(asm.size() - 1).setid(uniqIdGenerator());// new id is alloted
            // we dont care who gave assignment or who checks it wrt instructors viewpoint

        } else if (opt == 2) {// quiz
            quiz.add(new Quiz());
            System.out.print("Enter quiz question: ");
            quiz.get(quiz.size() - 1).setprob(scan2.nextLine());
            // System.out.print("Enter max marks: ");
            quiz.get(quiz.size() - 1).setmaxg(1);
            quiz.get(quiz.size() - 1).setid(uniqIdGenerator());// new id is alloted

        } else {
            System.out.println("Wrong input!!");
            return;
        }

    }

    static void view_classmaterial() {
        for (int i = 0; i < slides.size(); i++) {
            slides.get(i).display();
            System.out.println("--------------------------------------------------------");

        }
        for (int j = 0; j < vid.size(); j++) {
            vid.get(j).display();
            System.out.println("--------------------------------------------------------");
        }
    }

    static void view_assessment() {
        for (int i = 0; i < asm.size(); i++) {
            asm.get(i).view();
            System.out.println("--------------------------------------------------------");

        }
        for (int j = 0; j < quiz.size(); j++) {
            quiz.get(j).view();
            System.out.println("--------------------------------------------------------");
        }
    }

    static void submit_assessment(int stu_id, String stu_name) {// student id
        System.out.println("Pending Assessments:");
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        int type = -1;// 0 for assignment 1 for quiz
        int pin = 0;
        for (int i = 0; i < asm.size(); i++) {
            pin = (pin + asm.get(i).checksoln(stu_id));
        }

        for (int i = 0; i < quiz.size(); i++) {
            pin = (pin + quiz.get(i).checksoln(stu_id));
        }

        if (pin == 0) {
            System.out.println(pin + "No Pending Assessments Found!");
            return;
        }
        int index = -1;
        // System.out.println("NOTE: if no pending assessment then input 10000");
        System.out.print("Enter ID of assessment: ");
        int assessment_id = scan.nextInt();
        for (int i = 0; i < asm.size(); i++) {
            if (asm.get(i).getid() == assessment_id) {
                type = 0;
                index = i;
            }
        }

        for (int i = 0; i < quiz.size(); i++) {
            if (quiz.get(i).getid() == assessment_id) {// only if they both dont have same ids
                type = 1;
                index = i;
            }
        }

        if (type == 1) {// quiz
            if (quiz.get(index).getflag() == 1) {
                System.out.println("Deadline Crossed! Assessment has been closed..");
                return;
            }
            // System.out.println("Enter filename of assignment: ");
            System.out.println(quiz.get(index).getprob());
            String str = scan2.nextLine();
            quiz.get(index).setsoln(stu_id, str, stu_name);

        } else if (type == 0) {// assignment
            if (asm.get(index).getflag() == 1) {
                System.out.println("Deadline Crossed! Assessment has been closed..");
                return;
            }
            System.out.println("Enter filename of assignment: ");
            String str = scan2.nextLine();
            if ((str.length() > 4) && (str.substring(str.length() - 4)).equals(".zip")) {
                if (index != -1) {
                    asm.get(index).setsoln(stu_id, str, stu_name);
                }
            } else {
                System.out.println("Incorrect file type!!");
                return;
            }

        } else {
            System.out.println("Cannot find ID mentioned!!");
            return;
        }
    }

    /*
     * List of assessments ID: 0 Assignment: Assignment 1 problem Max Marks: 5
     * ---------------- ID: 1 Question: Name a language which supports OOPS?
     * 
     * ---------------- Enter ID of assessment to view submissions: 0 Choose ID from
     * these ungraded submissions 0. S0 1. S1
     */
    static void grade_assessment(String ins_name) {// also concept of open and closed asm modify past code
        System.out.println("List of Assessments ");
        view_assessment();
        System.out.println("Enter ID of assessment to view submission: ");
        Scanner scan = new Scanner(System.in);
        int ip = scan.nextInt();// id of assmnt
        int index = -1;// index for assmt ka location
        int type = 2;// 0 for asm, 1 for quiz
        for (int i = 0; i < asm.size(); i++) {
            if (ip == asm.get(i).getid()) {
                index = i;
                type = 0;
            }
        }
        for (int j = 0; j < quiz.size(); j++) {// cannot have same id
            if (ip == quiz.get(j).getid()) {
                index = j;
                type = 1;
            }
        }
        if (index == -1) {
            System.out.println("Assessment ID not found!");
        } else {
            if (type == 0) {
                System.out.println("Choose Student ID from the given submissions:");
                asm.get(index).viewsolns();// assessment specific soln ids
                int opt = scan.nextInt();// shows id not indeex so opt is id not index
                asm.get(index).view_ans(opt);
                asm.get(index).showmax();
                System.out.print("Marks Scored: ");
                int score = scan.nextInt();
                asm.get(index).setgrade(score, opt);
                asm.get(index).set_soln_ins(ins_name, opt);

            } else if (type == 1) {
                System.out.println("Choose ID from the given submissions:");
                quiz.get(index).viewsolns();
                int opt = scan.nextInt();
                quiz.get(index).view_ans(opt);
                System.out.print("Marks Scored: ");
                int score = scan.nextInt();
                quiz.get(index).set_soln_ins(ins_name, opt);
                quiz.get(index).setgrade(score, opt);// markscan be greater than max as not handled error
            } else {
                System.out.println("ID not found");
                return;
            }

        }

    }

    static void close_assessment() {
        System.out.println("List of Open Assessments");
        int type = 2;
        for (int i = 0; i < asm.size(); i++) {// open assignments
            if (asm.get(i).getflag() == 0) {
                asm.get(i).view();
                System.out.println("------------------");
            }
        }
        for (int j = 0; j < quiz.size(); j++) {
            if (quiz.get(j).getflag() == 0) {
                quiz.get(j).view();
                System.out.println("------------------");
            }
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter ID of Assessment to close");
        int close = scan.nextInt();
        int index = -1;
        for (int i = 0; i < asm.size(); i++) {// open assignments
            if (asm.get(i).getid() == close) {
                index = i;
                type = 0;
            }
        }
        for (int j = 0; j < quiz.size(); j++) {
            if (quiz.get(j).getflag() == 0) {
                index = j;
                type = 1;
            }
        }
        if (type == 0) {
            asm.get(index).set_close_flag();
        } else if (type == 1) {
            quiz.get(index).set_close_flag();

        } else {
            System.out.println("ID not found!");
            return;
        }

    }

    static void view_grades(int sid) {
        System.out.println("Graded Assessments:");
        for (int i = 0; i < asm.size(); i++) {
            asm.get(i).search_solution_by_studentId(sid);
        }
        for (int j = 0; j < quiz.size(); j++) {
            quiz.get(j).search_solution_by_studentId(sid);
        }

        System.out.println("Ungraded Assesments:");
        for (int i = 0; i < asm.size(); i++) {
            asm.get(i).search_solution_by_studentId_ungraded(sid);
        }
        for (int j = 0; j < quiz.size(); j++) {
            quiz.get(j).search_solution_by_studentId_ungraded(sid);
        }

    }

    static void add_comments(String name) {
        System.out.print("Enter Comment:");
        Scanner scan = new Scanner(System.in);
        String chat = scan.nextLine();
        discuss.add(new Discussion(chat, name));
    }

    static void view_comments() {
        for (int i = 0; i < discuss.size(); i++) {
            discuss.get(i).read();
            System.out.println("______________________");
        }
    }

    static void greet() {
        System.out.println("Welcome to Backpack\n1. Enter as instructor\n2. Enter as student\n3. Exit");
    }

    static void imenu() {
        System.out.println(
                "INSTRUCTOR MENU\n1. Add class material\n2. Add assessments\n3. View lecture materials\n4. View assessments\n5. Grade assessments\n6. Close assessment\n7. View comments\n8. Add comments\n9. Logout");
    }

    static void smenu() {
        System.out.println(
                "STUDENT MENU\n1. View lecture materials\n2. View assessments\n3. Submit assessment\n4. View grades\n5. View comments\n6. Add comments\n7. Logout");
    }

    public static void main(String[] args) {
        Date date = java.util.Calendar.getInstance().getTime();
        System.out.println(date);
        Scanner scan = new Scanner(System.in);
        // dummy input
        Student s0 = new Student("ram", 0);
        Student s1 = new Student("raju", 1);
        Student s2 = new Student("rahul", 2);
        Instructor i0 = new Instructor("Dr. Raj", 0);
        Instructor i1 = new Instructor("Dr.Dev prasad", 1);
        stu.add(s0);
        stu.add(s1);
        stu.add(s2);
        ins.add(i0);
        ins.add(i1);

        // ends here
        int main_input = 0;
        while (main_input != 3) {
            greet();
            main_input = scan.nextInt();

            if (main_input == 1) {
                System.out.println("Instructors:");
                for (int i = 0; i < ins.size(); i++) {
                    ins.get(i).printinfo();
                }
                System.out.print("Choose id : ");
                int opt2 = scan.nextInt();// opt 2 is chosen id
                int check = 0;
                int index = 0;
                for (int j = 0; j < ins.size(); j++) {
                    if (opt2 == ins.get(j).getid()) {
                        check++;
                        index = j;

                    }
                }
                if (check == 0) {
                    System.out.println("wrong input!!");
                    return;
                } else if (check == 1) {// further if check>1 then duplicate ids

                    // from here we go in instructors while loop
                    int sub_input_flag = 1;
                    int sub_input;
                    while (sub_input_flag == 1) {
                        System.out.println("Welcome " + ins.get(index).getname());
                        imenu();
                        sub_input = scan.nextInt();
                        if (sub_input == 1) {
                            add_classmaterial(ins.get(index).getname());
                        } else if (sub_input == 2) {
                            add_assessment();
                        } else if (sub_input == 3) {
                            view_classmaterial();
                        } else if (sub_input == 4) {
                            view_assessment();
                        } else if (sub_input == 5) {
                            grade_assessment(ins.get(index).getname());
                        } else if (sub_input == 6) {
                            close_assessment();
                        } else if (sub_input == 7) {
                            view_comments();
                        } else if (sub_input == 8) {
                            add_comments(ins.get(index).getname());
                        }

                        else if (sub_input == 9) {
                            sub_input_flag = 0;
                            continue;
                        }
                    }
                }

            } else if (main_input == 2) {
                System.out.println("Students:");
                for (int i = 0; i < stu.size(); i++) {
                    stu.get(i).printinfo();
                }
                System.out.print("Choose id : ");
                int opt2 = scan.nextInt();// opt 2 is chosen id
                int check = 0;
                int index = 0;
                for (int j = 0; j < stu.size(); j++) {
                    if (opt2 == stu.get(j).getid()) {
                        check++;
                        index = j;

                    }
                }
                if (check == 0) {
                    System.out.println("wrong input!!");
                    return;
                } else if (check == 1) {// further if check>1 then duplicate ids

                    // from here we go in instructors while loop
                    int sub_input_flag = 1;
                    int sub_input;
                    while (sub_input_flag == 1) {
                        System.out.println("Welcome " + stu.get(index).getname());
                        smenu();
                        sub_input = scan.nextInt();
                        if (sub_input == 1) {
                            view_classmaterial();
                        } else if (sub_input == 2) {
                            view_assessment();
                        } else if (sub_input == 3) {
                            submit_assessment(opt2, stu.get(index).getname());// opt2 =stu id as check =1
                        } else if (sub_input == 4) {
                            view_grades(stu.get(index).getid());
                        } else if (sub_input == 5) {
                            view_comments();
                        } else if (sub_input == 6) {
                            add_comments(stu.get(index).getname());
                        } else if (sub_input == 7) {
                            sub_input_flag = 0;
                            continue;
                        }
                    }
                }
            } else if (main_input == 3) {// for exit
                continue;
            } else {
                System.out.println("wrong input!!");
                continue;
            }
        }

    }
}
/*
 * Title: Slide 1 Slide 1: Content 1 Slide 2: Content 2 Number of slides: 2 Date
 * of upload: Thu Oct 14 23:25:25 IST 2021 Uploaded by: I0
 * 
 * 
 * ungraded wale cases and error handling ... if needed no pending
 */