// insert header here


import java.util.ArrayList;
import java.util.List;

public class Theater {
	private int totalRows;		
	private int seatsInRow;	

	private String theShow;
	private ArrayList<ArrayList<Seat>> theaterSeats = new ArrayList<ArrayList<Seat>>();
	private ArrayList<Ticket> ticketLog = new ArrayList<Ticket>();
	
	
	
	/*
	 * Represents a seat in the theater
	 * A1, A2, A3, ... B1, B2, B3 ...
	 */
	static class Seat {
		private int rowNum;
		private int seatNum;
		private boolean reserved = false; 

		public Seat(int rowNum, int seatNum) {
			this.rowNum = rowNum;
			this.seatNum = seatNum;
		}

		public int getSeatNum() {
			return seatNum;
		}

		public int getRowNum() {
			return rowNum;
		}
		
		public synchronized boolean isReserved(){
			return reserved;
		}
		public synchronized void seatTaken(){
			reserved = true;
			return;
		}
		

		@Override	
		public String toString() {
			// TODO: Implement this method to return the full Seat location ex: A1
			char letter;	
			String seatRev = new String();
			String seat = "";
			
			int rowVal = rowNum;
			
			while (rowVal > 0){
				rowVal--; // row numbers provided are from 1 to 26
				letter = (char) (rowVal%26 + 'A'); // number to ascii letter conversion
				seatRev = seatRev + letter;
				rowVal = rowVal/26;
			}
			
			/* reverse string */
			char[] seatLoc = seatRev.toCharArray();
			int revLength = seatRev.length();
			for (int i=0; i<revLength; i++){
				seat = seat + seatLoc[revLength - 1 - i];
			}
			seat = seat + seatNum;
			
			return seat;		
		}
	}

  /*
	 * Represents a ticket purchased by a client
	 */
	static class Ticket {
		private String show;
		private String boxOfficeId;
		private Seat seat;
		private int client;

		public Ticket(String show, String boxOfficeId, Seat seat, int client) {
			this.show = show;
			this.boxOfficeId = boxOfficeId;
			this.seat = seat;
			this.client = client;
		}

		public Seat getSeat() {
			return seat;
		}

		public String getShow() {
			return show;
		}

		public String getBoxOfficeId() {
			return boxOfficeId;
		}

		public int getClient() {
			return client;
		}

		@Override
		public String toString() {
			// TODO: Implement this method to return a string that resembles a ticket
			String result = "";
			String first = "";
			String show = "";
			String boxOffice = "";
			String seat = "";
			String client = "";
			String end = ""; 
			
			for (int i=0; i< 31; i++){
				first = first.concat("-");
			}
			first = first.concat("\n");
			
			show = "| Show: " + getShow();
			int strlen = show.length() - 1;
			for (int i = 0; i < (29 - strlen ); i++){
				show = show.concat(" ");
			}
			show = show.concat("|\n");			
			
			boxOffice = "| Box Office ID: " + getBoxOfficeId();
			strlen = boxOffice.length() - 1;
			for (int i = 0; i < (29 - strlen ); i++){
				boxOffice = boxOffice.concat(" ");
			}			
			boxOffice = boxOffice.concat("|\n");
			
			seat = "| Seat: " + getSeat();
			strlen = seat.length() - 1;
			for (int i = 0; i < (29 - strlen); i++){
				seat = seat.concat(" ");
			}			
			seat = seat.concat("|\n");
						
			client = "| Client: " + getClient();
			strlen = client.length() - 1;
			for (int i = 0; i < (29 - strlen ); i++){
				client = client.concat(" ");
			}
			client = client.concat("|\n");
			
			end = first;
			
			result = first + show + boxOffice + seat + client + end;			
			return result;

		}
	}

	public Theater(int numRows, int seatsPerRow, String show) {
		// TODO: Implement this constructor
		totalRows = numRows;
		seatsInRow = seatsPerRow;
		theShow = show;
		for (int i = 0; i < numRows; i++){
			theaterSeats.add(new ArrayList<>());
			for (int j =0; j<seatsPerRow; j++){
				theaterSeats.get(i).add(new Seat(i+1,j+1));	
			}
		}

	}

	/*
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
   */
	public synchronized Seat bestAvailableSeat() {
		//TODO: Implement this method
		for (int i = 0; i < totalRows; i++){
			for(int j =0; j< seatsInRow; j++){
				if (theaterSeats.get(i).get(j).isReserved() == false){
					
					return theaterSeats.get(i).get(j);
				}
			}
		}

		return null;
		
	}

	/*
	 * Prints a ticket for the client after they reserve a seat
   * Also prints the ticket to the console
	 *
   * @param seat a particular seat in the theater
   * @return a ticket or null if a box office failed to reserve the seat
   */
	public Ticket printTicket(String boxOfficeId, Seat seat, int client) {
		//TODO: Implement this method

		if (seat == null){
			return null;
		}
		if (seat.isReserved() == false){
			seat.seatTaken();
			Ticket t = new Ticket(theShow,boxOfficeId,seat,client);
						
			System.out.println(t.toString());
			ticketLog.add(t);
			return t;
		}
			return null;
		
	}

	/*
	 * Lists all tickets sold for this theater in order of purchase
	 *
   * @return list of tickets sold
   */
	public synchronized List<Ticket> getTransactionLog() {
		//TODO: Implement this method

		return ticketLog;

	}

}
