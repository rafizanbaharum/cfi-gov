package net.canang.cfi.biz.event;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * @author rafizan.baharum
 * @since 9/15/13
 */
@Component("emailListener")
public class EmailListener implements ApplicationListener<EmailEvent> {

    private static final Logger log = Logger.getLogger(EmailListener.class);

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(EmailEvent event) {
        log.debug("Email event triggered");
        try {
            send(event.getTo(), event.getFrom(), event.getSubject(), event.getBody());
        } catch (Exception e) {
            log.error("error occured", e);
        }
    }


    private void send(final String to, final String from, final String subject, final String body) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(to);
                message.setFrom(from);
                message.setSubject(subject);
                message.setText(body, false);
            }
        };
        doSend(preparator);
    }

    private void doSend(MimeMessagePreparator preparator) {
        try {
            mailSender.send(preparator);
            log.debug("email sent");
        } catch (MailException e) {
            log.error("error occured", e);
            throw e;
        }
    }

}
