package sg.edu.rp.c346.id18054367.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView tvAmount;
    EditText amount;
    TextView tvPax;
    EditText pax;
    ToggleButton svs;
    ToggleButton gst;
    TextView tvDiscount;
    EditText discount;
    TextView totalBill;
    TextView eachPays;
    Button btnSplit;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAmount = findViewById(R.id.amountText);
        amount = findViewById(R.id.amount);
        tvPax = findViewById(R.id.paxText);
        pax = findViewById(R.id.pax);
        svs = findViewById(R.id.SVSToggle);
        gst = findViewById(R.id.GSTToggle);
        tvDiscount = findViewById(R.id.discountText);
        discount = findViewById(R.id.discount);
        totalBill = findViewById(R.id.totalBill);
        eachPays = findViewById(R.id.eachPay);
        btnSplit = findViewById(R.id.split);
        btnReset = findViewById(R.id.reset);




        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                double totalamt = 0.0;
                if (!svs.isChecked() && !gst.isChecked()) {
                    totalamt = Double.parseDouble(amount.getText().toString());
                } else if (svs.isChecked() && !gst.isChecked()){
                    totalamt = Double.parseDouble(amount.getText().toString()) * 1.1;
                } else if (!svs.isChecked() && gst.isChecked()) {
                    totalamt = Double.parseDouble(amount.getText().toString()) * 1.07;
                } else {
                    totalamt = Double.parseDouble(amount.getText().toString()) * 1.17;
                }

                if (discount.length() != 0){
                    totalamt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                }

                totalBill.setText("Total Bill: $" + String.format("%.2f", totalamt));
                int nop = Integer.parseInt(pax.getText().toString());
                if (nop != 1){
                    eachPays.setText("Each Pays: $" + String.format("%.2f", totalamt / nop));
                } else {
                    eachPays.setText("Each Pays: $" + totalamt);
                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                pax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        });

    }
}
