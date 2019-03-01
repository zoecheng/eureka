package com.csy.spgcld.servicefeign.clients.fallback;

import com.csy.spgcld.servicefeign.clients.SchedualServiceHi;
import org.springframework.stereotype.Component;

/**
 *
 * @author caosy
 * create 2018-07-09
 **/
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry, you are fail,"+name;
    }
}