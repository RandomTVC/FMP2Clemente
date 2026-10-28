import java.util.*;

class Ticket {
    private int ticketNumber;
    private String customerName;
    private String requestType;

    public Ticket(int ticketNumber, String customerName, String requestType) {
        this.ticketNumber = ticketNumber;
        this.customerName = customerName;
        this.requestType = requestType;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRequestType() {
        return requestType;
    }

    @Override
    public String toString() {
        return "Ticket " + ticketNumber + " " + customerName + " " + requestType;
    }
}

public class MP3 {
    private Queue<Ticket> queue;
    private int ticketCounter;
    private Scanner scanner;

    public MP3() {
        queue = new ArrayDeque<>();
        ticketCounter = 1;
        scanner = new Scanner(System.in);
    }

    public void run() {
        int choice;
        do {
            System.out.println("\nMenu: 1 New ticket | 2 Serve next | 3 Preview next | 4 Show queue | 0 Exit");
            System.out.print("Enter choice: ");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.print("Invalid input. Enter choice: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addNewTicket();
                    break;
                case 2:
                    serveNext();
                    break;
                case 3:
                    previewNext();
                    break;
                case 4:
                    showQueue();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void addNewTicket() {
        System.out.print("Enter customer name and request: ");
        String input = scanner.nextLine().trim();
        String[] parts = input.split("\\s+", 2);
        if (parts.length < 2) {
            System.out.println("Invalid format.");
            return;
        }
        String name = parts[0];
        String request = parts[1];
        Ticket ticket = new Ticket(ticketCounter++, name, request);
        queue.offer(ticket);
        System.out.println("Ticket " + ticket.getTicketNumber() + " created");
    }

    private void serveNext() {
        Ticket ticket = queue.poll();
        if (ticket == null) {
            System.out.println("No pending tickets");
        } else {
            System.out.println("Ticket " + ticket.getTicketNumber() + " served");
        }
    }

    private void previewNext() {
        Ticket ticket = queue.peek();
        if (ticket == null) {
            System.out.println("No pending tickets");
        } else {
            System.out.println("Ticket " + ticket.getTicketNumber() + " " + ticket.getCustomerName());
        }
    }

    private void showQueue() {
        if (queue.isEmpty()) {
            System.out.println("No pending tickets");
            return;
        }
        List<String> nums = new ArrayList<>();
        for (Ticket t : queue) { 
            nums.add(String.valueOf(t.getTicketNumber()));
        }
        System.out.println("Tickets " + String.join(", ", nums));
    }

    public static void main(String[] args) {
        new MP3().run();
    }
}