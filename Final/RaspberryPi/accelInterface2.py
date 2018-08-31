from PyQt4.QtGui import *

from PyQt4.QtGui import *
from PyQt4.QtCore import *
from PyQt4 import QtGui
import os
import sys
import socket
import struct

from math import ceil

from SocketManager import *

class Screen(QWidget):
    def __init__(self):
        super(Screen, self).__init__()
        
        self.initUI()
    
    def askConnection(self):
        print("try")
        self.sck.connect((self.ip_addr.text(),int(self.port.text())))
        #self.sck.setblocking(0)
        print("connected")
        #self.sck.send(str.encode("hello you are connected"))
    def startServer(self):
        print("serve")
        self.serv=ServerManager("", 9000);
        self.serv.start()
    def sendMessage(self):
#        mssg=int(self.message_type.text())

#        comm=int(self.comm_type.text())
#        val=float(self.value_text.text())
#        #temp=struct.pack("i", mssg)+struct.pack("i", comm)+struct.pack("f", val)
#        p=Message(mssg, comm, val)
#        print(len(p.buff))
#        print(p.buff)
        if self.cbox.value()==1:
            m=Message(COMM, REQT_ACCELE, 10)
            self.sck.send(m.buff)
            r=self.sck.recv(16)
            m=Message(buf=r)
            c=open("rec.dat", "wb")
            if m.mssg_type==CONFI and m.comm_type==REQT_ACCELE:
                print("recieving")
                s3=int(m.value)
                s=int(ceil(m.value/4096))
                temp=b''
                print(s3)

                while len(temp)<s3:
                    r=self.sck.recv(4096)
                    temp=temp+r
                    print(len(temp))
                    #r=r+[self.sck.recv(4096)]
                c.write(temp)
                print("done")
                c.close()
        elif self.cbox.value()==2:
            print(self.cbox.value())
            m=Message(COMM, REQT_WEIGHT, 10)
            self.sck.send(m.buff)
        elif self.cbox.value()==3:
            m=Message(COMM, START_ACCELE_REC, 10)
            self.sck.send(m.buff)
        elif self.cbox.value()==4:
            m=Message(COMM, TRANS_ACCEL, 10)
            self.sck.send(m.buff)
            r=self.sck.recv(16)
            m=Message(buf=r)
            c=open("rec.dat", "wb")
            if m.mssg_type==CONFI and m.comm_type==REQT_ACCELE:
                print("recieving")
                s3=int(m.value)
                s=int(ceil(m.value/4096))
                temp=b''
                print(s3)

                while len(temp)<s3:
                    r=self.sck.recv(4096)
                    temp=temp+r''
                    print(len(temp))
                    #r=r+[self.sck.recv(4096)]
                c.write(temp)
                print("done")
                c.close()
        elif self.cbox.value()==5:
            m=Message(COMM, STOP_ACCELE_REC, 10)
            self.sck.send(m.buff)
            r=self.sck.recv(16)
            m=Message(buf=r)
            if m.mssg_type==CONFI and m.comm_type==STOP_ACCELE_REC:
                print("stopped")
        elif self.cbox.value()==6:
            m=Message(COMM, FIN_WEIGHT_INIT, 10)
            self.sck.send(m.buff)
            r=self.sck.recv(16)
            m=Message(buf=r)
            if m.mssg_type==CONFI and m.comm_type==FIN_WEIGHT_INIT:
                print("weight handled")
        elif self.cbox.value()==7:
            m=Message(COMM, REQT_WEIGHT, 10)
            self.sck.send(m.buff)
            r=self.sck.recv(16)
            m=Message(buf=r)
            print(str(m.value))
    def initUI(self):
        self.vlayout=QtGui.QVBoxLayout()
        
        self.label1=QtGui.QLabel("Ip Address")
        self.ip_addr=QtGui.QLineEdit()
        self.ip_lay=QtGui.QHBoxLayout()
        
        self.ip_lay.addWidget(self.label1)
        self.ip_lay.addWidget(self.ip_addr)
        
        
        self.label2=QtGui.QLabel("Port")
        self.port=QtGui.QLineEdit()
        self.port_lay=QtGui.QHBoxLayout()
        
        self.port_lay.addWidget(self.label2)
        self.port_lay.addWidget(self.port)
        
        self.label3=QtGui.QLabel("message type")
        self.message_type=QtGui.QLineEdit()
        self.message_lay=QtGui.QHBoxLayout()
        self.message_lay.addWidget(self.label3)
        self.message_lay.addWidget(self.message_type)
        
        self.label4=QtGui.QLabel("comm type")
        self.comm_type=QtGui.QLineEdit()
        self.comm_lay=QtGui.QHBoxLayout()
        self.comm_lay.addWidget(self.label4)
        self.comm_lay.addWidget(self.comm_type)
        
        self.label5=QtGui.QLabel("Value")
        self.value_text=QtGui.QLineEdit()
        self.value_lay=QtGui.QHBoxLayout()
        self.value_lay.addWidget(self.label5)
        self.value_lay.addWidget(self.value_text)
        
        self.conn_button=QtGui.QPushButton("Connect")
        self.conn_button.clicked.connect(self.askConnection)
        
        self.connServer_button=QtGui.QPushButton("Start Server")
        self.connServer_button.clicked.connect(self.startServer)
        self.cbox=QtGui.QSpinBox()
        self.cbox.setMinimum(1)
        self.cbox.setMaximum(10)
        
        self.button1=QtGui.QPushButton("Send Message")
        self.button1.clicked.connect(self.sendMessage)
        
        
        
        self.vlayout.addLayout(self.ip_lay)
        self.vlayout.addLayout(self.port_lay)
        self.vlayout.addWidget(self.conn_button)
        self.vlayout.addWidget(self.connServer_button)
        self.vlayout.addLayout(self.message_lay)
        self.vlayout.addLayout(self.comm_lay)
        self.vlayout.addLayout(self.value_lay)
        
        
        
        self.vlayout.addWidget(self.cbox)
        self.vlayout.addWidget(self.button1)
        
        
        
        
        
        self.setLayout(self.vlayout)
        self.show()
    
    
        

def main():
    app = QApplication(sys.argv)
    temp=Screen()
    sys.exit(app.exec_())

if __name__ == '__main__':
   main()
