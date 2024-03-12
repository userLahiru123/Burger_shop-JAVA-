import java.util.*;

class List{
    
//..........inner class..............
    class Node{
    Node next;
    private Burger data;
    Node(Burger data){
        this.data = data;
    }
    
    public Burger getData(){
        return data;
    }
}
    
    Node first;
 
    public int listSize(){
        if(isEmpty()){
            return 0;
        }else{
            Node temp = first;
            int count = 1;
            while(temp.next != null){
                temp = temp.next;
                count++;
            }
            return count;
        }
    }
    
    private boolean isEmpty(){
        return first == null;
    }
    
    public void add(int index, Burger data){
            if(index >= 0 && index<=listSize()){
                Node n1 = new Node(data);
                if(isEmpty()){
                    first = n1;
                }else{
                    Node temp = first;
                    int count = 1;
                    while(count < index){
                        temp = temp.next;
                        count++;
                    }
                    n1.next = temp.next;
                    temp.next = n1;
                }
            }
            
    }
    
    public Burger get(int index){
        if(index>=0 && index<listSize()){
            Node temp = first;
            int count = 0;
            while(count < index){
                temp = temp.next;
                count++;
            }
            return temp.getData();
        }
        return null;
    }
}

class Burger {

    final static double BURGERPRICE = 500;

    private String orderId;
    private String customerId;
    private String customerName;
    private int orderStatus;
    private int orderQTY;
    private double orderValue;

    // Order status
    public final static int CANCEL = 0;
    public final static int PREPARING = 1;
    public final static int DELIVERED = 2;

    // set methods........
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderQTY(int orderQTY) {
        this.orderQTY = orderQTY;
    }

    public void setOrderValue(double orderValue) {
        this.orderValue = orderValue;
    }

    // get methods...........
    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public int getOrderQTY() {
        return orderQTY;
    }

    public double getOrderValue() {
        return orderValue;
    }
}

class ShopController{
    
    List list = new List();
    
    public int size(){
        return list.listSize();
    }
    
//     generate order Id
    public String generateOrderId() {
        if (list.listSize() == 0) {
            return "B0001";
        }
        String lastOrderId = list.get(list.listSize() - 1).getOrderId();
        int number = Integer.parseInt(lastOrderId.split("B")[1]); // 1
        number++;// 2
        return String.format("B%04d", number); // printf("",) //B0002
    }
    
    public boolean isExitCustomer(String customerId){
        for (int i = 0; i < list.listSize(); i++) {
                if (customerId.equals(list.get(i).getCustomerId())) {
                    return true;
                }
            }
        return false;
    }
    
    public boolean isExitOrder(String orderId){
        for (int i = 0; i < list.listSize(); i++) {
                if (orderId.equals(list.get(i).getOrderId())) {
                    return orderId.equals(list.get(i).getOrderId());
                }
            }
        return false;
    }
    
    public String getCustomerId(String orderId){
        for (int i = 0; i < list.listSize(); i++) {
                if (orderId.equals(list.get(i).getOrderId())) {
                    return list.get(i).getCustomerId();
                }
            }
        return null;
    }
    
    public String getCustomer(String orderId){
        for (int i = 0; i < list.listSize(); i++) {
                if (orderId.equals(list.get(i).getOrderId())) {
                    return list.get(i).getCustomerName();
                }
            }
        return null;
    }
    
    public int getOrderQTY(String orderId){
        for (int i = 0; i < list.listSize(); i++) {
                if (orderId.equals(list.get(i).getOrderId())) {
                    return list.get(i).getOrderQTY();
                }
            }
        return -1;
    }
    
    public double getOrderValue(String orderId){
        for (int i = 0; i < list.listSize(); i++) {
                if (orderId.equals(list.get(i).getOrderId())) {
                    return list.get(i).getOrderValue();
                }
            }
        return 0.0;
    }
    
    public Burger getOrder(String orderId){
        for (int i = 0; i < list.listSize(); i++) {
            if(orderId.equals(list.get(i).getOrderId())){
                return list.get(i);
            }
        }
        return null;
    }
    
