import java.util.*;

class node {
    public String item;
    public String date;
    public double cost;
    public node next;
    
    //constructor
//    public node(int itm, node ptr){
//        item = itm;
//        next = ptr;
//    }
    //constructor2
    public node(String item, String date, double cost, node next){
        this.item = item;
        this.date = date;
        this.cost = cost;
        this.next = next;
    }
}

class list extends node{
    public node first;
    public node curpos;
    public node prev;
    
    list(){
        super("","",0.0,null);
        first = null;
        curpos = null;
    }
    
    public void add(String item, String date, double cost, node next){
        node newnode = new node(item,date,cost,next);
        
        if(first == null){
            first = newnode;
            curpos = newnode;
        }
        else{
            curpos.next = newnode;
            curpos = newnode;
        }
    }
    
    public void print(){
        curpos = first;
        
        System.out.println("-Item List-");
        while(curpos != null){
            System.out.print(curpos.item + " - " + curpos.date);
            System.out.printf(" - $%.2f\n", curpos.cost);
            
            curpos = curpos.next;
        }
    }
    
    public boolean search(String userItem, String userDate){
        curpos = first;
        boolean found = false;
        
        while(curpos != null && !found){
            if(curpos.item.toUpperCase().equals(userItem.toUpperCase()) && curpos.date.toUpperCase().equals(userDate.toUpperCase())){
                found = true;
                System.out.println("Item was found");
            }
            else{
                curpos = curpos.next;
            }
        }
        
        if(!found){
            System.out.println("Item was not found");
        }
        
        return found;
    }
    
    public void searchAndDestroy(String userItem, String userDate){
        curpos = prev = first;
        boolean found = false;
        
        //checks if the first node contains the searched item and date
        //if so, moves the first pointer to the next node
        if(first.item.toUpperCase().equals(userItem.toUpperCase()) && first.date.toUpperCase().equals(userDate.toUpperCase())){
            first = first.next;
            found = true;
            System.out.println("Item was found and deleted");
        }
        
        while(curpos != null && !found){
            if(curpos.item.toUpperCase().equals(userItem.toUpperCase()) && curpos.date.toUpperCase().equals(userDate.toUpperCase())){
                prev.next = curpos.next;
                found = true;
                System.out.println("Item was found and deleted");
            }
            else{
                prev = curpos;
                curpos = curpos.next;
            }    
        }
        
        if(!found){
            System.out.println("Item was not found and not deleted");
        }
    }
    
    public void getMinMax(){
        curpos = first;
        double min = first.cost;
        String minItem = first.item;
        double max = first.cost;
        String maxItem = first.item;
        
        while(curpos != null){
            if(curpos.cost < min){
                min = curpos.cost;
                minItem = curpos.item;
            }
            if(curpos.cost > max){
                max = curpos.cost;
                maxItem = curpos.item;
            }
            curpos = curpos.next;
        }
        
        System.out.print("Minimum Expense: " + minItem);
        System.out.printf(" - $%.2f\n", min);
        System.out.print("Maximum Expense: " + maxItem);
        System.out.printf(" - $%.2f\n", max);
    }
    
    public void getAverage(){
        curpos = first;
        double total = 0;
        int count = 0;
        
        while(curpos != null){
            total += curpos.cost;
            curpos = curpos.next;
            count++;
        }
        
        double average = (total/count);
        System.out.printf("Average Expense: $%.2f\n" , average);
    }
    
    public void getCost(String userItem, String userDate){
        boolean found = search(userItem, userDate);
        
        if(found){
            System.out.print(curpos.item);
            System.out.printf(" - $%.2f\n", curpos.cost);
        }
    }
    
    public void getItemwiseBreakup(){
        node itemLoc = first;
        
        System.out.println("-Itemwise Breakup-");
        while(itemLoc != null){
            itemwiseCombineSearch(itemLoc.item, itemLoc);
            System.out.print(itemLoc.item);
            System.out.printf(" - $%.2f\n", itemLoc.cost);
            itemLoc = itemLoc.next;
        }
    }
    
    private void itemwiseCombineSearch(String itemName, node itemPos){
        curpos = itemPos.next;
        prev = itemPos;
        
        //Searches linked list for another instance of the item name
        //if found, combines the cost with the first instance of the item
        //and deletes the duplicate item's node from the list
        while(curpos != null){
            if(curpos.item.equals(itemName)){
                itemPos.cost += curpos.cost;
                prev.next = curpos.next;
                curpos = curpos.next;
            }
            else{
                prev = curpos;
                curpos = curpos.next;
            }    
        }
    }
}

public class Project1 {
    public static void main(String[] args){
        list list1 = new list();
        Scanner keyboard = new Scanner(System.in);
        String userItem;
        String userDate;
        
        list1.add("food", "Jan 22", 14.99, null);
        list1.add("clothes", "Jan 24", 8.99, null);
        list1.add("coffee", "Feb 4", 2.99, null);
        list1.add("bed", "Feb 8", 100, null);
        list1.add("clothes", "Feb 10", 7.99, null);
        list1.add("food", "Feb 19", 9.99, null);
        list1.add("computer", "Mar 1", 685, null);
        list1.add("monitor", "Mar 3", 125, null);
        list1.add("food", "Mar 15", 19.99, null);
        list1.add("sofa", "Apr 27" , 150, null);
        
        list1.print();
        System.out.println("");
        
        list1.getMinMax();
        System.out.println("");
        
        list1.getAverage();
        System.out.println("");
        
        System.out.print("Enter the name of the item you want the cost of:");
        userItem = keyboard.nextLine();
        System.out.print("Enter the date of the item you want the cost of(FORMAT -> Jan 1):");
        userDate = keyboard.nextLine();
        list1.getCost(userItem, userDate);
        System.out.println("");
        
        list1.getItemwiseBreakup();
    }
}
