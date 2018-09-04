
import socket
import sys
import threading
import time
from messageSystem import Message2
import os

COMM="COMM"
CONFI="CONFI"
    
    
REQT_WEIGHT="REQT_WEIGHT"
REQT_ACCELE="REQT_ACCELE"
REQT_M_ACCELE="REQT_M_ACCELE"
SET_TIME="SET_TIME"
CONN_REQT="CONN_REQT"
START_ACCELE_REC="START_ACCELE_REC"
STOP_ACCELE_REC="STOP_ACCELE_REC"
START_WEIGHT_REC="START_WEIGHT_REC"
TRANS="TRANS"
SEND_SIGNAL_DATA="SEND_SIGNAL_DATA"
SEND_SETTINGS="SEND_SETTINGS"
REQT_SETTINGS="REQT_SETTINGS"
SETT_POSITION="SETT_POSITION"
SETT_POSITION_DONE="SETT_POSITION_DONE"
REQT_POSITION="REQT_POSITION"
INIT_MACHINE="INIT_MACHINE"
RTRV_POSITION="RTRV_POSITION"
RTRV_WEIGHT="RTRV_WEIGHT"

class  ServerManager(threading.Thread):

        
    def __init__(self, HOST, PORT):
        threading.Thread.__init__(self)
        self.sck = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        try:
            self.sck.bind((HOST, PORT))
            print("start server");
        except socket.error as msg:
            print(msg)
        self.sck.listen(10)
        
    def run(self):
        while True:
            conn, addr=self.sck.accept()
            print(conn)
            print(addr)
            temp=ClientManageThread(conn, addr)
            temp.start()
        
class ClientManageThread(threading.Thread):
    def __init__(self, sck, addr):
        print("accepted")
        threading.Thread.__init__(self)
        self.sck=sck;
        self.addr=addr;
        
        
    def run(self):
        try:
            while True:
                b=self.readlines(self.sck)
                msg=Message2(buff=b)
    #            c=msg.comm_type
                print(msg.mssg_type)
                if msg.mssg_type==COMM:
                    print(COMM)
                    if msg.comm_type==SETT_POSITION:
                        print(SETT_POSITION)
                        msg=Message2(CONFI, SETT_POSITION, msg.value)
                        b =msg.buff
                        self.sck.send(b.encode());
                        self.sck.send('\n'.encode())
                    elif msg.comm_type==RTRV_POSITION:
                        print(REQT_WEIGHT)
                        msg=Message2(CONFI, RTRV_POSITION, "30")
                        b =msg.buff
                        self.sck.send(b.encode());
                        self.sck.send('\n'.encode())
                    elif msg.comm_type==REQT_WEIGHT:
                        print(REQT_WEIGHT)
                        msg=Message2(CONFI, REQT_WEIGHT, "30")
                        b =msg.buff
                        self.sck.send(b.encode());
                        self.sck.send('\n'.encode())
                    elif msg.comm_type==RTRV_WEIGHT:
                        print(REQT_WEIGHT)
                        msg=Message2(CONFI, RTRV_WEIGHT, "30")
                        b =msg.buff
                        self.sck.send(b.encode());
                        self.sck.send('\n'.encode())
                    elif msg.comm_type==REQT_ACCELE:
                        print("request accel data")
                        l_f=os.path.getsize('accel_data.csv')
                        print("l_f "+str(l_f))
                        f=open('accel_data.csv', 'rb')
                        msg=Message2(CONFI,REQT_ACCELE, l_f)
                        b =msg.buff
                        self.sck.send(b.encode());
                        self.sck.send('\n'.encode())
                        
                        ctr=0
                        print(l_f)
                        while ctr<l_f:
                            res=f.read()

                            self.sck.send(res);
                            #res=self.sck.recv(1024)
                            ctr=ctr+1
                        
                        print("ctr= "+str(ctr))
                        print("reqt= "+REQT_ACCELE)
                        
                        f.close()
                        
                    elif msg.comm_type==SEND_SIGNAL_DATA:
                        print("request signal data")
                        msg=Message2(CONFI,SEND_SIGNAL_DATA, msg.value)
                        b =msg.buff
                        self.sck.send(b.encode());
                        self.sck.send('\n'.encode())
                        
                        f=open("data.dat", "wb")
                        l_f=int(msg.value)
                        ctr=0
                        print(l_f)
                        while ctr<l_f:
                            res=self.sck.recv(1024)

                            f.write(res)
                            ctr=ctr+len(res)

                        print("send= "+SEND_SIGNAL_DATA)
                        
                        f.close()
                    
                elif msg.mssg_type==CONFI:
                    print(CONFI)
        except Exception:
            print("error")
            
            
                
    def readlines(self,sock):
        chars = b''
        while True:
            a = sock.recv(1)
            chars=chars+a; 
            print(a)   
            if len(chars)!=0:
                if chars[len(chars)-1] == 10:
                    print("here4")
                    return chars.decode("utf-8")
                    #return "".join(map(chr, chars))
