package com.springbootmustache.bbs3.domain.entity;

import com.springbootmustache.bbs3.domain.dto.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "nation_wide_hospitals")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hospital {

    //  entity만들 때 적절한 타입 매핑 필요함
    @Id
    private Integer id;

    @Column(name = "hospital_name")
    private String hospitalName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "road_name_address")
    private String roadNameAddress;
    @Column(name = "open_service_name")
    private String openServiceName;
    @Column(name = "open_local_government_code")
    private Integer openLocalGovernmentCode;
    @Column(name = "management_number")
    private String managementNumber;
    @Column(name = "license_date")
    private LocalDateTime licenseDate;
    @Column(name = "business_status", columnDefinition = "TINYINT")
    private Integer businessStatus;
    @Column(name = "business_status_code", columnDefinition = "TINYINT")
    private Integer businessStatusCode;
    @Column(name = "full_address")
    private String fullAddress;
    @Column(name = "business_type_name")
    private String businessTypeName;
    @Column(name = "healthcare_provider_count")
    private Integer healthcareProviderCount;
    @Column(name = "patient_room_count")
    private Integer patientRoomCount;
    @Column(name = "total_number_of_beds")
    private Integer totalNumberOfBeds;
    @Column(name = "total_area_size")
    private float totalAreaSize;


    // HospitalEntity를 HospitalResponse Dto로 만들어주는 부분
    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(),
                hospital.getRoadNameAddress(), hospital.getHospitalName(),
                hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(), hospital.getBusinessTypeName(),
                hospital.getTotalAreaSize());

    }
}
