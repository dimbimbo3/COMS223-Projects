import java.util.Scanner;

class node{
    public char data;
    public node parent;
    public node left;
    public node right;
        
    public node(char data, node parent, node left, node right){
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
}//ends node class
    
class BinaryTree extends node{
    public node root;
    public node curpos;
        
    public BinaryTree(){
        super(' ',null,null,null);
        root = null;
        curpos = null;
    }
        
    public void makeRoot(char data){
        node newnode = new node(data,null,null,null);
            
        if(root == null){
            root = newnode;
            curpos = newnode;
            System.out.println("Root node of " + data + " has been made");
        }
        else
            System.out.println("Root node already exists.");
    }
      
    public void attachLeftNode(char data){
        node newnode = new node(data,null,null,null);
        
        //makes sure a left node doesn't already exist, and that the pointer is on the root node or the level 1 left node
        if(curpos.left == null && (curpos.parent == root || curpos.parent == null)){
            newnode.parent = curpos;
            curpos.left = newnode;
            System.out.println("Left node of " + data + " has been attached");
        }
        else if(curpos.parent != root && curpos.parent != null)
            System.out.println("A node cannot be placed beyond level 2 of the Binary Tree");
        else
            System.out.println("Left node already exists.");
    }

    public void attachRightNode(char data){
        node newnode = new node(data,null,null,null);
        
        //makes sure a left node doesn't already exist, and that the pointer is on the root node or the level 1 right node
        if(curpos.right == null && (curpos.parent == root || curpos.parent == null)){
            newnode.parent = curpos;
            curpos.right = newnode;
            System.out.println("Right node of " + data + " has been attached");
        }
        else if(curpos.parent != root && curpos.parent != null)
            System.out.println("A node cannot be placed beyond level 2 of the Binary Tree");
        else
            System.out.println("Right node already exists.");
    }
        
    public void returnToRoot(){
        if(root != null){
            curpos = root;
            System.out.println("Returned to root node: " + getData());
        }
        else
            System.out.println("The root does not currently exist");
    }
    
    public void returnToParent(){
        if(curpos != root){
            curpos = curpos.parent;
            System.out.println("Returned to parent node: " + getData());
        }
        else
            System.out.println("The root does not have a parent node");
    }
        
    public void moveLeft(){
        if(curpos.left != null){
            curpos = curpos.left;
            System.out.println("Moved to left node: " + getData());
        }
        else
            System.out.println("The left node does not currently exist");
    }
        
    public void moveRight(){
        if(curpos.right != null){
            curpos = curpos.right;
            System.out.println("Moved to right node: " + getData());
        }
        else
            System.out.println("The right node does not currently exist");
    }
    
    public void deleteNode(char data){
        boolean leftChild = false;
        //if the node has been found, deletes the node and moves child nodes
        if(searchNode(data) && curpos.data != root.data){
            //checks if the node being deleted has a left child and/or a right child
            if(curpos.left != null)
                leftChild = true;
            if(curpos.right != null){
                //if the current node has a left child, as well as a right child, make the right child the left child of the current node's left child
                //and make the parent of the right child the current node's left child
                if(leftChild){
                    curpos.left.left = curpos.right;
                    curpos.right.parent = curpos.left;
                }
            }
            //ensure the parent's left or right nodes are not null
            //then checks if the node being deleted is the left or right of the parent
            if(curpos.parent.left != null){
                if(curpos.parent.left.data == curpos.data){
                    //sets the parent's left node to the current node's left node
                    //if the current node does not have a left node, sets the parent's left to the current's right(null or otherwise)
                    if(leftChild){
                        curpos.parent.left = curpos.left;
                    }
                    else{
                        curpos.parent.left = curpos.right;
                    }
                    //set's the new left node's parent to old left node's parent, unless the node is null
                    if(curpos.parent.left != null){
                        curpos.parent.left.parent = curpos.parent;
                    }
                }
            }
            if(curpos.parent.right != null){
                if(curpos.parent.right.data == curpos.data){
                    //sets the parent's right node to the current node's left node
                    //if the current node does not have a left node, sets the parent's right to the current's right(null or otherwise)
                    if(leftChild){
                        curpos.parent.right = curpos.left;
                    }
                    else{
                        curpos.parent.right = curpos.right;
                    }
                    //set's the new right node's parent to old right node's parent, unless the node is null
                    if(curpos.parent.right != null){
                        curpos.parent.right.parent = curpos.parent;
                    }
                }
            }
            curpos.parent = null;
            curpos.left = null;
            curpos.right = null;
            System.out.println("Node " + data + " has been deleted");
        }
        else if(curpos == root)
            System.out.println("The root node cannot be deleted.");
        else
            System.out.println("Node " + data + " cannot be deleted");
        
        returnToRoot();
    }
    
