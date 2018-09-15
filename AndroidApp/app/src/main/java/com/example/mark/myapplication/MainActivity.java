package com.example.mark.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button conn_btn,graph_btn,sendgraph_btn,setpos_btn,getweight_btn, accel_btn,reset_btn,request_btn,ftp_btn,exec_btn;
    private EditText ip_addr,port_numb;
    private Spinner sigshape_sp,freq_sp,ampl_sp,time_sp,position_ed,file_spinner;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private Socket sck;

    private Socket MyClient;
    private AlertDialog.Builder alertDialog;
    private SocketManager bgth;
    private Boolean qL;

    private File datfile;
    private GraphView graph;
    private LineGraphSeries<DataPoint>mSeries;
    private Queue<Integer> queue;

    private MainActivity temp;


    private ArrayList<Float> gr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ip_addr = (EditText)findViewById(R.id.ip_addr);
        this.ip_addr.setText("192.168.1.4");
        this.port_numb =(EditText)findViewById(R.id.port_numb);
        this.port_numb.setText("9000");
        this.position_ed=(Spinner) findViewById(R.id.pos_sp);



        this.sigshape_sp=(Spinner)findViewById(R.id.sigshape_sp);
        this.freq_sp=(Spinner)findViewById(R.id.freq_sp);
        this.ampl_sp=(Spinner)findViewById(R.id.ampl_sp);
        this.time_sp=(Spinner)findViewById(R.id.time_sp);




        this.alertDialog = new AlertDialog.Builder(this);
        //set the title to be appear in the dialog
        this.alertDialog.setTitle("Draft");

