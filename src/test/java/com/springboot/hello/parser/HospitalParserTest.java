package com.springboot.hello.parser;

import com.springboot.hello.dao.HospitalDao;
import com.springboot.hello.domain.Hospital;
import com.springboot.hello.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest // 중요. Extendswith, ConfigurationContext를 대체. SpringBoot가 스캔해서 등록한 Bean을 Test에서 쓸 수 있게 해준다.
class HospitalParserTest {

    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";
    String line2 = "";

    @Autowired // 주로 Test에서 쓰는 추세. 서비스 코드는 final과 constructor를 쓴다. 이렇게 해도 Spring이 DI를 한다.
    ReadLineContext<Hospital> hospitalReadLineContext;


    @Autowired           // @Component 어노테이션이 달린 클래스를 Bean으로 등록한다. 따라서 factory가 없어도 된다.
    HospitalDao hospitalDao; // factory도 없는데 왜 DI가 되냐? HospitalDao에 @Component Annotation. Springboot App -> ComponentScan으로 인해 가능

    @Autowired
    HospitalService hospitalService;

    @Test
    @DisplayName("Hospital이 insert가 잘 되고, select도 잘 되는지")
    void addAndGet() {
//        hospitalDao.deletAll(); // 지우니까
//        assertEquals(0, hospitalDao.getCount()); // 0일 것이고
//        HospitalParser hp = new HospitalParser();
//        Hospital hospital = hp.parse(line1);
//        hospitalDao.add(hospital); // 1줄 입력했으니
//        assertEquals(1, hospitalDao.getCount()); // 1이 있어야
//
//        Hospital selectedHospital = hospitalDao.findById(hospital.getId());
//        assertEquals(selectedHospital.getId(), hospital.getId());
//        assertEquals(selectedHospital.getOpenServiceName(), hospital.getOpenServiceName());
//        assertEquals(selectedHospital.getOpenLocalGovernmentCode(), hospital.getOpenLocalGovernmentCode());
//
//        assertEquals(selectedHospital.getManagementNumber(), hospital.getManagementNumber());
//        assertEquals(selectedHospital.getBusinessStatus(), hospital.getBusinessStatus());
//        assertEquals(selectedHospital.getBusinessStatusCode(), hospital.getBusinessStatusCode());
//
//        assertTrue(selectedHospital.getLicenseDate().isEqual(hospital.getLicenseDate()));
//
//        assertEquals(selectedHospital.getPhone(), hospital.getPhone());
//        assertEquals(selectedHospital.getFullAddress(), hospital.getFullAddress());
//        assertEquals(selectedHospital.getRoadNameAddress(), hospital.getRoadNameAddress());
//
//        assertEquals(selectedHospital.getHospitalName(), hospital.getHospitalName());
//        assertEquals(selectedHospital.getBusinessTypeName(), hospital.getBusinessTypeName());
//        assertEquals(selectedHospital.getHealthcareProviderCount(), hospital.getHealthcareProviderCount());
//
//        assertEquals(selectedHospital.getPatientRoomCount(), hospital.getPatientRoomCount());
//        assertEquals(selectedHospital.getTotalNumberOfBeds(), hospital.getTotalNumberOfBeds());
//        assertEquals(selectedHospital.getTotalAreaSize(), hospital.getTotalAreaSize());
    }

    @Test
    @DisplayName("10만건 이상 데이터가 파싱되는지")
    void name() throws IOException {
//        hospitalDao.deletAll();
//        String filename = "/Users/moomin/Downloads/nation_wide_hospital.csv";
//        int cnt = this.hospitalService.insertLargeVolumeHospitalData(filename);
//        assertTrue(cnt > 1000); // 데이터가 만개가 넘으면 잘된걸로
//        assertTrue(cnt > 10000);
//        System.out.printf("parsing된 데이터 개수 : %d %n", cnt);
    }

    @Test
    @DisplayName("csv 1줄을 Hospital로 잘 만드는지 테스트")
    void convertToHospital() {

        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId()); // columnNum : 0
        assertEquals("의원", hospital.getOpenServiceName()); // columnNum : 1
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode()); // columnNum : 3
        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber()); // columnNum : 4
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); //19990612  // columnNum : 5
        assertEquals(1, hospital.getBusinessStatus()); // columnNum : 7
        assertEquals(13, hospital.getBusinessStatusCode()); // columnNum : 9
        assertEquals("062-515-2875", hospital.getPhone()); // columnNum : 15
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress()); // columnNum : 18
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress()); // columnNum : 19
        assertEquals("효치과의원", hospital.getHospitalName()); // columnNum : 21
        assertEquals("치과의원", hospital.getBusinessTypeName()); // columnNum : 25
        assertEquals(1, hospital.getHealthcareProviderCount()); // columnNum : 30
        assertEquals(0, hospital.getPatientRoomCount()); // columnNum : 31
        assertEquals(0, hospital.getTotalNumberOfBeds()); // columnNum : 32
        assertEquals(52.29f, hospital.getTotalAreaSize()); // columnNum : 33

    }
}