    public boolean searchNode(char data){
        returnToRoot();
        boolean found = false;
        boolean leftEnd = false;
        boolean rightEnd = false;
        
        //checks root
        if(root.data == data)
            found = true;
        else
            curpos = curpos.left;
        
        //checks if root had a left child, if not marks leftEnd
        if(curpos == null)
            leftEnd = true;
        
        //checks left side of Binary Tree
        while(!found && !leftEnd){
            //checks if data is at the current position
            if(curpos.data == data){
                found = true;
            }
            //checks if left node is null, if not moves to left
            else if(curpos.left != null)
                curpos = curpos.left;
            //if left node is null, checks if right node is null, if not moves to right
            else if(curpos.right != null){
                curpos = curpos.right;
            }
            //if left and right nodes are null, checks if parent's right node is null or the current node
            else if(curpos.parent.right != null && curpos.parent.right != curpos){
                curpos = curpos.parent.right;
            }
            else
                leftEnd = true;
        }
        
        //if not found on left side, then checks right side of Binary Tree 
        if(!found){
            curpos = root;
            curpos = curpos.right;
            
            //checks if root had a right child, if not marks rightEnd
            if(curpos == null)
                rightEnd = true;
            
            while(!found && !rightEnd){
                //checks if data is at the current position
                if(curpos.data == data){
                    found = true;
                }
                //checks if left node is null, if not moves to left
                else if(curpos.left != null)
                    curpos = curpos.left;
                //if left node is null, checks if right node is null, if not moves to right
                else if(curpos.right != null){
                    curpos = curpos.right;
                }
                //if left and right nodes are null, checks if parent's right node is null or the current node
                else if(curpos.parent.right != null && curpos.parent.right != curpos){
                    curpos = curpos.parent.right;
                }
                else
                    rightEnd = true;
            }
        }
        
        //after checking left and right sides, outputs whether the node has been found
        if(found)
            System.out.println("Node " + data + " has been found");
        else
            System.out.println("Node " + data + " has NOT been found");
        
        return found;
    }
    
    public char getData(){
        return curpos.data;
    }
    
    //root,left,right
    public void preorderTraversal(){
        curpos = root;
        String preorder = "";
        boolean leftEnd = false;
        boolean rightEnd = false;
        
        //add root data to output
        preorder += curpos.data;
        
        //priming read for left side
        if(curpos.left != null){
            curpos = curpos.left;
            preorder += curpos.data;
        }
        else
            leftEnd = true;
        
        //adds left side of preorder tree traversal to output
        while(!leftEnd){
            if(curpos.left != null){
                curpos = curpos.left;
                preorder += curpos.data;
            }
            else if(curpos.right != null){
                curpos = curpos.right;
                preorder += curpos.data;
            }
            else if(curpos.parent != root && curpos.parent.right != null && curpos.parent.right != curpos){
                curpos = curpos.parent.right;
                preorder += curpos.data;
            }
            else{
                leftEnd = true;
            }
        }
        
        //reset current node to the root
        curpos = root;
        
        //priming read for right side
        if(curpos.right != null){
            curpos = curpos.right;
            preorder += curpos.data;
        }
        else
            rightEnd = true;
        
        //adds right side of preorder tree traversal to output
        while(!rightEnd){
            if(curpos.left != null){
                curpos = curpos.left;
                preorder += curpos.data;
            }
            else if(curpos.right != null){
                curpos = curpos.right;
                preorder += curpos.data;
            }
            else if(curpos.parent.right != null && curpos.parent.right != curpos){
                curpos = curpos.parent.right;
                preorder += curpos.data;
            }
            else{
                rightEnd = true;
            }
        }
        
        System.out.println("Preorder Traversal: " + preorder);
    }
    
    //left,root,right
    public void inorderTraversal(){
        curpos = root;
        String inorder = "";
        boolean leftEnd = false;
        boolean rightEnd = false;
        
        //priming read for left side
        if(curpos.left != null){
            curpos = curpos.left;
        }
        else
            leftEnd = true;
        
        //adds left side of inorder tree traversal to output
        while(!leftEnd){
            if(curpos.left != null){
                curpos = curpos.left;
            }
            else if(curpos.right != null){
                curpos = curpos.right;
            }
            else if(curpos.parent != root){
                inorder += curpos.data;
                inorder += curpos.parent.data;
                if(curpos.parent.right != null && curpos.parent.right != curpos){
                    curpos = curpos.parent.right;
                    inorder += curpos.data;
                }
                leftEnd = true;
            }
            else{
                inorder += curpos.data;
                leftEnd = true;
            }
        }
        
        //reset current node to the root, and add to inorder output
        curpos = root;
        inorder += curpos.data;
        
        //priming read for right side
        if(curpos.right != null){
            curpos = curpos.right;
        }
        else
            rightEnd = true;
        
        //adds right side of inorder tree traversal to output
        while(!rightEnd){
            if(curpos.left != null){
                curpos = curpos.left;
            }
            else if(curpos.right != null){
                curpos = curpos.right;
            }
            else if(curpos.parent != root){
                inorder += curpos.data;
                inorder += curpos.parent.data;
                if(curpos.parent.right != null && curpos.parent.right != curpos){
                    curpos = curpos.parent.right;
                    inorder += curpos.data;
                }
                rightEnd = true;
            }
            else{
                inorder += curpos.data;
                rightEnd = true;
            }
        }
        
        System.out.println("Inorder Traversal: " + inorder);
    }
    
