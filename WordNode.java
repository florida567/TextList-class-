
/**
 * Write a description of class WordNode here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WordNode
{
    private String _word; 
    private WordNode _next; //A recursive vote on the next organ on the list

    //
    // The default constructor, producing an empty IntNode element
    //
    /**
     * Construct a new node with value
     *
     */
    public WordNode(String word){
        _word = word;
        _next = null;
    }

    /**
     * Construct a new node with value and pointer to the next
     *
     */
    public WordNode(String word, WordNode next){
        this._word = word;
        this._next = next;
    }

    /**
     * This method gets the word in node.
     * 
     * @return Return the word in node.
     */
    public String getWord(){
        return _word ;
    }

    /**
     * This method sets the word in node
     * 
     * @param w - this word set in node
     */
    public void setWord(String w){
        _word=w ;
    }

    /**
     * This method promotes ptinter.
     */
    public WordNode getNext(){
        return _next ;
    }

     /**
     * This method sets the pointer on next node
     * 
     * @param Return node - pointer on next node
     */
    public void setNext(WordNode node){
        _next=node ;
    }

}
