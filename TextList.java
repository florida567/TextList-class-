/**
 * Write a description of class TextList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TextList
{
    private WordNode _head;

    /**
     * Construct a new clear TextList - The empty builder that creates an empty list
     * 
     * The time complexity is 1 = O(1).
     * The space complexity is O(1) - you only working on the same list.
     */
    public TextList(){
        _head = null;
    }

    /**
     * Construct a new TextList with your text .
     * The list is sorted in lexicographic order.
     * 
     * @param text -- Its your text - sentence
     * 
     * The time complexity is nlogn = O(nlogn). n*logn (logn - mergeSort)
     * The space complexity is O(2) - you only working on the same list + p .
     */
    public TextList(String text){
        if(empty(text)){ //Check if text is eampty set _head null and return
            _head = null;
            return;
        }
        _head = new WordNode(getFirstWord(text)); //The top of the list will point to the first word from the string text
        text = deletFirstWord(text); //Delet first word from string text
        WordNode p = _head; // make new node to point to the top of the list
        while (text!=""){ // Stop conditions as long as the resulting string is not empty
            p.setNext(new WordNode(getFirstWord(text))); //The next of the list will point to the first word from the string text          
            text = deletFirstWord(text); //Delet first word from string text
            p = p.getNext(); //Move on to the next node

        }
        _head = mergeSort(_head); //Make sorting in lexicographic order

    }

    /**
     * Add your new text to the List in the appropriate 
     * place in terms of lexicographic order
     * 
     * The time complexity is O(n). while loop
     * The space complexity is O(3) - you working on the list + p + w.
     */
    public void addToData (String word){
        WordNode temp = _head; // make new node
        WordNode w = new WordNode(word); // make new node that pointer on word you get

        if (word == null) // check if the word is empty
            return ;

        if (temp == null){ // check if the list is empty
            _head =new WordNode(word) ; //top of list pointer on word you get
            return;
        }
        if (temp.getNext() == null){ // check if the next node is empty
            temp.setNext(w) ; //next of list pointer on word you get
            return;
        }
        if (temp.getWord().compareTo(word)>=0){ // check if your word you get smaller the first word in list like word = a top of list its b so a need to be before
            w.setNext(temp); // the new node of the word next pointer on the head of list
            _head=w; // the head of list pointer on the word you get
            return;
        }else{
            while (temp != null && temp.getNext() !=null){  // Stop conditions as long as that node is not empty and not the next node
                if(temp.getWord().compareTo(word)<0 && temp.getNext().getWord().compareTo(word)>=0){ // check if your word you get bigger the word in list and smaller or equal the next word in list like word = b the word of now in list is a and the next its c so b need to be after a and before c
                    w.setNext(temp.getNext()); // the new node of the word next pointer on the next node
                    temp.setNext(w); //the next node in list will pointer on the word u get 
                    return;
                }               
                temp = temp.getNext(); //Promote the pointer
                if(temp.getWord().compareTo(word)<0 && temp.getNext()==null){ // checkck if your word you get bigger the word in list and next node its empty 
                    temp.setNext(w); // the word you get will be pointer in last
                    return;
                }

            }
        }
    }

    /**
     * Calculates the number of words
     *
     * @return Returns the total number of words in the text
     * 
     * The time complexity is O(n). while loop
     * The space complexity is O(2) - you only working on the same list + temp.
     */
    public int howManyWords (){        

        WordNode temp = _head; //make new node that pointer on the head of list
        int count = 0; // make counter to count the number of word
        while (temp != null){ // Stop conditions as long as that node is not empty 
            count++; //As long as I did not reach the end of the junction to advance the counter
            temp = temp.getNext(); //Promote the pointer     
        }
        return count; 

    }

    /**
     * Calculates the number of different words
     *
     * @return Returns the number of different words in the text
     * 
     * The time complexity is O(n). while loop
     * The space complexity is O(2) - you only working on the same list + temp.
     */
    public int howManyDifferentWords (){
        WordNode temp = _head; //make new node that pointer on the head of list

        int c = 0,h=howManyWords(); // make counter to count the number of equal and check how many word u have beacuse the remainder between them is the number of different words
        if (_head == null) return 0;

        while (temp != null ) // Stop conditions as long as that node is not empty 
        {
            if(temp.getNext() == null) //If I came last on the list 
                return h-c;

            if(temp.getWord().equals(temp.getNext().getWord()))  // If the words are identical you will advance the counter
                c++;

            temp = temp.getNext();//Promote the pointer   
        }

        return h-c;
    }

    /**
     * Checks what is the most common word in the text,
     * if there is more than one such word, 
     * the first of them will be returned by sorting the list
     *
     * @return Returns the most common word in the text
     * 
     * The time complexity is O(n). while loop
     * The space complexity is O(3) - you only working on the same list + mostF + next.
     */
    public String mostFrequentWord (){
        if(_head == null) return ""; //if the string its empty retrun empty string

        WordNode mostF = _head; //make new node that pointer on the head of list
        WordNode next = _head.getNext(); //make new node that pointer on the next of head of list
        int count = 0;
        while (next != null){  // Stop conditions as long as that next node is not empty          
            if(getMulti(next) > getMulti(mostF)) // check if frequent next node bigger the frequent of before node
                mostF= next;
            next= next.getNext();

        }

        return mostF.getWord(); // return the most frequent word
    }

    /**
     * Check by a letter and calculates the number 
     * of words in the text that begin with this letter
     *
     * @param Set your letter to check
     * @return Returns the number of words in the text that begin with this letter.
     * 
     * The time complexity is O(n). while loop
     * The space complexity is O(2) - you only working on the same list + curr.
     * 
     */
    public int howManyStarting (char letter){ 
        WordNode curr = _head; //make new node that pointer on the head of list
        int count = 0; //make a counter

        while( curr !=null && curr.getWord().charAt(0) <= letter){ //check if you have reached the end of the list and also the first letter in the word that is on the list is less than or equal to the letter you receive
            if(curr.getWord().charAt(0) == letter){ // if the first letter in the word that is on the list eqaul your letter you get 
                count++; //Promote the counter
            }
            curr = curr.getNext();
        } 
        return count;

    }

    /**
     * Checks which letter most words begin with in the text.
     * If there are more than one such hundred, the first of them will
     * be returned, in order of sorting the list.
     *
     * @return Returns the letter that most words begin with in the text
     */
    public char mostFrequentStartingLetter (){
        return mostFrequentStartingLetter(_head, ' ',0); //Send these values to the recursion function 
    }

    /**
     * This method Passes the list to a string of characters representing the list .
     * When all the words appear in it, and next to each word appears the 
     * number of its occurrences in the text.
     *
     * @return Return the list in the string.
     * 
     * The time complexity is O(n). while loop
     * The space complexity is O(2) - you only working on the same list + temp.
     */
    public String toString(){
        String s = ""; //make empty string
        WordNode temp = _head; //make new node that pointer on the head of list

        int c = 1; // start with first word
        if (_head == null) return s; // if the list empty nothing no matter and returns an empty string

        while (temp != null ) // Stop conditions as long as that node is not empty 
        {
            if(temp.getNext() == null) //if next node empty
                return s += temp.getWord() + "\t" + c +"\n"; // add to string the word in node and tab and the amount of the word in the string and downline

            if(temp.getWord().equals(temp.getNext().getWord()) ==  false){ // if the word not qauals to the next word in list 
                s += temp.getWord() + "\t" + c +"\n";
                c=1;
            } 
            if(temp.getWord().equals(temp.getNext().getWord()))  // if the word qauals to the next word in list 
                c++;

            temp = temp.getNext(); //Promote the pointer
        }

        return s; //return the string
    }

    private char mostFrequentStartingLetter (WordNode curr, char most,int count){
        if(curr==null) return most;
        int currw = howManyStarting(curr.getWord().charAt(0));
        if(currw > count){
            count=currw;
            most = curr.getWord().charAt(0);

        }
        return mostFrequentStartingLetter(curr.getNext(),most,count);

    }

    private boolean empty(){
        return _head ==  null;
    }

    private boolean empty(String word){
        return (word.length()==0);
    }

    private int getMulti(WordNode word){   
        //check how much this word in this node how many times does it appear in the list
        WordNode curr = _head; 
        int count = 0;

        while( curr !=null ){
            if(curr.getWord().equals( word.getWord())){
                count++;
            }
            curr = curr.getNext();
        } 
        return count;
    }

    private String getFirstWord(String text){
        int index = text.indexOf(' '); // cheack the first sapce beacuse after word you get sapce and this is the fisrt word

        if (index > -1) { // Check if there is more than one word.

            return text.substring(0, index); // Extract first word.

        } else {

            return text; // Text is the first word itself.
        }

    }

    private String deletFirstWord(String text){
        int index = text.indexOf(" "); // cheack the first sapce beacuse after word you get sapce and this is the fisrt word

        if (index > -1) { // Check if there is more than one word.

            return text.substring(index+1); // Delet first word.

        } else {

            return ""; // Text is the first word itself.
        }

    }

    private void mergeSort()
    {
        _head =mergeSort(_head);
    }

    private WordNode mergeSort (WordNode node)
    {
        if (node == null || node.getNext() == null)
            return node; // checks for empty or single list
        WordNode list2 = split (node);
        node = mergeSort (node);
        list2 = mergeSort (list2);
        return merge (node, list2);

    } // end merge_sort
    private WordNode split(WordNode node){
        if (node == null || node.getNext() == null)
            return null;
        WordNode list2 = node.getNext();
        node.setNext(list2.getNext());
        list2.setNext(split(list2.getNext()));
        return list2;

    }

    private WordNode merge(WordNode list1, WordNode list2){

        if (list2 == null) return list1;
        if (list1 == null) return list2;

        if (list1.getWord().compareTo(list2.getWord())<0) {
            list1.setNext(merge (list1.getNext(), list2));
            return list1;
        } // end if
        else {
            list2.setNext(merge (list1, list2.getNext()));
            return list2;

        } // end else

    }
}
