package com.fhouse.sample.model;

import com.fhouse.sample.model.audit.AuditBase;
import com.fhouse.sample.model.enums.Gender;
import com.fhouse.sample.util.AppConstants;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@Entity
@Table(name = "CUSTOMER")
@SQLDelete(sql = "update CUSTOMER SET DATA_STATUS = 'DELETED' WHERE id = ? AND version = ?")
@Where(clause = "DATA_STATUS <> 'DELETED'")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class Customer extends AuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CUSTOMER_ID")
    @SequenceGenerator(name = "SEQ_CUSTOMER_ID", sequenceName = "SEQ_CUSTOMER_ID", allocationSize = 1, initialValue = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CARD_NUMBER")
    private String number;

    @Column(name = "EXPIRY_MONTH")
    private String expiryMonth;

    @Column(name = "EXPIRY_YEAR")
    private String expiryYear;

    @Column(name = "START_MONTH")
    private String startMonth;

    @Column(name = "START_YEAR")
    private String startYear;

    @Column(name = "ISSUE_NUMBER")
    private String issueNumber;

    @Email
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "GENDER")
    private Gender gender;

    @AttributeOverrides({
            @AttributeOverride(name="title",column=@Column(name= AppConstants.BILLING + "title")),
            @AttributeOverride(name="firstName",column=@Column(name=AppConstants.BILLING +"firstName")),
            @AttributeOverride(name="lastName",column=@Column(name=AppConstants.BILLING +"lastName")),
            @AttributeOverride(name="company",column=@Column(name=AppConstants.BILLING + "company")),
            @AttributeOverride(name="address1",column=@Column(name= AppConstants.BILLING + "address1")),
            @AttributeOverride(name="address2",column=@Column(name=AppConstants.BILLING +"address2")),
            @AttributeOverride(name="city",column=@Column(name=AppConstants.BILLING +"city")),
            @AttributeOverride(name="postcode",column=@Column(name=AppConstants.BILLING + "postcode")),
            @AttributeOverride(name="state",column=@Column(name= AppConstants.BILLING + "state")),
            @AttributeOverride(name="country",column=@Column(name=AppConstants.BILLING +"country")),
            @AttributeOverride(name="city",column=@Column(name=AppConstants.BILLING +"city")),
            @AttributeOverride(name="phone",column=@Column(name=AppConstants.BILLING + "phone")),
            @AttributeOverride(name="fax",column=@Column(name=AppConstants.BILLING + "fax"))
    })
    @Embedded private CustomerInfo billing;

    @AttributeOverrides({
            @AttributeOverride(name="title",column=@Column(name= AppConstants.SHIPPING + "title")),
            @AttributeOverride(name="firstName",column=@Column(name=AppConstants.SHIPPING +"firstName")),
            @AttributeOverride(name="lastName",column=@Column(name=AppConstants.SHIPPING +"lastName")),
            @AttributeOverride(name="company",column=@Column(name=AppConstants.SHIPPING + "company")),
            @AttributeOverride(name="address1",column=@Column(name= AppConstants.SHIPPING + "address1")),
            @AttributeOverride(name="address2",column=@Column(name=AppConstants.SHIPPING +"address2")),
            @AttributeOverride(name="city",column=@Column(name=AppConstants.SHIPPING +"city")),
            @AttributeOverride(name="postcode",column=@Column(name=AppConstants.SHIPPING + "postcode")),
            @AttributeOverride(name="state",column=@Column(name= AppConstants.SHIPPING + "state")),
            @AttributeOverride(name="country",column=@Column(name=AppConstants.SHIPPING +"country")),
            @AttributeOverride(name="city",column=@Column(name=AppConstants.SHIPPING +"city")),
            @AttributeOverride(name="phone",column=@Column(name=AppConstants.SHIPPING + "phone")),
            @AttributeOverride(name="fax",column=@Column(name=AppConstants.SHIPPING + "fax"))
    })
    @Embedded private CustomerInfo shipping;

}