//sets the message to be displayed in the alert dialog

        this.alertDialog.setMessage("Connected");
        // Showing Alert Message

        this.conn_btn = findViewById(R.id.conn_btn);
        this.conn_btn.setOnClickListener(this);
        this.graph_btn = findViewById(R.id.graph_btn);
        this.graph_btn.setOnClickListener(this);
        this.setpos_btn=findViewById(R.id.setpos_btn);
        this.setpos_btn.setOnClickListener(this);
        this.sendgraph_btn=findViewById(R.id.sendgraph_btn);
        this.sendgraph_btn.setOnClickListener(this);
        this.getweight_btn=findViewById(R.id.getweight_btn);
        this.getweight_btn.setOnClickListener(this);
        this.accel_btn=findViewById(R.id.accel_btn);
        this.accel_btn.setOnClickListener(this);
        this.reset_btn=findViewById(R.id.reset_btn);
        this.reset_btn.setOnClickListener(this);
        this.request_btn=findViewById(R.id.request_list);
        this.request_btn.setOnClickListener(this);

        this.ftp_btn=findViewById(R.id.ftp_btn);
        this.ftp_btn.setOnClickListener(this);

        this.exec_btn=findViewById(R.id.exec_btn);
        this.exec_btn.setOnClickListener(this);
        this.bgth =new SocketManager();
        this.qL=true;
        this.queue =new LinkedList<>();

        this.file_spinner=findViewById(R.id.file_spinner);

        this.bgth.execute();

        /*
        ActivityCompat.requestPermissions(
                this,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
        );*/
        temp=this;

        String s,s2;
        try {
            s=Environment.getExternalStorageDirectory().getPath();
            Log.d("DebugMark",s);
            BufferedWriter w = new BufferedWriter(new FileWriter(s+"/test3.txt"));
            w.write("start");
            w.flush();
            w.close();
            BufferedReader f=new BufferedReader(new FileReader(s+"/test3.txt"));
            s2=f.readLine();
            File f2=new File(s+"/test3.txt");
            Log.d("DebugMark","str "+s2+" "+f2.length());

        }
        catch (Exception e) {
            Log.d("DebugMark", e.toString());
        }
        Log.d("DebugMark", "First");

        this.graph = (GraphView) findViewById(R.id.graph);

        graph.getViewport().setMinX(0);
        //graph.getViewport().setMaxX(gr.size()*0.1);
        graph.getViewport().setMaxX(120);


        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setMinY(-1);
        graph.getViewport().setMaxY(1);
        graph.getViewport().setYAxisBoundsManual(true);

        this.mSeries = new LineGraphSeries<>();

        //series.
        //graph.addSeries(series);
        FTPClient a;
    }
    public void hello()
    {
        Log.d("DebugMark","hello");
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }
    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.graph_btn){

            this.queue.add(9);



        }
        else if(view.getId()==R.id.conn_btn) {


            queue.add(1);

            this.alertDialog.show();
        }
        else if(view.getId()==R.id.setpos_btn){
            Log.d("DebugMark","setpos_btn");
            this.queue.add(4);

        }
        else if(view.getId()==R.id.sendgraph_btn){
            Log.d("DebugMark","sendgraph_btn");
            this.queue.add(3);
        }
        else if(view.getId()==R.id.getweight_btn){
            this.queue.add(5);
        }
        else if(view.getId()==R.id.accel_btn){
            this.queue.add(8);
        }
        else if(view.getId()==R.id.reset_btn){
            this.gr=null;
            this.mSeries.resetData(new DataPoint[] {
                    new DataPoint(0,0)
            });
        }
        else if(view.getId()==R.id.request_list){
            this.queue.add(10);

        }
        else if(view.getId()==R.id.ftp_btn){
            this.queue.add(11);
        }
        else if(view.getId()==R.id.exec_btn){
            this.queue.add(12);


        }

    }


    private class SocketManager extends AsyncTask<String, Integer, Void>{
        private BufferedWriter out;
        private BufferedReader in;
        private Message msg;
        private byte a[]=new byte[16];
        private byte brecv[]=new byte[16];
        private byte btemp[];
        private final static int PROC_POS=1;
        private final static int FIN_POS=2;
        private final static int PROC_SENDING_SIG =3;
        private final static int FIN_SENDING_SIG=4;

        private final static int PROC_REQT_WEIGHT=5;
        private final static int FIN_REQT_WEIGHT=6;
        private final static int INIT_MACHINE=7;
        private final static int FIN_INIT_MACHINE=8;

        private final static int UPDATE_GRAPH=123;

        private final static int UPDATE_FILELIST=9;

        private float weight,position;
        private TextView status_tv,weight_tv;

        private ArrayAdapter<String> arr_str;

        public SocketManager()
        {
            super();
            status_tv=(TextView)findViewById(R.id.status_tv);
            weight_tv=(TextView)findViewById(R.id.weight_tv);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aLong) {
            super.onPostExecute(aLong);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int ctr;
            switch(values[0]){
                case PROC_POS:
                    this.status_tv.setText("PROCESSING POSITION");
                    break;
                case FIN_POS:
                    Log.d("DebugMark","POSITION: "+Float.toString(this.position));
                    this.status_tv.setText("POSITION: "+Float.toString(this.position));
                    break;
                case PROC_SENDING_SIG:
                    this.status_tv.setText("SENDING SIGNAL");
                    break;
                case FIN_SENDING_SIG:
                    this.status_tv.setText("SIGNAL SENT");
                    break;
                case PROC_REQT_WEIGHT:
                    this.status_tv.setText("PROCESSING POSITION");
                    break;
                case FIN_REQT_WEIGHT:
                    this.status_tv.setText("PROCESSED WEIGHT");
                    this.weight_tv.setText(Float.toString(this.weight));
                    break;
                case  INIT_MACHINE:
                    this.status_tv.setText("Initializing Machine");
                case FIN_INIT_MACHINE:
                    this.status_tv.setText("Machine Initialized");
                    break;
                case UPDATE_GRAPH:
                Log.d("DebugMark","update graph");
                    //graph.addSeries(mSeries);
                 //mSeries.appendData(new DataPoint(values[1]* 0.1, gr.get(values[1])), false, gr.size());
                    break;
                case UPDATE_FILELIST:
                    file_spinner.setAdapter(arr_str);

            }

        }

        @Override
        protected void onCancelled(Void aLong) {
            super.onCancelled(aLong);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(String... params) {
            String ip;
            int ctr;
            int port;

            String recv, send;
            Integer t;
            while (true) {
                if (queue.size() > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    t = queue.remove();
                    Log.d("DebugMark", t.toString());
                    switch (t) {
                        case 1:
                            this.initSockets();
                            break;
                        case 2:
                            this.initMachine();
                            break;
                        case 3:
                            this.SendData();
                            break;
                        case 4:
                            this.SetPos(Integer.parseInt(position_ed.getSelectedItem().toString()));
                            break;
                        case 5:
                            this.Reqt_Weight();
                            break;
                        case 6:
                            this.Rtrv_Weight();
                            break;
                        case 7:
                            this.Rtrv_Pos();
                            break;
                        case 8:
                            this.getAccel();
                            break;
                        case 9:
                            this.customGraph();
                            break;
                        case 10:
                            Log.d("debugmark","here at get siglist");
                            this.getSigList();
                            break;
                        case 11:
                            this.filelist();
                            break;
                        case 12:
                            String s=((TextView)((LinearLayout)file_spinner.getSelectedView()).getChildAt(0)).getText().toString();
                            Log.d("debugmark","get "+((TextView)((LinearLayout)file_spinner.getSelectedView()).getChildAt(0)).getText());
                            this.execSig(s);
                    }
                }
            }
        }

        private void execSig(String file) {
            float value;

            String temp;
            this.msg=new Message(Mssg_Type.COMM,Comm_Type.EXEC_SIGNAL,file);
            temp=this.msg.getBuff();
            try {
                //   out.write("hello");
                out.flush();
                Log.d("DebugMark",temp+" test");

                out.write(temp);
                out.flush();

                out.newLine();
                //this.publishProgress(this.PROC_REQT_WEIGHT);
                out.flush();
                temp=in.readLine();
                Log.d("DebugMark",temp+" here");
                this.msg=new Message(temp);
                if(this.msg.getMssgtype().equals(Mssg_Type.CONFI.toString()) && this.msg.getCommtype().equals(Comm_Type.EXEC_SIGNAL)){
                    Log.d("DebugMark","confirmed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void filelist() {

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
                        String[] fileNames = client.listNames();
                        //                      String jack[]={"mark","fda","fdf"};
                        arr_str=new ArrayAdapter<String>(temp,R.layout.signal_pi_list,R.id.textView,fileNames);
                        //a.addAll(jack);
                        this.publishProgress(UPDATE_FILELIST);

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


        private void getSigList() {
            float value;

            String temp;
            String cont[];
            this.msg=new Message(Mssg_Type.COMM,Comm_Type.REQT_SIGLIST,"0");
            temp=msg.getBuff();
            try {
                //   out.write("hello");
                out.flush();
                Log.d("DebugMark",temp+" test");

                out.write(temp);
                out.flush();

                out.newLine();
                //this.publishProgress(this.PROC_REQT_WEIGHT);
                out.flush();
                temp=in.readLine();
                Log.d("DebugMark",temp);
                this.msg=new Message(temp);
                Log.d("DebugMark2",this.msg.getCommtype());
                Log.d("DebugMark2",this.msg.getMssgtype());
                if(this.msg.getMssgtype().equals(Mssg_Type.CONFI.toString()) && this.msg.getCommtype().equals(Comm_Type.REQT_SIGLIST.toString())){
                    Log.d("DebugMark","confirmed");
                    temp=in.readLine();

                    cont=temp.split(",");

                            Log.d("DebugMark","l= "+ cont.length);


                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        private void customGraph() {
            Log.d("DebugMark","customGraph");
            String shape;
            float freq,ampl,time;
            int ctr,div;
            //ArrayList<Float> temp
            shape=sigshape_sp.getSelectedItem().toString();
            ampl=Float.parseFloat(ampl_sp.getSelectedItem().toString());
            freq=Float.parseFloat(freq_sp.getSelectedItem().toString());
            time=Float.parseFloat(time_sp.getSelectedItem().toString())*60;
            if (gr==null){
            if(sigshape_sp.getSelectedItem().toString().equals(("Square")))
            {
                gr=WaveGenerator.getSquare(ampl,freq,time,0.1f);
            }
            else if(sigshape_sp.getSelectedItem().toString().equals(("Sine"))){
                gr=WaveGenerator.getSine(ampl,freq,time,0.1f);
            }
            else if(sigshape_sp.getSelectedItem().toString().equals(("Triangle"))){
                gr=WaveGenerator.getTriangle(ampl,freq,time,0.1f);

            }
            }
            else{

                if(sigshape_sp.getSelectedItem().toString().equals(("Square")))
                {

                    gr.addAll(WaveGenerator.getSquare(ampl,freq,time,0.1f));
                }
                else if(sigshape_sp.getSelectedItem().toString().equals(("Sine"))){
                    gr.addAll(WaveGenerator.getSine(ampl,freq,time,0.1f));
                }
                else if(sigshape_sp.getSelectedItem().toString().equals(("Triangle"))){
                    gr.addAll(WaveGenerator.getTriangle(ampl,freq,time,0.1f));


                }
            }
            //this.gr=WaveGenerator.getSquare(ampl,freq,time,0.1f);

/*
            graph.getViewport().setMinX(0);
            //graph.getViewport().setMaxX(gr.size()*0.1);
            graph.getViewport().setMaxX(120);


            graph.getViewport().setXAxisBoundsManual(true);

            graph.getViewport().setMinY(-1);
            graph.getViewport().setMaxY(1);
            graph.getViewport().setYAxisBoundsManual(true);*/
            //graph.getViewport().setScalable(false);

           // graph.addSeries(mSeries);

            try {

                BufferedWriter w = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getPath() + "/current_data.csv"));
                for(ctr=0;ctr<gr.size();ctr++)
                {
                    w.write(gr.get(ctr)+","+(ctr*0.1)+"\n");
                }
                w.close();
            }

            catch(Exception e)
            {

            }

            mSeries.resetData(new DataPoint[] {
                    new DataPoint(0,gr.get(0))
            });


            Log.d("DebugMark","here");
            // a potentially  time consuming task
            div=(int)(Math.ceil(time*10/200));
            for(ctr=1;ctr<gr.size();ctr=div+ctr) {
               /* try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                mSeries.appendData(new DataPoint(ctr* 0.1, gr.get(ctr)), false, gr.size());

                    //mSeries.appendData(new DataPoint(ctr*0.1,gr.get(ctr)),false,gr.size());

            }
            graph.addSeries(mSeries);
            this.publishProgress(UPDATE_GRAPH,ctr);
        }

        private void getAccel() {

            float value;
            long ctr;
            String temp;
            int ltemp;
            try {
            this.msg=new Message(Mssg_Type.COMM,Comm_Type.REQT_ACCELE,"0");
            BufferedWriter wrt=new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getPath()+"/accel_data3.csv"));
            temp=msg.getBuff();

                //   out.write("hello");
                out.flush();
                Log.d("DebugMark",temp+" test");

                out.write(temp);
                out.flush();

                out.newLine();
                out.flush();
                temp=in.readLine();
                while (temp.equals("")) {
                    temp = in.readLine();
                }
                Log.d("DebugMark",temp);

                this.msg=new Message(temp);

                Log.d("DebugMark",this.msg.getMssgtype());
                Log.d("DebugMark",this.msg.getCommtype());
                Log.d("DebugMark",this.msg.getValue());

                if(this.msg.getMssgtype().equals(Mssg_Type.CONFI.toString()) && this.msg.getCommtype().equals(Comm_Type.REQT_ACCELE.toString())){
                    ctr=0;
                    value=Float.parseFloat(this.msg.getValue());
                    Log.d("DebugMark",Float.toString(value));
                    while(ctr<value)
                    {

                            //Log.d("DebugMark","temp");


                        ltemp=this.in.read();
                            wrt.write(ltemp);


                            ctr = ctr + 1;//temp.length();
                            Log.d("DebugMark",""+ltemp);

                    }
                    Log.d("DebugMark","finished");
                    wrt.flush();
                    wrt.close();

                }


            } catch (Exception e) {
                Log.d("DebugMark",e.toString());
                e.printStackTrace();
            }
        }

        private void Rtrv_Weight() {
            float value;

            String temp;
            this.msg=new Message(Mssg_Type.COMM,Comm_Type.RTRV_WEIGHT,"0");
            temp=msg.getBuff();
            try {
                //   out.write("hello");
                out.flush();
                Log.d("DebugMark",temp+" test");

                out.write(temp);
                out.flush();

                out.newLine();
                //this.publishProgress(this.PROC_REQT_WEIGHT);
                out.flush();
                temp=in.readLine();
                Log.d("DebugMark",temp);
                this.msg=new Message(temp);
                if(this.msg.getMssgtype().equals(Mssg_Type.CONFI.toString()) && this.msg.getCommtype().equals(Comm_Type.RTRV_WEIGHT.toString())){
                    this.weight=Float.parseFloat(this.msg.getValue());
                    this.publishProgress(this.FIN_REQT_WEIGHT,Integer.parseInt(this.msg.getValue()));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private void Rtrv_Pos() {
            float value;

            String temp;
            this.msg=new Message(Mssg_Type.COMM,Comm_Type.RTRV_POSITION,"0");
            temp=msg.getBuff();
            try {
                //   out.write("hello");
                out.flush();
                Log.d("DebugMark",temp+" test");

                out.write(temp);
                out.flush();

                out.newLine();
                //this.publishProgress(this.PROC_REQT_WEIGHT);
                out.flush();
                temp=in.readLine();
                Log.d("DebugMark",temp);
                this.msg=new Message(temp);
                if(this.msg.getMssgtype().equals(Mssg_Type.CONFI.toString()) && this.msg.getCommtype().equals(Comm_Type.RTRV_POSITION.toString())){
                    this.weight=Float.parseFloat(this.msg.getValue());
                    this.publishProgress(this.FIN_POS,Integer.parseInt(this.msg.getValue()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void initSockets(){
            float settings;
            String temp;

            String ip = ip_addr.getText().toString();
            int port = Integer.parseInt(port_numb.getText().toString());
            try {
                sck=new Socket(ip,port);
                out =new BufferedWriter(new OutputStreamWriter(sck.getOutputStream()));
                in = new BufferedReader(new InputStreamReader(sck.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private void initMachine(){
            String temp,dtemp;
            //long l=datfile.length();

            this.msg=new Message(Mssg_Type.COMM,Comm_Type.INIT_MACHINE,"0");
            temp=msg.getBuff();
            try {
                BufferedReader r=new BufferedReader(new FileReader(datfile));
                out.write(temp);
                out.newLine();
                temp=in.readLine();
                this.msg=new Message(temp);
                this.publishProgress(INIT_MACHINE);
                if(this.msg.getMssgtype().equals(Mssg_Type.CONFI.toString()) && this.msg.getCommtype().equals(Comm_Type.INIT_MACHINE.toString())){
                    temp=" ";
                    while(temp!=null) {
                        temp = r.readLine();
                        out.write(temp);
                    }
                    this.publishProgress(FIN_SENDING_SIG);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("DebugMark","initMachine");

        }
        private void SendData() {
            String temp,dtemp;
            Long l,rl;
            Log.d("DebugMark","enter send data");
            try {
            File f=new File(Environment.getExternalStorageDirectory().getPath()+"/rec.dat");
            BufferedReader r=new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath()+"/rec.dat"));
            Log.d("DebugMark","File initialize");
            l=f.length();
            Log.d("DebugMark",Long.toString(l));
            this.msg=new Message(Mssg_Type.COMM,Comm_Type.SEND_SIGNAL_DATA,Long.toString(l));
            temp=msg.getBuff();
            out.flush();
            Log.d("DebugMark",temp+" test");

            out.write(temp);
            out.flush();

            out.newLine();
            out.flush();
            Log.d("DebugMark","Sending data");

            rl=0l;

                /*while ((temp = in.readLine()) != null) {
                    if (temp.trim().isEmpty()) {
                        continue;
                    }
                }*/
                do {
                    temp = in.readLine();
                }while(temp=="" );
                Log.d("DebugMark"," templ= "+temp.length());
             //   if(temp!=null && temp!="") {
                    Log.d("DebugMark", "temp="+temp+"-");
                    this.msg = new Message(temp);

                    Log.d("DebugMark", this.msg.getMssgtype());
                    Log.d("DebugMark", this.msg.getCommtype());
                    Log.d("DebugMark", this.msg.getValue());
                    this.publishProgress(PROC_SENDING_SIG);
                    if (this.msg.getMssgtype().equals(Mssg_Type.CONFI.toString()) && this.msg.getCommtype().equals(Comm_Type.SEND_SIGNAL_DATA.toString())) {
                        temp = "";
                        int ch;
                        ch = r.read();
                        Log.d("DebugMark", "confirmed");
                        do {
                            rl = rl + 1;
                            out.write(ch);
                            out.flush();
                            ch = r.read();
                        } while (ch != -1);
                        Log.d("DebugMark", "Finish Sending " + rl);
                        this.publishProgress(FIN_SENDING_SIG);
                    }
               // }

            } catch (IOException e) {
                e.printStackTrace();
                Log.d("DebugMark",e.toString());
            }
            Log.d("DebugMark","SendData");
        }
        private void SetPos(int pos) {
            float value;

            String temp;
            this.msg=new Message(Mssg_Type.COMM,Comm_Type.SETT_POSITION,Integer.toString(pos));
            temp=this.msg.getBuff();
            try {
             //   out.write("hello");
                out.flush();
                Log.d("DebugMark",temp+" test");

                out.write(temp);
                out.flush();

                out.newLine();
                out.flush();
                temp=in.readLine();
                while (temp.equals("")) {
                    temp = in.readLine();
                }
                Log.d("DebugMark",temp);

                this.msg=new Message(temp);

                Log.d("DebugMark",this.msg.getMssgtype());
                Log.d("DebugMark",this.msg.getCommtype());
                Log.d("DebugMark",this.msg.getValue());

                if(this.msg.getMssgtype().equals(Mssg_Type.CONFI.toString()) && this.msg.getCommtype().equals(Comm_Type.SETT_POSITION.toString())){
                    this.position=Float.parseFloat(this.msg.getValue());
                    this.publishProgress(this.FIN_POS);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("DebugMark","Sent Pos");
        }
        private void Reqt_Weight() {
            float value;

            String temp;
            this.msg=new Message(Mssg_Type.COMM,Comm_Type.REQT_WEIGHT,"0");
            temp=msg.getBuff();
            try {
                //   out.write("hello");
                out.flush();
                Log.d("DebugMark",temp+" test");

                out.write(temp);
                out.flush();

                out.newLine();
                this.publishProgress(this.PROC_REQT_WEIGHT);
                out.flush();
                temp=in.readLine();
                Log.d("DebugMark",temp);
                this.msg=new Message(temp);
                if(this.msg.getMssgtype().equals(Mssg_Type.CONFI.toString()) && this.msg.getCommtype().equals(Comm_Type.REQT_WEIGHT.toString())){
                    this.weight=Float.parseFloat(this.msg.getValue());
                    this.publishProgress(this.FIN_REQT_WEIGHT,Integer.parseInt(this.msg.getValue()));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
