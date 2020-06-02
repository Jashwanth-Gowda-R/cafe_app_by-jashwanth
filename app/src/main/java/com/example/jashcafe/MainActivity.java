package com.example.jashcafe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order is clicked.
     */
    public void submitOrder(View view) {
        int price = 0;
        CheckBox whippedCreamchechbox = (CheckBox) findViewById(R.id.notify_me_checkbox);
        boolean add = whippedCreamchechbox.isChecked();

        CheckBox chocolatechechbox = (CheckBox) findViewById(R.id.notify_me_checkbox2);
        boolean add1 = chocolatechechbox.isChecked();

        CheckBox chitranachechbox = (CheckBox) findViewById(R.id.notify_me_checkbox3);
        boolean add2 = chitranachechbox.isChecked();

        CheckBox vadachechbox = (CheckBox) findViewById(R.id.notify_me_checkbox4);
        boolean add3 = vadachechbox.isChecked();

        EditText nameedit = (EditText) findViewById(R.id.name);
        String name = nameedit.getText().toString();

        String priceMessage = createOrderSummary(price, add, add1, add2, add3, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        intent.putExtra(Intent.EXTRA_SUBJECT, "JASHCAFE TAKE THESE ORDER FOR " + name);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public void increment(View view) {
        if (quantity == 20) {
            Toast.makeText(this, "you cannot have more than 20 Coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "you cannot have less than 1 Coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    public int calPrice(boolean add, boolean add1, boolean add2, boolean add3) {
//        int totalprice= quantity*price;
//        return totalprice;

        int price = 0;

        if (add) {
            price += 50;
        }
        if (add1) {
            price += 70;
        }
        if (add2) {
            price += 40;
        }
        if (add3) {
            price += 35;
        }

        return price;

    }


    private String createOrderSummary(int price, boolean add, boolean add1, boolean add2, boolean add3, String names) {
        String priceMessage = "NAME:" + names;
        priceMessage += "\n ADD 1 plate Bisibelebath ? = " + add;
        priceMessage += "\n ADD 1 plate Veg Briyani ? = " + add1;
        priceMessage += "\n ADD 1 plate Chitrana ? = " + add2;
        priceMessage += "\nADD 2 Idli and 1 VADa ? = " + add3;
//        priceMessage=priceMessage+"\n QUANTITY:" +quantity;
        priceMessage = priceMessage + "\n Total item Count " + quantity + " coffees is=Rs " + quantity * 20 + "\n total Special Thindi amount=Rs " + calPrice(add, add1, add2, add3) + "\nTotal Bill Amount= RS" + ((quantity * 20) + calPrice(add, add1, add2, add3)) + " \n Thank you ";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }


}





