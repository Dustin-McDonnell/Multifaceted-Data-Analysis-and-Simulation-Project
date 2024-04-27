package Java;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class StockBot {
    private Scanner scanner = new Scanner(System.in);
    public ArrayList<String> stockBot(ArrayList<ArrayList<String>> data){
        Integer initialBalance = null;
        ArrayList<String> balance = new ArrayList<>();
        while (initialBalance == null) {
            System.out.println("Enter your starting balance.");
            if(scanner.hasNextInt()){
                initialBalance = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
            }
        }

        int algorithmNumber = -1;
        if (algorithmNumber == -1) {
            System.out.println("Enter the number of the algorithm you wish to use.");
            if(scanner.hasNextInt()){
                algorithmNumber = scanner.nextInt();
                scanner.nextLine();  // Clear the scanner
                if (algorithmNumber == 1){
                    balance = tradeEvaluator(initialBalance,data);
                }
                //if (algorithmNumber == 2){
                //   buyAndHold(initialBalance,data);
                //}
                //if (algorithmNumber == 3){
                //  buyLowSellHigh(initialBalance,data);
                //}
            } else {
                scanner.nextLine();
            }
        }
        return balance;
    }

    //I hate this monster I'm embarrassed to have made it.
    public ArrayList<String> tradeEvaluator(int initialBalance,  ArrayList<ArrayList<String>> data){
        ArrayList<String> dateBalance = new ArrayList<>();
        int timePeriod = -1;
        while (timePeriod < 0) {
            System.out.println("Enter the desired time period. ie: If you enter 2 depending on the CSV chosen it will be two weeks from the first date in the CSV");
            System.out.println("or two days from the start etc.");
            if (scanner.hasNextInt()) {
                timePeriod = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
            }
        }
        int stocksHeld = 0;
        Double totalValue  = (double) initialBalance;
        Double stockValue = 0.0;
        for (int i = 0; i < timePeriod; i++){
            int counter = 0;
            Double closingPrice = Double.parseDouble(data.get(i).get(4));
            if(i < data.size()){
                if(movingAveragecheck(data, i)){
                    counter ++;
                }
                if(fallingKnife(data, i)){
                    counter ++;
                }
                if(vibeCheck()){
                    counter ++;
                }
            }
            if (counter == 0){
                //Sells 20 Percent of Stock Portfolio then adds the value back to Liquid Cash
                double portfolioValue = stocksHeld * closingPrice;
                double fundsFromSale = portfolioValue * 0.2;
                int stocksToSell = (int) (fundsFromSale / closingPrice);
                double costOfStocksSold = stocksToSell * closingPrice;
                initialBalance += costOfStocksSold;
                stocksHeld -= stocksToSell;
                stockValue = stocksHeld * closingPrice;
                totalValue = stockValue + initialBalance;
                dateBalance.add(data.get(i).get(0) + ", Liquid Cash: " + initialBalance + ", Total Portfolio Balance: " +  totalValue + ", Stocks Held: "+ stocksHeld +
                        ", Value in Stock: "+ stockValue + ", Movement: Sold");
            }
            if (counter == 1){
                //Here we hodl.
                dateBalance.add(data.get(i).get(0) + ", Balance: " + totalValue + " , Movement: Held");
            }

            if (counter == 2){
                double fundsToUse = initialBalance * 0.2;
                initialBalance -= fundsToUse;
                int stocksBought = (int) (fundsToUse / closingPrice);
                double costOfStocksBought = stocksBought * closingPrice;
                double remainder = fundsToUse - costOfStocksBought;
                initialBalance += remainder;
                stocksHeld += stocksBought;
                stockValue = stocksHeld * closingPrice;
                totalValue = stockValue + initialBalance;
                dateBalance.add(data.get(i).get(0) + ", Liquid Cash: " + initialBalance + ", Total Portfolio Balance: " +  totalValue + ", Stocks Held: "+ stocksHeld +
                        ", Value in Stock: "+ stockValue + ", Movement: Bought");
            }
            if (counter == 3){
                //Buy signal with 50 percent of funds
                double fundsToUse = initialBalance * 0.5;
                initialBalance -= fundsToUse;
                int stocksBought = (int) (fundsToUse / closingPrice);
                double costOfStocksBought = stocksBought * closingPrice;
                double remainder = fundsToUse - costOfStocksBought;
                initialBalance += remainder;
                stocksHeld += stocksBought;
                stockValue = stocksHeld * closingPrice;
                totalValue = stockValue + initialBalance;
                dateBalance.add(data.get(i).get(0) + ", Liquid Cash: " + initialBalance + ", Total Portfolio Balance: " +  totalValue + ", Stocks Held: "+ stocksHeld +
                        ", Value in Stock: "+ stockValue + ", Movement: Bought");

            }
            if (counter == 4){
                //Buy signal with 10 percent of funds
                double fundsToUse = initialBalance;
                initialBalance -= fundsToUse;
                int stocksBought = (int) (fundsToUse / closingPrice);
                double costOfStocksBought = stocksBought * closingPrice;
                double remainder = fundsToUse - costOfStocksBought;
                initialBalance += remainder;
                stocksHeld += stocksBought;
                stockValue = stocksHeld * closingPrice;
                totalValue = stockValue + initialBalance;
                dateBalance.add(data.get(i).get(0) + ", Liquid Cash: " + initialBalance + ", Total Portfolio Balance: " +  totalValue + ", Stocks Held: "+ stocksHeld +
                        ", Value in Stock: "+ stockValue + ", Movement: Bought");
            }
        }
        return dateBalance;
    }

    public Boolean movingAveragecheck(ArrayList<ArrayList<String>> data, int i){
        //Sends a buy signal if moving average is above close
        if(Double.parseDouble(data.get(i).get(7)) > Double.parseDouble(data.get(i).get(4))){
            return true;
        }
        else{
            return false;
        }
    }

    //public Boolean rsiCheck(ArrayList<ArrayList<String>> data, int i){
      //  //We're taking a more aggressive stance if RSI is above 50 as opposed to the traditional 70 we buy!
        //if(Double.parseDouble(data.get(i).get(8)) > 50){
          //  return true;
        //}
        //else{
          //  return false;
        //}
   // }

    public Boolean fallingKnife(ArrayList<ArrayList<String>> data, int i){
        //Sending a buy signal is closing price is less than opening
        if(Double.parseDouble(data.get(i).get(4)) < Double.parseDouble(data.get(i).get(1))){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean vibeCheck(){
        //The market isn't rational and neither am I.
        //Back when I used to trade actively my intuition played a heavy factor in pulling the trigger on trades.
        //To simulate this we will be using a coin flip.
        if (Math.random() > .5){
            return true;
        }
        else{
            return false;
        }
    }
}
