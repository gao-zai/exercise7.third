import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

class Pizzeria {
    public String storeName;
    public String storeAddress;
    public long storePhone;
    public String storeMenu;
    public String storeWebsite;

    public String pizzaIngredients;
    public double pizzaPrice;
    public String sides;
    public String drinks;
    public String orderID;
    public String username1;
    public static final String DEF_ORDER_ID = "DEF-SOH-099";
    public static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    public static final double DEF_ORDER_TOTAL = 15.00;
    public static final String BLACKLISTED_NUMBER = "12345678901234";
    Scanner scanner = new Scanner(System.in);

    public Pizzeria(String storeName, String storeAddress, String storeWebsite, long storePhone, String storeMenu) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storePhone = storePhone;
        this.storeMenu = storeMenu;
        this.storeWebsite = storeWebsite;
    }

    public void takeOrder() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        int sizeChoice;
        String pizzaSize;
        do {
            System.out.println("What size should your pizza be?");
            System.out.println("1. Large");
            System.out.println("2. Medium");
            System.out.println("3. Small");
            System.out.print("Enter only one choice (1, 2, or 3): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            sizeChoice = scanner.nextInt();
            if (sizeChoice < 1 || sizeChoice > 3) {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        } while (sizeChoice < 1 || sizeChoice > 3);

        switch (sizeChoice) {
            case 1:
                pizzaSize = "Large";
                break;
            case 2:
                pizzaSize = "Medium";
                break;
            case 3:
                pizzaSize = "Small";
                break;
            default:
                pizzaSize = "";
        }

        System.out.println("Enter three ingredients for your pizza (use spaces to separate ingredients):");
        String ing1 = scanner.next();
        String ing2 = scanner.next();
        String ing3 = scanner.next();
        pizzaIngredients = ing1 + ", " + ing2 + ", " + ing3;

        char extraCheese;
        do {
            System.out.print("Do you want extra cheese (Y/N): ");
            extraCheese = scanner.next().toUpperCase().charAt(0);
            if (extraCheese != 'Y' && extraCheese != 'N') {
                System.out.println("Invalid choice. Please enter Y or N.");
            }
        } while (extraCheese != 'Y' && extraCheese != 'N');

        int sideDishChoice;
        do {
            System.out.println("Following are the side dish that go well with your pizza:");
            System.out.println("1. Calzone");
            System.out.println("2. Garlic bread");
            System.out.println("3. Chicken puff");
            System.out.println("4. Muffin");
            System.out.println("5. Nothing for me");
            System.out.print("What would you like? Pick one (1, 2, 3,…): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            sideDishChoice = scanner.nextInt();
            if (sideDishChoice < 1 || sideDishChoice > 5) {
                System.out.println("Invalid choice. Please enter a valid number.");
            }
        } while (sideDishChoice < 1 || sideDishChoice > 5);

        switch (sideDishChoice) {
            case 1:
                sides = "Calzone";
                break;
            case 2:
                sides = "Garlic bread";
                break;
            case 3:
                sides = "Chicken puff";
                break;
            case 4:
                sides = "Muffin";
                break;
            case 5:
                sides = "Nothing for me";
                break;
            default:
                sides = "";
        }

        int drinkChoice;
        do {
            System.out.println("Choose from one of the drinks below. We recommend Coca Cola:");
            System.out.println("1. Coca Cola");
            System.out.println("2. Cold coffee");
            System.out.println("3. Cocoa Drink");
            System.out.println("4. No drinks for me");
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            drinkChoice = scanner.nextInt();
            if (drinkChoice < 1 || drinkChoice > 4) {
                System.out.println("Invalid choice. Please enter a valid number.");
            }
        } while (drinkChoice < 1 || drinkChoice > 4);

        switch (drinkChoice) {
            case 1:
                drinks = "Coca Cola";
                break;
            case 2:
                drinks = "Cold coffee";
                break;
            case 3:
                drinks = "Cocoa Drink";
                break;
            case 4:
                drinks = "No drinks for me";
                break;
            default:
                drinks = "";
        }

        char halfPriceChoice;
        do {
            System.out.print("Would you like the chance to pay only half for your order? (Y/N): ");
            halfPriceChoice = scanner.next().toUpperCase().charAt(0);
            if (halfPriceChoice != 'Y' && halfPriceChoice != 'N') {
                System.out.println("Invalid choice. Please enter Y or N.");
            }
        } while (halfPriceChoice != 'Y' && halfPriceChoice != 'N');

        if (halfPriceChoice == 'Y') {
            isItYourBirthday();
        } else {
            makeCardPayment();
        }

        System.out.println("Order " + DEF_ORDER_ID + " accepted!");
        makePizza();
        System.out.println(this);
    }

    public void isItYourBirthday() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date birthdate = null;
        boolean validDate = false;
        while (!validDate) {
            System.out.println("Enter your birthday (MM/dd/yyyy):");
            String birthdateStr = scanner.next();
            try {
                birthdate = sdf.parse(birthdateStr);
                Calendar birthCal = Calendar.getInstance();
                birthCal.setTime(birthdate);
                Calendar nowCal = Calendar.getInstance();
                int age = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
                if (nowCal.get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
                    age--;
                }
                Calendar fiveYearsAgo = Calendar.getInstance();
                fiveYearsAgo.add(Calendar.YEAR, -5);
                Calendar oneHundredTwentyYearsAgo = Calendar.getInstance();
                oneHundredTwentyYearsAgo.add(Calendar.YEAR, -120);
                if (birthdate.after(fiveYearsAgo.getTime()) || birthdate.before(oneHundredTwentyYearsAgo.getTime())) {
                    System.out.println("Invalid date. You are either too young or too dead to order. Please enter a valid date:");
                } else {
                    validDate = true;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            }
        }

        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birthdate);
        Calendar nowCal = Calendar.getInstance();
        int age = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
        if (nowCal.get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        Calendar today = Calendar.getInstance();
        if (age < 18 && today.get(Calendar.MONTH) == birthCal.get(Calendar.MONTH) &&
                today.get(Calendar.DAY_OF_MONTH) == birthCal.get(Calendar.DAY_OF_MONTH)) {
            System.out.println("Congratulations! You pay only half the price for your order");
        } 
        else {
            System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
        }
    }

    public void makeCardPayment() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String cardNumber;
        do {
            System.out.println("Enter your 14 - digit card number:");
            cardNumber = scanner.next();
            if (cardNumber.length() != 14 || cardNumber.equals(BLACKLISTED_NUMBER)) {
                System.out.println("Invalid card number. Please enter a 14 - digit number that is not blacklisted.");
            }
        } while (cardNumber.length() != 14 || cardNumber.equals(BLACKLISTED_NUMBER));

        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        Date expiryDate = null;
        boolean validExpiry = false;
        while (!validExpiry) {
            System.out.println("Enter the card's expiry date (MM/yyyy):");
            String expiryDateStr = scanner.next();
            try {
                expiryDate = sdf.parse(expiryDateStr);
                Calendar nowCal = Calendar.getInstance();
                if (expiryDate.before(nowCal.getTime())) {
                    System.out.println("Invalid expiry date. Please enter a future date.");
                } else {
                    validExpiry = true;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use mm/yyyy.");
            }
        }

        System.out.println("Enter the card's cvv number (3 digits):");
        int cvv = scanner.nextInt();
        CardPaymentProcessor.processCardPayment(cardNumber, expiryDate, cvv);
    }

    public void makePizza() {
        System.out.println("Making pizza with " + pizzaIngredients);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Pizza making was interrupted: " + e.getMessage());
        }
        System.out.println("Your order is ready!");
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("********RECEIPT********\n");
        receipt.append("Store Name: ").append(storeName).append("\n");
        receipt.append("Order ID: ").append(DEF_ORDER_ID).append("\n");
        receipt.append("Pizza Ingredients: ").append(pizzaIngredients).append("\n");
        if (sides != null && !sides.isEmpty() && !sides.equals("Nothing for me")) {
            receipt.append("Sides: ").append(sides).append("\n");
        }
        if (drinks != null && !drinks.isEmpty() && !drinks.equals("No drinks for me")) {
            receipt.append("Drinks: ").append(drinks).append("\n");
        }
        return receipt.toString();
    }

    public void takeOrder(String string, String string2, String string3, String string4) {
        throw new UnsupportedOperationException("Unimplemented method 'takeOrder'");
    }
}

class CardPaymentProcessor {
    public static void processCardPayment(String cardNumber, Date expiryDate, int cvv) {
        if (cardNumber.length() == 14) {
            System.out.println("Card accepted");
        } else {
            System.out.println("Invalid card");
            return;
        }
        char firstCardDigit = cardNumber.charAt(0);
        String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
        StringBuilder cardNumberToDisplay = new StringBuilder(cardNumber);
        for (int i = 1; i < cardNumber.length() - 4; i++) {
            cardNumberToDisplay.setCharAt(i, '*');
        }
        System.out.println("First card digit: " + firstCardDigit);
        System.out.println("Last four digits: " + lastFourDigits);
        System.out.println("Card number to display: " + cardNumberToDisplay.toString());
    }

    public static void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        System.out.println("Pizza of the day: " + pizzaOfTheDay);
        System.out.println("Side of the day: " + sideOfTheDay);
        System.out.println("Special price: " + specialPrice);
    }
}

public class exercise7 {
    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria("Slice - o - Heaven", "123 Pizza St", "www.pizza.com", 1234567890L, "Pizza, Sides, Drinks");
        pizzeria.takeOrder();
        CardPaymentProcessor.specialOfTheDay("Pepperoni Pizza", "Fries", "$10");
    }
}