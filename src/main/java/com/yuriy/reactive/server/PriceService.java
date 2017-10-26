package com.yuriy.reactive.server;

import com.yuriy.reactive.model.PriceInfo;

import java.util.HashMap;
import java.util.Map;

public class PriceService {

    private static Map<String, Double> companyPrice = new HashMap<>();

    static {
        companyPrice.put("APPL", 234.0);
        companyPrice.put("GOOG", 2323.0);
        companyPrice.put("MSFT", 123.0);
        companyPrice.put("INTC", 322.2);
    }

    public static PriceInfo getPrice(final String companyName) {
        return new PriceInfo(companyName, companyPrice.get(companyName));
    }
}
