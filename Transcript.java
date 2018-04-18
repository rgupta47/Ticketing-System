import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Transcript {
	private final int topFrequencySize;
	
	private ArrayList<WordList> sentenceList;
	
	private double averageSentenceLength;
	private String[] topFreqWords;
	private HashMap<String, Integer> wordFrequency;
	
	private String transcriptText = "";
	//point e needs to implement
	
	public final static String[] stopWords = {"a","about","above","after","again","against","all","am","an","and","any","are","aren't","as","at","be","because","been","before","being","below","between",
			"both","but","by","can't","cannot","could","couldn't","did","didn't","do","does","doesn't","doing","don't","down","during","each","few","for","from","further",
			"had","hadn't","has","hasn't","have","haven't","having","he","he'd","he'll","he's","her","here","here's","hers","herself","him","himself","his","how","how's",
			"I","I'd","I'll","I'm","I've","if","in","into","is","isn't","it","it's","its","itself","let's","me","more","most","mustn't","my","myself","no","nor","not","of",
			"off","on","once","only","or","other","ought","our","ours","ourselves","out","over","own","same","shan't","she","she'd","she'll","she's","should","shouldn't",
			"so","some","such","than","that","that's","the","their","theirs","them","themselves","then","there","there's","these","they","they'd","they'll","they're",
			"they've","this","those","through","to","too","under","until","up","very","was","wasn't","we","we'd","we'll","we're","we've","were","weren't","what","what's",
			"when","when's","where","where's","which","while","who","who's","whom","why","why's","with","won't","would","wouldn't","you","you'd","you'll","you're","you've",
			"your","yours","yourself","yourselves"}; 
		
	public final static String[] positiveLexicons = {"great","grateful","good","helpful","thankful","best","better","best","beyond",
			"comfortable","commitment","committed","renew","register","unbelievable","unbelievably","awesome","wonderful"};
	
	public final static String[] negativeLexicons = {"bad","worse","worst","crazy","confuse","confused","confusing","disturb","disturbance",
			"disturbed","angry","annoy","annoyed","annoying","annoyingly","refuse","refused","regret","reject","rejected","repetitive","repetitively","cancel","discontinue","terminate","mad","unhappy","unhelpful","unlucky"};
	
	public Transcript(){
		topFrequencySize = 3;
		sentenceList = new ArrayList<WordList>();
		topFreqWords = new String[topFrequencySize];
		wordFrequency = new HashMap<String, Integer>();
	}

	public boolean getSimilarity(Transcript transcriptObj){
		boolean isSimilar = true;
		
		for(int counter=0; counter<topFreqWords.length; counter++){
			if(!(Arrays.asList(transcriptObj.getTopFreqWords()).contains(topFreqWords[counter]))){
				isSimilar = false;
			}
		}
		
		return isSimilar;
	}
	
	public double getAverageSentenceLength() {
		return averageSentenceLength;
	}

	public void setAverageSentenceLength() {
		int totalWords = 0;
		for(int counter=0; counter<sentenceList.size(); counter++){
			totalWords += sentenceList.get(counter).size();
		}
		
		this.averageSentenceLength = totalWords / sentenceList.size();
	}

	public String[] getTopFreqWords() {
		return topFreqWords;
	}

	public void setTopFreqWords() {		
		Iterator it = wordFrequency.entrySet().iterator();
		int counter=0;
		
	    while (it.hasNext() && counter< topFrequencySize) {
	        Map.Entry pair = (Map.Entry)it.next();
	        this.topFreqWords[counter] = pair.getKey().toString();	        
	    }		
	}
	
	public void populateTranscript(String text){
		this.transcriptText = text;
		
		StringTokenizer sentenceTokenizer = new StringTokenizer(text, "\n\r");
		
		while(sentenceTokenizer.hasMoreTokens()){
			String sentence = sentenceTokenizer.nextToken();
			StringTokenizer wordTokenizer = new StringTokenizer(sentence, " ");
			WordList wordList = new WordList();
			
			while(wordTokenizer.hasMoreTokens()){
				String word = wordTokenizer.nextToken();
				word = word.replaceAll("^a-zA-Z0-9", "");
				wordList.add(word);
				
				if(wordFrequency.containsKey(word)){
					wordFrequency.put(word, wordFrequency.get(word) + 1);
				}
				else{
					wordFrequency.put(word, 1);
				}
			}
			sentenceList.add(wordList);
		}
		
		removeStopWords();	
		wordFrequency = (HashMap<String, Integer>)sortByValue(wordFrequency);
	}
	
	private Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/


        return sortedMap;
    }
	private void removeStopWords(){
		for(int counter=0; counter<sentenceList.size(); counter++){
			for(int counter2=0; counter2<sentenceList.get(counter).size(); counter2++){
				if(Arrays.asList(stopWords).contains(sentenceList.get(counter).get(counter2))){
					sentenceList.get(counter).remove(counter2);
				}
			}
		}
	}

	public ArrayList<WordList> getSentenceList() {
		return sentenceList;
	}

	public void setSentenceList(ArrayList<WordList> sentenceList) {
		this.sentenceList = sentenceList;
	}
	
	public String getTranscript(){
		return transcriptText;
	}
	
	public void setTranscript(String text){
		this.transcriptText = text;
	}
}

class WordList extends ArrayList<String> {
	
	public WordList(){
		super();
	}
}
