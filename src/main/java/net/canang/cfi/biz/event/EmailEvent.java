package net.canang.cfi.biz.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author rafizan.baharum
 * @since 9/15/13
 */
public class EmailEvent extends ApplicationEvent {

    private String to;
    private String from;
    private String subject;
    private String body;


    public EmailEvent(String to, String from, String subject, String body) {
        super(to);
        setTo(to);
        setFrom(from);
        setSubject(subject);
        setBody(body);
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

