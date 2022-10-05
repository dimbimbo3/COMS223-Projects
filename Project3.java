import java.util.Scanner;

class job{
    public String owner;
    public String filename;
    public int jobID;
    public job next;
    
    //constructor
    job(String owner, String filename, int jobID, job next){
        this.owner = owner;
        this.filename = filename;
        this.jobID = jobID;
        this.next = next;
    }
}//ends job class

class queueList extends job{
    public job first;
    public job rear;
    public job curpos;
    
    //constructor
    queueList(){
        super("","",0,null);
        first = null;
    }
    
    public void enqueue(String owner, String filename, int jobID, job next){
        job newnode = new job(owner, filename, jobID, next);
        
        if(first == null){
            first = newnode;
            rear = newnode;
        }
        else{
            rear.next = newnode;
            rear = newnode;
        }
    }//ends enqueue
    
    public void dequeue(){
        first = first.next;
    }//ends dequeue
    
    public void enumerate(){
        curpos = first;
        
        System.out.println("-Queue-");
        while(curpos != null){
            System.out.println(curpos.owner + " " + curpos.filename + " " + curpos.jobID);
            curpos = curpos.next;
        }
    }//ends enumerate
    
    public boolean isEmpty(){
        boolean empty = false;
        
        if(first == null)
            empty = true;
        
        return empty;
    }//ends isEmpty
    
    public void size(){
        int count = 0;
        curpos = first;
        
        while(curpos != null){
            count++;
            curpos = curpos.next;
        }
        
        System.out.println("Size:" + count);
    }//ends size
}//ends queueList class

class printQueue extends queueList{
    //constructor
    printQueue(){
        super();
    }
    
    public void LPR(String owner, String filename, int jobID, job next){
        enqueue(owner, filename, jobID, next);
    }//ends LPR
    
    public void LPRM(){
        dequeue();
    }//ends LPRM

    public void LPQ(){
        enumerate();
    }//ends LPQ
    
    public void LPQ_Owner(String owner){
        curpos = first;
        int count = 0;
        
        System.out.println("-Entries-");
        while(curpos != null){
            if(curpos.owner.toUpperCase().equals(owner.toUpperCase())){
                System.out.println((count + 1) + ". " + curpos.filename + " " + curpos.jobID);
                count++;
            }
            curpos = curpos.next;
        }
    }//ends LPQ_Owner
    
    public void LPQ_ID(int ID){
        curpos = first;
        boolean found = false;
        
        while(curpos != null && !found){
            if(curpos.jobID == ID){
                System.out.print("JobID(" + ID + "):");
                System.out.println(curpos.owner + " " + curpos.filename);
                found = true;
            }
            curpos = curpos.next;
        }
        
        if(!found){
            System.out.println("There are no jobs matching that ID in the queue.");
        }
    }//ends LPQ_ID
}//ends printQueue

public class Project3 {
    public static void main(String[] args){
        printQueue list = new printQueue();
        
        list.LPR("Dan", "test", 123, null);
        list.LPR("Josh", "homework", 456, null);
        list.LPR("Kevin", "payroll", 789, null);
        list.LPR("Cait", "webpage", 264, null);
        list.LPR("Chelsea", "questionnaire", 315, null);
        
        Scanner keyboard = new Scanner(System.in);
        String userSelection = "";
        String userOwner = "";
        String userFile = "";
        String userID = "";
        
        do{
            menu();
            userSelection = keyboard.nextLine();
            switch(userSelection){
                case "1":
                    System.out.println("");
                    System.out.print("Enter the Owner:");
                    userOwner = keyboard.nextLine();
                    System.out.print("Enter the File Name:");
                    userFile = keyboard.nextLine();
                    System.out.print("Enter the Job ID:");
                    userID = keyboard.nextLine();
                    list.LPR(userOwner, userFile, Integer.parseInt(userID), null);
                    break;
                case "2":
                    list.LPRM();
                    break;
                case "3":
                    System.out.println("");
                    if(list.isEmpty())
                        System.out.println("The print queue is empty");
                    else
                        list.LPQ();
                    break;
                case "4":
                    System.out.println("");
                    list.size();
                    break;
                case "5":
                    System.out.println("");
                    System.out.print("Enter the Owner to search for:");
                    userOwner = keyboard.nextLine();
                    list.LPQ_Owner(userOwner);
                    break;
                case "6":
                    System.out.println("");
                    System.out.print("Enter the Job ID to search for:");
                    userID = keyboard.nextLine();
                    list.LPQ_ID(Integer.parseInt(userID));
                    break;
                case "-1":
                    System.out.println("");
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("");
                    System.out.println("Invalid user input!");
            }
            System.out.println("");
        }while(Integer.parseInt(userSelection) != -1);
    }
    
    public static void menu(){
        System.out.println("--------USER MENU--------");
        System.out.println("1. Add job to print queue");
        System.out.println("2. Remove the first job from the queue");
        System.out.println("3. Return print queue status");
        System.out.println("4. Get print queue size");
        System.out.println("5. Find jobs by Owner");
        System.out.println("6. Find job info by Job ID");
        System.out.print("ENTER(-1 to quit):");
    }
}
