package net.xdclass.xdvideo.exceiption;

public class JwtException extends RuntimeException{
    private String msg;

    public JwtException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
