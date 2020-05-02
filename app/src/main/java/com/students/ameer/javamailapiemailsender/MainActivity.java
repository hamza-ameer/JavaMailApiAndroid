package com.students.ameer.javamailapiemailsender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText myEmail;
    EditText myPassword;
    EditText receiverEmail;
    EditText Title;
    EditText Message;
    Button BtnSendEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myEmail=(EditText)findViewById(R.id.myEmail);
        myPassword=(EditText)findViewById(R.id.myPassword);
        receiverEmail=(EditText)findViewById(R.id.receiverEmail);
        Title=(EditText)findViewById(R.id.title);
        Message=(EditText)findViewById(R.id.message);
        BtnSendEmail=(Button) findViewById(R.id.SendEmail);

        BtnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sender=myEmail.getText().toString();
                String senderPass=myPassword.getText().toString();
                String receiver=receiverEmail.getText().toString();
                String title=Title.getText().toString();
                String message=Message.getText().toString();

                sendEmail(sender,senderPass,receiver,title,message);
            }
        });

    }

    private void sendEmail(final String Sender,final String Password,final String Receiver,final String Title,final String Message)
    {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender(Sender,Password);
                    sender.sendMail(Title, "<b>"+Message+"</b>", Sender, Receiver);
                    makeAlert();

                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

        }).start();
    }
    private void makeAlert(){
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(MainActivity.this, "Mail Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

