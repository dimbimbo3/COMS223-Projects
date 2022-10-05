import java.util.*;

public class Project2 {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        
        list1.add("Clive");
        list1.add("Ellen");
        list1.add("Jake");
        list1.add("Yan");
        
        System.out.println("-List 1-");
        print(list1);
        
        list2.add("Amar");
        list2.add("Boris");
        list2.add("Jake");
        list2.add("Liz");
        list2.add("Yan");
        
        System.out.println("\n-List 2-");
        print(list2);
        
        merge(list1, list2);
        
        //(Test 1 with similiar and different strings)
        list1.add("Random123");
        list1.add("Testing456");
        list1.add("I do not know");
        sort(list1);
        
        list2.add("Sussex NJ");
        list2.add("United States");
        sort(list2);
        
        System.out.print("\nTest #1");
        merge(list1, list2);
        
        //(Test 2 with similiar and different strings)
        list1.add("ABCEFG");
        list1.add("HIJKLM");
        sort(list1);
        
        list2.add("1234567890");
        sort(list2);
        
        System.out.print("\nTest #2");
        merge(list1, list2);
    }
    
    //Prints the elements from the list argument
    public static void print(ArrayList<String> list){
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
    
    //Merges the two lists provided into one
    public static void merge(ArrayList<String> list1, ArrayList<String> list2){
        ArrayList<String> mergedList = new ArrayList<String>();
        
        int ptr1 = 0;//list1's pointer
        int ptr2 = 0;//list2's pointer
        
        while(ptr1 < list1.size() && ptr2 < list2.size()){
            //if list1's string comes before list2's string alphabetically
            //then add list1's string to the merged list and increment list1's pointer
            if(list1.get(ptr1).compareToIgnoreCase(list2.get(ptr2)) < 0){
                mergedList.add(list1.get(ptr1));
                ptr1++;
            }
            //else if list1's string comes after list2's string alphabetically
            //then add list2's string to the merged list and increment list2's pointer
            else if(list1.get(ptr1).compareToIgnoreCase(list2.get(ptr2)) > 0){
                mergedList.add(list2.get(ptr2));
                ptr2++;
            }
            //else both strings are equal
            //add list1's string to the merged list and increment both pointers
            else{
                mergedList.add(list1.get(ptr1));
                ptr1++;
                ptr2++;
            }
        }
        
        //if list1's pointer is greater than or equal to the size of its list
        //add what remains of list2 to the merged list
        if(ptr1 >= list1.size()){
            while(ptr2 < list2.size()){
                mergedList.add(list2.get(ptr2));
                ptr2++;
            }
        }
        
        //if list2's pointer is greater than or equal to the size of its list
        //add what remains of list1 to the merged list
        if(ptr2 >= list2.size()){
            while(ptr1 < list1.size()){
                mergedList.add(list1.get(ptr1));
                ptr1++;
            }
        }
        
        System.out.println("\n-Merged List-");
        print(mergedList);
    }
    
    //Sorts the provided list alphabetically
    public static void sort(ArrayList<String> list){
        String temp = "";
        
        for(int outerCount = 0; outerCount < list.size(); outerCount++){
            //compares every String in the list following the one at the current index
            for(int innerCount = outerCount + 1; innerCount < list.size(); innerCount++){
                //if the next String comes before the one at the current index
                //then replace their positions in the list
                if(list.get(innerCount).compareToIgnoreCase(list.get(outerCount)) < 0){
                    temp = list.get(outerCount);
                    list.set(outerCount, list.get(innerCount));
                    list.set(innerCount, temp);
                }
            }
        }
    }
}
