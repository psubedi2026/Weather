package com.tiydc.weather;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        start();

    }

    private void start() {

        String url = "https://spurious-balance-7335.justapis.io/weather.json";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        WeatherObject weatherObject = restTemplate.getForObject(url, WeatherObject.class);

        TextView tempView = (TextView) findViewById(R.id.tempTextView);
        tempView.setText(weatherObject.getTemp());

        TextView locationView = (TextView) findViewById(R.id.locationTextView);
        locationView.setText(weatherObject.getLocation());

        TextView conditionView = (TextView) findViewById(R.id.conditionTextView);
        conditionView.setText(weatherObject.getCondition());

        WebView weatherImg = (WebView) findViewById(R.id.weatherImgWebView);
        weatherImg.loadUrl(weatherObject.getImage());
        //weatherImg.

    }


}
