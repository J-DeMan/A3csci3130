package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity for creating Businesses
 */
public class CreateBusinessAcitivity extends Activity {

    private Button submitButton;
    private EditText numberField, nameField, primaryBusinessField, addressField, provinceFeild, hintFeild;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        numberField = (EditText) findViewById(R.id.number);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceFeild = (EditText) findViewById(R.id.province);

        hintFeild = (EditText) findViewById(R.id.hint);

    }

    /**
     * Adds a new business to the database based on the text field entries.
     * @param v View with text fields
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String businessID = appState.firebaseReference.push().getKey();

        String number = numberField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceFeild.getText().toString();

        if (number.length() != 9 || !number.matches("[0-9]*")) {
            hintFeild.setText("Invalid business number");
        } else if (name.length() < 2 || name.length() > 48) {
            hintFeild.setText("Invalid name");
        } else if (!primaryBusiness.matches("(Fisher|Distributor|Processor|Fish Monger)")) {
            hintFeild.setText("Invalid primary business");
        } else if (address.length() > 50) {
            hintFeild.setText("Invalid address");
        } else if (!province.matches("(AB|BC|MB|NB|NL|NS|NT|NU|ON|PE|QC|SK|YT)") || province.length() == 0) {
            hintFeild.setText("Invalid province/territory");
        } else {
            Business person = new Business(businessID, number, name, primaryBusiness, address, province);
            appState.firebaseReference.child(businessID).setValue(person);
            finish();
        }

    }
}
