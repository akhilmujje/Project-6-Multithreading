// insert header here
package assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		public String getString(String s, int a) {
			String val = Integer.toString(a, 27).toUpperCase(); // gets the base
																// 26 equivalent
																// to given
																// integer as
																// String
			// starts out with numbers and then switches to alphabets
			switch (val) {
			case "1":
				s += 'A';
				break;
			case "2":
				s += 'B';
				break;
			case "3":
				s += 'C';
				break;
			case "4":
				s += 'D';
				break;
			case "5":
				s += 'E';
				break;
			case "6":
				s += 'F';
				break;
			case "7":
				s += 'G';
				break;
			case "8":
				s += 'H';
				break;
			case "9":
				s += 'I';
				break;
			case "A":
				s += 'J';
				break;
			case "B":
				s += 'K';
				break;
			case "C":
				s += 'L';
				break;
			case "D":
				s += 'M';
				break;
			case "E":
				s += 'N';
				break;
			case "F":
				s += 'O';
				break;
			case "G":
				s += 'P';
				break;
			case "H":
				s += 'Q';
				break;
			case "I":
				s += 'R';
				break;
			case "J":
				s += 'S';
				break;
			case "K":
				s += 'T';
				break;
			case "L":
				s += 'U';
				break;
			case "M":
				s += 'V';
				break;
			case "N":
				s += 'W';
				break;
			case "O":
				s += 'X';
				break;
			case "P":
				s += 'Y';
				break;
			case "Q":
				s += 'Z';
				break;
			}
			return s;
		}

		@Override
		public String toString() {
			// Implement this method to return the full Seat location ex:
			// A1
			String s = "";

			int num = rowNum;
			int count = 0;
			int digits = (int) (Math.log10(rowNum) + 1);

			// get first character of row through count if row is made up of two
			// characters
		
				
			
			while (num > 26) {
				
				num -= 26;
				count++;

			}
			
			
			
			// get the character associated with base 26 for count (first character)
			s = getString(s,count);
			// get the character associated with base 26 (second character)
			s = getString(s, num);

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
	private String show;
	private ArrayList<Seat> seats;
	private ArrayList<Ticket> tickets;

	public Theater(int numRows, int seatsPerRow, String show) {
		// TODO: Implement this constructor
		this.numRows = numRows;
		this.seatsPerRow = seatsPerRow;
		seats = new ArrayList<Seat>();
		for (int i = 1; i < numRows; i++) {
			for (int j = 1; j < seatsPerRow; j++) {
				seats.add(new Seat(i, j));
			}
		}

		this.show = show;
	}

	/*
	 * Calculates the best seat not yet reserved
	 *
	 * @return the best seat or null if theater is full
	 */
	public Seat bestAvailableSeat() {
		// : Implement this method
		int min = numRows + seatsPerRow; // set min to MAX possible value
		Seat min_seat = null;
		int value = 0;
		for (Seat s : seats) {
			value = s.rowNum + s.seatNum;
			if (value < min) {
				min = value;
				min_seat = s;
			}

		}

		return min_seat;
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
			tickets.add(t);
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
		// TODO: Implement this method
		return tickets;
	}

	public static void main(String[] args) {

		 Seat s = new Seat(3, 102);
		// Ticket t = new Ticket("","",s,1552);
		System.out.println(s);
		// System.out.println(t);
		//Theater tr = new Theater(100, 100, "Oedipus");
		//System.out.println(tr.bestAvailableSeat());
	}
}
