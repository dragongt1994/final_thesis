def md5(bt):
    hash_md5 = hashlib.md5()
    hash_md5.update(bt)
    return hash_md5.hexdigest()

class messageType(Enum):
    CONN_REQT=2
    CONN_CONF=3
    SEND_FILE=4
    SEND_REQT=5
    SEND_CONF=6
    SEND_SUCC=7
    
    SETT_REQT=8
    SETT_CONF=9
    SETT_SUCC=10
    
class Order(Enum):
    SET_POSI=1
    SET_MODE=2
    SET_SELE=3
    
def message(msg_type,size=None, snum=None, username=None, fname=None, mname=None, lname=None, machine=None ):
    if msg_type==messageType.CONN_REQT:
       # msgtype=struct.unpack('i', val[1])
        dat=struct.pack('i', msg_type.value)+str.encode(username)+ str.encode(fname)+ str.encode(mname)+str.encode(lname)
    elif msg_type==messageType.CONN_CONF:
        dat=struct.pack('i', msg_type.value)+struct.pack('I', machine)
    elif msg_type==messageType.SEND_REQT:
        dat=struct.pack('I', msg_type.value)+struct.pack('I', size)+struct.pack('I', snum)
    elif msg_type==messageType.SEND_FILE:
        dat=struct.pack('I', msg_type.value)+struct.pack('I', size)+struct.pack('I', snum)
    elif msg_type==messageType.SEND_CONF:
        dat=struct.pack('I', msg_type.value)+ str.encode(machine)
    return dat
