package com.code.truck.sendbookemailsb.processor;

import com.code.truck.sendbookemailsb.domain.UserBookLoan;
import com.code.truck.sendbookemailsb.util.GenerateBookReturnDate;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessLoanNotificationEmailProcessorConfig {

    @Bean
    public ItemProcessor<UserBookLoan, Mail> processLoanNotificationEmailProcessor() {
        return new ItemProcessor<>() {
            @Override
            public Mail process(UserBookLoan item) throws Exception {
                final Email from = new Email("mario_chan15@hotmail.com", "Spring batch app");
                final Email to = new Email(item.getUser().getEmail());
                final Content content = new Content("text/plain", generateEmailText(item));
                final Mail mail = new Mail(from, "Notification renouvellement livre", to, content);
                Thread.sleep(1000);
                return mail;
            }

            private String generateEmailText(UserBookLoan loan) {
                StringBuilder writer = new StringBuilder();
                writer.append(String.format("Cher(e) %s, numéro d'inscription %d\n", loan.getUser().getName(),
                        loan.getUser().getId()));
                writer.append(
                        String.format("Nous vous informons que la date limite de retour du livre %s est demain (%s) \n",
                                loan.getBook().getName(), GenerateBookReturnDate.getDate(loan.getLoan_date())));
                writer.append("Nous vous demandons de renouveler le livre ou de le retourner dès que possible.\n");
                writer.append("La Bibliothèque Municipale est ouverte du lundi au vendredi, de 9h à 17h.\n\n");
                writer.append("Cordialement,\n");
                writer.append("Service de prêt et de retour\n");
                writer.append("BIBLIOTHÈQUE MUNICIPALE");
                return writer.toString();
            }
        };
    }
}