    public String getOrderStatus(String orderId){
        for (int i = 0; i < list.listSize(); i++) {
                if (orderId.equals(list.get(i).getOrderId())) {
                    switch (list.get(i).getOrderStatus()) {
                        case Burger.CANCEL:
                            return "Cancel";
                        case Burger.PREPARING:
                            return "Preparing";
                        case Burger.DELIVERED:
                            return "Delivered";
                    }
                }
            }
        return null;
    }
    
    public String getCustomerName(String customerId){
        for(int i=0;i<list.listSize();i++){
            if(list.get(i).getCustomerId().equals(customerId)){
                return list.get(i).getCustomerName();
            }
        }
        return null;
    }
    
    public String getCustomerName(Burger obj){
        return obj.getCustomerName();
    }
    
    public String getCustomerId(Burger obj){
        return obj.getCustomerId();
    }
    
    public String getOrderId(Burger obj){
        return obj.getOrderId();
    }
    
    public double getOrderValue(Burger obj){
        return obj.getOrderValue();
    }
    
    public int getOrderQTY(Burger obj){
        return obj.getOrderQTY();
    }
    
    public void addOrder(String orderId, String customerName, String customerId, int qty, double billValue){
        Burger obj = new Burger();
        obj.setOrderId(orderId);
        obj.setCustomerName(customerName);
        obj.setOrderStatus(1);
        obj.setCustomerId(customerId);
        obj.setOrderQTY(qty);
        obj.setOrderValue(billValue);
                 
        list.add((list.listSize()), obj);
        obj = null;
    }
    
    public Burger[] sort(){
        Burger[] sortBurgerArray = new Burger[0];
        
        for (int i = 0; i < list.listSize(); i++) {
            boolean isExist = false;
            for (int j = 0; j < sortBurgerArray.length; j++) {
                if (sortBurgerArray[j].getCustomerId().equals(list.get(i).getCustomerId())) {
                    if (list.get(i).getOrderStatus() != Burger.CANCEL) {
                        sortBurgerArray[j].setOrderValue(sortBurgerArray[j].getOrderValue() + list.get(i).getOrderValue());
                    }
                    isExist = true;
                }
            }
            if (!isExist && (list.get(i).getOrderStatus() != Burger.CANCEL)) {
                Burger[] tempSortBurgerArray = new Burger[sortBurgerArray.length + 1];
                for (int j = 0; j < sortBurgerArray.length; j++) {
                    Burger tempObj = new Burger();

                    tempObj.setOrderId(sortBurgerArray[j].getOrderId());
                    tempObj.setCustomerId(sortBurgerArray[j].getCustomerId());
                    tempObj.setCustomerName(sortBurgerArray[j].getCustomerName());
                    tempObj.setOrderQTY(sortBurgerArray[j].getOrderQTY());
                    tempObj.setOrderStatus(sortBurgerArray[j].getOrderStatus());
                    tempObj.setOrderValue(sortBurgerArray[j].getOrderValue());

                    tempSortBurgerArray[j] = tempObj;
                    tempObj = null;
                }
                sortBurgerArray = tempSortBurgerArray;

                Burger lastObj = new Burger();

                lastObj.setOrderId(list.get(i).getOrderId());
                lastObj.setCustomerId(list.get(i).getCustomerId());
                lastObj.setCustomerName(list.get(i).getCustomerName());
                lastObj.setOrderQTY(list.get(i).getOrderQTY());
                lastObj.setOrderStatus(list.get(i).getOrderStatus());
                lastObj.setOrderValue(list.get(i).getOrderValue());

                sortBurgerArray[sortBurgerArray.length - 1] = lastObj;
                lastObj = null;
            }
        }

        // sort
        for (int i = 1; i < sortBurgerArray.length; i++) {
            for (int j = 0; j < i; j++) {
                if (sortBurgerArray[j].getOrderValue() < sortBurgerArray[i].getOrderValue()) {
                    Burger temp = sortBurgerArray[j];
                    sortBurgerArray[j] = sortBurgerArray[i];
                    sortBurgerArray[i] = temp;
                }
            }
        }
        return sortBurgerArray;
    }
    
