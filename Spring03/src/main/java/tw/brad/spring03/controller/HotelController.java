package tw.brad.spring03.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.spring03.service.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    
    @Autowired
    private HotelService hotelService;

    @GetMapping("")
    public ResponseEntity<Map<String,Object>> queryHotelsByPage(
            @RequestParam(defaultValue= "0") int page,
            @RequestParam(defaultValue = "10") int rpp) {
        
        // Page<Hotel> pageHotel = hotelService.getHotels(page, rpp);
        System.out.println(1);
        return ResponseEntity.ok(hotelService.getHotelsV2(page, rpp));
    }
}
