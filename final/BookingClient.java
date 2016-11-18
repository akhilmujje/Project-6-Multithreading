// Insert header here
package assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import assignment6.Theater.Seat;

import java.lang.Thread;

public class BookingClient {
	static Map<String, Integer> office;
	Theater theater;
	private volatile static boolean full = false;
	private volatile static boolean end = false;

	/*
	 * @param office maps box office id to number of customers in line
	 * 
	 * @param theater the theater where the show is playing
	 */
	public BookingClient(Map<String, Integer> office, Theater theater) {
		// TODO: Implement this constructor
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
		// TODO: Implement this method

		class BoxOffice1 implements Runnable {
			@Override
			public void run() {
				boolean bx1_end = false;
				while (!bx1_end) {
					synchronized (theater) {

						int num = 0;
						int val = office.get("BX1");
						if (val > 0 && !full) {
							num = theater.getClientNum();
							Seat s = theater.bestAvailableSeat();

							if (s != null) {
								theater.printTicket("BX1", s, num);
								theater.updateClientNum();
								office.put("BX1", val - 1);
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {

									e.printStackTrace();
								}
							} else
								full = true;

						} else {
							bx1_end = true;
							if (full && !end) {
								System.out.println("Sorry, we are sold out!");
								end = true;
							}

						}

					}
				}

			}
		}

		class BoxOffice2 implements Runnable {
			@Override
			public void run() {
				boolean bx2_end = false;
				while (!bx2_end) {
					synchronized (theater) {
						int num = 0;
						int val = office.get("BX2");
						if (val > 0 && !full) {
							num = theater.getClientNum();
							Seat s = theater.bestAvailableSeat();
							office.put("BX2", val - 1);

							if (s != null) {
								theater.printTicket("BX2", s, num);
								theater.updateClientNum();
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {

									e.printStackTrace();
								}
							} else
								full = true;

						} else {
							bx2_end = true;
							if (full && !end) {
								System.out.println("Sorry, we are sold out!");
								end = true;
							}

						}
					}

				}
			}
		}

		class BoxOffice3 implements Runnable {
			@Override
			public void run() {
				boolean bx3_end = false;
				while (!bx3_end) {
					synchronized (theater) {
						int num = 0;
						int val = office.get("BX3");
						if (val > 0 && !full) {
							num = theater.getClientNum();
							Seat s = theater.bestAvailableSeat();
							office.put("BX3", val - 1);

							if (s != null) {
								theater.printTicket("BX3", s, num);
								theater.updateClientNum();
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {

									e.printStackTrace();
								}
							} else
								full = true;

						} else {
							bx3_end = true;
							if (full && !end) {
								System.out.println("Sorry, we are sold out!");
								end = true;
							}
						}
					}

				}
			}
		}

		class BoxOffice4 implements Runnable {
			@Override
			public void run() {
				boolean bx4_end = false;
				while (!bx4_end) {
					synchronized (theater) {
						int num = 0;
						int val = office.get("BX4");
						if (val > 0 && !full) {
							num = theater.getClientNum();
							Seat s = theater.bestAvailableSeat();

							if (s != null) {
								theater.printTicket("BX4", s, num);
								theater.updateClientNum();
								office.put("BX4", val - 1);
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {

									e.printStackTrace();
								}
							} else
								full = true;

						} else {
							bx4_end = true;
							if (full && !end) {
								System.out.println("Sorry, we are sold out!");
								end = true;
							}
						}
					}

				}
			}
		}

		class BoxOffice5 implements Runnable {
			@Override
			public void run() {
				boolean bx5_end = false;
				while (!bx5_end) {
					synchronized (theater) {
						int num = 0;
						int val = office.get("BX5");
						if (val > 0 && !full) {
							num = theater.getClientNum();
							Seat s = theater.bestAvailableSeat();

							if (s != null) {
								theater.printTicket("BX5", s, num);
								theater.updateClientNum();
								office.put("BX5", val - 1);

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {

									e.printStackTrace();
								}
							} else
								full = true;

						} else {
							bx5_end = true;
							if (full && !end) {
								System.out.println("Sorry, we are sold out!");
								end = true;
							}
						}
					}

				}
			}
		}

		ArrayList<Thread> threads = new ArrayList<Thread>();
		threads.add(new Thread(new BoxOffice1()));
		threads.add(new Thread(new BoxOffice2()));
		threads.add(new Thread(new BoxOffice3()));
		threads.add(new Thread(new BoxOffice4()));
		threads.add(new Thread(new BoxOffice5()));

		return threads;
	}

	public static void main(String[] args) {

		Map<String, Integer> patrons = new HashMap();
		patrons.put("BX1", 3);
		patrons.put("BX2", 4);
		patrons.put("BX3", 3);
		patrons.put("BX4", 3);
		patrons.put("BX5", 3);

		Theater th = new Theater(3, 5, "Ouija");
		BookingClient b = new BookingClient(patrons, th);
		List<Thread> threads = b.simulate();

		for (Thread t : threads) {
			t.start();
		}

	}

}
