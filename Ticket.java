
public class Ticket {
	private static int autoTicketID = 0;
	
	protected String ticketID;
	protected String customerRepID;
	protected String dateCreated;
	protected String customerServiceRepID;
	
	protected boolean isResolved;
	protected int departmentID;
	
	protected CustomerInformation customerInformationObject;
	protected ServiceInformation serviceInformationObject;	
	protected Transcript transcriptObject;
	
	public Ticket(){
		ticketID = "TK" + String.valueOf(autoTicketID++);
		customerRepID = "";
		dateCreated = "";
		customerServiceRepID = "";
		
		isResolved = false;
		departmentID = -1;
	
		customerInformationObject = new CustomerInformation();
		serviceInformationObject = new ServiceInformation();
		transcriptObject = new Transcript();
	}
	
	
}

class CustomerInformation {
	private static int autoIncrementCustomerId = 0;
	
	private String customerID;
	private String customerName;
	private int customerPhoneNumber;
	private String customerAddress;
	
	public CustomerInformation(){
		autoIncrementCustomerId++;
		
		customerID = String.valueOf(autoIncrementCustomerId);
		customerName = "";
		customerPhoneNumber = -1;
		customerAddress = "";
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(int customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
		
}

class ServiceInformation {
	private String serviceID;
	private String serviceStartDate;
	private String serviceEndDate;
	
	public ServiceInformation(){
		serviceID = "";
		serviceStartDate = "";
		serviceEndDate = "";
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public String getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}
	
	
}

