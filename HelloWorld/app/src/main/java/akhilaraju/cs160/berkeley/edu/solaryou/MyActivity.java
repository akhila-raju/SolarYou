package akhilaraju.cs160.berkeley.edu.solaryou;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final Spinner spinner = (Spinner) findViewById(R.id.planet_chooser);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        final TextView mercury = (TextView) findViewById(R.id.mercury);
        final TextView venus = (TextView) findViewById(R.id.venus);
        final TextView earth = (TextView) findViewById(R.id.earth);
        final TextView mars = (TextView) findViewById(R.id.mars);
        final TextView jupiter = (TextView) findViewById(R.id.jupiter);
        final TextView saturn = (TextView) findViewById(R.id.saturn);
        final TextView uranus = (TextView) findViewById(R.id.uranus);
        final TextView neptune = (TextView) findViewById(R.id.neptune);

        final TextView[] planetAges = {mercury, venus, earth, mars, jupiter, saturn, uranus, neptune};

        Button button= (Button) findViewById(R.id.convert_button);
        button.setOnClickListener(new View.OnClickListener() {
            String homePlanet = "Mercury";
            @Override
            public void onClick(View v) {
                EditText age = (EditText) findViewById(R.id.age_field);
                String ageString = age.getText().toString();
                if (!ageString.equals("")) {
                    Double origAge = Double.valueOf(ageString);
                    String itemText = (String) spinner.getSelectedItem();
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            homePlanet = (String) parentView.getItemAtPosition(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                        }
                    });
                    for (int i = 0; i < planetAges.length; i++) {
                        double homeYears = 0;
                        double[] planetYears = {0.24, 0.62, 1, 1.88, 11.862, 29.456, 84.07, 164.81};
                        if (homePlanet.equals("Mercury")) {
                            homeYears = planetYears[0];
                        } else if (homePlanet.equals("Venus")) {
                            homeYears = planetYears[1];
                        } else if (homePlanet.equals("Earth")) {
                            homeYears = planetYears[2];
                        } else if (homePlanet.equals("Mars")) {
                            homeYears = planetYears[3];
                        } else if (homePlanet.equals("Jupiter")) {
                            homeYears = planetYears[4];
                        } else if (homePlanet.equals("Saturn")) {
                            homeYears = planetYears[5];
                        } else if (homePlanet.equals("Uranus")) {
                            homeYears = planetYears[6];
                        } else if (homePlanet.equals("Neptune")) {
                            homeYears = planetYears[7];
                        }
                        for (int j = 0; j < planetYears.length; j++) {
                            double conversion = origAge * homeYears / planetYears[j];
                            BigDecimal bd = new BigDecimal(conversion).setScale(2, RoundingMode.HALF_EVEN);
                            conversion = bd.doubleValue();
                            planetAges[j].setText(String.valueOf(conversion) + " years");
                        }
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
