package br.com.felixgilioli.alunoservice.domain.event;
import java.io.Serializable;

public class SendEmailEvent implements Serializable {
    private String to;
    private String subject;
    private String body;

    public SendEmailEvent() {}

    public SendEmailEvent(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String getTo() { return to; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
}
