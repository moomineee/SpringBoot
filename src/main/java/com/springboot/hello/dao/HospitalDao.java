package com.springboot.hello.dao;

import com.springboot.hello.domain.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class HospitalDao {

    private final JdbcTemplate jdbcTemplate;  // Autowired 대신 private final = DI

    public HospitalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // List<Hospital> - 11만건이 들어있음. Hospital
    public void add(Hospital hospital) { // 따라서 Hospital이 들어간다
        String sql = "INSERT INTO `likelion-db`.`nation_wide_hospitals` (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_count`, `patient_room_count`, `total_number_of_beds`, `total_area_size`) " +
                     "VALUES ('1', '의원', '3620000', " +
                     "'PHMA119993620020041100004', '19990612', '1', " +
                     "'13', '062-515-2875', '광주광역시 북구 풍향동 565번지 4호 3층', " +
                     "'광주광역시 북구 동문대로 24, 3층 (풍향동)', '효치과의원', '치과의원', " +
                     "'1', '0', '0', '52.29');";
        this.jdbcTemplate.update(sql,
                hospital.getId(), hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(),
                hospital.getManagementNumber(), hospital.getLicenseDate(), hospital.getBusinessStatus(),
                hospital.getBusinessStatusCode(), hospital.getPhone(), hospital.getFullAddress(),
                hospital.getRoadNameAddress(), hospital.getHospitalName(), hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCount(), hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(),
                hospital.getTotalAreaSize()
                ); // 업데이트 하려면 위의 sql이 필요
    }
}
