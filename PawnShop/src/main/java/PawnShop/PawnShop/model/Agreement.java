package PawnShop.PawnShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Agreement {
    @Id
    @Column(name = "pawn_item_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "interest_rate")
    private int interestRate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date dueDate;

    @Column(name = "is_paid")
    private boolean isPaid;

    @OneToOne
    @JoinColumn(name = "pawn_item_id")
    private PawnItem item;

    private Agreement(AgreementBuilder builder) {
        this.firstName = builder.getFirstName();
        this.lastName = builder.getLastName();
        this.email = builder.getEmail();
        this.amount = builder.getAmount();
        this.interestRate = builder.getInterestRate();
        this.startDate = builder.getStartDate();
        this.dueDate = builder.getDueDate();
        this.isPaid = builder.isPaid();
    }

    @Getter
    public static class AgreementBuilder {
        private BigDecimal amount;
        private String firstName;
        private String lastName;
        private String email;
        private int interestRate;
        private Date startDate;
        private Date dueDate;
        private boolean isPaid;

        public static AgreementBuilder builder() {
            return new AgreementBuilder();
        }

        public AgreementBuilder personWithName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AgreementBuilder bySurname(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AgreementBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public AgreementBuilder takesLoanForAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public AgreementBuilder atAnInterestRate(int interestRate) {
            this.interestRate = interestRate;
            this.amount = amount.add(amount.divide(new BigDecimal(interestRate)));
            return this;
        }

        public AgreementBuilder startsWith(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public AgreementBuilder expires(Date dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public AgreementBuilder isPaid(boolean isPaid) {
            this.isPaid = isPaid;
            return this;
        }

        public Agreement build() {
            return new Agreement(this);
        }
    }
}
