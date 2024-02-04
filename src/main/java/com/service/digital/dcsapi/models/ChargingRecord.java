package com.service.digital.dcsapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "cdr")
public class ChargingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Invalid Vehicle: Vehicle is BLANK")
    private String vehicle;
    @NotNull(message = "Invalid Start Date: Start Date is NULL")
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @NotNull(message = "Invalid End Date: End Date is NULL")
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @NotNull(message = "Invalid Total Cost: Total Cost is NULL")
    @Column(name = "total_cost")
    private double totalCost;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Cpo cpo;

    @NotNull(message = "Invalid Company ID: Company ID is NULL")
    @Column(name = "company_id")
    private Long companyId;

}