    //left,right,root
    public void postorderTraversal(){
        curpos = root;
        String postorder = "";
        boolean leftEnd = false;
        boolean rightEnd = false;
        
        //priming read for left side
        if(curpos.left != null){
            curpos = curpos.left;
        }
        else
            leftEnd = true;
        
        //adds left side of inorder tree traversal to output
        while(!leftEnd){
            if(curpos.left != null){
                curpos = curpos.left;
            }
            else if(curpos.right != null){
                curpos = curpos.right;
            }
            else if(curpos.parent != root){
                postorder += curpos.data;
                if(curpos.parent.right != null && curpos.parent.right != curpos){
                    curpos = curpos.parent.right;
                    postorder += curpos.data;
                }
                postorder += curpos.parent.data;
                leftEnd = true;
            }
            else{
                postorder += curpos.data;
                leftEnd = true;
            }
        }
        
        //reset current node to the root
        curpos = root;
        
        //priming read for right side
        if(curpos.right != null){
            curpos = curpos.right;
        }
        else
            rightEnd = true;
        
        //adds right side of inorder tree traversal to output
        while(!rightEnd){
            if(curpos.left != null){
                curpos = curpos.left;
            }
            else if(curpos.right != null){
                curpos = curpos.right;
            }
            else if(curpos.parent != root){
                postorder += curpos.data;
                if(curpos.parent.right != null && curpos.parent.right != curpos){
                    curpos = curpos.parent.right;
                    postorder += curpos.data;
                }
                postorder += curpos.parent.data;
                rightEnd = true;
            }
            else{
                postorder += curpos.data;
                rightEnd = true;
            }
        }
        
        //add root data to postorder output
        postorder += root.data;
        
        System.out.println("Postorder Traversal: " + postorder);
    }
}

public class Project4 {
    public static void main(String args[]){
        BinaryTree tree = new BinaryTree();
        
        Scanner keyboard = new Scanner(System.in);
        String userSelection = "";
        char userInput;
        do{
            menu();
            userSelection = keyboard.nextLine();
            switch(userSelection){
                case "1":
                    System.out.print("Enter a value for the Root:");
                    userInput = keyboard.nextLine().charAt(0);
                    tree.makeRoot(userInput);
                    break;
                case "2":
                    System.out.print("Enter a value for the Left Node:");
                    userInput = keyboard.nextLine().charAt(0);
                    tree.attachLeftNode(userInput);
                    break;
                case "3":
                    System.out.print("Enter a value for the Right Node:");
                    userInput = keyboard.nextLine().charAt(0);
                    tree.attachRightNode(userInput);
                    break;
                case "4":
                    tree.moveLeft();
                    break;
                case "5":
                    tree.moveRight();
                    break;
                case "6":
                    tree.returnToParent();
                    break;
                case "7":
                    tree.returnToRoot();
                    break;
                case "8":
                    System.out.println("Current Node: " + tree.getData());
                    break;
                case "9":
                    System.out.print("Enter the value of the node to delete:");
                    userInput = keyboard.nextLine().charAt(0);
                    tree.deleteNode(userInput);
                    break;
                case "10":
                    tree.preorderTraversal();
                    break;
                case "11":
                    tree.inorderTraversal();
                    break;
                case "12":
                    tree.postorderTraversal();
                    break;
                case "-1":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid user input!");
            }
            System.out.println("");
        }while(Integer.parseInt(userSelection) != -1);
    }
    
    public static void menu(){
        System.out.println("--------USER MENU--------");
        System.out.println("1. Create Root Node");
        System.out.println("2. Attach a Left Node");
        System.out.println("3. Attach a Right Node");
        System.out.println("4. Move to the Left Node");
        System.out.println("5. Move to the Right Node");
        System.out.println("6. Return to the Parent Node");
        System.out.println("7. Return to the Root Node");
        System.out.println("8. Retrieve data of the Current Node");
        System.out.println("9. Delete specified Node");
        System.out.println("10. Display Preorder Traversal");
        System.out.println("11. Display Inorder Traversal");
        System.out.println("12. Display Postorder Traversal");
        System.out.print("ENTER(-1 to quit):");
    }
}
