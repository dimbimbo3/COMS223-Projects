import java.util.Scanner;

class node{
    public String name;
    public node parent;
    public node left;
    public node right;
        
    public node(String name, node parent, node left, node right){
        this.name = name;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
}//ends node class
    
class BinarySearchTree extends node{
    public node root;
    public node curpos;
    public node temp;
    public boolean rootTest;
        
    public BinarySearchTree(){
        super(" ",null,null,null);
        root = null;
        curpos = null;
        temp = null;
        rootTest = false;
    }
        
    private void makeRoot(node root){
        this.root = root;
        curpos = root;
        System.out.println("Root node of " + root.name + " has been made");
        rootTest = true;
    }
      
    public node constructBST(String[] array, int low, int high){
        int mid = (low + high)/2;
        
        //prevents array out of bounds for left and right sides
        //with high being less than the lowest index for the left side
        //and low being greater than the highest index for the right side
        if(low > high)
            return null;
        
        node temp = new node(array[mid],null,null,null);
        
        //creates root node, if it does not exist
        if(!rootTest)
            makeRoot(temp);
        
        //moves low right by 1 and high left by 1 until all entries on each side of the array
        //have been added to the left and right as nodes
        temp.left = constructBST(array,low,mid-1);
        temp.right = constructBST(array,mid+1,high);
        
        return temp;
    }
        
    public void returnToRoot(){
        if(root != null){
            curpos = root;
            System.out.println("Returned to root node: " + getName());
        }
        else
            System.out.println("The root does not currently exist");
    }
        
    public void moveLeft(){
        if(curpos.left != null){
            curpos = curpos.left;
            System.out.println("Moved to left node: " + getName());
        }
        else{
            System.out.println("The left node is null");
            System.out.println("Still on node: " + getName());
        }
    }
        
    public void moveRight(){
        if(curpos.right != null){
            curpos = curpos.right;
            System.out.println("Moved to right node: " + getName());
        }
        else{
            System.out.println("The right node is null");
            System.out.println("Still on node: " + getName());
        }
    }
    
    public boolean searchNode(String name){
        curpos = root;
        boolean found = false;
        boolean end = false;
        
        while(!found && !end){
            //checks if current position is set to null
            //if so, end is marked as true and the loop ends
            if(curpos == null)
                end = true;
            //checks if name is at the current position
            //if so, found is marked as true
            else if(name.compareToIgnoreCase(curpos.name) == 0){
                found = true;
            }
            //if the searched name comes before curpos's name in the alphabet
            //curpos moves to the left
            else if(name.compareToIgnoreCase(curpos.name) < 0)
                curpos = curpos.left;
            //if the searched name comes after curpos's name in the alphabet
            //curpos moves to the right
            else if(name.compareToIgnoreCase(curpos.name) > 0)
                curpos = curpos.right;
        }
        
        //after checking left and right sides, outputs whether the node has been found
        if(found)
            System.out.println("Node '" + name + "' has been found");
        else
            System.out.println("Node '" + name + "' has NOT been found");
        
        returnToRoot();
        
        return found;
    }
    
    public String getName(){
        return curpos.name;
    }
}//ends BinarySearchTree class

public class Project5 {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        int size = 0;
        String name = "";
        
        System.out.print("Enter the number of names you want in the array:");
        size = keyboard.nextInt();
        keyboard.nextLine();//consume newline character
        
        String[] nameArray = new String[size];
        
        int arrayLow = 0;
        int arrayHigh = nameArray.length - 1;
        
        for(int count = 0; count < nameArray.length; count++){
            System.out.print("Enter Name #" + (count + 1) + ":");
            name = keyboard.nextLine();
            nameArray[count] = name;
        }

        sortArray(nameArray);
        
        BinarySearchTree tree = new BinarySearchTree();
        tree.constructBST(nameArray, arrayLow, arrayHigh);
        
        String userSelection = "";
        String userInput = "";
        do{
            System.out.println("");
            menu();
            userSelection = keyboard.nextLine();
            switch(userSelection){
                case "1":
                    tree.moveLeft();
                    break;
                case "2":
                    tree.moveRight();
                    break;
                case "3":
                    tree.returnToRoot();
                    break;
                case "4":
                    System.out.print("Enter the name to search for:");
                    userInput = keyboard.nextLine();
                    tree.searchNode(userInput);
                    break;
                case "-1":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid user input!");
            }
        }while(Integer.parseInt(userSelection) != -1);
    }
    
    //sorts the array alphabetically
    public static void sortArray(String[] array){
        String temp = "";
        
        for(int outerCount = 0; outerCount < array.length; outerCount++){
            //compares every String in the list following the one at the current index
            for(int innerCount = outerCount + 1; innerCount < array.length; innerCount++){
                //if the next String comes before the one at the current index
                //then replace their positions in the list
                if(array[innerCount].compareToIgnoreCase(array[outerCount]) < 0){
                    temp = array[outerCount];
                    array[outerCount] = array[innerCount];
                    array[innerCount] = temp;
                }
            }
        }
        
        System.out.println("---Sorted Array---");
        printArray(array);
    }
    
    //Prints the elements of the array argument
    public static void printArray(String[] array){
        for(int i = 0; i < array.length; i++){
            System.out.println(i + ":" + array[i]);
        }
        System.out.println("");
    }
    
    public static void menu(){
        System.out.println("--------USER MENU--------");
        System.out.println("1. Move to the Left Node");
        System.out.println("2. Move to the Right Node");
        System.out.println("3. Return to the Root Node");
        System.out.println("4. Search for specified name");
        System.out.print("ENTER(-1 to quit):");
    }
}