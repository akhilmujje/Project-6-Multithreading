/* EE422C Project 6 Multithreading submission by 11/18/2016
 * Devisriram Akhil Mujje
 * dam4335
 * Slip days used: <1>
 * Fall 2016
 */

package assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import assignment6.Theater.Seat;
import assignment6.Theater.Ticket;

import java.lang.Thread;

public class BookingClient {
	static Map<String, Integer> office;
	Theater theater;
	private volatile static boolean full = false;
	private volatile static boolean end = false;
	private volatile static String bo_name = "";

	/*
	 * @param office maps box office id to number of customers in line
	 * 
	 * @param theater the theater where the show is playing
	 */
	public BookingClient(Map<String, Integer> office, Theater theater) {

		this.office = office;
		this.theater = theater;

	}

	/*
	 * Starts the box office simulation by creating (and starting) threads for
	 * each box office to sell tickets for the given theater
	 *
	 * @return list of threads used in the simulation, should have as many
	 * threads as there are box offices
	 */
	public List<Thread> simulate() {

		class BoxOffice implements Runnable {
			String name = bo_name;

			@Override
			public void run() {
				boolean bx_end = false;
				
				while (!bx_end) {
					//Integer client_num = new Integer(theater.getClientNum());
					//synchronized (theater) {

						int num = 0;
						int val = office.get(name);
						if (val > 0 && !full) {
							synchronized (theater) {
							num = theater.getClientNum();
							Seat s = theater.bestAvailableSeat();

							if (s != null) {
								theater.printTicket(name, s, num);
								
								theater.updateClientNum();
								
								office.put(name, val - 1);

								try {

									Thread.sleep(750);

								} catch (InterruptedException e) {

									e.printStackTrace();
								} finally {	}
							}
							else
								full = true;
						}

						} else {
							bx_end = true;
							if (full && !end) {
								System.out.println("Sorry, we are sold out!");
								end = true;
							}

						}

					}
			
				
		//}
				
		}
		}

		Set<String> box_offices = office.keySet();
		ArrayList<Thread> threads = new ArrayList<Thread>();

		for (String thread : box_offices) {
			bo_name = thread;
			threads.add(new Thread(new BoxOffice()));
		}

		for (Thread t : threads) {
			t.start();
		}

		return threads;
	}

	public static void main(String[] args) {

		Map<String, Integer> patrons = new HashMap();
		patrons.put("BX1", 3);
		patrons.put("BX2",4);
		patrons.put("BX3", 3);
		patrons.put("BX4",3);
		patrons.put("BX5", 3);
		

		Theater th = new Theater(3, 5, "Ouija");
		BookingClient b = new BookingClient(patrons, th);
		b.simulate();

	}

	// useless commented code
	/*
	 * class BoxOffice2 implements Runnable {
	 * 
	 * @Override public void run() { boolean bx2_end = false; while (!bx2_end) {
	 * //ReentrantLock lock = new ReentrantLock(); synchronized (theater) { int
	 * num = 0; int val = office.get(bo_name); if (val > 0 && !full) { num =
	 * theater.getClientNum(); Seat s = theater.bestAvailableSeat();
	 * office.put(bo_name, val - 1);
	 * 
	 * if (s != null) { theater.printTicket(bo_name, s, num);
	 * theater.updateClientNum(); //lock.lock(); try { Thread.sleep(1000); }
	 * catch (InterruptedException e) {
	 * 
	 * e.printStackTrace(); } finally{ //lock.unlock(); } } else full = true;
	 * 
	 * } else { bx2_end = true; if (full && !end) { System.out.println(
	 * "Sorry, we are sold out!"); end = true; }
	 * 
	 * } }
	 * 
	 * } } }
	 */

	/*
	 * class BoxOffice3 implements Runnable {
	 * 
	 * @Override public void run() { boolean bx3_end = false; while (!bx3_end) {
	 * //while(true){ //ReentrantLock lock = new ReentrantLock(); synchronized
	 * (theater) { int num = 0; int val = office.get(bo_name); if (val > 0 &&
	 * !full) { num = theater.getClientNum(); Seat s =
	 * theater.bestAvailableSeat(); office.put(bo_name, val - 1); //lock.lock();
	 * if (s != null) { theater.printTicket(bo_name, s, num);
	 * theater.updateClientNum(); try { Thread.sleep(1000); } catch
	 * (InterruptedException e) {
	 * 
	 * e.printStackTrace(); } finally{ // lock.unlock(); } } else full = true;
	 * 
	 * } else { bx3_end = true; if (full && !end) { System.out.println(
	 * "Sorry, we are sold out!"); end = true;
	 * 
	 * } } }
	 * 
	 * } } }
	 */

	/*
	 * class BoxOffice4 implements Runnable {
	 * 
	 * @Override public void run() { boolean bx4_end = false; while (!bx4_end) {
	 * //while(true){ //ReentrantLock lock = new ReentrantLock(); synchronized
	 * (theater) { int num = 0; int val = office.get(bo_name); if (val > 0 &&
	 * !full) { num = theater.getClientNum(); Seat s =
	 * theater.bestAvailableSeat();
	 * 
	 * if (s != null) { theater.printTicket(bo_name, s, num);
	 * theater.updateClientNum(); office.put(bo_name, val - 1); //lock.lock();
	 * try { Thread.sleep(1000); } catch (InterruptedException e) {
	 * 
	 * e.printStackTrace(); } finally{ // lock.unlock(); } } else full = true;
	 * 
	 * } else { bx4_end = true; if (full && !end) { System.out.println(
	 * "Sorry, we are sold out!"); end = true;
	 * 
	 * } } } }
	 * 
	 * } }
	 */

	/*
	 * class BoxOffice5 implements Runnable {
	 * 
	 * @Override public void run() { boolean bx5_end = false; while (!bx5_end) {
	 * //while(true){ //ReentrantLock lock = new ReentrantLock(); synchronized
	 * (theater) { int num = 0; int val = office.get(bo_name); if (val > 0 &&
	 * !full) { num = theater.getClientNum(); Seat s =
	 * theater.bestAvailableSeat();
	 * 
	 * if (s != null) { theater.printTicket(bo_name, s, num);
	 * theater.updateClientNum(); office.put(bo_name, val - 1); //lock.lock();
	 * 
	 * try { Thread.sleep(1000);
	 * 
	 * } catch (InterruptedException e) {
	 * 
	 * e.printStackTrace(); } finally{ //lock.unlock(); } } else full = true;
	 * 
	 * } else { bx5_end = true; if (full && !end) { System.out.println(
	 * "Sorry, we are sold out!"); end = true;
	 * 
	 * } } } }
	 * 
	 * } }
	 */

}