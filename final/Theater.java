// insert header here
package assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.lang.Math.*;

public class Theater {
	/*
	 * Represents a seat in the theater A1, A2, A3, ... B1, B2, B3 ...
	 */
	static class Seat {
		private int rowNum;
		private int seatNum;

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

		

		@Override
		public String toString() {
			// Implement this method to return the full Seat location ex:
			// A1
			int val = rowNum;
			char[] buf = new char[(int) floor(log(25 * (val + 1)) / log(26))];
		    for (int i = buf.length - 1; i >= 0; i--) {
		        val--;
		        buf[i] = (char) ('A' + val % 26);
		        val /= 26;
		    }
		    String s = new String(buf);

			// append seat number with row and return
			return s + seatNum;
		
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
			// Implement this method to return a string that resembles a
			// ticket
			String s = "";
			int border = 0;

			// top border
			for (int i = 0; i < 31; i++) {
				s += "-";
			}
			s += "\n";

			// Show row
			s += "| Show: ";
			s += show;

			// Show right border
			border = 23 - show.length();
			// insert empty spaces
			for (int i = 0; i < border - 1; i++) {
				s += " ";
			}
			s += "|";
			s += "\n";

			// Box Office ID row
			s += "| Box Office ID: ";
			s += boxOfficeId;

			// Box Office ID right border
			border = 14 - boxOfficeId.length();
			// insert empty spaces
			for (int i = 0; i < border - 1; i++) {
				s += " ";
			}
			s += "|";
			s += "\n";

			// Seat row
			s += "| Seat: ";
			s += seat.toString();

			// Seat right border
			border = 23 - seat.toString().length();
			// insert empty spaces
			for (int i = 0; i < border - 1; i++) {
				s += " ";
			}
			s += "|";
			s += "\n";

			// Client row
			s += "| Client: ";
			s += client;

			// Client right border

			// get number of digits of int client for border adjustment
			int digits = (int) (Math.log10(client) + 1);

			// handle zero case
			if (client == 0)
				digits = 1;

			// right border
			border = 21 - digits;
			// insert empty spaces
			for (int i = 0; i < border - 1; i++) {
				s += " ";
			}
			s += "|";
			s += "\n";

			// bottom border
			for (int i = 0; i < 31; i++) {
				s += "-";
			}

			return s;
		}
	}

	private int numRows;
	private int seatsPerRow;
	private static int client_num;
	private String show;
	static ArrayList<Seat> seats;
	static ArrayList<Ticket> tickets;

	public Theater(int numRows, int seatsPerRow, String show) {
		// TODO: Implement this constructor
		this.numRows = numRows;
		this.seatsPerRow = seatsPerRow;
		//TOTAL_SEATS = numRows*seatsPerRow;
		client_num = 1;
		seats = new ArrayList<Seat>();
		for (int i = 1; i <= numRows; i++) {
			for (int j = 1; j <= seatsPerRow; j++) {
				seats.add(new Seat(i, j));
			}
		}

		this.show = show;
	}
	
	public int getClientNum(){
		return client_num;
	}
	
	
	public void updateClientNum(){
		client_num++;
	}

	/*
	 * Calculates the best seat not yet reserved
	 *
	 * @return the best seat or null if theater is full
	 */
	public Seat bestAvailableSeat() {
		
		
		
		int minR = numRows; //set min number of rows as max
		int minS = seatsPerRow;
		
		
		//if house is full, return null because no seat available
		if(seats.size() == 0)
			return null;
		
		//find the best row among available seats
		for(Seat s: seats){
			if(s.rowNum <= minR){
				minR = s.rowNum;				
			}
			
		}
		
		//in the best row, find the best seat number		
		for(Seat s: seats){
			if(s.rowNum == minR){
				if(s.seatNum <= minS){
					minS = s.seatNum;
				}
			}
		}
		
		for(Seat s: seats){
			if(minR == s.rowNum && minS == s.seatNum){
				return s;
			}
		}
		
		return null;
		
	}

	/*
	 * Prints a ticket for the client after they reserve a seat Also prints the
	 * ticket to the console
	 *
	 * @param seat a particular seat in the theater
	 * 
	 * @return a ticket or null if a box office failed to reserve the seat
	 */
	public Ticket printTicket(String boxOfficeId, Seat seat, int client) {
		// TODO: Implement this method
		boolean used = false;

		for (int i = seats.size() - 1; i >= 0; i--) {

			if (seats.get(i).rowNum == seat.rowNum && seats.get(i).seatNum == seat.seatNum) {
				seats.remove(i);
				used = true;
				break;
			}

		}

		if (used) {
			Ticket t = new Ticket(show, boxOfficeId, seat, client);
			//tickets.add(t);
			System.out.println(t.toString());
			return t;
		} else {
			return null;
		}
	}

	/*
	 * Lists all tickets sold for this theater in order of purchase
	 *
	 * @return list of tickets sold
	 */
	public List<Ticket> getTransactionLog() {
		
		
	}

	public static void main(String[] args) {

		//Seat s = new Seat(2500, 102);
		// Ticket t = new Ticket("","",s,1552);
		//System.out.println(s);
		// System.out.println(t);
		Theater tr = new Theater(100, 100, "Oedipus");
		System.out.println(tr.bestAvailableSeat());
	}
}
