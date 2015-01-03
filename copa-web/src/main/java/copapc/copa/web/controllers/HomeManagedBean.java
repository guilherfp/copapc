package copapc.copa.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import copapc.model.time.Time;
import copapc.model.time.TimeRepository;

// @ManagedBean(name = "homeMB")
@Controller("homeMB")
public class HomeManagedBean {

  @Autowired
  private TimeRepository timeRepository;

  private List<Time> times;

  @Transactional
  public List<Time> getTimes() {
    if (times == null) {
      times = timeRepository.times();
    }
    return times;
  }

}