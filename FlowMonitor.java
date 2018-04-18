import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FlowMonitor {
	public static int[] arrayRepresentativeIDs;
	public static int[] arrayServiceIDs;
	
	private ArrayList<Ticket> ticketList;
	
	public FlowMonitor(){
		arrayRepresentativeIDs = new int[100];
		arrayServiceIDs = new int[100];
		
		ticketList = new ArrayList<Ticket>();
	}
	
	public void addTicketToList(Ticket ticket){
		ticketList.add(ticket);
	}
	
	public void updateTicket(){
		int matchCount = 0;
		
		for(int counter=0; counter<ticketList.size(); counter++){
			Ticket ticket = ticketList.get(counter);
			if(ticket.departmentID == -1){
				for(int counter2=0; counter2<ticketList.size(); counter2++){
					Ticket ticket2 = ticketList.get(counter2);
					if(ticket.ticketID != ticket2.ticketID){
						
						int matches = 0;
						if((Arrays.asList(ticket.transcriptObject.getTopFreqWords()).contains(ticket2.transcriptObject.getTopFreqWords()[0]))){
							matches++;
						}
						
						if((Arrays.asList(ticket.transcriptObject.getTopFreqWords()).contains(ticket2.transcriptObject.getTopFreqWords()[1]))){
							matches++;
						}
						
						if((Arrays.asList(ticket.transcriptObject.getTopFreqWords()).contains(ticket2.transcriptObject.getTopFreqWords()[2]))){
							matches++;
						}
						
						if(matches > matchCount){
							matchCount = matches;
							ticketList.get(counter).departmentID = ticket2.departmentID;
							if(matches == 3){
								break;
							}
						}						
					}
				}
			}
		}		
	}
	
	public int getSentiment(Ticket ticket){
		int feedback = -1;
		int positiveWords = 0;
		int negativeWords = 0;
		
		for(int counter=0; counter<ticket.transcriptObject.getSentenceList().size(); counter++){
			for(int counter2=0; counter2<ticket.transcriptObject.getSentenceList().get(counter).size(); counter2++){
				String word = ticket.transcriptObject.getSentenceList().get(counter).get(counter2);
				if(Arrays.asList(Transcript.positiveLexicons).contains(word)){
					positiveWords++;
				}
				else if(Arrays.asList(Transcript.negativeLexicons).contains(word)){
					negativeWords++;
				}
			}
		}
		
		if(positiveWords > 0 && negativeWords == 0){
			feedback = 3;
		}
		else if(negativeWords > 0 && positiveWords == 0){
			feedback = 1;
		}
		else{
			feedback = 2;
		}
		
		return feedback;
	}
	
	public ArrayList<Object> sentimentAnalysis(){
		ArrayList<Object> response = new ArrayList<>();
		
		HashMap<String, ArrayList<Ticket>> ticketsByCSR = new HashMap<String, ArrayList<Ticket>>();
		HashMap<String, ArrayList<Ticket>> ticketsByService = new HashMap<String, ArrayList<Ticket>>();
		HashMap<String, Double> csrAverageSentiment = new HashMap<String, Double>();
		HashMap<String, Double> serviceAverageSentiment = new HashMap<String, Double>();
		
		for(int counter=0; counter<ticketList.size(); counter++){
			String csr = ticketList.get(counter).customerServiceRepID;
			String serviceId = ticketList.get(counter).serviceInformationObject.getServiceID();
			
			if(!(ticketsByCSR.containsKey(csr))){
				ticketsByCSR.put(csr, new ArrayList<Ticket>());
			}
			
			if(!ticketsByService.containsKey(serviceId)){
				ticketsByService.put(serviceId, new ArrayList<Ticket>());
			}
			
			ticketsByCSR.get(csr).add(ticketList.get(counter));
			ticketsByService.get(serviceId).add(ticketList.get(counter));
		}
		
		int totalSentimentScore = 0;
		
		//Calculate Total Sentiment Score
		for(int counter=0; counter<ticketList.size(); counter++){
			totalSentimentScore += getSentiment(ticketList.get(counter));			
		}
		
		//Calculate Average Sentiment Score by CSR
		Iterator it = ticketsByCSR.entrySet().iterator();
		
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        ArrayList<Ticket> tokenList = (ArrayList<Ticket>)pair.getValue();
	        int totalCSRSentimentScore = 0;
	        for(int counter=0; counter<tokenList.size(); counter++){
	        	totalCSRSentimentScore += getSentiment(tokenList.get(counter));
	        	double averageSentiment = totalCSRSentimentScore / tokenList.size();
	        	csrAverageSentiment.put(pair.getKey().toString(), averageSentiment);
	        }
	    }
		
	    //Calculate Average Sentiment Score by Service
	    Iterator it1 = ticketsByService.entrySet().iterator();
		
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it1.next();
	        ArrayList<Ticket> tokenList = (ArrayList<Ticket>)pair.getValue();
	        int totalServiceSentimentScore = 0;
	        for(int counter=0; counter<tokenList.size(); counter++){
	        	totalServiceSentimentScore += getSentiment(tokenList.get(counter));
	        	double averageSentiment = totalServiceSentimentScore / tokenList.size();
	        	serviceAverageSentiment.put(pair.getKey().toString(), averageSentiment);
	        }
	    }
	    
	    response.add(totalSentimentScore);
	    response.add(csrAverageSentiment);
	    response.add(serviceAverageSentiment);
	    
		return response;
	}
	
	public void printAnalysis(int value){
		ArrayList<Object> sentimentResponse = sentimentAnalysis();
		
		System.out.println("\n\nSentiment Analysis");
		System.out.println("******************");
		
		if(value == 1){
			int sentimentAnalysis = (Integer)sentimentResponse.get(0);
			System.out.println("Total Sentiment = " + sentimentAnalysis);
		}
		
		if(value == 2){
			HashMap<String, Double> csrSentiment = (HashMap<String, Double>)sentimentResponse.get(1);
			Iterator it = csrSentiment.entrySet().iterator();
			
			System.out.println("CSR \t\t\t Average Sentiment");
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey().toString() + "\t\t\t" + pair.getValue().toString());		        
		    }	
		}
		
		if(value == 3){
			HashMap<String, Double> serviceSentiment = (HashMap<String, Double>)sentimentResponse.get(2);
			Iterator it = serviceSentiment.entrySet().iterator();
			
			System.out.println("Servoce \t\t\t Average Sentiment");
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey().toString() + "\t\t\t" + pair.getValue().toString());		        
		    }	
		}
	}
	
	public void printFlowStatus(){
		for(int counter=0; counter<ticketList.size(); counter++){
			Ticket ticket = ticketList.get(counter);			
			System.out.println(ticket.ticketID + "\t\t" + ticket.dateCreated + "\t\t" + ticket.isResolved);
		}
	}

	public ArrayList<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(ArrayList<Ticket> ticketList) {
		this.ticketList = ticketList;
	}
}
