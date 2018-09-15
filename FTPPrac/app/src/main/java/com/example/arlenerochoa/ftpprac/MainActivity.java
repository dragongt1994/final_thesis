package com.example.arlenerochoa.ftpprac;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.arlenerochoa.ftpprac.net.ftp.FTP;
import com.example.arlenerochoa.ftpprac.net.ftp.FTPClient;
import com.example.arlenerochoa.ftpprac.net.ftp.FTPHTTPClient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button b,b2;
    private Spinner sp1;
    private FTP ftp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initUI();
    }
    private void initUI(){
        this.b=findViewById(R.id.button);
        this.b.setOnClickListener(this);
        this.b2=findViewById(R.id.button2);
        this.b2.setOnClickListener(this);
        this.sp1=findViewById(R.id.spinner1);

    }

    @Override
    public void onClick(View v) {
        if(R.id.button==v.getId()) {
            Thread a = new Thread(new Runnable(

            ) {
                @Override
                public void run() {
                    String proxyHost, proxyUser, proxyPassword;
                    int proxyPort;
                    proxyHost = "192.168.1.9";
                    proxyPort = 21;
                    proxyUser = "user1";
                    proxyPassword = "123456";
                    FTPClient client = new FTPClient();
                    try {
                        client.connect(proxyHost);
                        client.login(proxyUser, proxyPassword);
                        String reply = client.getStatus();
                        ftp1 = new FTPHTTPClient(proxyHost, proxyPort, proxyUser, proxyPassword);
                        String[] fileNames = client.listNames();
                        if (fileNames != null) {
                            for (String file : fileNames) {
                                Log.d("DebugMark", "f=" + file);

                            }
                        }
                        File firstLocalFile = new File(Environment.getExternalStorageDirectory().getPath()+"/rec.dat");

                        String firstRemoteFile = "rec2.csv";
                        InputStream inputStream = new FileInputStream(firstLocalFile);

                        client.setFileType(FTP.BINARY_FILE_TYPE);
                        client.enterLocalPassiveMode();

                        boolean p=client.storeFile(firstRemoteFile,inputStream);
                        client.enterLocalPassiveMode();


                        if(p){

                            Log.d("DebugMark","success");
                        }
                        String remoteFilePath = "PeriodGraph-9-9-2018-7-48-7.dat";
                        File localfile = new File(Environment.getExternalStorageDirectory().getPath()+"/downloaded2.csv");
                        OutputStream outputStream = new FileOutputStream(localfile);
                        client.setFileType(FTP.BINARY_FILE_TYPE);
                        boolean success = client.retrieveFile(remoteFilePath, outputStream);
                        outputStream.close();
                        if(success){

                            Log.d("DebugMark","success doownloaded");
                        }
                    } catch (Exception e) {
                        Log.d("DebugMark", e.toString());
                    }

                }


            });
            a.start();

        }
        else if(R.id.button2== v.getId()){
            String jack[]={"mark","fda","fdf"}; 
            ArrayAdapter<String> a=new ArrayAdapter<String>(this,R.layout.activity_main2,R.id.textview1,jack);
            //a.addAll(jack);
            this.sp1.setAdapter(a);

        }

    }
}
