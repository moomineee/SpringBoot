package com.springboot.hello.parser;

import com.springboot.hello.dao.HospitalDao;
import com.springboot.hello.domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest // 중요. Extendswith, ConfigurationContext를 대체. SpringBoot가 스캔해서 등록한 Bean을 Test에서 쓸 수 있게 해준다.
class HospitalParserTest {

    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";
    String line2 = "";

    @Autowired // 주로 Test에서 쓰는 추세. 서비스 코드는 final과 constructor를 쓴다. 이렇게 해도 Spring이 DI를 한다.
    ReadLineContext<Hospital> hospitalReadLineContext;

    @Autowired // factory도 없는데 왜 DI가 되냐? HospitalDao에 @Component Annotation. Springboot App -> ComponentScan으로 인해 가능
                // @Component 어노테이션이 달린 클래스를 Bean으로 등록한다. 따라서 factory가 없어도 된다.
    HospitalDao hospitalDao;

    @Test
    @DisplayName("Hospital이 insert가 잘 되는지")
    void add() {
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);
        hospitalDao.add(hospital);
    }

    @Test
    @DisplayName("10만건 이상 데이터가 파싱되는지")
    void name() throws IOException {
        String filename = "/Users/moomin/Downloads/nation_wide_hospital.csv";
        List<Hospital> hospitalList = hospitalReadLineContext.readByLine(filename);
        assertTrue(hospitalList.size() > 10000); // 데이터가 만개가 넘으면 잘된걸로
        assertTrue(hospitalList.size() > 100000);
        for (int i = 0; i < 10; i++) {
            System.out.println(hospitalList.get(i).getHospitalName());
        }
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