    public Burger[] searchCustomer(String customerId){
        int count=0;
        for(int i=0;i<list.listSize();i++){
            if (list.get(i).getCustomerId().equals(customerId)) {
                count++;
            }
        }
        Burger[] array = new Burger[count];
        int index=0;
        for (int j = 0; j < list.listSize(); j++) {
                        if (list.get(j).getCustomerId().equals(customerId)) {
                            array[index] = list.get(j);
                            index++;
                        }
                    }
        return array;
    }
    
    public Burger[] viewOreders(int status){
        int count=0;
        for(int i=0;i<list.listSize();i++){
            if (list.get(i).getOrderStatus() == status) {
                count++;
            }
        }
        Burger[] deliveredOrderArray = new Burger[count];
        int index=0;
        for (int i = 0; i < list.listSize(); i++) {
            if (list.get(i).getOrderStatus() == status) {
                deliveredOrderArray[index++] = list.get(i);
            }
        }
        return deliveredOrderArray;
    }
    
    public void updateQTY(String orderId,int QTY){
        Burger obj = getOrder(orderId);
        obj.setOrderQTY(QTY);
        obj.setOrderValue(QTY*Burger.BURGERPRICE);
    }
    
    public void updateStatus(String orderId,int status){
        Burger obj = getOrder(orderId);
        obj.setOrderStatus(status);
    }
}

class BurgerShop {

    ShopController controller = new ShopController();

