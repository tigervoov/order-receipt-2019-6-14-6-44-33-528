package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceiptItems() {
        StringBuilder output = new StringBuilder();

        // print headers
        printHeader(output);

        // print date, bill no, customer name
        printCustomerInfo(output);

        // prints lineItems
        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            printReceiptItems(output, lineItem);

            // calculate sales tax @ rate of 10%
            totSalesTx += getSalesTax(lineItem);

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            tot += lineItem.totalAmount() + getSalesTax(lineItem);
        }

        // prints the state tax
        printsTheStateTax(output, totSalesTx, "Sales Tax");

        // print total amount
        printTotalAmount(tot, getOutput(output), "Total Amount");
        return output.toString();
    }

    private void printTotalAmount(double tot, StringBuilder output2, String s) {
        output2.append(s).append('\t').append(tot);
    }

    private StringBuilder getOutput(StringBuilder output) {
        return output;
    }

    private void printsTheStateTax(StringBuilder output, double totSalesTx, String s) {
        printTotalAmount(totSalesTx, output, s);
    }

    private double getSalesTax(LineItem lineItem) {
        return lineItem.totalAmount() * .10;
    }

    private void printReceiptItems(StringBuilder output, LineItem lineItem) {
        output.append(lineItem.getDescription());
        output.append('\t');
        output.append(lineItem.getPrice());
        output.append('\t');
        output.append(lineItem.getQuantity());
        output.append('\t');
        output.append(lineItem.totalAmount());
        output.append('\n');
    }

    private void printCustomerInfo(StringBuilder output) {
        output.append(this.order.getCustomerName());
        output.append(this.order.getCustomerAddress());
    }

    private void printHeader(StringBuilder output) {
        output.append("======Printing Orders======\n");
    }
}