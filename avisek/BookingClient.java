// Insert header here

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.lang.Thread;

public class BookingClient {
	private int clientNum = 0;
	private boolean allSold = false;
	Map<String, Integer> boxOffices;
	Theater theTheater;
	private ArrayList<Thread> threadLog = new ArrayList<Thread>();
	Object o = new Object();

	private int totalClients = 0;

	/*
	 * @param office maps box office id to number of customers in line
	 * 
	 * @param theater the theater where the show is playing
	 */
	public BookingClient(Map<String, Integer> office, Theater theater) {
		// TODO: Implement this constructor
		boxOffices = office;
		theTheater = theater;

		Iterator<Entry<String, Integer>> iterator = office.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<String, Integer> mapEntry = iterator.next();
			int numClients = mapEntry.getValue();
			for (int i = 0; i < numClients; i++) {
				totalClients += 1;
			}
		}

	}

	public class booking implements Runnable {

		private String boxOffice;
		private int numInLine;
		private ArrayList<Integer> clientNums = new ArrayList<Integer>();
		// int clientNum = 0;

		public booking(String boxOfficeId, Integer numPeople) {
			boxOffice = boxOfficeId;
			numInLine = numPeople;
			for (int i = 0; i < numPeople; i++) {

				clientNum++;
				clientNums.add(clientNum);

			}
		}

		public void run() {
			int i = 0;

			while (numInLine > 0 && theTheater.bestAvailableSeat() != null) {
				// clientNum = theTheater.getTransactionLog().size() + 1;
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				synchronized (o) {
					theTheater.printTicket(boxOffice, theTheater.bestAvailableSeat(), clientNums.get(i));
					numInLine = numInLine - 1;
					i++;
				}
			}
			synchronized (o) {
				if (allSold == false && theTheater.bestAvailableSeat() == null) {
					allSold = true;
					if (totalClients > theTheater.getTransactionLog().size()) {
						System.out.println("Sorry, we are sold out!");
					}
				}
			}

		}

	}

	/*
	 * Starts the box office simulation by creating (and starting) threads for
	 * each box office to sell tickets for the given theater
	 *
	 * @return list of threads used in the simulation, should have as many
	 * threads as there are box offices
	 */
	public List<Thread> simulate() {
		// TODO: Implement this method

		for (String key : boxOffices.keySet()) { // read all boxOffice names from map
			Thread boxOfficeThread = new Thread(new booking(key, boxOffices.get(key)));
			threadLog.add(boxOfficeThread);

		}
		/* start all boxOffice threads */
		for (Thread t : threadLog) {
			t.start();
		}

		return threadLog;
	}

	public static void main(String[] args) {
		Map<String, Integer> test = new HashMap<String, Integer>();
		test.put("BX1", 4);
		test.put("BX3", 3);
		test.put("BX2", 4);
		test.put("BX5", 3);
		test.put("BX4", 3);
		Theater test2 = new Theater(5, 3, "Ouija");
		BookingClient test3 = new BookingClient(test, test2);
		test3.simulate();
	}

}
