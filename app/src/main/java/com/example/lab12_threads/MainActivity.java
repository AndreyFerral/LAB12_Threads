package com.example.lab12_threads;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Описание тэга для логов debug
    private static final String TAG = "myLogs";

    // Объявим переменные компонентов
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Найдем компонент в XML разметке
        textView = (TextView)findViewById(R.id.textView);

        // Определяем объект Runnable
        Runnable firstRunnable = new Runnable() {
            @Override
            public void run() {

                Random random = new Random();

                while (true) {

                    // Изменяем цвет текстового поля
                    textView.post(new Runnable()
                    {
                        public void run()
                        {
                            // Генерируем рандомный цвет
                            int rRandColor = random.nextInt(255);
                            int gRandColor = random.nextInt(255);
                            int bRandColor = random.nextInt(255);
                            int randColor = Color.rgb(rRandColor, gRandColor, bRandColor);

                            // Устанавливаем рандомный цвет
                            textView.setTextColor(randColor);

                            // Отправляем в LogCat полученный цвет
                            Log.d(TAG, "Первый поток - " + String.valueOf(rRandColor) + " " +  String.valueOf(gRandColor) + " " + String.valueOf(bRandColor));
                        }
                    });

                    // Усыпляем поток на 2 секунды
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        // Определяем объект Thread - новый поток
        Thread firstThread = new Thread(firstRunnable);

        // Запускаем поток
        firstThread.start();

        // Определяем объект Runnable
        Runnable secondRunnable = new Runnable() {
            @Override
            public void run() {

                Random random = new Random();

                // Усыпляем поток на секунду
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while (true) {

                    // Изменеяем шрифт у текстового поля
                    textView.post(new Runnable()
                    {
                        public void run()
                        {
                            // Генерируем рандомный шрифт
                            int randSize = random.nextInt(100);

                            // Устанавливаем рандомный шрифт
                            textView.setTextSize(randSize);

                            // Отправляем в LogCat полученный размер
                            Log.d(TAG, "Второй поток - " + (String.valueOf(randSize)));
                        }
                    });

                    // Усыпляем поток на 2 секунды
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        // Определяем объект Thread - новый поток
        Thread secondThread = new Thread(secondRunnable);

        // Запускаем поток
        secondThread.start();
    }
}