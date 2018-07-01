package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Activity for viewing, updating, and deleting businesses
 */
public class DetailViewActivity extends Activity {

    private EditText numberField, nameField, primaryBusinessField, addressField, provinceFeild, hintFeild;
    Business receivedbusinessInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedbusinessInfo = (Business)getIntent().getSerializableExtra("Business");
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        numberField = (EditText) findViewById(R.id.number);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceFeild = (EditText) findViewById(R.id.province);

        hintFeild = (EditText) findViewById(R.id.hint);

        if(receivedbusinessInfo != null){
            numberField.setText(receivedbusinessInfo.number);
            nameField.setText(receivedbusinessInfo.name);
            primaryBusinessField.setText(receivedbusinessInfo.primaryBusiness);
            addressField.setText(receivedbusinessInfo.address);
            provinceFeild.setText(receivedbusinessInfo.province);
        }
    }

    /**
     * Updates a business in the database
     * @param v view with new attributes in fields
     */
    public void updateContact(View v){
        String number = numberField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceFeild.getText().toString();
        Business person = new Business(receivedbusinessInfo.key, number, name, primaryBusiness, address, province);

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
            appState.firebaseReference.child(receivedbusinessInfo.key).setValue(person);
            hintFeild.setText("");
        }
    }

    /**
     * Deletes a business in the database
     * @param v view that displays business that will be deleted
     */
    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedbusinessInfo.key).removeValue();
        finish();
    }
}
