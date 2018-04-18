# Ticketing-System

# Basic Functionalities:
1.	Handling customer services tickets
2.	Storing large bodies of text in a structured way.
3.	Providing basic analytics about the calls. 
4.	Helping to channel customers’ request. 

# Users:
1.	Customer service representative
2.	Customer services analyst

# Design

# 1.	Ticket: 
a.	It is a super class, with three subclasses (PhoneCall, Email, InPerson). /
b.	These subclasses all share basic ticket info (i.e. String ticketID, String customerRepID, String dateCreated, String customerServiceRepID), customer information (String id,  String name, int phonenumber, String Address). 
c.	Also, some basic information about the service that the customer has contacted the company about (String serviceID, String serviceStartDate, String serviceEndDate,), 
d.	and some information about if the contact (boolean isResolved, and int departmentID). departmentID holds the ID of the department that the customer service transfers the ticket to. If the phone call is resolved, then the department ID will be 0. Otherwise, it is something between 1 and 10 (for different departments) or -1 (if the ticket is suspended). 
e.	Finally, a Transcript object and a contactHandler method. 
f.	Each subclass, however, has a different contactHandler method. 
i.	For the emails, all the information is fetched to the system automatically, and the customer service rep only needs to view them and determine a next step for the ticket.  Therefore for the Email class, the contactHandler method receives customer information, and service information, as input arguments. It also receives the actual text of the email as an argument. It then does two things: (1) prints the information and the text on the screen for the customer service representative and allow them to identify if the Ticket isResolved, or route it to a new departmentID. (2) creates a Transcript object from the text.
ii.	For the in-person tickets, the customer service representative fills the customer and service information while the customer is in the store, but makes a decision about the ticket and writes down what he/she remembers from the conversation only after the customer leaves the store.  The contactHandler method of the InPerson class does the same. The customer service representative needs to be able to update the customer and service information while the customer is present. When the customer leaves the store there is no need to update those info anymore, and only then, first, the representative should make a decision about isResolved and departmentID, and, secod, a Transcript object should be created as he/she types in what she remembers from the conversation. 
iii.	For phone conversations, the representative is constantly typing. He/she is typing what the customer says, and enters or updates their name or address or service info in between. The contactHandler should be able to do the same.  A representative should always finish a sentence (by clicking enter) before being able to update information.

# 2.	Transcript:
a.	The Transcript object stores the text in an ArrayList sentencesList, with each element being another ArrayList wordsList of type Strings.  In other words, the text stored in both words and seentences. There is one ArrayList of sentences (ArrayList sentencesList) which stores the sentences. Each sentence is stored in the form of an ArrayList  of Strings (ArrayList wordsList) with each element being a word of that sentence. 
b.	Each word only consists of alphabets or numbers. No full stops, no semicolumns, no parentheses, etc.
c.	The transcript class has a public final statice array of type String which stores the stopWords in English, a public final static array of type String keeping a number of posetiveLexicons and a public final static array of type String keeping a number of negativeLexicons (see Appendix 1 for the list of each of these groups of words).
d.	All of the stop words in the sentences are removed. 
e.	Each Transcript has a two dimensional frequencies[totalWordCount][2] array in which frequencies[totalWordCount][0]s are words and of type String and frequencies[totalWordCount][1]s are frequency of that word in that text and of type double. 
f.	Each Transcript object has an averageSentenceLength attribute of type double, and two get and set methods for accessing and calculating that.
g.	Each Transcript object has a topFreqWords[3] of type string keeping the top 3 most used words, and two methods for getting and setting that. 
h.	Each Transcript object has a getSimilarity method which gets a Transcript object in its argument and checks the similarity between most frequent words of that Transcript object (i.e. inputTranscript.topFreqWords) with its own object (i.e. this.topFreqWords[]).

# 3.	FlowMonitor:
a.	FlowMonitor has a public static array of arrayRepresentativeIDs, arrayServiceIDs
b.	The FlowMonitor class manages the execution flow of the program.
c.	A flow manager class has an ArrayList of type Ticket.
d.	Every time that there is a new email, in-person, or phone contact with the company, the FlowMonitor, creates a new Ticket.  
e.	FlowMonitor has an updateTickets method. After each ticket is created, the flow monitor loops over all of the tickets in the array to make sure there is no suspended ticket. If it finds any suspended ticket, it checks the similarity between its three more frequent words of that ticket with the three most frequent words of all other tickets in the array, and sets the departmentID of that ticket to be the same as the departmentID of the ticket with highest similarity.
f.	FlowMonitor has a getSentiment method which returns an integer between 1 and 3, 1 being angriest conversation and 3 being happiest conversation. 
g.	FlowMonitor has a sentimentAnalysis method, which receives no argument and returns an ArrayList of three objects. Object one is an integer, which is the overall sentiment scores of the Tickets, object two is the average sentiment scores of the ticket for each customer representative, and object three is the average sentiment scores for the services.
h.	FlowMonitor has a printAnalysis methods that prints the results of one of the three analyses in 3.g. The method receives an integer between 0 to 2 (corresponsing to different analyses) and prints the requested analysis on the screen. 
i.	FlowMonitor has a printFlowStatus method that prints all tickets in the system, their dateCreate, isSolved, and their status in a readable format.

# 4.	Execution flow:
a.	The program waits for the representative to enter the type of contact (“phone”, “email”, “inperson”) or for the analyst to type “analysis”.
b.	If phone, email or inperson is selected, a Ticket of that type is created, and the program goes back to wait for a new contact. 
c.	If analysis is selected, a menu with one item for each type of analysis, created in the FlowMonitor class in 3.g, another item for printing the flow status, and another item for updating the status of the tickets (calling updateTickets in 3.4) 


