package com.training.HealthcareApp.Misc;

import com.training.HealthcareApp.DTO.Patient;
import com.training.HealthcareApp.DTO.PatientDoctorMapping;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@Slf4j
public class RequestIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "REQ";
        String table= "mapping";
        log.info("generating request id");

        Connection connection = session.connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(id) as Id from " + table);
            if (rs.next()) {
                int id = rs.getInt(1) + 101;
                log.info(prefix+id);
                return prefix + id;
            }
        } catch (SQLException e) {
            throw new UnableToProcessException(e.getMessage());
        }
        return null;
    }
}
