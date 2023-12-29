package com.code.truck.sendbookemailsb.reader;

import com.code.truck.sendbookemailsb.domain.Book;
import com.code.truck.sendbookemailsb.domain.User;
import com.code.truck.sendbookemailsb.domain.UserBookLoan;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

@Configuration
public class ReadUsersWithLoanCloseToReturnReaderConfig {

    private final static int numDaysToNotifyReturn = 6;

    @Bean
    public ItemReader<UserBookLoan> readUsersWithLoanCloseToReturnReader(@Qualifier("libraryDS") final DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<UserBookLoan>()
                .name("readUsersWithLoanCloseToReturnReader")
                .dataSource(dataSource)
                .sql("""
                        SELECT
                            user.id as user_id,
                            user.name as user_name,
                            user.email as user_email,
                            book.id as book_id,
                            book.name as book_name,
                            loan.loan_date
                        FROM tb_user_book_loan as loan
                        INNER JOIN tb_user as user ON loan.user_id = user.id
                        INNER JOIN tb_book as book ON loan.book_id = book.id
                        WHERE DATE_ADD(loan_date, INTERVAL 6 DAY) = DATE(NOW());
                        """)
                .rowMapper(this.rowMapper())
                .build();
    }

    private RowMapper<UserBookLoan> rowMapper() {
        return (rs, rowNum) -> {
            final User user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_email"));
            Book book = new Book();
            book.setId(rs.getInt("book_id"));
            book.setName(rs.getString("book_name"));
            return new UserBookLoan(user, book, rs.getDate("loan_date"));
        };
    }
}
