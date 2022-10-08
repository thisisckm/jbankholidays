package com.ck.bankholidays.data;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HolidayRepository  extends CrudRepository<Holiday, Long> {

    Holiday findById(long id);

    Holiday findByDate(Date  date);

    @Query("select h from Holiday h where h.date > :date")
    List<Holiday> findAllUpComingHoliday(
      @Param("date") Date date);
    
}
