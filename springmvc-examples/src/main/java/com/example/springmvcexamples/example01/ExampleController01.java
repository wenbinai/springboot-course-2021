package com.example.springmvcexamples.example01;

import com.example.springmvcexamples.example01.entity.Address;
import com.example.springmvcexamples.vo.ResultVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/example01/")
public class ExampleController01 {
    @GetMapping("index")
    public ResultVO getIndex() {
        return ResultVO.success(Map.of("name", "SUN"));
    }

    @GetMapping("addresses")
    public ResultVO getAddresses() {
        return ResultVO.success(Map.of("addresses", ADDRESSES));
    }

    @PostMapping("addresses")
    public ResultVO postAddress(@RequestBody Address address) {
        log.debug(address.getDetail());
        log.debug(address.getComment());
        return ResultVO.success(Map.of());
    }

    @PostMapping("addresses02")
    public ResultVO postAddress2(@RequestBody Address address) {
        log.debug(address.getDetail());
        log.debug(address.getComment());
        log.debug("{}", address.getUser().getId());
        return ResultVO.success(Map.of());
    }

    @GetMapping("addresses/{aid}")
    public ResultVO getAddress(@PathVariable("aid") int aid) {
        Address address = ADDRESSES.stream()
                .filter(a -> a.getId() == aid)
                .findFirst()
                .orElse(new Address());
        return ResultVO.success(Map.of("address", address));
    }

    @GetMapping("inject")
    public void inject(HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestHeader HttpHeaders headers) {
        log.debug(request.getRequestURI());
        log.debug(String.valueOf(headers));
    }

    @Autowired
    private ObjectMapper mapper;

    // @RequestParam用于接收传统的`?`传参
    @GetMapping("search")
    public ResultVO getJson(@RequestParam String address) throws JsonProcessingException {
        log.debug(address);
        Address a = mapper.readValue(address, Address.class);
        log.debug(a.getDetail());
        return ResultVO.success(Map.of("name", "BO"));
    }


    private final List<Address> ADDRESSES = create();

    private List<Address> create() {
        Address a1 = new Address(1, "956", "a", LocalDateTime.now());
        Address a2 = new Address(2, "925", "b", LocalDateTime.now());
        Address a3 = new Address(3, "121", null, null);
        return List.of(a1, a2, a3);
    }
}
