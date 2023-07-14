package com.xloop.rentalform;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="rentalform")
@NoArgsConstructor
public class RentalForm {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private int carId;
    private String driverName;
    private String address;
    private String email;
    private String phoneNumber;
    private String licenseNumber;
    private LocalDateTime pickDate;
    private LocalDateTime returnDate;
    private long totalPrice;

    public RentalForm(int carId, String driverName, String address, String phoneNumber, String licenseNumber, LocalDateTime pickDate ,LocalDateTime returnDate, long totalPrice){
        this.carId = carId;
        this.driverName = driverName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.licenseNumber = licenseNumber;
        this.pickDate = pickDate;
        this.returnDate = returnDate;
        this.totalPrice = totalPrice;
    }


}
