package com.example.mark.myapplication;



public enum Comm_Type {
    REQT_WEIGHT("REQT_WEIGHT"),
    REQT_ACCELE("REQT_ACCELE"),
    REQT_M_ACCELE("REQT_M_ACCELE"),
    SET_TIME("SET_TIME"),
    CONN_REQT("CONN_REQT"),
    START_ACCELE_REC("START_ACCELE_REC"),
    STOP_ACCELE_REC("STOP_ACCELE_REC"),
    START_WEIGHT_REC("START_WEIGHT_REC"),
    TRANS("TRANS"),
    SEND_SIGNAL_DATA("SEND_SIGNAL_DATA"),
    SEND_SETTINGS("SEND_SETTINGS"),
    REQT_SETTINGS("REQT_SETTINGS"),
    SETT_POSITION("SETT_POSITION"),
    SETT_POSITION_DONE("SETT_POSITION_DONE"),
    REQT_POSITION("REQT_POSITION"),
    INIT_MACHINE("INIT_MACHINE"),
    RTRV_POSITION("RTRV_POSITION"),
    RTRV_WEIGHT("RTRV_WEIGHT"),
    REQT_SIGLIST("REQT_SIGLIST"),
    EXEC_SIGNAL("EXEC_SIGNAL");
    private final String text;
    Comm_Type(final String text) {
        this.text = text;
    }
@Override
    public String toString() {
        return text;
    }


}