    // console clear
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }

    // placeOrder
    public void placeOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tPLACE ORDER\t\t\t\t|");
        System.out.println("-------------------------------------------------------------------------\n\n");
        System.out.print("ORDER ID - ");
        String orderId = controller.generateOrderId();
        System.out.println(orderId + "\n================\n\n");

        L1: do {
            System.out.print("Enter Customer ID (phone no.): ");
            String customerId = input.next();
            if (customerId.charAt(0) != '0' || customerId.length() != 10) {
                continue L1;
            }
            boolean isExistCustomer = false;
            String customerName = "";
            isExistCustomer = controller.isExitCustomer(customerId);
            if(isExistCustomer){
                System.out.println("Enter Customer Name: " + controller.getCustomerName(customerId));
                customerName = controller.getCustomerName(customerId);
            }else{
                System.out.print("\nEnter Customer Name: ");
                customerName = input.next();
            }

            System.out.print("Enter Burger Quantity - ");
            int qty = input.nextInt();
            if (qty > 0) {
                double billValue = qty * Burger.BURGERPRICE;
                System.out.printf("Total value - %.2f", billValue);
                System.out.println();
                L3: do {
                    System.out.print("\tAre you confirm order - ");
                    String option = input.next().toUpperCase();
                    if (option.equalsIgnoreCase("Y")) {
                        controller.addOrder(orderId, customerName, customerId, qty, billValue);
                        System.out.println("\n\tYour order is enter to the system successfully...");
                        break L1;
                    } else if (option.equalsIgnoreCase("N")) {
                        System.out.println("\n\tYour order is not enter the system...");
                        clearConsole();
                        return;
                    } else {
                        System.out.println("\tInvalid option..input again...");
                        break L1;
                    }
                } while (true);
            }
        } while (true);
        L4: do {
            System.out.println();
            System.out.print("Do you want to place another order (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                placeOrder();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                homePage();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L4;
            }
        } while (true);
    }

    // Search best customer
    public void searchBestCustomer() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tBEST Customer\t\t\t\t|");
        System.out.println("-------------------------------------------------------------------------\n");

        Burger[] sortBurgerArray = controller.sort();

        System.out.println("\n----------------------------------------");
        String line1 = String.format("%-14s%-15s%8s", " CustomerID", "Name", "Total");
        System.out.println(line1);
        System.out.println("----------------------------------------");
        for (int i = 0; i < sortBurgerArray.length; i++) {
            String line = String.format("%1s%-14s%-15s%8.2f", " ", controller.getCustomerId(sortBurgerArray[i]), controller.getCustomerName(sortBurgerArray[i]), controller.getOrderValue(sortBurgerArray[i]));
            System.out.println(line);
            System.out.println("----------------------------------------");
        }
        L: do {
            Scanner input = new Scanner(System.in);
            System.out.print("\n\tDo you want to go back to main menu? (Y/N)> ");
            String exitOption = input.nextLine();
            if (exitOption.equalsIgnoreCase("Y")) {
                clearConsole();
                homePage();
            } else if (exitOption.equalsIgnoreCase("N")) {
                clearConsole();
                searchBestCustomer();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L;
            }
        } while (true);
    }

    // search order
    public void searchOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tSEARCH ORDER DETAILS\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        L1: do {
            System.out.print("Enter order Id - ");
            String orderId = input.next();
            System.out.println();
            if(controller.isExitOrder(orderId)){
                System.out.println("---------------------------------------------------------------------------");
                    String line1 = String.format("%-10s%-14s%-12s%-10s%-14s%-10s", " OrderID", " CustomerID", " Name", "Quantity", "  OrderValue", "  OrderStatus");
                    System.out.print(line1);
                    System.out.println(" |");
                    System.out.println("---------------------------------------------------------------------------");
                    String line = String.format("%1s%-10s%-14s%-15s%-10d%-14.2f%-10s", " ", orderId, controller.getCustomerId(orderId), controller.getCustomer(orderId), controller.getOrderQTY(orderId), controller.getOrderValue(orderId), controller.getOrderStatus(orderId));
                    System.out.print(line);
                    System.out.println("|");
                    System.out.println("---------------------------------------------------------------------------");
                    break L1;
            }
            L2: do {
                System.out.print("\n\nInvalid Order ID. Do you want to enter again? (Y/N)>");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    clearConsole();
                    searchOrder();
                } else if (option.equalsIgnoreCase("N")) {
                    clearConsole();
                    return;
                } else {
                    System.out.println("\tInvalid option..input again...");
                    continue L2;
                }
            } while (true);
        } while (true);
        L3: do {
            System.out.println();
            System.out.print("Do you want to search another order details (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                searchOrder();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                homePage();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L3;
            }
        } while (true);
    }

    // search Customer
    public void searchCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tSEARCH CUSTOMER DETAILS\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        L1: do {
            System.out.print("Enter customer Id - ");
            String customerId = input.next();
            System.out.println("\n");
            if(controller.isExitCustomer(customerId)){
               System.out.println("CustomerID - " + customerId);
                    System.out.println("Name       - " + controller.getCustomerName(customerId));
                    System.out.println("\nCustomer Order Details");
                    System.out.println("========================\n");
                    System.out.println("----------------------------------------------");
                    String line = String.format("%-12s%-18s%-14s", " Order_ID", "Order_Quantity", "Total_Value  ");
                    System.out.println(line);
                    System.out.println("----------------------------------------------"); 
                    
                    Burger[] searchCustomerArray = controller.searchCustomer(customerId);
                    
                    for (int j = 0; j < searchCustomerArray.length; j++) {
                        
                            String line2 = String.format("%1s%-12s%-18d%-14.2f", " ", controller.getOrderId(searchCustomerArray[j]), controller.getOrderQTY(searchCustomerArray[j]), controller.getOrderValue(searchCustomerArray[j]));
                            System.out.println(line2);
                        
                    }
                    System.out.println("----------------------------------------------");
                    break L1;
            }
            L2: do {
                System.out.print("\n\nInvalid Customer ID. Do you want to enter again? (Y/N)>");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    clearConsole();
                    searchCustomer();
                } else if (option.equalsIgnoreCase("N")) {
                    clearConsole();
                    return;
                } else {
                    System.out.println("\tInvalid option..input again...");
                    continue L2;
                }
            } while (true);
        } while (true);
        L3: do {
            System.out.println();
            System.out.print("Do you want to search another customer details (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                searchCustomer();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                homePage();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L3;
            }
        } while (true);
    }

    // View Order list
    public void viewOrders() {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tVIEW ORDER LIST\t\t\t\t|");
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("[1] Delivered Order");
        System.out.println("[2] Preparing Order");
        System.out.println("[3] Cancel Order");

        System.out.print("\nEnter an option to continue > ");
        int option = input.nextInt();
        switch (option) {
            case 1:
                clearConsole();
                deliverOrder();
                break;
            case 2:
                clearConsole();
                preparingOrder();
                break;
            case 3:
                clearConsole();
                cancelOrder();
                break;
        }
    }

    // Delivered Order
    public void deliverOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tDELIVERED ORDER\t\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        System.out.println("\n--------------------------------------------------------------");
        String line1 = String.format("%-10s%-15s%-13s%-10s%12s", " OrderID", " CustomerID", " Name", "Quantity", "  OrderValue");
        System.out.println(line1);
        System.out.println("--------------------------------------------------------------");
        
        Burger[] deliveredOrders = controller.viewOreders(Burger.DELIVERED);
        for (int i = 0; i < deliveredOrders.length; i++) {
            String line = String.format("%1s%-10s%-15s%-15s%-10d%8.2f", " ", controller.getOrderId(deliveredOrders[i]), controller.getCustomerId(deliveredOrders[i]), controller.getCustomerName(deliveredOrders[i]), controller.getOrderQTY(deliveredOrders[i]), controller.getOrderValue(deliveredOrders[i]));
                System.out.println(line);
                System.out.println("--------------------------------------------------------------");
        }
        L1: do {
            System.out.print("\nDo you want to go to home page (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                homePage();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                deliverOrder();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L1;
            }
        } while (true);
    }

    // Preparing Order
    public void preparingOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tPREPARING ORDER\t\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        System.out.println("\n--------------------------------------------------------------");
        String line1 = String.format("%-10s%-15s%-13s%-10s%12s", " OrderID", " CustomerID", " Name", "Quantity", "  OrderValue");
        System.out.println(line1);
        System.out.println("--------------------------------------------------------------");
        
        Burger[] deliveredOrders = controller.viewOreders(Burger.PREPARING);
     
        for (int i = 0; i < deliveredOrders.length; i++) {
            String line = String.format("%1s%-10s%-15s%-15s%-10d%8.2f", " ", controller.getOrderId(deliveredOrders[i]), controller.getCustomerId(deliveredOrders[i]), controller.getCustomerName(deliveredOrders[i]), controller.getOrderQTY(deliveredOrders[i]), controller.getOrderValue(deliveredOrders[i]));
                System.out.println(line);
                System.out.println("--------------------------------------------------------------");
        }
        L1: do {
            System.out.print("\nDo you want to go to home page (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                homePage();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                preparingOrder();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L1;
            }
        } while (true);
    }

    // Cancel Order
    public void cancelOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tCANCEL ORDER\t\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        System.out.println("\n--------------------------------------------------------------");
        String line1 = String.format("%-10s%-15s%-13s%-10s%12s", " OrderID", " CustomerID", " Name", "Quantity", "  OrderValue");
        System.out.println(line1);
        System.out.println("--------------------------------------------------------------");
        
        Burger[] deliveredOrders = controller.viewOreders(Burger.CANCEL);
        for (int i = 0; i < deliveredOrders.length; i++) {
            String line = String.format("%1s%-10s%-15s%-15s%-10d%8.2f", " ", controller.getOrderId(deliveredOrders[i]), controller.getCustomerId(deliveredOrders[i]), controller.getCustomerName(deliveredOrders[i]), controller.getOrderQTY(deliveredOrders[i]), controller.getOrderValue(deliveredOrders[i]));
                System.out.println(line);
                System.out.println("--------------------------------------------------------------");
        }
        L1: do {
            System.out.print("\nDo you want to go to home page (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                homePage();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                cancelOrder();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L1;
            }
        } while (true);
    }

    // Update order details
    public void updateOrderDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tUPDATE ORDER DETAILS\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        L1: do {
            System.out.print("Enter order Id - ");
            String orderId = input.next();
            System.out.println();
            
            if(controller.isExitOrder(orderId)){
                Burger orderdetails = controller.getOrder(orderId);
                String status = controller.getOrderStatus(orderId);
                
                if (status.equalsIgnoreCase("Cancel")) {
                        System.out.println("This Order is already cancelled...You can not update this order...");
                    } else if (status.equalsIgnoreCase("Delivered")) {
                        System.out.println("This Order is already delivered...You can not update this order...");
                    } else {
                        System.out.println("OrderID    - " + orderId);
                        System.out.println("CustomerID - " + controller.getCustomerId(orderId));
                        System.out.println("Name       - " + controller.getCustomerName(orderdetails));
                        System.out.println("Quantity   - " + controller.getOrderQTY(orderdetails));
                        System.out.printf("OrderValue - %.2f", controller.getOrderValue(orderdetails));
                        System.out.println("\nOrderStatus- " + status);

                        System.out.println("\nWhat do you want to update ? ");
                        System.out.println("\t(01) Quantity ");
                        System.out.println("\t(02) Status ");
                        System.out.print("\nEnter your option - ");
                        int option = input.nextInt();
                        switch (option) {
                            case 1:
                                clearConsole();
                                System.out.println("\nQuantity Update");
                                System.out.println("================\n");
                                System.out.println("OrderID    - " + controller.getOrderId(orderdetails));
                                System.out.println("CustomerID - " + controller.getCustomerId(orderdetails));
                                System.out.println("Name       - " + controller.getCustomerName(orderdetails));
                                System.out.print("\nEnter your quantity update value - ");
                                int qty = input.nextInt();
                                controller.updateQTY(orderId, qty);
                                System.out.println("\n\tupdate order quantity success fully...");
                                System.out.println("\nnew order quantity - " + controller.getOrderQTY(orderdetails));
                                System.out.printf("new order value - %.2f", controller.getOrderValue(orderdetails));
                                break;
                            case 2:
                                clearConsole();
                                System.out.println("\nStatus Update");
                                System.out.println("==============\n");
                                System.out.println("OrderID    - " + controller.getOrderId(orderdetails));
                                System.out.println("CustomerID - " + controller.getCustomerId(orderdetails));
                                System.out.println("Name       - " + controller.getCustomerName(orderdetails));
                                System.out.println("\n\t(0)Cancel");
                                System.out.println("\t(1)Preparing");
                                System.out.println("\t(2)Delivered");
                                int s = -1;
                                while (true) {
                                    System.out.print("\nEnter new order status - ");
                                    s = input.nextInt();
                                    if (s >= 0 && s <= 2) {
                                        break;
                                    }
                                }
                                controller.updateStatus(orderId, s);
                                System.out.println("\n\tUpdate order status successfully...");
                                System.out.println("\nnew order status - " + controller.getOrderStatus(orderId));
                                break;
                        }
                    }
                    break L1;
            }
            L3: do {
                System.out.print("\n\nInvalid Order ID. Do you want to enter again? (Y/N)>");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    System.out.println("\n");
                    continue L1;
                } else if (option.equalsIgnoreCase("N")) {
                    clearConsole();
                    return;
                } else {
                    System.out.println("\tInvalid option..input again...");
                    continue L3;
                }
            } while (true);
        } while (true);
        L3: do {
            System.out.println();
            System.out.print("Do you want to update another order details (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                updateOrderDetails();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                homePage();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L3;
            }
        } while (true);
    }

    // exit
    public static void exit() {
        clearConsole();
        System.out.println("\n\t\tYou left the program...\n");
        System.exit(0);
    }

    // home page
    public void homePage() {
        clearConsole();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tiHungry Burger\t\t\t\t|");
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("[1] Place Order\t\t\t[2] Search Best Customer");
        System.out.println("[3] Search Order\t\t[4] Search Customer");
        System.out.println("[5] View Orders\t\t\t[6] Update Order Details");
        System.out.println("[7] Exit");

        Scanner input = new Scanner(System.in);
        do {

            System.out.print("\nEnter an option to continue > ");
            char option = input.next().charAt(0);

            switch (option) {
                case '1':
                    clearConsole();
                    placeOrder();
                    break;
                case '2':
                    clearConsole();
                    searchBestCustomer();
                    break;
                case '3':
                    clearConsole();
                    searchOrder();
                    break;
                case '4':
                    clearConsole();
                    searchCustomer();
                    break;
                case '5':
                    clearConsole();
                    viewOrders();
                    break;
                case '6':
                    clearConsole();
                    updateOrderDetails();
                    break;
                case '7':
                    exit();
                    break;
            }
        } while (true);
    }
}

public class Demo {
    public static void main(String[] args) {
        BurgerShop obj = new BurgerShop();
        obj.homePage();
    }
}
